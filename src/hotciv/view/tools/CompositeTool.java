package src.hotciv.view.tools;

import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;
import src.hotciv.framework.Game;

import java.awt.event.MouseEvent;

/**
 * Created by Lasse Letager Hansen on 16-12-2016.
 */
public class CompositeTool extends NullTool {
    private DrawingEditor editor;
    private Game game;

    private ActionTool actionTool;
    private EndOfTurnTool endOfTurnTool;
    private SetFocusTool setFocusTool;
    private UnitMoveTool unitMoveTool;

    public CompositeTool(DrawingEditor editor, Game game) {
        this.editor = editor;
        this.game = game;

        this.actionTool = new ActionTool(game);
        this.endOfTurnTool = new EndOfTurnTool(editor, game);
        this.setFocusTool = new SetFocusTool(editor, game);
        this.unitMoveTool = new UnitMoveTool(editor, game);
    }

    @Override
    public void mouseDown(MouseEvent mouseEvent, int i, int i1) {
        endOfTurnTool.mouseDown(mouseEvent, i, i1);

        if(mouseEvent.isShiftDown()){
            actionTool.mouseDown(mouseEvent, i, i1);
        }
        else{
            setFocusTool.mouseDown(mouseEvent, i, i1);
            unitMoveTool.mouseDown(mouseEvent, i, i1);
        }
    }

    @Override
    public void mouseDrag(MouseEvent mouseEvent, int i, int i1) {
        unitMoveTool.mouseDrag(mouseEvent, i, i1);
    }

    @Override
    public void mouseUp(MouseEvent mouseEvent, int i, int i1) {
        endOfTurnTool.mouseUp(mouseEvent, i, i1);

        if(mouseEvent.isShiftDown()){
            actionTool.mouseUp(mouseEvent, i, i1);
        }
        else{
            setFocusTool.mouseUp(mouseEvent, i, i1);
            unitMoveTool.mouseUp(mouseEvent, i, i1);
        }
    }
}
