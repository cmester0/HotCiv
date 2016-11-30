package src.hotciv.standard;

import src.hotciv.framework.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lasse Letager Hansen on 30-11-2016.
 */
public class Winning3BattlesWinsStrategy implements WinnerStrategy {
    int redWins;
    int blueWins;
    Player winner;

    public Winning3BattlesWinsStrategy(){
        redWins = 0;
        blueWins = 0;
        winner = null;
    }

    @Override
    public Player getWinner() {
        return winner;
    }

    @Override
    public void setAge(int age) { }

    @Override
    public void checkWorld(Map<Position, Unit> units, Map<Position, City> cities, Map<Position, Tile> tiles) {

    }

    @Override
    public void addAttackWin(Player p) {
        switch (p){
            case RED:
                redWins++;
                break;
            case BLUE:
                blueWins++;
                break;
        }

        if(redWins >= 3){
            winner = Player.RED;
        }
        if (blueWins >= 3){
            winner = Player.BLUE;
        }
    }
}
