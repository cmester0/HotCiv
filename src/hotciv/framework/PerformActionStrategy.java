package src.hotciv.framework;

import java.util.Map;

/**
 * Created by Lasse Letager Hansen on 06-12-2016.
 */
public interface PerformActionStrategy {
    void performUnitAction(Position p, Map<Position, Unit> units, Map<Position, City> cities);
}
