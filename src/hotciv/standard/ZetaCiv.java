package src.hotciv.standard;

import src.hotciv.framework.*;

import java.util.Map;

/**
 * Created by Lasse Letager Hansen on 28-11-2016.
 */
public class ZetaCiv implements Civ {
    AlphaCiv alphaCiv;
    BetaCiv betaCiv;
    EpsilonCiv epsilonCiv;

    private int rounds;

    public ZetaCiv(){
        alphaCiv = new AlphaCiv();
        betaCiv = new BetaCiv();
        epsilonCiv = new EpsilonCiv();

        rounds = 0;
    }

    @Override
    public int getNextAge(int currentAge) {
        rounds++;
        return alphaCiv.getNextAge(currentAge);
    }

    @Override
    public Player getWinner() {
        if(rounds <= 20) {
            return betaCiv.getWinner();
        }else{
            return null;
        }
    }

    @Override
    public void performUnitActionAt(Position p) {
        alphaCiv.performUnitActionAt(p);
    }

    @Override
    public void setup(Map<Position, Unit> units, Map<Position, City> cities, Map<Position, Tile> tiles) {
        betaCiv.setup(units, cities, tiles);
    }

    @Override
    public void update() {
        if(rounds <= 20) betaCiv.update();
    }
}
