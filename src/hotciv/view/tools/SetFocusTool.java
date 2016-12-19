package src.hotciv.view.tools;

import javafx.geometry.Pos;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Tool;
import minidraw.standard.NullTool;
import src.hotciv.framework.City;
import src.hotciv.framework.Game;
import src.hotciv.framework.Position;
import src.hotciv.framework.Unit;
import src.hotciv.view.GfxConstants;
import src.hotciv.view.TextFigure;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by Lasse Letager Hansen on 19-12-2016.
 */
public class SetFocusTool extends NullTool {
    private Game game;
    private DrawingEditor editor;
    private Position from;

    public SetFocusTool(DrawingEditor editor, Game game){
        this.game = game;
        this.editor = editor;
    }

    @Override
    public void mouseDown(MouseEvent mouseEvent, int i, int i1) {
        from = GfxConstants.getPositionFromXY(i,i1);

    }

    @Override
    public void mouseUp(MouseEvent mouseEvent, int i, int i1) {
        Position to = GfxConstants.getPositionFromXY(i, i1);
        if(from.equals(to)){
            game.setTileFocus(from);
        }

        from = null;
    }
}
