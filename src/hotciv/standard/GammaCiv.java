package src.hotciv.standard;

import src.hotciv.framework.*;

import java.util.Map;

/**
 * Created by ssodelta on 11/22/16.
 */
public class GammaCiv implements Civ {

    private AbstractCiv alphaCiv;

    private Map<Position, City> cities;
    private Map<Position, Unit> units;

    public GammaCiv(){
        alphaCiv = new AbstractCiv(new AlphaCivFactory());
    }

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
        Unit u = units.get(p);
        if(u==null)return;

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

    @Override
    public void setup(Map<Position, Unit> units, Map<Position, City> cities, Map<Position, Tile> tiles) {
        alphaCiv.setup(units, cities, tiles);

        this.cities = cities;
        this.units = units;
    }

    @Override
    public void update(Map<Position, Unit> units, Map<Position, City> cities, Map<Position, Tile> tiles) {
        alphaCiv.update(units, cities, tiles);
    }

    @Override
    public boolean outcomeOfBattle(Unit attacker, Unit defender) {
        return alphaCiv.outcomeOfBattle(attacker, defender);
    }
}
