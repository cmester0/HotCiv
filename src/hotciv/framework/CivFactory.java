package src.hotciv.framework;

/**
 * Created by Lasse Letager Hansen on 30-11-2016.
 */
public interface CivFactory {
    AgeingStrategy createAgeingStrategy();
    WinnerStrategy createWinnerStrategy();
    StartingLayoutStrategy createStartingLayoutStrategy();
    BattleStrategy createBattleStrategy();
    PerformActionStrategy createPerformActionStrategy();
    UnitFactory createUnitFactory();
}
