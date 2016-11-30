package src.hotciv.standard;

import jdk.nashorn.internal.runtime.regexp.JoniRegExp;
import src.hotciv.framework.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssodelta on 11/21/16.
 */
public class BetaCiv implements Civ {

    private final WinnerStrategy winnerStrategy;
    private final AgeingStrategy ageingStrategy;

    private AbstractCiv alphaCiv;
    private Player winner;

    private Map<Position, City> cities;
    private Map<Position, Unit> units;

    public BetaCiv(){
        alphaCiv = new AbstractCiv(new AlphaCivFactory());
        CivFactory factory = new BetaCivFactory();
        winner = null;

        ageingStrategy = factory.createAgeingStrategy();
        winnerStrategy = factory.createWinnerStrategy();
    }

    @Override
    public int getNextAge(int currentAge) {
        return ageingStrategy.getNextAge(currentAge);
    }

    @Override
    public Player getWinner() {
        return winnerStrategy.getWinner();
    }

    @Override
    public void performUnitActionAt(Position p) {

    }

    @Override
    public void setup(Map<Position, Unit> units, Map<Position, City> cities, Map<Position, Tile> tiles) {
        alphaCiv.setup(units, cities, tiles);

        this.cities = cities;
        this.units = units;
    }

    @Override
    public void update(Map<Position, Unit> units, Map<Position, City> cities, Map<Position, Tile> tiles) {
        winnerStrategy.checkWorld(units, cities, null);
    }

    @Override
    public boolean outcomeOfBattle(Unit attacker, Unit defender) {
        return alphaCiv.outcomeOfBattle(attacker, defender);
    }

}
