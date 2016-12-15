package src.hotciv.view;

import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.framework.Tool;
import src.hotciv.framework.Game;
import src.hotciv.framework.Position;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by Lasse Letager Hansen on 15-12-2016.
 */
public class UnitMoveTool implements Tool {
    private DrawingEditor editor;
    private Position from, to;
    private Game game;
    private Figure figure;
    private double cx, cy;
    private double figurePosX, figurePosY;
    private double figureStartPosX, figureStartPosY;

    private static final int moveWidth = 30;
    private static final int screenWidth = 455;
    private static final int borderWidth = 19;

    public UnitMoveTool(DrawingEditor editor, Game game){
        this.editor = editor;
        this.game = game;

        reset();
    }

    private void reset(){
        from = null;
        to = null;
        figure = null;
        cx = 0;
        cy = 0;
    }

    @Override
    public void mouseDown(MouseEvent mouseEvent, int i, int i1) {
        int x = (int) ((i1 - borderWidth) * moveWidth / ((double)2 * screenWidth));
        int y = (int) ((i - borderWidth) * moveWidth / ((double)2 * screenWidth));

        from = new Position(x, y);
        figure = editor.drawing().findFigure(i, i1);
        figurePosX = i;
        figurePosY = i1;

        figureStartPosX = i;
        figureStartPosY = i1;
    }

    @Override
    public void mouseDrag(MouseEvent mouseEvent, int i, int i1) {
        if(figure == null) return;

        double dx = i - figurePosX;
        double dy = i1 - figurePosY;

        cx += dx;
        cy += dy;

        int mx = 0;
        int my = 0;

        while(cx > moveWidth){
            mx+=moveWidth;
            cx-=moveWidth;
        }
        while(cx < -moveWidth){
            mx-=moveWidth;
            cx+=moveWidth;
        }

        while(cy > moveWidth){
            my+=moveWidth;
            cy-=moveWidth;
        }
        while(cy < -moveWidth){
            my-=moveWidth;
            cy+=moveWidth;
        }

        figure.moveBy(mx, my);

        figurePosX = i;
        figurePosY = i1;
    }

    @Override
    public void mouseUp(MouseEvent mouseEvent, int i, int i1) {
        if(figure == null) return;

        double xcoord = figurePosX - cx;
        double ycoord = figurePosY - cy;

        int x = (int) ((ycoord - borderWidth) * moveWidth / ((double)2 * screenWidth));
        int y = (int) ((xcoord - borderWidth) * moveWidth / ((double)2 * screenWidth));
        to = new Position(x, y);

        game.moveUnit(from, to);
        reset();
    }

    @Override
    public void mouseMove(MouseEvent mouseEvent, int i, int i1) { }

    @Override
    public void keyDown(KeyEvent keyEvent, int i) { }
}
