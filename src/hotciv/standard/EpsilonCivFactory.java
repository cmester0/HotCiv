package src.hotciv.standard;

import src.hotciv.framework.*;

import java.sql.Time;

/**
 * Created by Lasse Letager Hansen on 30-11-2016.
 */
public class EpsilonCivFactory implements CivFactory {
    @Override
    public AgeingStrategy createAgeingStrategy() {
        return new StandardAging();
    }

    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new Winning3BattlesWinsStrategy();
    }

    @Override
    public StartingLayoutStrategy createStartingLayoutStrategy() {
        return new StandardStartingLayoutStrategy();
    }

    @Override
    public BattleStrategy createBattleStrategy() {
        return null;
    }
}
