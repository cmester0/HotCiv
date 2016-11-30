package src.hotciv.standard;

import jdk.nashorn.internal.runtime.regexp.JoniRegExp;
import src.hotciv.framework.*;

import javax.xml.parsers.FactoryConfigurationError;
import java.util.Map;

/**
 * Created by Lasse Letager Hansen on 30-11-2016.
 */
public class AbstractCiv implements Civ {
    AgeingStrategy ageingStrategy;
    WinnerStrategy winnerStrategy;
    StartingLayoutStrategy startingLayoutStrategy;
    BattleStrategy battleStrategy;

    public AbstractCiv(CivFactory civFactory){
        ageingStrategy = civFactory.createAgeingStrategy();
        winnerStrategy = civFactory.createWinnerStrategy();
        startingLayoutStrategy = civFactory.createStartingLayoutStrategy();
        battleStrategy = civFactory.createBattleStrategy();
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
        units.putAll(startingLayoutStrategy.createUnits());
        cities.putAll(startingLayoutStrategy.createCities());
        tiles.putAll(startingLayoutStrategy.createMap());
    }

    @Override
    public void update(Map<Position, Unit> units, Map<Position, City> cities, Map<Position, Tile> tiles) {
        winnerStrategy.checkWorld(units, cities, tiles);
    }

    @Override
    public boolean outcomeOfBattle(Unit attacker, Unit defender) {
        return battleStrategy.getOutcomeOfBattle(attacker, defender);
    }
}
