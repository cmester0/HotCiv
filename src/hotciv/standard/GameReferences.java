package src.hotciv.standard;

import src.hotciv.framework.Die;

/**
 * Created by Lasse Letager Hansen on 07-12-2016.
 */
public class GameReferences {
    public static Die die = () -> (int) (Math.random() * 5 + 1);
}
