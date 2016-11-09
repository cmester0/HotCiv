package test.hotciv.standard;

import src.hotciv.framework.*;

import src.hotciv.standard.GameImpl;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

/** Skeleton class for AlphaCiv test cases

    Updated Oct 2015 for using Hamcrest matchers

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
public class TestAlphaCiv {
  private Game game;

  /** Fixture for alphaciv testing. */
  @Before
  public void setUp() {
    game = new GameImpl();
  }

  // FRS p. 455 states that 'Red is the first player to take a turn'.
  @Test
  public void shouldBeRedAsStartingPlayer() {
    assertThat(game.getPlayerInTurn(), is(Player.RED));
  }

  // There is a red city at (1,1)
  @Test
  public void redCityAt1comma1(){
    assertThat(game.getCityAt(new Position(1,1)).getOwner(), is(Player.RED));
  }

  @Test
  public void blueCityIsAt4comma1(){
    assertThat(game.getCityAt(new Position(4,1)).getOwner(), is(Player.BLUE));
  }

  // There is ocean at (1,0)
  @Test
  public void oceanAt1comma0(){
    assertThat(game.getTileAt(new Position(1,0)).getTypeString(), is("Ocean"));
  }

  // There is a Mountain at (2,2)
  @Test
  public void mountainAt2Comma2(){
    assertThat(game.getTileAt(new Position(2,2)).getTypeString(), is("Mountain"));
  }

  // There is a hill at (0,1)
  @Test
  public void hillAt0Comma1(){
    assertThat(game.getTileAt(new Position(0,1)).getTypeString(), is("Hill"));
  }

  @Test
  public void startingYearIs4000BC(){
    assertThat(game.getAge(), is(-4000));
  }

  @Test
  public void oneRoundIs3900BC(){
    game.endOfTurn();
    assertThat(game.getAge(), is(-3900));
  }

  @Test
  public void twoRoundsIs3800BC(){
    game.endOfTurn();
    game.endOfTurn();
    assertThat(game.getAge(), is(-3800));
  }
}
