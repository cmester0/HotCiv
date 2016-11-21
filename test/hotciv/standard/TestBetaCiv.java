package test.hotciv.standard;

import org.junit.Before;
import org.junit.Test;
import src.hotciv.framework.Game;
import src.hotciv.framework.Position;
import src.hotciv.standard.GameImpl;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by Lasse Letager Hansen on 21-11-2016.
 */
public class TestBetaCiv {

    private Game game;

    private Position redPosition, bluePosition;

    /** Fixture for alphaciv testing. */
    @Before
    public void setUp() {
        game = new GameImpl(true);
        redPosition = (new Position(1,1));
        bluePosition = (new Position(4,1));
    }

    private void endRound(int n){
        for(int i=0; i<2*n; i++)
            game.endOfTurn();
    }


    @Test
    public void after40RoundsAgeIs1BC(){
        endRound(40);
        assertThat(game.getAge(), is(-1));
    }


}
