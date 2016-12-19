package src.hotciv.view.tools;

import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;
import src.hotciv.framework.Game;
import src.hotciv.view.TextFigure;

/**
 * Created by Lasse Letager Hansen on 19-12-2016.
 */
public class ActionTool extends NullTool {
    private Game game;
    private DrawingEditor editor;

    public ActionTool(DrawingEditor editor, Game game){
        this.game = game;
        this.editor = editor;
    }
}
