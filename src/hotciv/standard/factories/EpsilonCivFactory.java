package src.hotciv.standard.factories;

import src.hotciv.framework.*;

import src.hotciv.standard.strategies.*;

/**
 * Created by Lasse Letager Hansen on 30-11-2016.
 */
public class EpsilonCivFactory implements CivFactory {
    private AlphaCivFactory alphaCivFactory;

    public EpsilonCivFactory(){
        alphaCivFactory = new AlphaCivFactory();
    }

    @Override
    public AgeingStrategy createAgeingStrategy() {
        return alphaCivFactory.createAgeingStrategy();
    }

    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new Winning3BattlesWinsStrategy();
    }

    @Override
    public StartingLayoutStrategy createStartingLayoutStrategy() {
        return alphaCivFactory.createStartingLayoutStrategy();
    }

    @Override
    public BattleStrategy createBattleStrategy() {
        return new FightBattleStrategy();
    }

    @Override
    public PerformActionStrategy createPerformActionStrategy() {
        return alphaCivFactory.createPerformActionStrategy();
    }

    @Override
    public UnitFactory createUnitFactory() {
        return alphaCivFactory.createUnitFactory();
    }
}
