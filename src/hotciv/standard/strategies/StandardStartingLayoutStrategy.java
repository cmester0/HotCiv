package src.hotciv.standard.strategies;

import src.hotciv.framework.*;
import src.hotciv.standard.StandardTile;
import src.hotciv.standard.StandardCity;
import src.hotciv.standard.StandardUnit;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lasse Letager Hansen on 30-11-2016.
 */
public class StandardStartingLayoutStrategy implements StartingLayoutStrategy {
    @Override
    public Map<Position, Tile> createMap() {
        Map<Position, Tile> tiles = new HashMap<Position,Tile>();
        for(int i=0; i<16; i++)
            for(int j=0; j<16; j++)
                tiles.put(new Position(i,j), new StandardTile(GameConstants.PLAINS));

        tiles.put(new Position(1,0), new StandardTile(GameConstants.OCEANS));
        tiles.put(new Position(0,1), new StandardTile(GameConstants.HILLS));
        tiles.put(new Position(2,2), new StandardTile(GameConstants.MOUNTAINS));
        return tiles;
    }

    @Override
    public Map<Position, City> createCities() {
        Map<Position, City> cities = new HashMap<Position,City>();

        cities.put(new Position(1, 1), new StandardCity(Player.RED));
        cities.put(new Position(4, 1), new StandardCity(Player.BLUE));

        return cities;
    }

    @Override
    public Map<Position, Unit> createUnits() {
        Map<Position, Unit> units = new HashMap<Position,Unit>();

        units.put(new Position(2, 0), new StandardUnit(GameConstants.ARCHER, Player.RED));
        units.put(new Position(3, 2), new StandardUnit(GameConstants.LEGION, Player.BLUE));
        units.put(new Position(4, 3), new StandardUnit(GameConstants.SETTLER, Player.RED));

        return units;
    }


}
