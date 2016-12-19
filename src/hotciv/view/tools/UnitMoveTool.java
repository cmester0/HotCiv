package src.hotciv.view.tools;

import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.framework.Tool;
import minidraw.standard.NullTool;
import src.hotciv.framework.City;
import src.hotciv.framework.Game;
import src.hotciv.framework.Position;
import src.hotciv.framework.Unit;
import src.hotciv.view.GfxConstants;
import src.hotciv.view.TextFigure;
import src.hotciv.view.UnitFigure;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by Lasse Letager Hansen on 15-12-2016.
 */
public class UnitMoveTool extends NullTool {
    private DrawingEditor editor;
    private Position from, to;
    private Game game;
    private Figure figure;
    private int figurePosX, figurePosY;
    private int figureStartX, figureStartY;

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
        from = GfxConstants.getPositionFromXY(i, i1);
        figure = editor.drawing().findFigure(i, i1);
        if(!(figure instanceof UnitFigure)){
            figure = null;
        }

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

        to = GfxConstants.getPositionFromXY(i, i1);

        if(!game.moveUnit(from, to)){
            figure.moveBy(-figurePosX, -figurePosY); // move to 0,0
            figure.moveBy(figureStartX, figureStartY); // move to startx, starty
        }

        Unit u = game.getUnitAt(to);
        if(u != null){
            TextFigure figure = (TextFigure) editor.drawing().findFigure(GfxConstants.UNIT_COUNT_X, GfxConstants.UNIT_COUNT_Y);
            if(figure != null) {
                figure.setText(""+u.getMoveCount());
            }
        }

        reset();
    }
}
