package src.hotciv.standard;

import src.hotciv.framework.City;
import src.hotciv.framework.GameConstants;
import src.hotciv.framework.Player;
import src.hotciv.framework.Position;

import java.util.Map;

/**
 * Created by Lasse Letager Hansen on 09-11-2016.
 */
public class StandardCity implements City {
    private Player owner;
    private String unitType;

    public StandardCity(Player owner){
        this(owner, GameConstants.ARCHER);
    }

    public StandardCity(Player owner, String unitType) {
        this.owner = owner;
        this.unitType = unitType;
    }

    @Override
    public Player getOwner() {
        return owner;
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
