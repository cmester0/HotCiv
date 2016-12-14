package src.hotciv.standard.strategies;

import src.hotciv.framework.*;
import src.hotciv.standard.StandardTile;
import thirdparty.ThirdPartyFractalGenerator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lasse Letager Hansen on 14-12-2016.
 */
public class FractalStartingLayoutStrategy implements StartingLayoutStrategy {
    private static final ThirdPartyFractalGenerator generator = new ThirdPartyFractalGenerator();

    @Override
    public Map<Position, Tile> createMap() {
        Map<Position,Tile> theWorld = new HashMap<Position,Tile>();
        for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
            for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
                char tileChar = generator.getLandscapeAt(r,c);
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
        return null;
    }

    @Override
    public Map<Position, Unit> createUnits() {
        return null;
    }
}
