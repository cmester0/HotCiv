package test.hotciv.standard;

        import org.junit.Before;
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

    @Before
    public void setUp() {
        game = new GameImpl(new EpsilonCiv());
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
}
