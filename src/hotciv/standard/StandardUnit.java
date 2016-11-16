package src.hotciv.standard;

import src.hotciv.framework.GameConstants;
import src.hotciv.framework.Player;
import src.hotciv.framework.Unit;

public class StandardUnit implements Unit {

    private String type;
    private Player player;

    public StandardUnit(String type, Player p){
        this.type = type;
        player = p;
    }

    @Override
    public String getTypeString() {
        return type;
    }

    @Override
    public Player getOwner() {
        return player;
    }

    @Override
    public int getMoveCount() {
        return 0;
    }

    @Override
    public int getDefensiveStrength() {
        return 0;
    }

    @Override
    public int getAttackingStrength() {
        return 0;
    }
}
