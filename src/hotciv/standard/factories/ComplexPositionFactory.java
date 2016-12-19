package src.hotciv.standard.factories;

import src.hotciv.framework.Player;
import src.hotciv.framework.Position;
import src.hotciv.framework.PositionFactory;

/**
 * Created by Lasse Letager Hansen on 19-12-2016.
 */
public class ComplexPositionFactory implements PositionFactory {
    @Override
    public Position[] getPossiblePositions(Player player) {
        if(player.equals(Player.BLUE)) {
            return new Position[]{
                    new Position(4, 5),
                    new Position(3, 6),
                    new Position(4, 6),
                    new Position(5, 6),
                    new Position(5, 5),
                    new Position(5, 4),
                    new Position(4, 4)
            };
        }else{
            return new Position[]{
                    new Position(8,12),
                    new Position(7,12),
                    new Position(8,13),
                    new Position(9,13),
                    new Position(9,12),
                    new Position(9,11),
                    new Position(8,11),
                    new Position(7,11)
            };
        }
    }
}
