package src.hotciv.standard.strategies;

import src.hotciv.framework.*;
import src.hotciv.standard.StandardCity;
import src.hotciv.standard.StandardUnit;

import java.util.Map;

/**
 * Created by Lasse Letager Hansen on 06-12-2016.
 */
public class SimpleUnitActionStrategy implements PerformActionStrategy{
    @Override
    public void performUnitAction(Position p, Map<Position, Unit> units, Map<Position, City> cities) {
        Unit u = units.get(p);

        if(u == null) return;

        if(u.getTypeString().equals(GameConstants.SETTLER)) {
            units.remove(p);
            cities.put(p, new StandardCity(u.getOwner()));
        }
        if(u.getTypeString().equals(GameConstants.ARCHER)) {
            units.remove(p);
            int defensiveStrength = u.getDefensiveStrength()==3?3:0;
            units.put(p, new StandardUnit(GameConstants.ARCHER, u.getOwner(), u.getMoveCount(), defensiveStrength));
        }
    }
}
