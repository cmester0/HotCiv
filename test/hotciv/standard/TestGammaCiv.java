package test.hotciv.standard;

import org.junit.Before;
import org.junit.Test;
import src.hotciv.framework.Game;
import src.hotciv.framework.GameConstants;
import src.hotciv.framework.Player;
import src.hotciv.framework.Position;
import src.hotciv.standard.AbstractCiv;
import src.hotciv.standard.GameImpl;
import src.hotciv.standard.GammeCivFactory;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by Lasse Letager Hansen on 21-11-2016.
 */
public class TestGammaCiv {


    private Game game;

    private Position redPosition, bluePosition;

    /** Fixture for alphaciv testing. */
    @Before
    public void setUp() {
        game = new GameImpl(new AbstractCiv(new GammeCivFactory()));
        redPosition = (new Position(1,1));
        bluePosition = (new Position(4,1));
    }

    private void endRound(int n){
        for(int i=0; i<2*n; i++)
            game.endOfTurn();
    }

    @Test
    public void redSettlerAt4comma3CanProduceACity(){
        game.performUnitActionAt(new Position(4,3));

        assertThat(game.getUnitAt(new Position(4,3)), is(nullValue()));
        assertThat(game.getCityAt(new Position(4,3)), is(notNullValue()));
        assertThat(game.getCityAt(new Position(4,3)).getOwner(), is(Player.RED));
    }

    @Test
    public void unitActionAt0comma0DoesNothing(){
        game.performUnitActionAt(new Position(0,0));

        assertThat(game.getCityAt(new Position(0,0)), is(nullValue()));
        assertThat(game.getUnitAt(new Position(0,0)), is(nullValue()));
    }

    @Test
    public void redArcherAt2comma0DoesNotProduceCity(){
        game.performUnitActionAt(new Position(2,0));

        assertThat(game.getUnitAt(new Position(2,0)).getTypeString(), is(GameConstants.ARCHER));
        assertThat(game.getCityAt(new Position(2,0)), is(nullValue()));
    }

    @Test
    public void blueWaitsForSettlerAndMovesItAndPerformsUnitActionAndProducesABlueCity(){
        game.changeProductionInCityAt(bluePosition, GameConstants.SETTLER);
        endRound(5);
        game.endOfTurn();
        game.moveUnit(bluePosition, new Position(5,1));
        game.performUnitActionAt(new Position(5,1));

        assertThat(game.getUnitAt(new Position(5,1)), is(nullValue()));
        assertThat(game.getCityAt(new Position(5,1)), is(notNullValue()));
        assertThat(game.getCityAt(new Position(5,1)).getOwner(), is(Player.BLUE));
    }

    @Test
    public void archerAt2comma0Has3DefensiveStrength(){
        assertThat(game.getUnitAt(new Position(2,0)).getDefensiveStrength(), is(3));
    }

    @Test
    public void archerAt2comma0Has6DefensiveStrengthAfterFortification(){
        game.performUnitActionAt(new Position(2,0));
        assertThat(game.getUnitAt(new Position(2,0)).getDefensiveStrength(), is(6));
    }

    @Test
    public void archerAt2comma0Has3DefensiveStrengthAfter2Fortification(){
        game.performUnitActionAt(new Position(2,0));
        game.performUnitActionAt(new Position(2,0));
        assertThat(game.getUnitAt(new Position(2,0)).getDefensiveStrength(), is(3));
    }

}
