package test.hotciv.standard;

import org.junit.Before;
import org.junit.Test;
import src.hotciv.framework.Game;
import src.hotciv.framework.GameObserver;
import src.hotciv.framework.Player;
import src.hotciv.framework.Position;
import src.hotciv.standard.AbstractCiv;
import src.hotciv.standard.GameImpl;
import src.hotciv.standard.factories.civ.AlphaCivFactory;

/**
 * Created by Lasse Letager Hansen on 16-12-2016.
 */
public class TestGameObserver {
    private Game game;

    private Position redPosition, bluePosition;

    @Before
    public void setUp() {
        game = new GameImpl(new AbstractCiv(new AlphaCivFactory()));
        redPosition = (new Position(1,1));
        bluePosition = (new Position(4,1));
    }

    @Test
    public void gameHasObserver(){

    }


}
