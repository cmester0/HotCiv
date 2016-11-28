package src.hotciv.standard;

import src.hotciv.framework.GameConstants;
import src.hotciv.framework.Player;
import src.hotciv.framework.Unit;

public class StandardUnit implements Unit {

    private int moveCount, defensiveStrength;
    private String type;
    private Player player;
    private boolean cityBonus;
    private int terrainBonus;
    private int adjacentCount;

    public StandardUnit(String type, Player p){
        this(type, p, 1);
    }

    public StandardUnit(String type, Player p, int moveCount){
        this(type,p,moveCount,3);
    }


    public StandardUnit(Unit u, boolean cityBonus, int terrainBonus, int adjecentCount){
        this(u.getTypeString(), u.getOwner(), u.getMoveCount(), u.getDefensiveStrength());
        this.cityBonus = cityBonus;
        this.terrainBonus = terrainBonus;
        this.adjacentCount = adjecentCount;
    }

    public StandardUnit(String type, Player p, int moveCount, int defensiveStrength){
        this.type = type;
        player = p;
        this.moveCount = moveCount;
        this.defensiveStrength = defensiveStrength;

        this.cityBonus = false;
        this.terrainBonus = 1;
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
        int baseStrength;
        switch (getTypeString()){
            case GameConstants.LEGION:
                baseStrength = 4;
                break;
            case GameConstants.ARCHER:
                baseStrength = 2;
                break;
            default:
                baseStrength = 0;
                break;
        }

        return (baseStrength + adjacentCount) * terrainBonus * (cityBonus ? 3 : 1);
    }
}
