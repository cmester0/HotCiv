package src.hotciv.standard;

import src.hotciv.framework.*;

/**
 * Created by Lasse Letager Hansen on 30-11-2016.
 */
public class AlphaCivFactory implements CivFactory {

    @Override
    public AgeingStrategy createAgeingStrategy() {
        return new StandardAging();
    }

    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new TimeBasedWinningStrategy();
    }

    @Override
    public StartingLayoutStrategy createStartingLayoutStrategy() {
        return new StandardStartingLayoutStrategy();
    }



}
