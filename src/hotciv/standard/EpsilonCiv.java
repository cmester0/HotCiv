package src.hotciv.standard;

import src.hotciv.framework.*;

import java.util.Map;

/**
 * Created by Lasse Letager Hansen on 28-11-2016.
 */
public class EpsilonCiv implements Civ {
    private AbstractCiv alphaCiv;
    private Die die;
    private int blueWinCount;
    private int redWinCount;

    private final AgeingStrategy ageingStrategy;
    private final WinnerStrategy winnerStrategy;
    private final StartingLayoutStrategy startingLayoutStrategy;
    private final BattleStrategy battleStrategy;

    public EpsilonCiv(Die die){
        alphaCiv = new AbstractCiv(new AlphaCivFactory());
        this.die = die;

        blueWinCount = 0;
        redWinCount = 0;

        CivFactory factory = new EpsilonCivFactory();
        ageingStrategy = factory.createAgeingStrategy();
        winnerStrategy = factory.createWinnerStrategy();
        startingLayoutStrategy = factory.createStartingLayoutStrategy();
        battleStrategy = factory.createBattleStrategy();
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
        alphaCiv.performUnitActionAt(p);
    }

    @Override
    public void setup(Map<Position, Unit> units, Map<Position, City> cities, Map<Position, Tile> tiles) {
        units.putAll(startingLayoutStrategy.createUnits());
        cities.putAll(startingLayoutStrategy.createCities());
        tiles.putAll(startingLayoutStrategy.createMap());
    }

    @Override
    public void update(Map<Position, Unit> units, Map<Position, City> cities, Map<Position, Tile> tiles) {
        winnerStrategy.checkWorld(units, cities,tiles);
    }

    @Override
    public boolean outcomeOfBattle(Unit attacker, Unit defender) {
        int atkRoll = die.rollDie();
        int defRoll = die.rollDie();

        int attackStrength =  attacker.getAttackingStrength() * atkRoll;
        int defenceStrength = defender.getDefensiveStrength() * defRoll;

        boolean attackerWins = attackStrength > defenceStrength;
        if(attackerWins)
            winnerStrategy.addAttackWin(attacker.getOwner());

        return attackerWins;
    }
}
