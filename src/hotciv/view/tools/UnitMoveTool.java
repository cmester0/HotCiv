package src.hotciv.view.tools;

import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.framework.Tool;
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
public class UnitMoveTool implements Tool {
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

        Unit u = game.getUnitAt(from);
        TextFigure textFigure = (TextFigure) editor.drawing().findFigure(GfxConstants.UNIT_COUNT_X, GfxConstants.UNIT_COUNT_Y);
        if(textFigure != null) {
            if(u != null) {
                textFigure.setText("" + u.getMoveCount());
            } else {
                textFigure.setText(" ");
            }
        }

        City city = game.getCityAt(from);

        textFigure = (TextFigure) editor.drawing().findFigure(GfxConstants.WORKFORCEFOCUS_X, GfxConstants.WORKFORCEFOCUS_Y);
        if(textFigure != null){
            if(city != null) {
                textFigure.setText("" + city.getWorkforceFocus());
            } else {
                textFigure.setText(" ");
            }
        }

        textFigure = (TextFigure) editor.drawing().findFigure(GfxConstants.CITY_PRODUCTION_X, GfxConstants.CITY_PRODUCTION_Y);
        if(textFigure != null){
            if(city != null) {
                textFigure.setText("" + game.getProductionAmountOfCity(city));
            } else {
                textFigure.setText(" ");
            }
        }
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

    @Override
    public void mouseMove(MouseEvent mouseEvent, int i, int i1) { }

    @Override
    public void keyDown(KeyEvent keyEvent, int i) { }
}
