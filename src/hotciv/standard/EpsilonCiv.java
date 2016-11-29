package src.hotciv.standard;

import src.hotciv.framework.*;

import java.util.Map;

/**
 * Created by Lasse Letager Hansen on 28-11-2016.
 */
public class EpsilonCiv implements Civ {
    private AlphaCiv alphaCiv;
    private Die die;
    private int blueWinCount;
    private int redWinCount;

    public EpsilonCiv(Die die){
        alphaCiv = new AlphaCiv();
        this.die = die;

        blueWinCount = 0;
        redWinCount = 0;
    }

    @Override
    public int getNextAge(int currentAge) {
        return alphaCiv.getNextAge(currentAge);
    }

    @Override
    public Player getWinner() {
        System.out.println("Red: " + redWinCount + ", Blue: " + blueWinCount);

        if(redWinCount >= 3)
            return Player.RED;
        if(blueWinCount >= 3)
            return Player.BLUE;
        return null;
    }

    @Override
    public void performUnitActionAt(Position p) {
        alphaCiv.performUnitActionAt(p);
    }

    @Override
    public void setup(Map<Position, Unit> units, Map<Position, City> cities, Map<Position, Tile> tiles) {
        alphaCiv.setup(units, cities, tiles);
    }

    @Override
    public void update() {
        alphaCiv.update();
    }

    @Override
    public boolean outcomeOfBattle(Unit attacker, Unit defender) {

        int atkRoll = die.rollDie();
        int defRoll = die.rollDie();

        int attackStrength =  attacker.getAttackingStrength() * atkRoll;
        int defenceStrength = defender.getDefensiveStrength() * defRoll;

        boolean attackerWins = attackStrength > defenceStrength;

        if(attackerWins){
            switch (attacker.getOwner()){
                case RED:
                    redWinCount++;
                    break;
                case BLUE:
                    blueWinCount++;
                    break;
            }
        }

        return attackerWins;
    }
}
