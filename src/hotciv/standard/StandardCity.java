package src.hotciv.standard;

import src.hotciv.framework.City;
import src.hotciv.framework.GameConstants;
import src.hotciv.framework.Player;
import src.hotciv.framework.Position;

/**
 * Created by Lasse Letager Hansen on 09-11-2016.
 */
public class StandardCity implements City {
    private Position p;
    private String unitType;

    public StandardCity(Position p){
        this(p, GameConstants.ARCHER);
    }

    public StandardCity(Position p, String unitType) {
        this.p = p;
        this.unitType = unitType;
    }

    @Override
    public Player getOwner() {
        if(p.getRow() == 4) {
            return Player.BLUE;
        } else {
            return Player.RED;
        }
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getProduction() {
        return unitType;
    }

    @Override
    public String getWorkforceFocus() {
        return null;
    }
}
