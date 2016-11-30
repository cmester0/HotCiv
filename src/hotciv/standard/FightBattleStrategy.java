package src.hotciv.standard;

import src.hotciv.framework.BattleStrategy;
import src.hotciv.framework.GameConstants;
import src.hotciv.framework.Unit;

/**
 * Created by Lasse Letager Hansen on 30-11-2016.
 */
public class FightBattleStrategy implements BattleStrategy {

    @Override
    public boolean getOutcomeOfBattle(Unit attacker, Unit defender) {
        int atkRoll = GameConstants.die.rollDie();
        int defRoll = GameConstants.die.rollDie();

        int attackStrength =  attacker.getAttackingStrength() * atkRoll;
        int defenceStrength = defender.getDefensiveStrength() * defRoll;

        return attackStrength > defenceStrength;
    }
}
