package test.hotciv.standard;

import org.junit.Before;
import org.junit.Test;
import src.hotciv.framework.*;
import src.hotciv.standard.*;
import src.hotciv.standard.factories.ZetaCivFactory;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by Lasse Letager Hansen on 28-11-2016.
 */
public class TestZetaCiv {
    private Game game;
    private Position redPosition, bluePosition;

    @Before
    public void setUp() {
        game = new GameImpl(new AbstractCiv(new ZetaCivFactory()));
        redPosition = (new Position(1,1));
        bluePosition = (new Position(4,1));
    }

    private void endRound(int n){
        for(int i=0; i<2*n; i++)
            game.endOfTurn();
    }

    @Test
    public void thereIsNoWinnerAtBeginning(){
        assertThat(game.getWinner(), is(nullValue()));
    }

    @Test
    public void redConquersBlueCityDoesWinTheGameBeforeRound21(){
        game.moveUnit(new Position(2,0), new Position(3,0));
        endRound(1);
        game.moveUnit(new Position(3,0), new Position(4,1));
        assertThat(game.getWinner(), is(Player.RED));
    }

    @Test
    public void redConquersBlueCityDoesWinTheGameAtRound20(){
        game.moveUnit(new Position(2,0), new Position(3,0));
        endRound(20);
        game.moveUnit(new Position(3,0), new Position(4,1));
        assertThat(game.getWinner(), is(Player.RED));
    }

    @Test
    public void redConquersBlueCityDoesNotWinTheGameAtRound21(){
        game.moveUnit(new Position(2,0), new Position(3,0));
        endRound(21);
        game.moveUnit(new Position(3,0), new Position(4,1));
        assertThat(game.getWinner(), is(nullValue()));
    }

    @Test
    public void blueWins3AttacksAndWinsTheGameFromRound21(){
        endRound(21); // Make some units, to fight
        game.endOfTurn(); // Blues turn

        // Move one unit, through three battles
        game.moveUnit(new Position(3,1), new Position(2,1));
        endRound(1);
        game.moveUnit(new Position(2,1), new Position(1,2));
        endRound(1);
        game.moveUnit(new Position(1,2), new Position(0,2));

        assertThat(game.getWinner(), is(Player.BLUE));
    }

    @Test
    public void redWins3AttacksAndWinsTheGameFromRound21(){
        endRound(21); // Make some units, to fight

        // Move one unit, through three battles
        game.moveUnit(new Position(2,0), new Position(3,1));
        endRound(1);
        game.moveUnit(new Position(3,1), new Position(3,2));
        endRound(1);
        game.moveUnit(new Position(3,2), new Position(4,2));

        assertThat(game.getWinner(), is(Player.RED));
    }

    @Test
    public void blueWins3AttacksDoesNotWinTheGameBeforeRound21(){
        endRound(10); // Make some units, to fight
        game.endOfTurn(); // Blues turn

        // Move one unit, through three battles
        game.moveUnit(new Position(3,1), new Position(2,1));
        endRound(1);
        game.moveUnit(new Position(2,1), new Position(1,2));
        endRound(1);
        game.moveUnit(new Position(1,2), new Position(0,2));
        assertThat(game.getWinner(), is(nullValue()));
    }

}
