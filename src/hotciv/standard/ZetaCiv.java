package src.hotciv.standard;

import src.hotciv.framework.*;
import test.hotciv.standard.LoadedDie;

import java.util.Map;

/**
 * Created by Lasse Letager Hansen on 28-11-2016.
 */
public class ZetaCiv implements Civ {
    private final BattleStrategy battleStrategy;
    private final AgeingStrategy ageingStrategy;
    private final WinnerStrategy winningStrategy;
    private final StartingLayoutStrategy startingLayout;

    public ZetaCiv(){
        CivFactory factory = new ZetaCivFactory();
        battleStrategy = factory.createBattleStrategy();
        ageingStrategy = factory.createAgeingStrategy();
        winningStrategy = factory.createWinnerStrategy();
        startingLayout = factory.createStartingLayoutStrategy();
    }

    @Override
    public int getNextAge(int currentAge) {
        int nextAge = ageingStrategy.getNextAge(currentAge);
        winningStrategy.setAge(nextAge);
        return nextAge;
    }

    @Override
    public Player getWinner() {
        return winningStrategy.getWinner();
    }

    @Override
    public void setup(Map<Position, Unit> units, Map<Position, City> cities, Map<Position, Tile> tiles) {
        units.putAll(startingLayout.createUnits());
        cities.putAll(startingLayout.createCities());
        tiles.putAll(startingLayout.createMap());
    }

    @Override
    public void update(Map<Position, Unit> units, Map<Position, City> cities, Map<Position, Tile> tiles) {
        winningStrategy.checkWorld(units, cities, tiles);
    }

    @Override
    public boolean outcomeOfBattle(Unit attacker, Unit defender) {
        boolean winner = battleStrategy.getOutcomeOfBattle(attacker, defender);

        if(winner)
            winningStrategy.addAttackWin(attacker.getOwner());

        return winner;
    }
}
