package src.hotciv.standard;

import src.hotciv.framework.AgeingStrategy;
import src.hotciv.framework.Player;

/**
 * Created by Lasse Letager Hansen on 30-11-2016.
 */
public class StandardAging implements AgeingStrategy {
    @Override
    public int getNextAge(int currentAge) {
        return currentAge+100;
    }
}
