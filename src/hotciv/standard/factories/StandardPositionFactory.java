package src.hotciv.standard.factories;

import src.hotciv.framework.Player;
import src.hotciv.framework.Position;
import src.hotciv.framework.PositionFactory;

/**
 * Created by Lasse Letager Hansen on 19-12-2016.
 */
public class StandardPositionFactory implements PositionFactory {
    @Override
    public Position[] getPossiblePositions(Player player) {
        if(player.equals(Player.BLUE)) {
            return new Position[]{
                    new Position(4, 1),
                    new Position(3, 1),
                    new Position(4, 2),
                    new Position(5, 2),
                    new Position(5, 1),
                    new Position(5, 0),
                    new Position(4, 0)
            };
        }else{
            return new Position[]{
                    new Position(1,1),
                    new Position(0,1),
                    new Position(0,2),
                    new Position(1,2),
                    new Position(2,1),
                    new Position(2,0),
                    new Position(0,0)
            };
        }
    }
}
