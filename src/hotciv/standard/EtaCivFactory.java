package src.hotciv.standard;

import src.hotciv.framework.*;

/**
 * Created by Lasse Letager Hansen on 30-11-2016.
 */
public class EtaCivFactory implements CivFactory {
    @Override
    public AgeingStrategy createAgeingStrategy() {
        return null;
    }

    @Override
    public WinnerStrategy createWinnerStrategy() {
        return null;
    }

    @Override
    public StartingLayoutStrategy createStartingLayoutStrategy() {
        return null;
    }

    @Override
    public BattleStrategy createBattleStrategy() {
        return null;
    }
}
