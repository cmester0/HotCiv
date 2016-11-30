package src.hotciv.standard;

import src.hotciv.framework.*;

import java.util.Map;

/**
 * Created by ssodelta on 11/21/16.
 */
public class AlphaCiv implements Civ {

    CivFactory factory;
    AgeingStrategy ageingStrategy;
    WinnerStrategy winnerStrategy;
    private Player winner = null;

    public AlphaCiv(){
        factory = new AlphaCivFactory();
        ageingStrategy = factory.createAgeingStrategy();
        winnerStrategy = factory.createWinnerStrategy();
    }

    @Override
    public int getNextAge(int currentAge) {
        int nextAge = ageingStrategy.getNextAge(currentAge);
        winnerStrategy.setAge(nextAge);
        return nextAge;
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
        StartingLayoutStrategy layoutStrategy = factory.createStartingLayoutStrategy();
        layoutStrategy.createCities();

        cities.putAll(layoutStrategy.createCities());
        units.putAll(layoutStrategy.createUnits());
        tiles.putAll(layoutStrategy.createMap());
    }

    @Override
    public void update() {

    }

    @Override
    public boolean outcomeOfBattle(Unit attacker, Unit defender) {
        return true; // Attacker always wins
    }

}
