package src.hotciv.framework;

import java.util.Map;

/**
 * Created by Lasse Letager Hansen on 30-11-2016.
 */
public interface StartingLayoutStrategy {
    Map<Position,Tile> createMap();
    Map<Position, City> createCities();
    Map<Position, Unit> createUnits();
}
