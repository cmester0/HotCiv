package src.hotciv.standard.strategies;

import src.hotciv.framework.StartingLayoutStrategy;
import src.hotciv.framework.Player;
import src.hotciv.framework.Position;
import src.hotciv.framework.Unit;
import src.hotciv.framework.GameConstants;
import src.hotciv.framework.City;
import src.hotciv.framework.Tile;

import src.hotciv.standard.StandardTile;
import src.hotciv.standard.StandardCity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lasse Letager Hansen on 30-11-2016.
 */
public class ComplexStartingLayoutStrategy implements StartingLayoutStrategy {

    @Override
    public Map<Position, Tile> createMap() {
        // Basically we use a 'data driven' approach - code the
        // layout in a simple semi-visual representation, and
        // convert it to the actual Game representation.
        String[] layout =
                new String[] {
                        "...ooMooooo.....",
                        "..ohhoooofffoo..",
                        ".oooooMooo...oo.",
                        ".ooMMMoooo..oooo",
                        "...ofooohhoooo..",
                        ".ofoofooooohhoo.",
                        "...ooo..........",
                        ".ooooo.ooohooM..",
                        ".ooooo.oohooof..",
                        "offfoooo.offoooo",
                        "oooooooo...ooooo",
                        ".ooMMMoooo......",
                        "..ooooooffoooo..",
                        "....ooooooooo...",
                        "..ooohhoo.......",
                        ".....ooooooooo..",
                };
        // Conversion...
        Map<Position,Tile> theWorld = new HashMap<Position,Tile>();
        String line;
        for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
            line = layout[r];
            for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
                char tileChar = line.charAt(c);
                String type = "error";
                if ( tileChar == '.' ) { type = GameConstants.OCEANS; }
                if ( tileChar == 'o' ) { type = GameConstants.PLAINS; }
                if ( tileChar == 'M' ) { type = GameConstants.MOUNTAINS; }
                if ( tileChar == 'f' ) { type = GameConstants.FOREST; }
                if ( tileChar == 'h' ) { type = GameConstants.HILLS; }
                Position p = new Position(r,c);
                theWorld.put( p, new StandardTile(type));
            }
        }
        return theWorld;
    }

    @Override
    public Map<Position, City> createCities() {
        Map<Position, City> cities = new HashMap<Position, City>();

        cities.put(new Position(4,5), new StandardCity(Player.BLUE));
        cities.put(new Position(8,12), new StandardCity(Player.RED));

        return cities;
    }

    @Override
    public Map<Position, Unit> createUnits() {
        return new HashMap<Position, Unit>(); // Should not be empty?
    }
}
