package src.hotciv.standard.strategies;

import src.hotciv.framework.City;
import src.hotciv.framework.PerformActionStrategy;
import src.hotciv.framework.Position;
import src.hotciv.framework.Unit;

import java.util.Map;

/**
 * Created by Lasse Letager Hansen on 06-12-2016.
 */
public class BombUnitActionStrategy implements PerformActionStrategy {
    @Override
    public void performUnitAction(Position p, Map<Position, Unit> units, Map<Position, City> cities) {
        Unit u = units.get(p);

        if(u.getTypeString().equals("bomb")){
            for(int i = -1; i < 2; i++){
                for(int j = -1; j < 2; j++){
                    Position toRemove = new Position(p.getRow() + i, p.getColumn() + j);
                    units.remove(toRemove);
                }
            }
        }
    }
}
