package src.hotciv.standard;

import src.hotciv.framework.GameConstants;
import src.hotciv.framework.Player;
import src.hotciv.framework.Unit;

public class StandardUnit implements Unit {

    int moveCount;
    private String type;
    private Player player;

    public StandardUnit(String type, Player p){
        this.type = type;
        player = p;
        this.moveCount = 1;
    }

    public StandardUnit(String type, Player p, int moveCount){
        this.type = type;
        player = p;
        this.moveCount = moveCount;
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
        return moveCount;
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
