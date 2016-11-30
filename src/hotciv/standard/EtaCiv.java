package src.hotciv.standard;

import src.hotciv.framework.*;

import java.util.Map;

/**
 * Created by Lasse Letager Hansen on 28-11-2016.
 */
public class EtaCiv implements Civ {
    private AbstractCiv alphaCiv;

    private final AgeingStrategy ageingStrategy;
    private final WinnerStrategy winnerStrategy;
    private final StartingLayoutStrategy startingLayoutStrategy;
    private final BattleStrategy battleStrategy;

    public EtaCiv(){
        alphaCiv = new AbstractCiv(new AlphaCivFactory());

        CivFactory factory = new EtaCivFactory();
        ageingStrategy = factory.createAgeingStrategy();
        winnerStrategy = factory.createWinnerStrategy();
        startingLayoutStrategy = factory.createStartingLayoutStrategy();
        battleStrategy = factory.createBattleStrategy();
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
        alphaCiv.performUnitActionAt(p);
    }

    @Override
    public void setup(Map<Position, Unit> units, Map<Position, City> cities, Map<Position, Tile> tiles) {
        alphaCiv.setup(units, cities, tiles);
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
