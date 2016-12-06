package src.hotciv.framework;

import src.hotciv.standard.GameImpl;

import java.util.Map;

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

    /** change the type of unit a city will produce next.
     * Precondition: there is a city at location 'p'.
     * Predondition: the unit type is a valid type.
     * @param p the position of the city whose production
     * should be changed.
     * @param unitType a string defining the type of unit that the
     * city should produce next.
     */
    void changeProductionInCityAt( Position p, String unitType );


}
