package src.hotciv.standard;

import src.hotciv.framework.*;

import java.util.HashMap;

/** Skeleton implementation of HotCiv.
 
   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Department of Computer Science
     Aarhus University
   
   Please visit http://www.baerbak.com/ for further information.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/

public class GameImpl implements Game {
  private int age = -4000, production = 0;

  private HashMap<Position, City> cities;

  public GameImpl(){
    cities = new HashMap<Position, City>();

    cities.put(new Position(1,1), new StandardCity(new Position(1,1)));
    cities.put(new Position(4,1), new StandardCity(new Position(4,1)));
  }

  public Tile getTileAt( Position p ) {
    if(p.getColumn() == 0 && p.getRow() == 1) {
      return new StandardTile(GameConstants.OCEANS);
    } else if(p.getColumn() == 1 && p.getRow() == 0){
      return new StandardTile(GameConstants.HILLS);
    } else if(p.getColumn() == 2 && p.getRow() == 2) {
      return new StandardTile(GameConstants.MOUNTAINS);
    } else {
      return new StandardTile(GameConstants.PLAINS);
    }
  }
  public Unit getUnitAt( Position p ) { return null; }

  public City getCityAt( Position p ) {
    return cities.get(p);
  }


  public Player getPlayerInTurn() { return Player.RED; }
  public Player getWinner() { return Player.RED; }
  public int getAge() {
    return age;
  }
  public boolean moveUnit( Position from, Position to ) {
    return false;
  }
  public void endOfTurn() {
    age += 100;
    production=(production+6)%(cities.get(new Position(1,1)).getProduction().equals(GameConstants.SETTLER)?30:10);
  }
  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}
  public void changeProductionInCityAt( Position p, String unitType ) {
    cities.put(p, new StandardCity(p, unitType));
  }
  public void performUnitActionAt( Position p ) {}

  @Override
  public int getProductionAmountOfCity(City c) {
    return production;
  }
}
