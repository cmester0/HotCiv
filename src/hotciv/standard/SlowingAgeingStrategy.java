package src.hotciv.standard;

import src.hotciv.framework.AgeingStrategy;

/**
 * Created by Lasse Letager Hansen on 30-11-2016.
 */
public class SlowingAgeingStrategy implements AgeingStrategy {
    @Override
    public int getNextAge(int currentAge) {
        if(currentAge < -100)
            return currentAge+100;
        else if(currentAge == -100)
            return -1;
        else if(currentAge==-1)
            return 1;
        else if(currentAge==1)
            return 50;
        else if(currentAge < 1750)
            return currentAge+50;
        else if(currentAge < 1900)
            return currentAge+25;
        else if(currentAge < 1970)
            return currentAge+5;
        else
            return currentAge+1;
    }
}
