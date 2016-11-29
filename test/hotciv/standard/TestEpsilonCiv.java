package test.hotciv.standard;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import src.hotciv.framework.*;
import src.hotciv.standard.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by Lasse Letager Hansen on 28-11-2016.
 */
public class TestEpsilonCiv {
    private Game game;
    private Position redPosition, bluePosition;
    private LoadedDie die;

    @Before
    public void setUp() {
        die = new LoadedDie();

        game = new GameImpl(new EpsilonCiv(die));
        redPosition = (new Position(1,1));
        bluePosition = (new Position(4,1));
    }

    private void endRound(int n){
        for(int i=0; i<2*n; i++)
            game.endOfTurn();
    }

    @Test
    public void baseAttackOfArcherIs2(){
        Unit u = new StandardUnit(GameConstants.ARCHER, Player.RED);
        assertThat(u.getAttackingStrength(), is(2));
    }

    @Test
    public void baseAttackOfLegionIs4(){
        Unit u = new StandardUnit(GameConstants.LEGION, Player.RED);
        assertThat(u.getAttackingStrength(), is(4));
    }

    @Test
    public void baseAttackOfSettlerIs0(){
        Unit u = new StandardUnit(GameConstants.SETTLER, Player.RED);
        assertThat(u.getAttackingStrength(), is(0));
    }

    @Test
    public void combinedAttackStrengthOfAUnitInACityIsTripled(){
        game.moveUnit(new Position(2,0), redPosition);
        Unit u = game.getUnitAt(redPosition);

        // base attack strength of archer is 2, and multiplier of 3 from city
        assertThat(u.getAttackingStrength(), is(2*3));
    }

    @Test
    public void strengthOfUnitOnHillIsDoubled(){
        endRound(2);
        game.moveUnit(new Position(1,1), new Position(0,1));
        Unit u = game.getUnitAt(new Position(0,1));


        // base attack strength of archer is 2, and multiplier of 2 from terrain
        assertThat(u.getAttackingStrength(), is(2*2));
    }

    @Test
    public void legionInCityWithTwoAdjacentFriendlyUnitsIs18(){
        game.changeProductionInCityAt(redPosition, GameConstants.LEGION);

        // wait until two legions spawn
        endRound(6);

        Unit u = game.getUnitAt(redPosition);
        assertThat(u.getAttackingStrength(), is(18));
    }

    @Test
    public void baseDefenceOfArcherIs3(){
        Unit u = new StandardUnit(GameConstants.ARCHER, Player.RED);
        assertThat(u.getDefensiveStrength(), is(3));
    }

    @Test
    public void baseDefenceOfLegionIs2(){
        Unit u = new StandardUnit(GameConstants.LEGION, Player.RED);
        assertThat(u.getDefensiveStrength(), is(2));
    }

    @Test
    public void baseDefenceOfSettlerIs3(){
        Unit u = new StandardUnit(GameConstants.SETTLER, Player.RED);
        assertThat(u.getDefensiveStrength(), is(3));
    }

    @Test
    public void defenceOfUnitOnHillIsDouble(){
        endRound(2);
        game.moveUnit(new Position(1,1), new Position(0,1));
        Unit u = game.getUnitAt(new Position(0,1));


        // base attack strength of archer is 2, and multiplier of 2 from terrain
        assertThat(u.getDefensiveStrength(), is(3*2));
    }

    @Test
    public void defenceOfUnitInCityIsTripled(){
        game.moveUnit(new Position(2,0), redPosition);
        Unit u = game.getUnitAt(redPosition);

        // base attack strength of archer is 2, and multiplier of 3 from city
        assertThat(u.getDefensiveStrength(), is(3*3));
    }

    @Test
    public void strongerUnitWinBattle(){
        die.addNumber(1); // first roll (attacker) will be 1
        die.addNumber(1); // second roll (defender) will be 1

        game.moveUnit(new Position(2,0), new Position(2,1));
        game.endOfTurn();
        game.moveUnit(new Position(3,2), new Position(2,1));
        Unit u = game.getUnitAt(new Position(2,1));
        assertThat(u.getOwner(), is(Player.BLUE));
    }


    @Test
    public void diceRollDecidesTheWinnerOfABattle(){
        die.addNumber(1); // first roll (attacker) will be 1
        die.addNumber(5); // second roll (defender) will be 5

        game.moveUnit(new Position(2,0), new Position(2,1));
        game.endOfTurn();
        game.moveUnit(new Position(3,2), new Position(2,1));
        Unit u = game.getUnitAt(new Position(2,1));
        assertThat(u.getOwner(), is(Player.RED));
    }

    @Test
    public void theWinnerOf3AttacksWinsTheGame(){
        endRound(10);
        game.endOfTurn();

        for(int i = 0; i < 3; i++) { // Attacker should win the next three battles
            die.addNumber(6); // first roll (attacker) will be 5
            die.addNumber(1); // second roll (defender) will be 1
        }

        game.moveUnit(new Position(3,1), new Position(2,1));
        endRound(1);
        game.moveUnit(new Position(2,1), new Position(1,2));
        endRound(1);
        game.moveUnit(new Position(1,2), new Position(0,2));

        assertThat(game.getWinner(), is(Player.BLUE));
    }
}
