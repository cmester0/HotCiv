package src.hotciv.standard;

import src.hotciv.framework.*;
import src.hotciv.standard.factories.StandardPositionFactory;
import src.hotciv.standard.factories.StandardUnitFactory;
import src.hotciv.standard.strategies.AttackerWinsStrategy;
import src.hotciv.standard.strategies.StandardAging;
import src.hotciv.standard.strategies.StandardStartingLayoutStrategy;
import src.hotciv.standard.strategies.TimeBasedWinningStrategy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Skeleton implementation of HotCiv.
 * <p>
 * This source code is from the book
 * "Flexible, Reliable Software:
 * Using Patterns and Agile Development"
 * published 2010 by CRC Press.
 * Author:
 * Henrik B Christensen
 * Department of Computer Science
 * Aarhus University
 * <p>
 * Please visit http://www.baerbak.com/ for further information.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class GameImpl implements Game {
    private int age = -4000;

    private HashMap<Position, Unit> units;
    private HashMap<Position, City> cities;
    private HashMap<Position, Tile> tiles;
    private HashMap<Player, Integer> production;
    private HashMap<Position, Integer> food;

    private GameObserver gameObserver;

    private Player playerInTurn;

    private Civ civ;

    private Position[] possibleBluePositions;
    private Position[] possibleRedPositions;

    public GameImpl(Civ civ) {
        production = new HashMap<Player, Integer>();
        cities     = new HashMap<Position, City>();
        units      = new HashMap<Position, Unit>();
        tiles      = new HashMap<Position, Tile>();
        food       = new HashMap<Position, Integer>();

        this.civ = civ;

        civ.setup(units, cities, tiles);

        playerInTurn = Player.RED;

        for (Player p : Player.values())
            production.put(p, 0);

        for(Position cityPos : cities.keySet())
            food.put(cityPos, 0);

        possibleBluePositions  = civ.getPossibleBluePositions();
        possibleRedPositions = civ.getPossibleRedPositions();
    }

    public Tile getTileAt(Position p) {
        return tiles.get(p);
    }

    public Unit getUnitAt(Position p) {
        Unit u = units.get(p);

        // should only change for one Civ, not all
        if(u != null) {
            Tile t = getTileAt(p);

            int tileMultiplier;
            switch (t.getTypeString()){
                case GameConstants.FOREST:
                    tileMultiplier = 2;
                    break;
                case GameConstants.HILLS:
                    tileMultiplier = 2;
                    break;
                default:
                    tileMultiplier = 1;
                    break;
            }

            int count = -1;
            for(int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    Position adjacent = new Position(p.getRow() + i, p.getColumn() + j);
                    Unit adjacentUnit = units.get(adjacent);
                    if(adjacentUnit != null && adjacentUnit.getOwner().equals(u.getOwner()))
                        count++;
                }
            }

            units.put(p, new StandardUnit(u, cities.get(p) != null, tileMultiplier, count));
            u = units.get(p);
        }

        return u;
    }

    public City getCityAt(Position p) {
        return cities.get(p);
    }

    public Player getPlayerInTurn() {
        return playerInTurn;
    }

    public Player getWinner() {
        return civ.getWinner();
    }

    public int getAge() {
        return age;
    }

    public boolean moveUnit(Position from, Position to) {
        Unit fromUnit = getUnitAt(from);
        Unit toUnit = getUnitAt(to);

        if(!(units.containsKey(from) && units.get(from).getMoveCount() > 0)) return false;
        if(fromUnit.getOwner() != playerInTurn) return false;
        if(toUnit != null && toUnit.getOwner() == playerInTurn) return false;
        if(getTileAt(to).getTypeString().equals(GameConstants.MOUNTAINS)) return false;
        if(getTileAt(to).getTypeString().equals(GameConstants.OCEANS)) return false;

        if(Math.max(Math.abs(from.getColumn() - to.getColumn()), Math.abs(from.getRow() - to.getRow())) > 1){
            return false;
    }

        if(toUnit == null || civ.outcomeOfBattle(fromUnit, toUnit)){
            units.put(to, new StandardUnit(fromUnit.getTypeString(), fromUnit.getOwner(), 0));
        }

        units.remove(from);

        if(gameObserver != null) {
            gameObserver.worldChangedAt(from);
            gameObserver.worldChangedAt(to);
        }

        civ.update(units, cities, tiles);

        return units.get(to) != null;
    }

    private Position getAvailablePosition(Player player){
        Position[] pos = possibleRedPositions;
        if(player==Player.BLUE)
            pos = possibleBluePositions;

        for(Position p : pos)
            if (getUnitAt(p) == null)
                return p;

        return null;
    }

    public void endOfRound(){
        age = civ.getNextAge(age);

        for (City c : cities.values()) {
            if(!c.getWorkforceFocus().equals(GameConstants.productionFocus)) continue;
            int newProd = production.get(c.getOwner()) + 6; // change to production based on terrain here

            AtomicInteger ai = new AtomicInteger(newProd);
            Unit u = civ.createUnit(c, ai);
            newProd = ai.get();

            if(u != null){
                Position p = getAvailablePosition(c.getOwner());
                units.put(p, u);

                if(gameObserver != null) {
                    gameObserver.worldChangedAt(p);
                }
            }

            production.put(c.getOwner(), newProd);
        }

        // Should be only for EtaCiv
        HashMap<Position, City> cityTemp = new HashMap<Position, City>();
        for(Map.Entry<Position, City> e : cities.entrySet()) {
            City c = e.getValue();
            Position p = e.getKey();

            if(!c.getWorkforceFocus().equals(GameConstants.foodFocus)) continue;

            int newFood = food.get(p);
            newFood++;

            boolean shouldGrow = newFood >= c.getSize() * 3 + 5;
            boolean smallEnoughToGrow = c.getSize() < 9;

            if(shouldGrow && smallEnoughToGrow){
                food.put(p, 0);
                cityTemp.put(p, new StandardCity(c.getOwner(), c.getProduction(), c.getWorkforceFocus(), c.getSize() + 1));
            }
            else {
                food.put(p, newFood);
            }
        }

        cities.putAll(cityTemp);

        HashMap<Position, Unit> unitsTemp = new HashMap<Position, Unit>();
        for(Map.Entry<Position, Unit> e : units.entrySet()){
            unitsTemp.put(e.getKey(), new StandardUnit(e.getValue().getTypeString(), e.getValue().getOwner()));
        }
        units.clear();
        units.putAll(unitsTemp);

    }

    public void endOfTurn() {
        if(playerInTurn == Player.RED)
            playerInTurn = Player.BLUE;
        else {
            playerInTurn = Player.RED;
            endOfRound();
        }

        if(gameObserver != null){
            gameObserver.turnEnds(playerInTurn, age);
        }
    }

    @Override
    public void changeWorkforceFocusInCityAt(Position p, String balance) {
        City c = cities.get(p);
        cities.put(p, new StandardCity(c.getOwner(), c.getProduction(), balance, c.getSize()));
    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType) {
        Player cityOwner = cities.get(p).getOwner();
        cities.put(p, new StandardCity(cityOwner, unitType));
    }

    @Override
    public void performUnitActionAt(Position p) {
        civ.performUnitActionAt(p);
    }

    @Override
    public int getProductionAmountOfCity(City c) {
        return production.get(c.getOwner());
    }

    @Override
    public int getFoodAmountOfCityAt(Position p) {
        return food.get(p);
    }

    @Override
    public void addObserver(GameObserver observer) {
        gameObserver = observer;
    }

    @Override
    public void setTileFocus(Position position) {
        gameObserver.tileFocusChangedAt(position);
    }

    public static class GameBuilder {
        private AgeingStrategy ageingStrategy;
        private BattleStrategy battleStrategy;
        private PerformActionStrategy performActionStrategy;
        private StartingLayoutStrategy startingLayoutStrategy;
        private WinnerStrategy winnerStrategy;
        private UnitFactory unitFactory;
        private PositionFactory positionFactory;

        public GameBuilder(){
            ageingStrategy = new StandardAging();
            battleStrategy = new AttackerWinsStrategy();
            performActionStrategy  = (p, units, cities) -> {};
            startingLayoutStrategy = new StandardStartingLayoutStrategy();
            winnerStrategy = new TimeBasedWinningStrategy();
            unitFactory = new StandardUnitFactory();
            positionFactory = new StandardPositionFactory();
        }

        public GameBuilder setAgeingStrategy(AgeingStrategy ageingStrategy){
            this.ageingStrategy = ageingStrategy;
            return this;
        }

        public GameBuilder setBattleStrategy(BattleStrategy battleStrategy){
            this.battleStrategy = battleStrategy;
            return this;
        }

        public GameBuilder setPerformActionStrategy(PerformActionStrategy performActionStrategy){
            this.performActionStrategy = performActionStrategy;
            return this;
        }

        public GameBuilder setStartingLayoutStrategy(StartingLayoutStrategy startingLayoutStrategy){
            this.startingLayoutStrategy = startingLayoutStrategy;
            return this;
        }

        public GameBuilder setWinnerStrategy(WinnerStrategy winnerStrategy){
            this.winnerStrategy = winnerStrategy;
            return this;
        }

        public GameBuilder setUnitFactory(UnitFactory unitFactory){
            this.unitFactory = unitFactory;
            return this;
        }

        public GameBuilder setPositionFactory(PositionFactory positionFactory){
            this.positionFactory = positionFactory;
            return this;
        }

        public Game build(){
            final AgeingStrategy ageingStrategyBuild = ageingStrategy;
            final WinnerStrategy winnerStrategyBuild = winnerStrategy;
            final StartingLayoutStrategy startingLayoutStrategyBuild = startingLayoutStrategy;
            final BattleStrategy battleStrategyBuild = battleStrategy;
            final PerformActionStrategy performActionStrategyBuild = performActionStrategy;
            final UnitFactory unitFactoryBuild = unitFactory;
            final PositionFactory positionFactoryBuild = positionFactory;

            Game game = new GameImpl(new AbstractCiv(new CivFactory() {
                @Override
                public AgeingStrategy createAgeingStrategy() {
                    return ageingStrategyBuild;
                }

                @Override
                public WinnerStrategy createWinnerStrategy() {
                    return winnerStrategyBuild;
                }

                @Override
                public StartingLayoutStrategy createStartingLayoutStrategy() {
                    return startingLayoutStrategyBuild ;
                }

                @Override
                public BattleStrategy createBattleStrategy() {
                    return battleStrategyBuild;
                }

                @Override
                public PerformActionStrategy createPerformActionStrategy() {
                    return performActionStrategyBuild;
                }

                @Override
                public UnitFactory createUnitFactory() {
                    return unitFactoryBuild;
                }

                @Override
                public PositionFactory createPositionFactory() {
                    return positionFactoryBuild;
                }

            }));

            return game;
        }
    }

}

