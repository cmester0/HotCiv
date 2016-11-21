package src.hotciv.standard;

import src.hotciv.framework.*;

import javax.swing.plaf.synth.SynthStyle;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssodelta on 11/21/16.
 */
public class BetaCiv implements Civ {

    private AlphaCiv alphaCiv;
    private Player winner;

    private Map<Position, City> cities;
    private Map<Position, Unit> units;

    public BetaCiv(){
        alphaCiv = new AlphaCiv();
        winner = null;
    }

    @Override
    public int getNextAge(int age) {
        winner = Player.RED;
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
        return winner;
    }

    @Override
    public void performUnitActionAt(Position p) {

    }

    @Override
    public void setup(Map<Position, Unit> units, Map<Position, City> cities, Map<Position, Tile> tiles) {
        alphaCiv.setup(units, cities, tiles);

        this.cities = cities;
        this.units = units;
    }

    @Override
    public void update() {
        System.out.println("HERE");
        cities.put(new Position(1,1), new StandardCity(Player.BLUE));
    }

}
