//API : http://mabe02.github.io/lanterna/apidocs/2.1/
import com.googlecode.lanterna.terminal.Terminal.SGR;
import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.Key.Kind;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.Terminal.Color;
import com.googlecode.lanterna.terminal.TerminalSize;
import com.googlecode.lanterna.LanternaException;
import com.googlecode.lanterna.input.CharacterPattern;
import com.googlecode.lanterna.input.InputDecoder;
import com.googlecode.lanterna.input.InputProvider;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.KeyMappingProfile;

//javac -cp "lanterna.jar;." MenuDemo.java
//java -cp "lanterna.jar;." MenuDemo TestCSV.csv

public class MenuDemo {

  public static void putString(int r, int c,Terminal t, String s){
    t.moveCursor(r,c);
    for(int i = 0; i < s.length();i++){
      t.putCharacter(s.charAt(i));
    }
  }

  public static void putString(int r, int c,Terminal t,
        String s, Terminal.Color forg, Terminal.Color back ){
    t.moveCursor(r,c);
    t.applyBackgroundColor(forg);
    t.applyForegroundColor(Terminal.Color.BLACK);

    for(int i = 0; i < s.length();i++){
      t.putCharacter(s.charAt(i));
    }
    t.applyBackgroundColor(Terminal.Color.DEFAULT);
    t.applyForegroundColor(Terminal.Color.DEFAULT);
  }

  public static void highlight(int row, int col, Terminal t, Sheet sheet) {
    int r = row + 2;
    int c = 0;
    int spaceLength = sheet.longestInCol(col) + 3;
    String entry = String.format("%-" + spaceLength + "." + spaceLength + "s", sheet.getString(row,col));
    for (int i = 0; i < col; i++) {
      c += sheet.longestInCol(i);
    }
    t.moveCursor(c,r);
    t.applyBackgroundColor(Terminal.Color.YELLOW);
    for (int j = 0; j < spaceLength; j++) {
      t.putCharacter(entry.charAt(j));
    }
  }

  public static void main(String[] args) {

    Terminal terminal = TerminalFacade.createTextTerminal();
    terminal.enterPrivateMode();

    TerminalSize size = terminal.getTerminalSize();
    terminal.setCursorVisible(false);

    boolean running = true;
    int mode = 0;
    long lastTime =  System.currentTimeMillis();
    long currentTime = lastTime;
    long timer = 0;

    String filename = args[0];
    Sheet file = new Sheet(filename);

    while(running){
      Key key = terminal.readInput();
      if (key != null)
      {

        //YOU CAN PUT DIFFERENT SETS OF BUTTONS FOR DIFFERENT MODES!!!

        //only for the game mode.
        if(mode == 0){
          if (key.getKind() == Key.Kind.Escape) {
            terminal.exitPrivateMode();
            running = false;
          }
        }

        //for all modes
        if (key.getCharacter() == ' ') {
          mode++;
          mode%=2;//2 modes
          terminal.clearScreen();
          lastTime = System.currentTimeMillis();
          currentTime = System.currentTimeMillis();
        }
      }

      terminal.applySGR(Terminal.SGR.ENTER_BOLD);
      //putString(1,1,terminal, "This is mode "+mode,Terminal.Color.WHITE,Terminal.Color.RED);
      terminal.applySGR(Terminal.SGR.RESET_ALL);


      if(mode==0){
        lastTime = currentTime;
        currentTime = System.currentTimeMillis();
        timer += (currentTime -lastTime);//add the amount of time since the last frame.
        //DO GAME STUFF HERE
        putString(0,0,terminal, "Spreadsheet: " + filename,Terminal.Color.WHITE,Terminal.Color.RED);
        //putString(3,5,terminal, "Time: "+timer,Terminal.Color.WHITE,Terminal.Color.RED);
        putString(0,2,terminal,file.toString(),Terminal.Color.WHITE,Terminal.Color.RED);
        highlight(0,0,terminal,file);
        

      }else{

        terminal.applySGR(Terminal.SGR.ENTER_BOLD,Terminal.SGR.ENTER_BLINK);
        putString(1,3,terminal, "Not game, just a pause!",Terminal.Color.RED,Terminal.Color.WHITE);
        terminal.applySGR(Terminal.SGR.RESET_ALL);

      }

    }

    
  }
}