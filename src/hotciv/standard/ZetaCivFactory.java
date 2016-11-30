package src.hotciv.standard;

import src.hotciv.framework.*;

/**
 * Created by Lasse Letager Hansen on 30-11-2016.
 */
public class ZetaCivFactory implements CivFactory {
    AlphaCivFactory alphaCivFactory;

    public ZetaCivFactory(){
        alphaCivFactory = new AlphaCivFactory();
    }

    @Override
    public AgeingStrategy createAgeingStrategy() {
        return alphaCivFactory.createAgeingStrategy();
    }

    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new ConquerCityAndWinning3BattlesStrategy();
    }

    @Override
    public StartingLayoutStrategy createStartingLayoutStrategy() {
        return alphaCivFactory.createStartingLayoutStrategy();
    }

    @Override
    public BattleStrategy createBattleStrategy() {
        return alphaCivFactory.createBattleStrategy();
    }
}

