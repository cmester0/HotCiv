package src.hotciv.standard.factories.civ;

import src.hotciv.framework.*;
import src.hotciv.standard.factories.BombUnitFactory;
import src.hotciv.standard.factories.ComplexPositionFactory;
import src.hotciv.standard.factories.StandardPositionFactory;
import src.hotciv.standard.strategies.*;

/**
 * Created by Lasse Letager Hansen on 05-12-2016.
 */
public class SemiCivFactory implements CivFactory {

    @Override
    public AgeingStrategy createAgeingStrategy() {
        return new SlowingAgeingStrategy();
    }

    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new ConquerCityAndWinning3BattlesStrategy();
    }

    @Override
    public StartingLayoutStrategy createStartingLayoutStrategy() {
        return new ComplexStartingLayoutStrategy();
    }

    @Override
    public BattleStrategy createBattleStrategy() {
        return new FightBattleStrategy();
    }

    @Override
    public PerformActionStrategy createPerformActionStrategy() {
        return new BombUnitActionStrategy();
    }

    @Override
    public UnitFactory createUnitFactory() {
        return new BombUnitFactory();
    }

    @Override
    public PositionFactory createPositionFactory() {
        return new ComplexPositionFactory();
    }
}
