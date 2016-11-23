package src.hotciv.standard;

import src.hotciv.framework.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lasse Letager Hansen on 23-11-2016.
 */
public class DeltaCiv implements Civ {
    AlphaCiv alphaCiv;

    @Override
    public int getNextAge(int currentAge) {
        return alphaCiv.getNextAge(currentAge);
    }

    @Override
    public Player getWinner() {
        return alphaCiv.getWinner();
    }

    @Override
    public void performUnitActionAt(Position p) {
        alphaCiv.performUnitActionAt(p);
    }

    @Override
    public void setup(Map<Position, Unit> units, Map<Position, City> cities, Map<Position, Tile> tiles) {
        cities.put(new Position(4,5), new StandardCity(Player.BLUE));
        cities.put(new Position(8,12), new StandardCity(Player.RED));
        tiles.putAll(defineWorld());
    }

    /** Define the world as the DeltaCiv layout */
    private Map<Position,Tile> defineWorld() {
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
    public void update() {
        alphaCiv.update();
    }
}
