package src.hotciv.framework;

import src.hotciv.standard.GameImpl;

import java.util.Map;

/**
 * Created by ssodelta on 11/21/16.
 */
public interface Civ {
    public int getNextAge(int currentAge);

    public Player getWinner();

    /** perform the action associated with the unit at position p.
     * Example: a settler unit may create a new city at its location.
     * Precondition: there is a unit at location 'p'.
     * @param p the position of a unit that must perform its action.
     * Nothing happens in case the unit has no associated action.
     */
    public void performUnitActionAt( Position p );

    public void setup(Map<Position, Unit> units, Map<Position, City> cities, Map<Position, Tile> tiles);

    /**
     * Updates the board according to the civ
     */
    public void update();
}
