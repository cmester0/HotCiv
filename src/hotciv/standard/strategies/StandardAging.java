package src.hotciv.standard.strategies;

import src.hotciv.framework.AgeingStrategy;

/**
 * Created by Lasse Letager Hansen on 30-11-2016.
 */
public class StandardAging implements AgeingStrategy {
    @Override
    public int getNextAge(int currentAge) {
        return currentAge+100;
    }
}
