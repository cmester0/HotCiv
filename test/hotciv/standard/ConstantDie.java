package test.hotciv.standard;

import src.hotciv.framework.Die;

/**
 * Created by Lasse Letager Hansen on 28-11-2016.
 */
public class ConstantDie implements Die {
    int constant;

    public ConstantDie(int constantValue){
        constant = constantValue;
    }

    @Override
    public int rollDie() {
        return constant;
    }
}
