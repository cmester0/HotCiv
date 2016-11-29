package test.hotciv.standard;

import src.hotciv.framework.Die;

import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Lasse Letager Hansen on 29-11-2016.
 */
public class LoadedDie implements Die {
    private Queue<Integer> upcomingRolls;

    public LoadedDie(){
        upcomingRolls = new LinkedBlockingDeque<Integer>();
    }

    public void addNumber(int nextNumber){
        upcomingRolls.add(nextNumber);
    }

    @Override
    public int rollDie() {
        return upcomingRolls.poll();
    }
}
