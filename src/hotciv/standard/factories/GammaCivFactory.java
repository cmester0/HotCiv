package src.hotciv.standard.factories;

import src.hotciv.framework.*;
import src.hotciv.standard.strategies.SimpleUnitActionStrategy;

/**
 * Created by Lasse Letager Hansen on 30-11-2016.
 */
public class GammaCivFactory implements CivFactory {
    private AlphaCivFactory alphaCivFactory;

    public GammaCivFactory(){
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
        return new SimpleUnitActionStrategy();
    }

    @Override
    public UnitFactory createUnitFactory() {
        return alphaCivFactory.createUnitFactory();
    }
}
