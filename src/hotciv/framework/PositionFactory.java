package src.hotciv.framework;

/**
 * Created by Lasse Letager Hansen on 19-12-2016.
 */
public interface PositionFactory {
    Position[] getPossiblePositions(Player player);
}
