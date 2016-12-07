package src.hotciv.standard.factories.civ;

import src.hotciv.framework.*;

import src.hotciv.standard.factories.StandardUnitFactory;
import src.hotciv.standard.strategies.*;

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
        return new FightBattleStrategy();
    }

    @Override
    public PerformActionStrategy createPerformActionStrategy() {
        return new SimpleUnitActionStrategy();
    }

    @Override
    public UnitFactory createUnitFactory() {
        return new StandardUnitFactory();
    }
}
