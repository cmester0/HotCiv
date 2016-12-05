package src.hotciv.framework;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Lasse Letager Hansen on 05-12-2016.
 */
public interface UnitFactory {
    Unit createUnit(City c, AtomicInteger newProd);
}
