package src.hotciv.standard.factories.civ;

import src.hotciv.framework.*;
import src.hotciv.standard.factories.BombUnitFactory;
import src.hotciv.standard.strategies.*;

/**
 * Created by Lasse Letager Hansen on 05-12-2016.
 */
public class ThetaCivFactory implements CivFactory {
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
        return new BombUnitActionStrategy();
    }

    @Override
    public UnitFactory createUnitFactory() {
        return new BombUnitFactory();
    }
}
