package src.hotciv.standard;

import src.hotciv.framework.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssodelta on 11/21/16.
 */
public class AlphaCiv implements Civ {

    private Player winner = null;

    @Override
    public int getNextAge(int currentAge) {
        if(currentAge+100 >= -3000)
            winner = Player.RED;
        return currentAge+100;
    }

    @Override
    public Player getWinner() {
        return winner;
    }

    @Override
    public void performUnitActionAt(Position p) {

    }

    @Override
    public void setup(Map<Position, Unit> units, Map<Position, City> cities, Map<Position, Tile> tiles) {
        cities.put(new Position(1, 1), new StandardCity(new Position(1, 1)));
        cities.put(new Position(4, 1), new StandardCity(new Position(4, 1)));

        units.put(new Position(2, 0), new StandardUnit(GameConstants.ARCHER, Player.RED));
        units.put(new Position(3, 2), new StandardUnit(GameConstants.LEGION, Player.BLUE));
        units.put(new Position(4, 3), new StandardUnit(GameConstants.SETTLER, Player.BLUE));

        for(int i=0; i<16; i++)
        for(int j=0; j<16; j++)
            tiles.put(new Position(i,j), new StandardTile(GameConstants.PLAINS));

        tiles.put(new Position(1,0), new StandardTile(GameConstants.OCEANS));
        tiles.put(new Position(0,1), new StandardTile(GameConstants.HILLS));
        tiles.put(new Position(2,2), new StandardTile(GameConstants.MOUNTAINS));
    }

}
