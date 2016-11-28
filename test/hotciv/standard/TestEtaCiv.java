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
public class TestEtaCiv {
    private Game game;
    private Position redPosition, bluePosition;

    @Before
    public void setUp() {
        game = new GameImpl(new EtaCiv());
        redPosition = (new Position(1, 1));
        bluePosition = (new Position(4, 1));
    }

    private void endRound(int n) {
        for (int i = 0; i < 2 * n; i++)
            game.endOfTurn();
    }

    @Test
    public void workforceFocusIsProductionAtStart(){
        game.changeWorkforceFocusInCityAt(redPosition, GameConstants.foodFocus);
        assertThat(game.getCityAt(redPosition).getWorkforceFocus(), is(GameConstants.foodFocus));
    }

    @Test
    public void changingTheWorkforceFocusActuallyChangesIt(){
        game.changeWorkforceFocusInCityAt(redPosition, GameConstants.foodFocus);
        assertThat(game.getCityAt(redPosition).getWorkforceFocus(), is(GameConstants.foodFocus));
    }

    @Test
    public void changingWorkforceFocusToFoodMakesFood(){
        game.changeWorkforceFocusInCityAt(redPosition, GameConstants.foodFocus);
        endRound(2);
        assertThat(game.getFoodAmountOfCityAt(redPosition), is(2));
    }

    @Test
    public void gatheringFoodGrowsTheCity(){
        game.changeWorkforceFocusInCityAt(redPosition, GameConstants.foodFocus);
        endRound(1*3 + 5);
        assertThat(game.getCityAt(redPosition).getSize(), is(2));
        endRound(2*3 + 5);
        assertThat(game.getCityAt(redPosition).getSize(), is(3));
        endRound(3*3 + 5);
        assertThat(game.getCityAt(redPosition).getSize(), is(4));
        endRound(4*3 + 5);
        assertThat(game.getCityAt(redPosition).getSize(), is(5));
        endRound(5*3 + 5);
        assertThat(game.getCityAt(redPosition).getSize(), is(6));
        endRound(6*3 + 5);
        assertThat(game.getCityAt(redPosition).getSize(), is(7));
        endRound(7*3 + 5);
        assertThat(game.getCityAt(redPosition).getSize(), is(8));
        endRound(8*3 + 5);
        assertThat(game.getCityAt(redPosition).getSize(), is(9));
    }

    @Test
    public void citiesCannotExceedSize9(){
        game.changeWorkforceFocusInCityAt(redPosition, GameConstants.foodFocus);

        endRound(1*3 + 5); // Size 1
        endRound(2*3 + 5); // Size 2
        endRound(3*3 + 5); // Size 3
        endRound(4*3 + 5); // Size 4
        endRound(5*3 + 5); // Size 5
        endRound(6*3 + 5); // Size 6
        endRound(7*3 + 5); // Size 7
        endRound(8*3 + 5); // Size 8
        endRound(9*3 + 5); // Size 9
        // No size 10 exists
        endRound(10*3 + 5); // Size 10

        endRound(300);
        assertThat(game.getCityAt(redPosition).getSize(), is(9));
    }

    @Ignore
    @Test
    public void productionChangesBasedOnEnvironment(){
        endRound(1); // City size 1

        City redCity = game.getCityAt(redPosition);
        assertThat(game.getProductionAmountOfCity(redCity), is(3));
    }
}