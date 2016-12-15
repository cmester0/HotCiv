package test.hotciv.visual;

import minidraw.standard.*;
import minidraw.framework.*;

import src.hotciv.framework.*;
import src.hotciv.standard.AbstractCiv;
import src.hotciv.standard.GameImpl;
import src.hotciv.standard.factories.civ.AlphaCivFactory;
import src.hotciv.view.tools.UnitMoveTool;
import test.hotciv.stub.*;

/** Template code for exercise FRS 36.39.

   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Computer Science Department
     Aarhus University
   
   This source code is provided WITHOUT ANY WARRANTY either 
   expressed or implied. You may study, use, modify, and 
   distribute it for non-commercial purposes. For any 
   commercial use, see http://www.baerbak.com/
 */
public class ShowMove {
  
  public static void main(String[] args) {
    Game game = new GameImpl(new AbstractCiv(new AlphaCivFactory())); //new StubGame2();

    DrawingEditor editor = 
      new MiniDrawApplication( "Move any unit using the mouse",  
                               new HotCivFactory4(game) );
    editor.open();
    editor.showStatus("Move units to see Game's moveUnit method being called.");

    // Replace the setting of the tool with your UnitMoveTool implementation.
    editor.setTool( new UnitMoveTool(editor, game) );
  }
}
