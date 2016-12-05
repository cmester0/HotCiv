package src.hotciv.standard.factories;

import src.hotciv.framework.City;
import src.hotciv.framework.GameConstants;
import src.hotciv.framework.Unit;
import src.hotciv.framework.UnitFactory;
import src.hotciv.standard.StandardUnit;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Lasse Letager Hansen on 28-11-2016.
 */
public class StandardUnitFactory implements UnitFactory {
    public Unit createUnit(City c, AtomicInteger newProd){
        Unit u = null;
        switch (c.getProduction()) {
            case GameConstants.SETTLER:
                if (newProd.get() >= 30)
                    u = new StandardUnit(GameConstants.SETTLER, c.getOwner());
                break;
            case GameConstants.LEGION:
                if(newProd.get() >= 15)
                    u = new StandardUnit(GameConstants.LEGION, c.getOwner());
                break;
            default:
                if (newProd.get() >= 10)
                    u = new StandardUnit(GameConstants.ARCHER, c.getOwner());
                break;
        }

        if(u != null) {
            switch (u.getTypeString()) {
                case GameConstants.SETTLER:
                    newProd.set(newProd.get() % 30);
                    break;
                case GameConstants.LEGION:
                    newProd.set(newProd.get() % 15);
                    break;
                default:
                    newProd.set(newProd.get() % 10);
                    break;
            }
        }

        return u;
    }
}
