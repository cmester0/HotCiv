package src.hotciv.view.tools;

import minidraw.framework.DrawingEditor;
import minidraw.framework.Tool;
import src.hotciv.framework.Game;
import src.hotciv.view.TextFigure;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by Lasse Letager Hansen on 16-12-2016.
 */
public class ToolSelector implements Tool {
    private DrawingEditor editor;
    private Game game;
    private Tool currTool;

    public ToolSelector(DrawingEditor editor, Game game) {
        this.editor = editor;
        this.game = game;
        this.currTool = new UnitMoveTool(editor, game);
    }

    @Override
    public void mouseDown(MouseEvent mouseEvent, int i, int i1) {
        currTool.mouseDown(mouseEvent, i, i1);
    }

    @Override
    public void mouseDrag(MouseEvent mouseEvent, int i, int i1) {
        currTool.mouseDrag(mouseEvent, i, i1);
    }

    @Override
    public void mouseUp(MouseEvent mouseEvent, int i, int i1) {
        currTool.mouseUp(mouseEvent, i, i1);
    }

    @Override
    public void mouseMove(MouseEvent mouseEvent, int i, int i1) {
        double x = mouseEvent.getLocationOnScreen().getX();
        if(x > 605 && !(currTool instanceof EndOfTurnTool)){
            System.out.println("Changed to end of turn tool");
            currTool = new EndOfTurnTool(editor, game);
        }
        if(x <= 605 && !(currTool instanceof UnitMoveTool)){
            System.out.println("Changed to move unit tool");
            currTool = new UnitMoveTool(editor, game);
        }
    }

    @Override
    public void keyDown(KeyEvent keyEvent, int i) {
        currTool.keyDown(keyEvent, i);
    }
}
