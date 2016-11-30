package src.hotciv.framework;

/**
 * Created by Lasse Letager Hansen on 30-11-2016.
 */
public interface BattleStrategy {
    boolean getOutcomeOfBattle(Unit attacker, Unit defender);
}
