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
        endRound(2);
        Unit u = game.getUnitAt(redPosition);

        // base attack strength of 2, and multiplier of 3 from city
        assertThat(u.getAttackingStrength(), is(2*3));
    }
}
