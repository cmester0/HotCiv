package src.hotciv.standard;

import src.hotciv.framework.*;

import java.util.HashMap;
import java.util.Map;

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
    private HashMap<Player, Integer> production;

    private Player playerInTurn;

    public GameImpl() {
        cities = new HashMap<Position, City>();
        production = new HashMap<Player, Integer>();
        units = new HashMap<Position, Unit>();

        cities.put(new Position(1, 1), new StandardCity(new Position(1, 1)));
        cities.put(new Position(4, 1), new StandardCity(new Position(4, 1)));

        units.put(new Position(2, 0), new StandardUnit(GameConstants.ARCHER, Player.RED));
        units.put(new Position(3, 2), new StandardUnit(GameConstants.LEGION, Player.BLUE));
        units.put(new Position(4, 3), new StandardUnit(GameConstants.SETTLER, Player.BLUE));

        playerInTurn = Player.RED;

        for (Player p : Player.values())
            production.put(p, 0);

    }

    public Tile getTileAt(Position p) {
        if (p.getColumn() == 0 && p.getRow() == 1) {
            return new StandardTile(GameConstants.OCEANS);
        } else if (p.getColumn() == 1 && p.getRow() == 0) {
            return new StandardTile(GameConstants.HILLS);
        } else if (p.getColumn() == 2 && p.getRow() == 2) {
            return new StandardTile(GameConstants.MOUNTAINS);
        } else {
            return new StandardTile(GameConstants.PLAINS);
        }
    }

    public Unit getUnitAt(Position p) {
        return units.get(p);
    }

    public City getCityAt(Position p) {
        return cities.get(p);
    }


    public Player getPlayerInTurn() {
        return playerInTurn;
    }

    public Player getWinner() {
        return Player.RED;
    }

    public int getAge() {
        return age;
    }

    public boolean moveUnit(Position from, Position to) {
        if(!(units.containsKey(from) && units.get(from).getMoveCount() > 0)) return false;
        if(getUnitAt(from).getOwner() != playerInTurn) return false;
        if(getUnitAt(to) != null && getUnitAt(to).getOwner() == playerInTurn) return false;
        if(getTileAt(to).getTypeString() != GameConstants.PLAINS) return false;

        Unit u = units.get(from);
        units.put(to, new StandardUnit(u.getTypeString(), u.getOwner(), 0));
        units.remove(from);
        return units.get(to) != null;
    }

    private static final Position[] possibleRedPositions = new Position[]{
            new Position(1,1),
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
        age += 100;

        for (City c : cities.values()) {
            int newProd = production.get(c.getOwner()) + 6;

            switch (c.getProduction()) {
                case GameConstants.SETTLER:
                    if (newProd >= 30)
                        units.put(getAvailablePosition(c.getOwner()), new StandardUnit(GameConstants.SETTLER, c.getOwner()));
                    newProd %= 30;
                    break;
                case GameConstants.LEGION:
                    if(newProd >= 15)
                        units.put(getAvailablePosition(c.getOwner()), new StandardUnit(GameConstants.LEGION, c.getOwner()));
                    newProd %= 15;
                    break;
                default:
                    if (newProd >= 10)
                        units.put(getAvailablePosition(c.getOwner()), new StandardUnit(GameConstants.ARCHER, c.getOwner()));
                    newProd %= 10;
                    break;
            }

            production.put(c.getOwner(), newProd);
        }

        HashMap<Position, Unit> unitsTemp = new HashMap<Position, Unit>();
        for(Map.Entry<Position, Unit> e : units.entrySet()){
            unitsTemp.put(e.getKey(), new StandardUnit(e.getValue().getTypeString(), e.getValue().getOwner()));
        }
        units = unitsTemp;
    }

    public void endOfTurn() {
        if(playerInTurn == Player.RED)
            playerInTurn = Player.BLUE;
        else {
            playerInTurn = Player.RED;
            endOfRound();
        }
    }

    public void changeWorkForceFocusInCityAt(Position p, String balance) {
    }

    public void changeProductionInCityAt(Position p, String unitType) {
        cities.put(p, new StandardCity(p, unitType));
    }

    public void performUnitActionAt(Position p) {
    }

    @Override
    public int getProductionAmountOfCity(City c) {
        return production.get(c.getOwner());
    }
}
