package src.hotciv.framework;

import src.hotciv.standard.GameImpl;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ssodelta on 11/21/16.
 */
public interface Civ {
    int getNextAge(int currentAge);

    Player getWinner();

    void setup(Map<Position, Unit> units, Map<Position, City> cities, Map<Position, Tile> tiles);

    /**
     * Updates the board according to the civ
     */
    void update(Map<Position, Unit> units, Map<Position, City> cities, Map<Position, Tile> tiles);


    boolean outcomeOfBattle(Unit attacker, Unit defender);

    /** perform the action associated with the unit at position p.
     * Example: a settler unit may create a new city at its location.
     * Precondition: there is a unit at location 'p'.
     * @param p the position of a unit that must perform its action.
     * Nothing happens in case the unit has no associated action.
     */
    void performUnitActionAt( Position p );

    Unit createUnit(City c, AtomicInteger ai);

    Position[] getPossibleBluePositions();
    Position[] getPossibleRedPositions();
}
