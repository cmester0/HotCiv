package test.hotciv.standard;

import org.junit.Before;
import org.junit.Test;
import src.hotciv.framework.Game;
import src.hotciv.framework.Player;
import src.hotciv.framework.Position;
import src.hotciv.standard.AbstractCiv;
import src.hotciv.standard.BetaCivFactory;
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
        game = new GameImpl(new AbstractCiv(new BetaCivFactory()));
        redPosition = (new Position(1,1));
        bluePosition = (new Position(4,1));
    }

    private void endRound(int n){
        for(int i=0; i<2*n; i++)
            game.endOfTurn();
    }

    @Test
    public void after0RoundsAgeIs4000BC(){
        assertThat(game.getAge(), is(-4000));
    }

    @Test
    public void after40RoundsAgeIs1BC(){
        endRound(40);
        assertThat(game.getAge(), is(-1));
    }

    @Test
    public void after41RoundsAgeIs1AD(){
        endRound(41);
        assertThat(game.getAge(), is(1));
    }

    @Test
    public void after42RoundsAgeIs50AD(){
        endRound(42);
        assertThat(game.getAge(), is(50));
    }

    @Test
    public void after43RoundsAgeIs100AD(){
        endRound(43);
        assertThat(game.getAge(), is(100));
    }

    @Test
    public void after77RoundsAgeIs1775AD(){
        endRound(77);
        assertThat(game.getAge(), is(1775));
    }

    @Test
    public void after83RoundsAgeIs1905AD(){
        endRound(83);
        assertThat(game.getAge(), is(1905));
    }

    @Test
    public void after97RoundsAgeIs1971AD() {
        endRound(97);
        assertThat(game.getAge(), is(1971));
    }

    @Test
    public void thereIsNoWinnerAtBeginning(){
        assertThat(game.getWinner(), is(nullValue()));
    }

    @Test
    public void redConquersBlueCityByMovingArcher(){
        game.moveUnit(new Position(2,0), new Position(3,0));
        endRound(1);
        game.moveUnit(new Position(3,0), new Position(4,1));
        assertThat(game.getCityAt(bluePosition).getOwner(), is(Player.RED));
    }

    @Test
    public void blueConquersRedCityByMovingLegion(){
        game.endOfTurn();
        game.moveUnit(new Position(3,2), new Position(2,1));
        endRound(1);
        game.moveUnit(new Position(2,1), new Position(1,1));
        assertThat(game.getCityAt(redPosition).getOwner(), is(Player.BLUE));
    }

    @Test
    public void settlerCannotConquerCities(){
        game.moveUnit(new Position(4,3), new Position(4,2));
        endRound(1);
        game.moveUnit(new Position(4,2), new Position(4,1));
        assertThat(game.getCityAt(bluePosition).getOwner(), is(Player.BLUE));
    }

    @Test
    public void redConquersBlueCityAndWinsTheGame(){
        game.moveUnit(new Position(2,0), new Position(3,0));
        endRound(1);
        game.moveUnit(new Position(3,0), new Position(4,1));
        assertThat(game.getWinner(), is(Player.RED));
    }

    @Test
    public void blueConquersRedCityAndWinsTheGame(){
        game.endOfTurn();
        game.moveUnit(new Position(3,2), new Position(2,1));
        endRound(1);
        game.moveUnit(new Position(2,1), new Position(1,1));
        assertThat(game.getWinner(), is(Player.BLUE));
    }

    @Test
    public void thereIsNoWinnerIfWaiting(){
        endRound(25);
        assertThat(game.getWinner(), is(nullValue()));
    }
}
