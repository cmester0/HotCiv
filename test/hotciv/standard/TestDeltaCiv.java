package test.hotciv.standard;

import org.junit.Before;
import org.junit.Test;
import src.hotciv.framework.*;
import src.hotciv.standard.*;
import src.hotciv.standard.factories.DeltaCivFactory;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by Lasse Letager Hansen on 21-11-2016.
 */
public class TestDeltaCiv {

    private Game game;

    private Position redPosition, bluePosition;

    @Before
    public void setUp() {
        game = new GameImpl(new AbstractCiv(new DeltaCivFactory()));
    }

    private int countNumberOfTilesOfType(String type){
        int counter = 0;

        for(int i=0; i < 16; i++){
            for(int j = 0; j < 16; j++){
                String tileAtIcommaJ = game.getTileAt(new Position(i,j)).getTypeString();
                if(tileAtIcommaJ.equals(type)){
                    counter++;
                }
            }
        }

        return counter;
    }

    @Test
    public void blueCityAt4comma5(){
        assertThat(game.getCityAt(new Position(4,5)), is(notNullValue()));
        assertThat(game.getCityAt(new Position(4,5)).getOwner(), is(Player.BLUE));
    }

    @Test
    public void redCityAt8comma12(){
        assertThat(game.getCityAt(new Position(8,12)), is(notNullValue()));
        assertThat(game.getCityAt(new Position(8,12)).getOwner(), is(Player.RED));
    }

    @Test
    public void mountainAt0comma5(){
        assertThat(game.getTileAt(new Position(0,5)).getTypeString(), is(GameConstants.MOUNTAINS));
    }

    @Test
    public void hillsAt1comma4(){
        assertThat(game.getTileAt(new Position(1,4)).getTypeString(), is(GameConstants.HILLS));
    }

    @Test
    public void deltaCivMap(){
        assertThat(game.getTileAt(new Position(8,6)).getTypeString(), is(GameConstants.OCEANS));
        assertThat(game.getTileAt(new Position(7,13)).getTypeString(), is(GameConstants.MOUNTAINS));
        assertThat(game.getTileAt(new Position(9,11)).getTypeString(), is(GameConstants.FOREST));
        assertThat(game.getTileAt(new Position(5,1)).getTypeString(), is(GameConstants.PLAINS));
        assertThat(game.getTileAt(new Position(0,0)).getTypeString(), is(GameConstants.OCEANS));
        assertThat(game.getTileAt(new Position(15,15)).getTypeString(), is(GameConstants.OCEANS));
        assertThat(game.getTileAt(new Position(4,8)).getTypeString(), is(GameConstants.HILLS));
        assertThat(game.getTileAt(new Position(8,13)).getTypeString(), is(GameConstants.FOREST));
    }

    @Test
    public void thereAre9MountainsInTheWorld(){
        assertThat(countNumberOfTilesOfType(GameConstants.MOUNTAINS), is(9));
    }

    @Test
    public void thereAre10HillsInTheWorld(){
        assertThat(countNumberOfTilesOfType(GameConstants.HILLS), is(10));
    }

    @Test
    public void thereAre12ForrestInTheWorld(){
        assertThat(countNumberOfTilesOfType(GameConstants.FOREST), is(14));
    }

}
