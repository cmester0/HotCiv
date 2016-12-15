package src.hotciv.view.tools;

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
    private int figurePosX, figurePosY;
    private int figureStartX, figureStartY;

    private static final int moveWidth = 30;
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
    }

    @Override
    public void mouseDown(MouseEvent mouseEvent, int i, int i1) {
        int x = (i1 - borderWidth) / moveWidth;
        int y = (i - borderWidth) / moveWidth;

        from = new Position(x, y);
        figure = editor.drawing().findFigure(i, i1);
        figureStartX = figurePosX = i;
        figureStartY = figurePosY = i1;
    }

    @Override
    public void mouseDrag(MouseEvent mouseEvent, int i, int i1) {
        if(figure == null) return;

        double dx = i - figurePosX;
        double dy = i1 - figurePosY;

        figure.moveBy((int)dx, (int)dy);

        figurePosX = i;
        figurePosY = i1;
    }

    @Override
    public void mouseUp(MouseEvent mouseEvent, int i, int i1) {
        if(figure == null) return;

        int x = (i1 - borderWidth) / moveWidth;
        int y = (i - borderWidth) / moveWidth;

        to = new Position(x, y);

        if(!game.moveUnit(from, to)){
            figure.moveBy(-figurePosX, -figurePosY); // move to 0,0
            figure.moveBy(figureStartX, figureStartY); // move to startx, starty
        }
        reset();
    }

    @Override
    public void mouseMove(MouseEvent mouseEvent, int i, int i1) { }

    @Override
    public void keyDown(KeyEvent keyEvent, int i) { }
}
