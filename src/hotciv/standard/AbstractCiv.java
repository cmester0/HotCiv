package src.hotciv.standard;

import src.hotciv.framework.*;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Created by Lasse Letager Hansen on 30-11-2016.
 */
public class AbstractCiv implements Civ {
    private AgeingStrategy ageingStrategy;
    private WinnerStrategy winnerStrategy;
    private StartingLayoutStrategy startingLayoutStrategy;
    private BattleStrategy battleStrategy;
    private PerformActionStrategy performActionStrategy;
    private UnitFactory unitFactory;
    private PositionFactory positionFactory;

    private Map<Position, Unit> units;
    private Map<Position, City> cities;

    public AbstractCiv(CivFactory civFactory){
        ageingStrategy = civFactory.createAgeingStrategy();
        winnerStrategy = civFactory.createWinnerStrategy();
        startingLayoutStrategy = civFactory.createStartingLayoutStrategy();
        battleStrategy = civFactory.createBattleStrategy();
        performActionStrategy = civFactory.createPerformActionStrategy();
        unitFactory = civFactory.createUnitFactory();
        positionFactory = civFactory.createPositionFactory();
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
    public void setup(Map<Position, Unit> units, Map<Position, City> cities, Map<Position, Tile> tiles) {
        units.putAll(startingLayoutStrategy.createUnits());
        cities.putAll(startingLayoutStrategy.createCities());
        tiles.putAll(startingLayoutStrategy.createMap());

        this.units = units;
        this.cities = cities;
    }

    @Override
    public void update(Map<Position, Unit> units, Map<Position, City> cities, Map<Position, Tile> tiles) {
        winnerStrategy.checkWorld(units, cities, tiles);
    }

    @Override
    public boolean outcomeOfBattle(Unit attacker, Unit defender) {
        boolean attackerWins = battleStrategy.getOutcomeOfBattle(attacker, defender);

        if(attackerWins)
            winnerStrategy.addAttackWin(attacker.getOwner());

        return attackerWins;
    }

    @Override
    public void performUnitActionAt(Position p) {
        performActionStrategy.performUnitAction(p, units, cities);
    }

    @Override
    public Unit createUnit(City c, AtomicInteger ai) {
        return unitFactory.createUnit(c, ai);
    }

    @Override
    public Position[] getPossibleBluePositions() {
        return positionFactory.getPossiblePositions(Player.BLUE);
    }

    @Override
    public Position[] getPossibleRedPositions() {
        return positionFactory.getPossiblePositions(Player.RED);
        /*
         */
    }
}
