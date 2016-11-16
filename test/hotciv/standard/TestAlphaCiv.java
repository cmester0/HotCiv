package test.hotciv.standard;

import src.hotciv.framework.*;

import src.hotciv.standard.GameImpl;
import org.junit.*;
import src.hotciv.standard.StandardCity;

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

  private Position redPosition, bluePosition;

  /** Fixture for alphaciv testing. */
  @Before
  public void setUp() {
    game = new GameImpl();
    redPosition = (new Position(1,1));
    bluePosition = (new Position(4,1));
  }

  private void endRound(int n){
    for(int i=0; i<2*n; i++)
      game.endOfTurn();
  }

  // FRS p. 455 states that 'Red is the first player to take a turn'.
  @Test
  public void shouldBeRedAsStartingPlayer() {
    assertThat(game.getPlayerInTurn(), is(Player.RED));
  }

  // There is a red city at (1,1)
  @Test
  public void redCityAt1comma1(){
    assertThat(game.getCityAt(redPosition).getOwner(), is(Player.RED));
  }

  @Test
  public void blueCityIsAt4comma1(){
    assertThat(game.getCityAt(bluePosition).getOwner(), is(Player.BLUE));
  }

  // There is ocean at (1,0)
  @Test
  public void oceanAt1comma0(){
    assertThat(game.getTileAt(new Position(1,0)).getTypeString(), is(GameConstants.OCEANS));
  }

  // There is a Mountain at (2,2)
  @Test
  public void mountainAt2Comma2(){
    assertThat(game.getTileAt(new Position(2,2)).getTypeString(), is(GameConstants.MOUNTAINS));
  }

  // There is a hill at (0,1)
  @Test
  public void hillAt0Comma1(){
    assertThat(game.getTileAt(new Position(0,1)).getTypeString(), is(GameConstants.HILLS));
  }

  //The board consists mainly of Plains
  @Test
  public void gameIsMostlyPlains(){
    assertThat(game.getTileAt(new Position(0,0)).getTypeString(), is(GameConstants.PLAINS));

    assertThat(game.getTileAt(new Position(2,0)).getTypeString(), is(GameConstants.PLAINS));

    assertThat(game.getTileAt(new Position(2,1)).getTypeString(), is(GameConstants.PLAINS));

    assertThat(game.getTileAt(new Position(8,6)).getTypeString(), is(GameConstants.PLAINS));

    assertThat(game.getTileAt(new Position(15,15)).getTypeString(), is(GameConstants.PLAINS));
  }

  @Test
  public void startingYearIs4000BC(){
    assertThat(game.getAge(), is(-4000));
  }

  @Test
  public void oneRoundIs3900BC(){
    endRound(1);
    assertThat(game.getAge(), is(-3900));
  }

  @Test
  public void twoRoundsIs3800BC(){
    endRound(2);
    assertThat(game.getAge(), is(-3800));
  }

  @Test
  public void threeRoundsIs3700BC(){
    endRound(3);
    assertThat(game.getAge(), is(-3700));
  }

  @Test
  public void redWinsAtAge3000BC(){
    endRound(10);
    assertThat(game.getWinner(), is(Player.RED));
  }

  @Test
  public void cityHas1Population(){
    City city = new StandardCity(null);

    assertThat(city.getSize(), is(1));
  }

  @Test
  public void citiesProduceArchersAtFirst(){
    City city = new StandardCity(null);

    assertThat(city.getProduction(), is(notNullValue()));
    assertThat(city.getProduction(), is(GameConstants.ARCHER));
  }

  @Test
  public void changingProductionToSettlersOfRedCityActuallyChangesIt(){
    game.changeProductionInCityAt(new Position(1,1), GameConstants.SETTLER);

    assertThat(game.getCityAt(new Position(1,1)).getProduction(), is(GameConstants.SETTLER));
  }

  @Test
  public void redCityHas0ProductionAtStart(){
    int prod  = game.getProductionAmountOfCity(game.getCityAt(redPosition));

    assertThat(prod, is(0));
  }

  @Test
  public void redCityHas6ProductionAfter1Round(){
    endRound(1);

    int prod  = game.getProductionAmountOfCity(game.getCityAt(redPosition));
    assertThat(prod, is(6));
  }

  //6 + 6 - 1 archer (10) = 2 production
  @Test
  public void redCityHas2ProductionAfter2RoundsIfProducingArchers(){
    endRound(2);

    int prod  = game.getProductionAmountOfCity(game.getCityAt(redPosition));
    assertThat(prod, is(6*2-10)); //6*2-10 = 2
  }

  //6*4 = 24 - 2 archers = 4
  @Test
  public void redCityHas4ProductionAfter4RoundsIfProducingArchers(){
    endRound(4);

    int prod  = game.getProductionAmountOfCity(game.getCityAt(redPosition));
    assertThat(prod, is(4));
  }

  @Test
  public void redCityHas24ProductionAfter4RoundsIfProducingSettlers(){
    game.changeProductionInCityAt(new Position(1,1), GameConstants.SETTLER);
    endRound(4);

    int prod  = game.getProductionAmountOfCity(game.getCityAt(redPosition));
    assertThat(prod, is(24));
  }

  @Test
  public void redCityHas4ProductionAfter4RoundsIfBlueProducingSettlers(){
    game.changeProductionInCityAt(new Position(4,1), GameConstants.SETTLER);
    endRound(4);

    int prod  = game.getProductionAmountOfCity(game.getCityAt(redPosition));
    assertThat(prod, is(4));
  }

  @Test
  public void blueCityHas4ProductionAfter4RoundsIfRedProducingSettlers(){
    game.changeProductionInCityAt(new Position(1,1), GameConstants.SETTLER);
    endRound(4);

    int prod  = game.getProductionAmountOfCity(game.getCityAt(bluePosition));
    assertThat(prod, is(4));
  }

  @Test
  public void at2RoundsRedProducesArcherAt1comma1(){
    endRound(2);
    assertThat(game.getUnitAt(new Position(1,1)).getTypeString(), is(GameConstants.ARCHER));
  }

  @Test
  public void redChangeProductionToSettlerWait5Rounds(){
    game.changeProductionInCityAt(redPosition,GameConstants.SETTLER);
    endRound(5);
    assertThat(game.getUnitAt(new Position(1,1)).getTypeString(), is(GameConstants.SETTLER));
  }

  @Test
  public void at6RoundsRedProduces3ArchersAt1comma1And0comma2And1comma2(){
    endRound(6);

    assertThat(game.getUnitAt(new Position(1,1)).getTypeString(), is(GameConstants.ARCHER));
    assertThat(game.getUnitAt(new Position(0,2)).getTypeString(), is(GameConstants.ARCHER));
    assertThat(game.getUnitAt(new Position(1,2)).getTypeString(), is(GameConstants.ARCHER));
  }

  @Test
  public void at3RoundsRedProduces1LegionAt1comma1(){
    game.changeProductionInCityAt(redPosition, GameConstants.LEGION);

    endRound(3);

    assertThat(game.getUnitAt(new Position(1,1)).getTypeString(), is(GameConstants.LEGION));
  }


  @Test
  public void at9RoundsRedProducesALotOfArchers(){
    endRound(9);

    assertThat(game.getUnitAt(new Position(1,1)).getTypeString(), is(GameConstants.ARCHER));
    assertThat(game.getUnitAt(new Position(0,2)).getTypeString(), is(GameConstants.ARCHER));
    assertThat(game.getUnitAt(new Position(1,2)).getTypeString(), is(GameConstants.ARCHER));
    assertThat(game.getUnitAt(new Position(2,1)).getTypeString(), is(GameConstants.ARCHER));
    assertThat(game.getUnitAt(new Position(0,0)).getTypeString(), is(GameConstants.ARCHER));
  }

  @Test
  public void at13RoundsRedProducesALotOfLegions(){
    game.changeProductionInCityAt(redPosition, GameConstants.LEGION);
    endRound(13);

    assertThat(game.getUnitAt(new Position(1,1)).getTypeString(), is(GameConstants.LEGION));
    assertThat(game.getUnitAt(new Position(0,2)).getTypeString(), is(GameConstants.LEGION));
    assertThat(game.getUnitAt(new Position(1,2)).getTypeString(), is(GameConstants.LEGION));
    assertThat(game.getUnitAt(new Position(2,1)).getTypeString(), is(GameConstants.LEGION));
    assertThat(game.getUnitAt(new Position(0,0)).getTypeString(), is(GameConstants.LEGION));
  }

  @Test
  public void at25RoundsRedProducesALotOfSettlers(){
    game.changeProductionInCityAt(redPosition, GameConstants.SETTLER);
    endRound(25);

    assertThat(game.getUnitAt(new Position(1,1)).getTypeString(), is(GameConstants.SETTLER));
    assertThat(game.getUnitAt(new Position(0,2)).getTypeString(), is(GameConstants.SETTLER));
    assertThat(game.getUnitAt(new Position(1,2)).getTypeString(), is(GameConstants.SETTLER));
    assertThat(game.getUnitAt(new Position(2,1)).getTypeString(), is(GameConstants.SETTLER));
    assertThat(game.getUnitAt(new Position(0,0)).getTypeString(), is(GameConstants.SETTLER));
  }

  @Test
  public void at2RoundsBlueProducesAnArcherAt4comma1(){
    endRound(2);

    assertThat(game.getUnitAt(new Position(4,1)).getTypeString(), is(GameConstants.ARCHER));
  }

  @Test
  public void at10RoundsBlueProducesALotOfBlueArchers(){
    endRound(10);

    assertThat(game.getUnitAt(new Position(4,1)).getTypeString(), is(GameConstants.ARCHER));
    assertThat(game.getUnitAt(new Position(4,1)).getOwner(),      is(Player.BLUE));

    assertThat(game.getUnitAt(new Position(3,1)).getTypeString(), is(GameConstants.ARCHER));
    assertThat(game.getUnitAt(new Position(3,1)).getOwner(),      is(Player.BLUE));

    assertThat(game.getUnitAt(new Position(4,2)).getTypeString(), is(GameConstants.ARCHER));
    assertThat(game.getUnitAt(new Position(4,2)).getOwner(),      is(Player.BLUE));

    assertThat(game.getUnitAt(new Position(5,2)).getTypeString(), is(GameConstants.ARCHER));
    assertThat(game.getUnitAt(new Position(5,2)).getOwner(),      is(Player.BLUE));

    assertThat(game.getUnitAt(new Position(5,1)).getTypeString(), is(GameConstants.ARCHER));
    assertThat(game.getUnitAt(new Position(5,1)).getOwner(),      is(Player.BLUE));

    assertThat(game.getUnitAt(new Position(5,0)).getTypeString(), is(GameConstants.ARCHER));
    assertThat(game.getUnitAt(new Position(5,0)).getOwner(),      is(Player.BLUE));

  }

  @Test
  public void redHasARedArcherAt2comma0(){
    Unit u = game.getUnitAt(new Position(2,0));
    assertThat(u.getTypeString(), is(GameConstants.ARCHER));
    assertThat(u.getOwner(),      is(Player.RED));
  }

  @Test
  public void blueHasALegionAt3comma2(){
    Unit u = game.getUnitAt(new Position(3,2));
    assertThat(u.getTypeString(), is(GameConstants.LEGION));
    assertThat(u.getOwner(),      is(Player.BLUE));
  }

  @Test
  public void blueHasASettlerAt4comma3(){
    Unit u = game.getUnitAt(new Position(4,3));
    assertThat(u.getTypeString(), is(GameConstants.SETTLER));
    assertThat(u.getOwner(),      is(Player.BLUE));
  }

  @Test
  public void blueIsAfterRed(){
    game.endOfTurn();
    assertThat(game.getPlayerInTurn(), is(Player.BLUE));
  }

  @Test
  public void redIsAfterBlue(){
    game.endOfTurn();
    game.endOfTurn();
    assertThat(game.getPlayerInTurn(), is(Player.RED));
  }

  @Test
  public void redCanMoveArcherOneDown(){
    assertTrue(game.moveUnit(new Position(2,0), new Position(3,0)));
  }

  @Test
  public void redCannotMoveNonExistingUnit(){
    assertFalse(game.moveUnit(new Position(2,1), new Position(2,0)));
  }

  @Test
  public void redMoveArcherActuallyMovesArcher(){
    game.moveUnit(new Position(2,0), new Position(3,0));
    assertThat(game.getUnitAt(new Position(3,0)),is(notNullValue()));
    assertThat(game.getUnitAt(new Position(2,0)),is(nullValue()));
  }

  @Test
  public void cannotMoveUnitTwiceOneTurn(){
    game.moveUnit(new Position(2,0), new Position(3,0));
    assertFalse(game.moveUnit(new Position(3,0), new Position(2,0)));
  }

  @Test
  public void moveCountResetsAfterARound(){
    game.moveUnit(new Position(2,0), new Position(3,0));
    endRound(1);
    assertThat(game.getUnitAt(new Position(3,0)).getMoveCount(), is(1));
  }

  @Test
  public void redCannotMoveBlueUnits(){
    assertFalse(game.moveUnit(new Position(3,2), new Position(3,3)));
  }

  @Test
  public void redCannotMoveUnitOntoAnotherUnit(){
    endRound(2);
    assertFalse(game.moveUnit(new Position(1,1),new Position(2,0)));
  }

  @Test
  public void redCannotMoveUnitOntoMountain(){
    endRound(2);
    assertFalse(game.moveUnit(new Position(1,1), new Position(0,1)));
  }


}
