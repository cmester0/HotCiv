package src.hotciv.standard;

import src.hotciv.framework.*;

import java.util.Map;

/**
 * Created by Lasse Letager Hansen on 30-11-2016.
 */
public class TimeBasedWinningStrategy implements WinnerStrategy {
    Player winner;
    int age;

    public TimeBasedWinningStrategy(){
        winner = null;
    }

    @Override
    public Player getWinner() {
        return winner;
    }

    @Override
    public void setAge(int age) {
        if(age >= -3000) winner = Player.RED;
        this.age = age;
    }

    @Override
    public void checkWorld(Map<Position, Unit> units, Map<Position, City> cities, Map<Position, Tile> tiles) { }

    @Override
    public void addAttackWin(Player p) { }

}
