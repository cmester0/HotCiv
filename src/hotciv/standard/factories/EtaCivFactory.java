package src.hotciv.standard.factories;

import src.hotciv.framework.*;

/**
 * Created by Lasse Letager Hansen on 30-11-2016.
 */
public class EtaCivFactory implements CivFactory{
    private AlphaCivFactory alphaCivFactory;
    public EtaCivFactory(){
        alphaCivFactory = new AlphaCivFactory();
    }

    @Override
    public AgeingStrategy createAgeingStrategy() {
        return alphaCivFactory.createAgeingStrategy();
    }

    @Override
    public WinnerStrategy createWinnerStrategy() {
        return alphaCivFactory.createWinnerStrategy();
    }

    @Override
    public StartingLayoutStrategy createStartingLayoutStrategy() {
        return alphaCivFactory.createStartingLayoutStrategy();
    }

    @Override
    public BattleStrategy createBattleStrategy() {
        return alphaCivFactory.createBattleStrategy();
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
