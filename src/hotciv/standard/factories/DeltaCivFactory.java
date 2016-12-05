package src.hotciv.standard.factories;

import src.hotciv.framework.*;
import src.hotciv.standard.strategies.*;

/**
 * Created by Lasse Letager Hansen on 30-11-2016.
 */
public class DeltaCivFactory implements CivFactory {
    private AlphaCivFactory alphaCivFactory;

    public DeltaCivFactory(){
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
        return new ComplexStartingLayoutStrategy();
    }

    @Override
    public BattleStrategy createBattleStrategy() {
        return alphaCivFactory.createBattleStrategy();
    }
}
