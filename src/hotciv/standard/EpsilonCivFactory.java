package src.hotciv.standard;

import src.hotciv.framework.*;

import java.sql.Time;

/**
 * Created by Lasse Letager Hansen on 30-11-2016.
 */
public class EpsilonCivFactory implements CivFactory {
    AlphaCivFactory alphaCivFactory;

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
}
