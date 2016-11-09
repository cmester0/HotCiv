package src.hotciv.standard;

import src.hotciv.framework.City;
import src.hotciv.framework.Player;
import src.hotciv.framework.Position;

/**
 * Created by Lasse Letager Hansen on 09-11-2016.
 */
public class StandardCity implements City {
    private Position p;

    public StandardCity(Position p) {
        this.p = p;
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
        return 0;
    }

    @Override
    public String getProduction() {
        return null;
    }

    @Override
    public String getWorkforceFocus() {
        return null;
    }
}
