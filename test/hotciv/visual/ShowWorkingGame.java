package test.hotciv.visual;

import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.DrawingView;
import minidraw.framework.Factory;
import minidraw.standard.MiniDrawApplication;
import src.hotciv.framework.Game;
import src.hotciv.standard.AbstractCiv;
import src.hotciv.standard.GameImpl;
import src.hotciv.standard.factories.civ.SemiCivFactory;
import src.hotciv.view.*;
import src.hotciv.view.tools.CompositeTool;

import javax.swing.*;

/**
 * Created by Lasse Letager Hansen on 16-12-2016.
 */
public class ShowWorkingGame {
    public static void main(String[] args) {
        Game game = new GameImpl(new AbstractCiv(new SemiCivFactory()));

        DrawingEditor editor =
                new MiniDrawApplication( "Move any unit using the mouse",
                        new HotCivFactoryFinal(game) );
        editor.open();
        editor.showStatus("Move units to see Game's moveUnit method being called.");

        // Replace the setting of the tool with your UnitMoveTool implementation.
        editor.setTool( new CompositeTool(editor, game) );
    }

    private static class HotCivFactoryFinal implements Factory {
        private Game game;

        public HotCivFactoryFinal(Game game) {
            this.game = game;
        }

        @Override
        public DrawingView createDrawingView( DrawingEditor editor ) {
            DrawingView view = new MapView(editor, game);
            return view;
        }

        @Override
        public Drawing createDrawing( DrawingEditor editor ) {
            return new CivDrawing( editor, game );
        }

        @Override
        public JTextField createStatusField( DrawingEditor editor ) {
            JTextField f = new JTextField("dSoftArk template code");
            f.setEditable(false);
            return f;
        }
    }
}
