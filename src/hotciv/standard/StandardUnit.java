package src.hotciv.standard;

import src.hotciv.framework.GameConstants;
import src.hotciv.framework.Player;
import src.hotciv.framework.Unit;

public class StandardUnit implements Unit {

    private int moveCount, defensiveStrength;
    private String type;
    private Player player;

    public StandardUnit(String type, Player p){
        this(type, p, 1);
    }

    public StandardUnit(String type, Player p, int moveCount){
        this(type,p,moveCount,3);
    }

    public StandardUnit(String type, Player p, int moveCount, int defensiveStrength){
        this.type = type;
        player = p;
        this.moveCount = moveCount;
        this.defensiveStrength = defensiveStrength;
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
        return defensiveStrength;
    }

    @Override
    public int getAttackingStrength() {
        return 0;
    }
}
