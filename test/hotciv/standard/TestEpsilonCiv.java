package test.hotciv.standard;

        import org.junit.Before;
        import org.junit.Test;
        import src.hotciv.framework.*;
        import src.hotciv.standard.BetaCiv;
        import src.hotciv.standard.DeltaCiv;
        import src.hotciv.standard.EpsilonCiv;
        import src.hotciv.standard.GameImpl;

        import static org.junit.Assert.*;
        import static org.hamcrest.CoreMatchers.*;

/**
 * Created by Lasse Letager Hansen on 28-11-2016.
 */
public class TestEpsilonCiv {
    Game game;

    @Before
    public void setUp() {
        game = new GameImpl(new EpsilonCiv());
    }

    @Test
    public void baseAttackOfArcherIs2(){
        Unit u = game.getUnitAt(new Position(2,0));
        assertThat(u.getAttackingStrength(), is(2));
    }
}
