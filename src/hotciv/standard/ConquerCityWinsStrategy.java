package src.hotciv.standard;

import src.hotciv.framework.*;

import java.util.Map;

/**
 * Created by Lasse Letager Hansen on 30-11-2016.
 */
public class ConquerCityWinsStrategy implements WinnerStrategy {
    Player winner;

    public ConquerCityWinsStrategy(){
        winner = null;
    }

    @Override
    public Player getWinner() {
        return winner;
    }

    @Override
    public void setAge(int age) { }

    @Override
    public void checkWorld(Map<Position, Unit> units, Map<Position, City> cities, Map<Position, Tile> tiles) {
        for(Map.Entry<Position, City> c : cities.entrySet()) {
            Position cityPos = c.getKey();
            Unit unitAtCityPos = units.get(cityPos);

            if(unitAtCityPos != null) {
                String unitType = unitAtCityPos.getTypeString();
                if(unitType.equals(GameConstants.SETTLER)) continue;

                Player unitOwner = unitAtCityPos.getOwner();
                Player cityOwner = cities.get(cityPos).getOwner();

                if(unitOwner != cityOwner) {
                    winner = unitOwner;
                    cities.put(cityPos, new StandardCity(unitOwner));
                }
            }
        }
    }

    @Override
    public void addAttackWin(Player p) { }
}
