package src.hotciv.standard.strategies;

import src.hotciv.framework.*;
import src.hotciv.standard.strategies.*;

import java.util.Map;

/**
 * Created by Lasse Letager Hansen on 30-11-2016.
 */
public class ConquerCityAndWinning3BattlesStrategy implements WinnerStrategy {
    private ConquerCityWinsStrategy conquerCityWinsStrategy;
    private Winning3BattlesWinsStrategy winning3BattlesWinsStrategy;

    private int age;
    private int rounds;

    public ConquerCityAndWinning3BattlesStrategy(){
        conquerCityWinsStrategy = new ConquerCityWinsStrategy();
        winning3BattlesWinsStrategy = new Winning3BattlesWinsStrategy();
        rounds = 0;
    }

    @Override
    public Player getWinner() {
        if(rounds <= 20) {
            return conquerCityWinsStrategy.getWinner();
        } else {
            return winning3BattlesWinsStrategy.getWinner();
        }
    }

    @Override
    public void setAge(int age) {
        if(this.age == age) return;
        rounds++;
        this.age = age;
    }

    @Override
    public void checkWorld(Map<Position, Unit> units, Map<Position, City> cities, Map<Position, Tile> tiles) {
        conquerCityWinsStrategy.checkWorld(units, cities, tiles);
    }

    @Override
    public void addAttackWin(Player p) {
        if(rounds > 20)
            winning3BattlesWinsStrategy.addAttackWin(p);
    }
}

