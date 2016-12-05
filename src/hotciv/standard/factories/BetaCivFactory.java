package src.hotciv.standard.factories;

import src.hotciv.framework.*;
import src.hotciv.standard.strategies.*;

/**
 * Created by Lasse Letager Hansen on 30-11-2016.
 */
public class BetaCivFactory implements CivFactory {
    private AlphaCivFactory alphaCivFactory;

    public BetaCivFactory(){
        alphaCivFactory = new AlphaCivFactory();
    }

    @Override
    public AgeingStrategy createAgeingStrategy() {
        return new SlowingAgeingStrategy();
    }

    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new ConquerCityWinsStrategy();
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
