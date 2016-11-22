package src.hotciv.standard;

import src.hotciv.framework.*;

import java.util.Map;

/**
 * Created by ssodelta on 11/22/16.
 */
public class GammaCiv implements Civ {

    private AlphaCiv alphaCiv;

    private Map<Position, City> cities;
    private Map<Position, Unit> units;

    public GammaCiv(){
        alphaCiv = new AlphaCiv();
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
        if(u != null && u.getTypeString() == GameConstants.SETTLER) {
            units.remove(p);
            cities.put(p, new StandardCity(u.getOwner()));
        }
    }

    @Override
    public void setup(Map<Position, Unit> units, Map<Position, City> cities, Map<Position, Tile> tiles) {
        alphaCiv.setup(units, cities, tiles);

        this.cities = cities;
        this.units = units;
    }

    @Override
    public void update() {
        alphaCiv.update();
    }
}
