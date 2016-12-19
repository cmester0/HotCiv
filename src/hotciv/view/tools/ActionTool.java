package src.hotciv.view.tools;

import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;
import src.hotciv.framework.Game;
import src.hotciv.framework.Position;
import src.hotciv.framework.Unit;
import src.hotciv.view.GfxConstants;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by Lasse Letager Hansen on 19-12-2016.
 */
public class ActionTool extends NullTool {
    private Game game;

    public ActionTool(Game game){
        this.game = game;
    }

    @Override
    public void mouseDown(MouseEvent mouseEvent, int i, int i1){
        if(mouseEvent.isShiftDown()){
            Position pos = GfxConstants.getPositionFromXY(i, i1);
            if(game.getUnitAt(pos) != null) {
                game.performUnitActionAt(pos);
            }
        }
    }
}
