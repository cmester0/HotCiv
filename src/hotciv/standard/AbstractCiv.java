package src.hotciv.standard;

import src.hotciv.framework.*;
import src.hotciv.standard.strategies.ConquerCityWinsStrategy;
import src.hotciv.standard.strategies.Winning3BattlesWinsStrategy;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Created by Lasse Letager Hansen on 30-11-2016.
 */
public class AbstractCiv implements Civ {
    private AgeingStrategy ageingStrategy;
    private StartingLayoutStrategy startingLayoutStrategy;
    private BattleStrategy battleStrategy;
    private PerformActionStrategy performActionStrategy;
    private UnitFactory unitFactory;

    private Map<Position, Unit> units;
    private Map<Position, City> cities;

    private int redWins;
    private int blueWins;
    private Player winner;
    private int age;

    int rounds;

    String civType = "ZetaCiv";

    public AbstractCiv(CivFactory civFactory, String civType){
        ageingStrategy = civFactory.createAgeingStrategy();
        startingLayoutStrategy = civFactory.createStartingLayoutStrategy();
        battleStrategy = civFactory.createBattleStrategy();
        performActionStrategy = civFactory.createPerformActionStrategy();
        unitFactory = civFactory.createUnitFactory();

        rounds = 0;
        redWins = 0;
        blueWins = 0;

        this.civType = civType;
    }

    @Override
    public int getNextAge(int currentAge) {
        int nextAge = ageingStrategy.getNextAge(currentAge);
        if(civType.equals("AlphaCiv")
                || civType.equals("GammaCiv")
                || civType.equals("ThetaCiv")
                || civType.equals("EtaCiv")
                || civType.equals("DeltaCiv")){
            if(nextAge >= -3000) winner = Player.RED;
            this.age = nextAge;
        }
        if(civType.equals("ZetaCiv") && this.age != nextAge){
            rounds++;
            this.age = nextAge;
        }
        return nextAge;
    }

    @Override
    public Player getWinner() {
        if(civType.equals("ZetaCiv")
                || civType.equals("BetaCiv")
                || civType.equals("DeltaCiv")
                || civType.equals("AlphaCiv")
                || civType.equals("GammaCiv")
                || civType.equals("ThetaCiv")
                || civType.equals("EpsilonCiv")
                || civType.equals("SemiCiv")){

            return winner;
        }
        return null;
        // return winnerStrategy.getWinner();
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
        if((civType.equals("ZetaCiv") && rounds <= 20) || civType.equals("BetaCiv")) {
            for (Map.Entry<Position, City> c : cities.entrySet()) {
                Position cityPos = c.getKey();
                Unit unitAtCityPos = units.get(cityPos);

                if (unitAtCityPos != null) {
                    String unitType = unitAtCityPos.getTypeString();
                    if (unitType.equals(GameConstants.SETTLER)) continue;

                    Player unitOwner = unitAtCityPos.getOwner();
                    Player cityOwner = cities.get(cityPos).getOwner();

                    if (unitOwner != cityOwner) {
                        winner = unitOwner;
                        cities.put(cityPos, new StandardCity(unitOwner));
                    }
                }
            }
        }
    }

    @Override
    public boolean outcomeOfBattle(Unit attacker, Unit defender) {
        boolean attackerWins = battleStrategy.getOutcomeOfBattle(attacker, defender);

        if(attackerWins) {
            if(civType.equals("EpsilonCiv") || civType.equals("SemiCiv") || civType.equals("ZetaCiv") && rounds > 20) {
                switch (attacker.getOwner()) {
                    case RED:
                        redWins++;
                        break;
                    case BLUE:
                        blueWins++;
                        break;
                }

                if (redWins >= 3) {
                    winner = Player.RED;
                }
                if (blueWins >= 3) {
                    winner = Player.BLUE;
                }
            }
        }

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
}
