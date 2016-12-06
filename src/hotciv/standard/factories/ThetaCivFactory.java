package src.hotciv.standard.factories;

import src.hotciv.framework.*;
import src.hotciv.standard.strategies.BombUnitActionStrategy;

/**
 * Created by Lasse Letager Hansen on 05-12-2016.
 */
public class ThetaCivFactory implements CivFactory {
    GammaCivFactory gammaCivFactory;

    public ThetaCivFactory(){
        gammaCivFactory = new GammaCivFactory();
    }

    @Override
    public AgeingStrategy createAgeingStrategy() {
        return gammaCivFactory.createAgeingStrategy();
    }

    @Override
    public WinnerStrategy createWinnerStrategy() {
        return gammaCivFactory.createWinnerStrategy();
    }

    @Override
    public StartingLayoutStrategy createStartingLayoutStrategy() {
        return gammaCivFactory.createStartingLayoutStrategy();
    }

    @Override
    public BattleStrategy createBattleStrategy() {
        return gammaCivFactory.createBattleStrategy();
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
