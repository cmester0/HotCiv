package src.hotciv.standard;

import src.hotciv.framework.*;
import test.hotciv.standard.LoadedDie;

import java.util.Map;

/**
 * Created by Lasse Letager Hansen on 28-11-2016.
 */
public class ZetaCiv implements Civ {
    private AbstractCiv alphaCiv;
    private BetaCiv betaCiv;

    private int rounds;
    private int redWins;
    private int blueWins;

    public ZetaCiv(){
        alphaCiv = new AbstractCiv(new AlphaCivFactory());
        betaCiv = new BetaCiv();

        rounds = 0;
        redWins = 0;
        blueWins = 0;
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
            if(redWins >= 3)
                return Player.RED;
            if(blueWins >= 3)
                return Player.BLUE;
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
    public void update(Map<Position, Unit> units, Map<Position, City> cities, Map<Position, Tile> tiles) {
        if(rounds <= 20) betaCiv.update(units, cities, tiles);
    }

    @Override
    public boolean outcomeOfBattle(Unit attacker, Unit defender) {
        boolean winner = alphaCiv.outcomeOfBattle(attacker, defender);
        if(winner && rounds > 20){
            switch (attacker.getOwner()){
                case RED:
                    redWins++;
                    break;
                case BLUE:
                    blueWins++;
                    break;
            }
        }

        return winner;
    }
}
