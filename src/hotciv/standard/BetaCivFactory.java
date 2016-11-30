package src.hotciv.standard;

import src.hotciv.framework.*;

/**
 * Created by Lasse Letager Hansen on 30-11-2016.
 */
public class BetaCivFactory implements CivFactory {
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
        return new StandardStartingLayoutStrategy();
    }

    @Override
    public BattleStrategy createBattleStrategy() {
        return new AttackerWinsStrategy();
    }
}