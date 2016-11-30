package src.hotciv.standard;

import src.hotciv.framework.BattleStrategy;
import src.hotciv.framework.Unit;

/**
 * Created by Lasse Letager Hansen on 30-11-2016.
 */
public class AttackerWinsStrategy implements BattleStrategy {
    @Override
    public boolean getOutcomeOfBattle(Unit attacker, Unit defender) {
        return true; // Attacker always wins
    }
}
