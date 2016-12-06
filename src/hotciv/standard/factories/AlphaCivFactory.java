package src.hotciv.standard.factories;

import src.hotciv.framework.*;
import src.hotciv.standard.strategies.*;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

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

    @Override
    public BattleStrategy createBattleStrategy() {
        return new AttackerWinsStrategy();
    }

    @Override
    public PerformActionStrategy createPerformActionStrategy() {
        return (p, units, cities) -> {};
    }

    @Override
    public UnitFactory createUnitFactory() {
        return new StandardUnitFactory();
    }

}
