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
}
