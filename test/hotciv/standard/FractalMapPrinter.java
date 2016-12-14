package test.hotciv.standard;

import src.hotciv.framework.GameConstants;
import src.hotciv.standard.strategies.FractalStartingLayoutStrategy;
import src.hotciv.framework.*;

import java.util.Map;

/**
 * Created by Lasse Letager Hansen on 14-12-2016.
 */
public class FractalMapPrinter {
    public static void main(String[] args){

        FractalStartingLayoutStrategy strategy = new FractalStartingLayoutStrategy();
        Map<Position, Tile> map = strategy.createMap();

        for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
            for (int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
                char tileChar = ' ';
                switch (map.get(new Position(r,c)).getTypeString()){
                    case GameConstants.FOREST:
                        tileChar = 'f';
                        break;
                    case GameConstants.HILLS:
                        tileChar = 'h';
                        break;
                    case GameConstants.OCEANS:
                        tileChar = '.';
                        break;
                    case GameConstants.PLAINS:
                        tileChar = 'o';
                        break;
                    case GameConstants.MOUNTAINS:
                        tileChar = 'M';
                        break;
                }

                System.out.print(tileChar);
            }
            System.out.println();
        }
    }
}
