package src.hotciv.view.tools;

import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.framework.Tool;
import minidraw.standard.NullTool;
import src.hotciv.framework.Game;
import src.hotciv.view.GfxConstants;
import src.hotciv.view.TextFigure;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by Lasse Letager Hansen on 15-12-2016.
 */
public class EndOfTurnTool extends NullTool {
    private Game game;
    private DrawingEditor editor;

    public EndOfTurnTool(DrawingEditor editor, Game game){
        this.game = game;
        this.editor = editor;
    }

    @Override
    public void mouseDown(MouseEvent mouseEvent, int i, int i1) {
        Figure figure = editor.drawing().findFigure(GfxConstants.TURN_SHIELD_X, GfxConstants.TURN_SHIELD_Y);
        if(figure.displayBox().contains(i, i1)){
            game.endOfTurn();

            TextFigure tf = (TextFigure) editor.drawing().findFigure(GfxConstants.AGE_TEXT_X, GfxConstants.AGE_TEXT_Y);
            if(tf != null) {
                int age = game.getAge();
                tf.setText(Math.abs(age) + (age < 0 ? "BC" : "AC"));
            }
        }

    }
}
