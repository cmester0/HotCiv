package src.hotciv.framework;

import java.util.Map;

/**
 * Created by Lasse Letager Hansen on 30-11-2016.
 */
public interface WinnerStrategy {
    Player getWinner();
    void setAge(int age);
    void checkWorld(Map<Position, Unit> units, Map<Position, City> cities, Map<Position, Tile> tiles);
    void addAttackWin(Player p);
}
