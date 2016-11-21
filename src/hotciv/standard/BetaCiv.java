package src.hotciv.standard;

import src.hotciv.framework.*;

import java.util.Map;

/**
 * Created by ssodelta on 11/21/16.
 */
public class BetaCiv implements Civ {

    @Override
    public int getNextAge(int age) {
        if(age < -100)
            return age+100;
        else if(age == -100)
            return -1;
        else if(age==-1)
            return 1;
        else if(age==1)
            return 50;
        else if(age < 1750)
            return age+50;
        else if(age < 1900)
            return age+25;
        else if(age < 1970)
            return age+5;
        else
            return age+1;
    }

    @Override
    public Player getWinner() {
        return null;
    }

    @Override
    public void performUnitActionAt(Position p) {

    }

    @Override
    public void setup(Map<Position, Unit> units, Map<Position, City> cities, Map<Position, Tile> tiles) {

    }

}
