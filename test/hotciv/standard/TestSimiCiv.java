package test.hotciv.standard;

import org.junit.Before;

import src.hotciv.framework.*;
import src.hotciv.standard.*;
import src.hotciv.standard.factories.civ.SemiCivFactory;

/**
 * Created by Lasse Letager Hansen on 05-12-2016.
 */
public class TestSimiCiv {
    private Game game;
    private Position redPosition, bluePosition;

    @Before
    public void setUp() {
        game = new GameImpl(new AbstractCiv(new SemiCivFactory()));
        redPosition = (new Position(1,1));
        bluePosition = (new Position(4,1));
    }

    private void endRound(int n){
        for(int i=0; i<2*n; i++)
            game.endOfTurn();
    }


}
