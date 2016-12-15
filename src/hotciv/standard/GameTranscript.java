package src.hotciv.standard;

import src.hotciv.framework.*;

/**
 * Created by Nikolaj Ignatieff Schwartzbach on 12/12/2016.
 */
public class GameTranscript implements Game {

    private GameImpl game;
    private boolean log = true;

    public GameTranscript(GameImpl game){
        this.game = game;
    }

    private void log(String s){
        if(log)
            System.out.println(getPlayerInTurn()+" "+s);
    }

    public void setLogging(boolean log){
        this.log = log;
    }

    @Override
    public Tile getTileAt(Position p) {
        return game.getTileAt(p);
    }
    @Override
    public void endOfTurn() {
        log("ends turn");
        game.endOfTurn();
    }

    @Override
    public Unit getUnitAt(Position p) {
        return game.getUnitAt(p);
    }

    @Override
    public City getCityAt(Position p) {
        return game.getCityAt(p);
    }

    @Override
    public Player getPlayerInTurn() {
        return game.getPlayerInTurn();
    }

    @Override
    public Player getWinner() {
        return game.getWinner();
    }

    @Override
    public int getAge() {
        return game.getAge();
    }

    @Override
    public boolean moveUnit(Position from, Position to) {
       Unit u = game.getUnitAt(from);
       log("tries to move from " + from + " to " + to);
       if(game.moveUnit(from, to)) {
           log("\t moves " + u.getTypeString() + " from " + from + " to " + to);
           return true;
       }
        return false;
    }



    @Override
    public void changeWorkforceFocusInCityAt(Position p, String balance) {
        log("changes work force focus in city\n at "+p+" to "+balance);
        game.changeWorkforceFocusInCityAt(p, balance);
    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType) {
        log("changes production in city at "+p+" to "+unitType);
        game.changeProductionInCityAt(p, unitType);
    }

    @Override
    public void performUnitActionAt(Position p) {
        Unit u = game.getUnitAt(p);
        if(u!=null)
            log("performs unit action on "+u.getTypeString()+" at "+p);
        game.performUnitActionAt(p);
    }

    @Override
    public int getProductionAmountOfCity(City c) {
        return game.getProductionAmountOfCity(c);
    }

    @Override
    public int getFoodAmountOfCityAt(Position p) {
        return game.getFoodAmountOfCityAt(p);
    }

    @Override
    public void addObserver(GameObserver observer) {

    }

    @Override
    public void setTileFocus(Position position) {

    }
}
