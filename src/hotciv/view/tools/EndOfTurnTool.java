package src.hotciv.view.tools;

import minidraw.framework.Tool;
import src.hotciv.framework.Game;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by Lasse Letager Hansen on 15-12-2016.
 */
public class EndOfTurnTool implements Tool {
    Game game;
    public EndOfTurnTool(Game game){
        this.game = game;
    }

    @Override
    public void mouseDown(MouseEvent mouseEvent, int i, int i1) {
        if(i > 557 && i1 > 59 && i < 594 && i1 < 109){
            game.endOfTurn();
        }
    }

    @Override
    public void mouseDrag(MouseEvent mouseEvent, int i, int i1) {

    }

    @Override
    public void mouseUp(MouseEvent mouseEvent, int i, int i1) {

    }

    @Override
    public void mouseMove(MouseEvent mouseEvent, int i, int i1) {

    }

    @Override
    public void keyDown(KeyEvent keyEvent, int i) {

    }
}
