package src.hotciv.standard;

import src.hotciv.framework.Tile;

/**
 * Created by ssodelta on 11/14/16.
 */
public class StandardTile implements Tile {

    private String tileType;

    public StandardTile(String type){
        tileType = type;
    }

    @Override
    public String getTypeString() {
        return tileType;
    }
}