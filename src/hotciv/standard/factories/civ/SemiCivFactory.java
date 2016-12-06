package src.hotciv.standard.factories.civ;

import src.hotciv.framework.*;

/**
 * Created by Lasse Letager Hansen on 05-12-2016.
 */
public class SemiCivFactory implements CivFactory {
    // MISSING UNIT ACTION AND CITY WORKFORCE / POPULATION

    private AlphaCivFactory alphaCivFactory;
    private BetaCivFactory betaCivFactory;
    private GammaCivFactory gammaCivFactory;
    private DeltaCivFactory deltaCivFactory;
    private EpsilonCivFactory epsilonCivFactory;
    private EtaCivFactory etaCivFactory;

    public SemiCivFactory(){
        alphaCivFactory = new AlphaCivFactory();
        betaCivFactory = new BetaCivFactory();
        gammaCivFactory = new GammaCivFactory();
        deltaCivFactory = new DeltaCivFactory();
        epsilonCivFactory = new EpsilonCivFactory();
        etaCivFactory = new EtaCivFactory();
    }

    @Override
    public AgeingStrategy createAgeingStrategy() {
        return betaCivFactory.createAgeingStrategy();
    }

    @Override
    public WinnerStrategy createWinnerStrategy() {
        return epsilonCivFactory.createWinnerStrategy();
    }

    @Override
    public StartingLayoutStrategy createStartingLayoutStrategy() {
        return deltaCivFactory.createStartingLayoutStrategy();
    }

    @Override
    public BattleStrategy createBattleStrategy() {
        return epsilonCivFactory.createBattleStrategy();
    }

    @Override
    public PerformActionStrategy createPerformActionStrategy() {
        return gammaCivFactory.createPerformActionStrategy();
    }

    @Override
    public UnitFactory createUnitFactory() {
        return alphaCivFactory.createUnitFactory();
    }
}
