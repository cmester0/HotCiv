package test.hotciv.standard;

import org.junit.Before;
import org.junit.Test;
import src.hotciv.framework.*;
import src.hotciv.standard.*;
import src.hotciv.standard.factories.civ.ThetaCivFactory;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by Lasse Letager Hansen on 05-12-2016.
 */
public class TestThetaCiv {
    private Game game;
    private Position redPosition, bluePosition;

    @Before
    public void setUp() {
        game = new GameImpl(new AbstractCiv(new ThetaCivFactory()));
        redPosition = (new Position(1,1));
        bluePosition = (new Position(4,1));
    }

    private void endRound(int n){
        for(int i=0; i<2*n; i++)
            game.endOfTurn();
    }

    @Test
    public void bombUnitHave1DefensiveStrength(){
        Unit bomb = new StandardUnit("bomb", Player.RED);
        assertThat(bomb.getDefensiveStrength(), is(1));
    }

    @Test
    public void citiesCanProduceBombs(){
        game.changeProductionInCityAt(redPosition, "bomb");
        endRound(11);
        assertThat(game.getUnitAt(redPosition).getTypeString(), is("bomb"));
    }

    @Test
    public void bombDisappearsOnExplosion(){
        game.changeProductionInCityAt(redPosition, "bomb");
        endRound(11);
        game.performUnitActionAt(redPosition);
        assertThat(game.getUnitAt(redPosition), is(nullValue()));
    }


    @Test
    public void unitsAroundBombDisappearsOnExplosion(){
        game.changeProductionInCityAt(redPosition, "bomb");
        endRound(11);
        game.changeProductionInCityAt(redPosition, GameConstants.ARCHER);
        endRound(2 * 4); // (2 * 4 * 6) / 10 = 4.8 => 4 units
        game.performUnitActionAt(redPosition);

        assertThat(game.getUnitAt(new Position(0,1)), is(nullValue()));
        assertThat(game.getUnitAt(new Position(0,2)), is(nullValue()));
        assertThat(game.getUnitAt(new Position(1,2)), is(nullValue()));
        assertThat(game.getUnitAt(new Position(2,1)), is(nullValue()));
        assertThat(game.getUnitAt(new Position(2,0)), is(nullValue()));
    }
}
