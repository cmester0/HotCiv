package src.hotciv.standard;

import src.hotciv.framework.Player;
import src.hotciv.framework.WinnerStrategy;

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

}
