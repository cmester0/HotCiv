package src.hotciv.standard.factories;

import src.hotciv.framework.City;
import src.hotciv.framework.GameConstants;
import src.hotciv.framework.Unit;
import src.hotciv.framework.UnitFactory;
import src.hotciv.standard.StandardUnit;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Lasse Letager Hansen on 05-12-2016.
 */
public class BombUnitFactory implements UnitFactory{
    StandardUnitFactory standardUnitFactory;

    public BombUnitFactory(){
        standardUnitFactory = new StandardUnitFactory();
    }

    @Override
    public Unit createUnit(City c, AtomicInteger newProd) {
        if(c.getProduction().equals("bomb")) {
            if (newProd.get() >= 60)
                newProd.set(newProd.get() % 60);
                return new StandardUnit("bomb", c.getOwner());
        }

        return standardUnitFactory.createUnit(c,newProd);
    }
}
