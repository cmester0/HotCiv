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
    private final BattleStrategy battleStrategy;
    private final StartingLayoutStrategy startingLayoutStrategy;

    public BetaCiv(){
        CivFactory factory = new BetaCivFactory();

        ageingStrategy = factory.createAgeingStrategy();
        winnerStrategy = factory.createWinnerStrategy();
        battleStrategy = factory.createBattleStrategy();
        startingLayoutStrategy = factory.createStartingLayoutStrategy();
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
        units.putAll(startingLayoutStrategy.createUnits());
        cities.putAll(startingLayoutStrategy.createCities());
        tiles.putAll(startingLayoutStrategy.createMap());
    }

    @Override
    public void update(Map<Position, Unit> units, Map<Position, City> cities, Map<Position, Tile> tiles) {
        winnerStrategy.checkWorld(units, cities, null);
    }

    @Override
    public boolean outcomeOfBattle(Unit attacker, Unit defender) {
        return battleStrategy.getOutcomeOfBattle(attacker, defender);
    }

}
