package src.hotciv.standard;

import src.hotciv.framework.*;

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
  private int age = -4000;

  public Tile getTileAt( Position p ) {
    if(p.getColumn() == 0) {
      return new OceanTile();
    } else if(p.getColumn() == 1){
      return new HillTile();
    } else {
      return new MountainTile();
    }
  }
  public Unit getUnitAt( Position p ) { return null; }

  public City getCityAt( Position p ) {
    return new StandardCity(p);
  }

  public Player getPlayerInTurn() { return Player.RED; }
  public Player getWinner() { return null; }
  public int getAge() {
    return age;
  }
  public boolean moveUnit( Position from, Position to ) {
    return false;
  }
  public void endOfTurn() {
    if(age == -3900){
      age = -3800;
    }
    else{
      age = -3900;
    }
  }
  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}
  public void changeProductionInCityAt( Position p, String unitType ) {}
  public void performUnitActionAt( Position p ) {}
}
