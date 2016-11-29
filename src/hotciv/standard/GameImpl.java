package src.hotciv.standard;

import src.hotciv.framework.*;

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

    private Player playerInTurn;

    private Civ civ;

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
    }

    public Tile getTileAt(Position p) {
        return tiles.get(p);
    }

    public Unit getUnitAt(Position p) {
        Unit u = units.get(p);

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
        if(getTileAt(to).getTypeString() == GameConstants.MOUNTAINS) return false;
        if(getTileAt(to).getTypeString() == GameConstants.OCEANS) return false;

        if(toUnit == null || civ.outcomeOfBattle(fromUnit, toUnit)){
            units.put(to, new StandardUnit(fromUnit.getTypeString(), fromUnit.getOwner(), 0));
        }

        units.remove(from);

        civ.update();

        return units.get(to) != null;
    }

    private static final Position[] possibleRedPositions = new Position[]{
            new Position(1,1),
            new Position(0,1),
            new Position(0,2),
            new Position(1,2),
            new Position(2,1),
            new Position(2,0),
            new Position(0,0)
    };
    private static final Position[] possibleBluePositions = new Position[]{
            new Position(4,1),
            new Position(3,1),
            new Position(4,2),
            new Position(5,2),
            new Position(5,1),
            new Position(5,0),
            new Position(4,0)
    };
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
            int newProd = production.get(c.getOwner()) + 6;

            AtomicInteger ai = new AtomicInteger(newProd);
            Unit u = UnitFactory.createUnit(c, ai);
            newProd = ai.get();

            if(u != null){
                Position p = getAvailablePosition(c.getOwner());
                units.put(p, u);
            }

            production.put(c.getOwner(), newProd);
        }

        HashMap<Position, Unit> unitsTemp = new HashMap<Position, Unit>();
        for(Map.Entry<Position, Unit> e : units.entrySet()){
            unitsTemp.put(e.getKey(), new StandardUnit(e.getValue().getTypeString(), e.getValue().getOwner()));
        }
        units.clear();
        units.putAll(unitsTemp);

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
    }

    public void endOfTurn() {
        if(playerInTurn == Player.RED)
            playerInTurn = Player.BLUE;
        else {
            playerInTurn = Player.RED;
            endOfRound();
        }
    }

    @Override
    public void changeWorkforceFocusInCityAt(Position p, String balance) {
        City c = cities.get(p);
        cities.put(p, new StandardCity(c.getOwner(), c.getProduction(), balance, c.getSize()));
    }

    public void changeProductionInCityAt(Position p, String unitType) {
        Player cityOwner = cities.get(p).getOwner();
        cities.put(p, new StandardCity(cityOwner, unitType));
    }

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

}
