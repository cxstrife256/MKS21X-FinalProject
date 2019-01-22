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

import java.util.ArrayList;

public class Game {

  // display
  /* 0 : world map
     1 : battle
     2 : menu
  */
  private static int mode;
  private static ArrayList<Player> players;
  private static ArrayList<Enemy> enemies;

  public static void putString(int row, int col, Terminal t, String str) { // replace with screen
		t.moveCursor(row, col);
		for(int i=0; i<str.length(); i++) {
			t.putCharacter(str.charAt(i));
		}

	}

  public static void wait(int time){
    try {
      Thread.sleep(time);
    } catch(InterruptedException e) {
      e.printStackTrace();
    }

  }

  // spawn enemies randomly
  public static void enemySetup() {
    // enemycount [2, 3]
    int enemycount = ((int)(Math.random() * 10000) % 2) + 2;
    int enemy_type = 0;
    for(int i=0; i<enemycount; i++) {
      enemy_type = (int)(Math.random() * 10000) % 10;

      // MilitaryPolice
      // 3 / 10
      if(enemy_type < 3) { enemies.add(new MilitaryPolice(30, 6, 50, 4, 0, 0, 4)); }

      // Grunt
      // 3 / 10
      else if(enemy_type < 6) { enemies.add(new Grunt(40, 12, 58, 10, 2, 2, 8)); }

      // GuardDog
      // 3 / 10
      else if(enemy_type < 9) { enemies.add(new GuardDog(42, 8, 64, 4, 2, 2, 6)); }

      // Sweeper
      // 1 / 10
      else { enemies.add(new Sweeper(140, 18, 20, 48, 0, 4, 1)); }

    }

  }

  public static void remove() {
    for(int i=0; i<enemies.size(); i++) {
      if(enemies.get(i).getHitpoints() <= 0) {
        enemies.remove(enemies.get(i));
      }
    }

    for(int i=0; i<players.size(); i++) {
      if(players.get(i).getHitpoints() <= 0) {
        players.remove(players.get(i));
      }
    }

  }

  // does a check to see if the encounter should occur
  public static void encounter(Map map, Terminal terminal) {
    if(map.getID() != 0) {  // enemies do not spawn in the first room
      if(((int)(Math.random() * 10000) % 100) < 4) {  // 4% chance upon step to get it spicy
        battleStart(terminal);
      }

    }
  }

  // starts a random encounter
  public static void battleStart(Terminal terminal) {
    // change mode --> battle
    mode = 1;

    terminal.clearScreen();
    enemySetup();

  }

  // check enemy count, if == 0, end battle, change mode
  public static void battleEnd(Terminal terminal) {
    if(enemies.isEmpty()) {
      // change mode --> world map
      mode = 0;

      terminal.clearScreen();
    }

  }

  public static void updateScreen(Terminal terminal) {
    terminal.clearScreen();
    // loops through list of enemies and places them on the map
    for(int i=0; i<enemies.size(); i++) {
      putString(8, 5 + (i * 3), terminal, enemies.get(i).getName());
      putString(8, 6 + (i * 3), terminal, "" + enemies.get(i).getHitpoints());
    }

    // loops through list of players and places them on the map
    for(int i=0; i<players.size(); i++) {
      putString(30, 5 + (i * 3), terminal, "" + players.get(i).getName().charAt(0));
      putString(30, 6 + (i * 3), terminal, "" + players.get(i).getHitpoints());
    }

    putString(6, 5 + (enemies.size() * 3), terminal, "------------------------------------------------------");

    for(int i=0; i<players.size(); i++) {
      putString(8, 7 + (enemies.size() * 3) + i, terminal, players.get(i).getName());
      putString(27, 7 + (enemies.size() * 3) + i, terminal, "HP " + players.get(i).getHitpoints() + " / " + players.get(i).getMaxHitpoints() + "    MP " + players.get(i).getManaPoints() + "    LIMIT " + players.get(i).getDamage_taken() + " / 100");
    }
  }

  public static void enemySelect(Player player, ArrayList<Enemy> enemies, Terminal terminal) {
    int cursor_xpos = 14;
    int cursor_ypos = 7 + (enemies.size() * 3);
    String cursor = "\u261B";   // default right pointing cursor

    while(true) {

      putString(cursor_xpos, cursor_ypos, terminal, cursor);

      Key key = terminal.readInput();

      updateScreen(terminal);
      putString(17, 7 + (enemies.size() * 3), terminal, "Attack");
      putString(17, 8 + (enemies.size() * 3), terminal, "Magic");

      if(key != null) {

        if(key.getCharacter() == 'w') {
          if(cursor.equals("\u261B")) {    // if up action is permitted
            if(cursor_ypos == 8) { cursor_ypos -= 1; }
          } else {
            cursor_ypos -= 3;
          }
        }

        if(key.getCharacter() == 's') {
          if(cursor.equals("\u261B")) {   // if down action is permitted
            if(cursor_ypos == 7) { cursor_ypos += 1; }
          } else {
            cursor_ypos += 3;
          }
        }

        if(key.getCharacter() == 'j') {
          if(cursor.equals("\u261B")) {
            if( cursor_ypos == 8 + (enemies.size() * 3)) {
              Physical = false;
            }

            cursor = "\u261A";

            // sets the cursor location to enemy selection
            cursor_xpos = 15;
            cursor_ypos = 5;
          } else {
            if(Physical){
              player.attack(enemies.get((cursor_ypos - 5) / 3));
            } else {
              player.magicAttack(enemies.get((cursor_ypos - 5) / 3));
            }
            return;
          }
        }
      }
    }
  }

  public static void main(String[] args) {

    mode = 0;
    players = new ArrayList<Player>();
    enemies = new ArrayList<Enemy>();

    // instance of Player "Cloud"
    //                        name     hp     atk  dex  vit  mag  spt  lck  mnp  lvl
    Player Cloud = new Player("Cloud", 314,   20,  6,   16,  19,  17,  14,  54,  6  );
    // add Cloud to list of Players
    players.add(Cloud);

//  // instance of Player "Barret"
//  //                         name        hp     atk  dex  vit  mag  spt  lck  mnp  lvl
//  Player Barret = new Player("Barret",   222,   15,  5,   13,  11,  9,   13,  15,  1  );
//  // add Barret to list of Players
//  Players.add(Barret);

    // init map
    Map map = new Map();

    // set up Terminal
    Terminal terminal = TerminalFacade.createTextTerminal();
    terminal.enterPrivateMode();

    TerminalSize terminalSize = terminal.getTerminalSize();
    terminal.setCursorVisible(false);

    int x = 8;
    int y = 12;

    // first battle
    boolean f = true;

    int k = 0;

    while(true) {
      currentTime = System.currentTimeMillis();

      // mode: world map
      if(mode == 0) {
        putString(0, 0, terminal, map.toString());

        terminal.moveCursor(x, y);
        terminal.putCharacter('C');

        Key key = terminal.readInput();

        if(key != null) {

          if(key.getKind() == Key.Kind.Escape) {
  					terminal.exitPrivateMode();
  					System.exit(0);
  				}

          // arrow control scheme
          if(key.getCharacter() == 'w') {
            if((map.canMove(x/2, y, 0, -1) != 1)) {
              if((map.canMove(x/2, y, 0, -1) == 2)) { terminal.clearScreen(); }
              terminal.moveCursor(x, y);
    					terminal.putCharacter(' ');
    					y -= 1;
              encounter(map, terminal);
              terminal.clearScreen();
            }
  				}

  				if(key.getCharacter() == 's') {
            if((map.canMove(x/2, y, 0, 1) != 1)) {
              if((map.canMove(x/2, y, 0, 1) == 2)) { terminal.clearScreen(); }
              terminal.moveCursor(x, y);
    					terminal.putCharacter(' ');
    					y += 1;
              encounter(map, terminal);
              terminal.clearScreen();
            }
  				}

          if (key.getCharacter() == 'a') {
            if((map.canMove(x/2, y, -1, 0) != 1)) {
              if((map.canMove(x/2, y, -1, 0) == 2)) { terminal.clearScreen(); }
              terminal.moveCursor(x, y);
    					terminal.putCharacter(' ');
    					x -= 2;
              encounter(map, terminal);
              terminal.clearScreen();
            }
          }

          if (key.getCharacter() == 'd') {
            if((map.canMove(x/2, y, 1, 0) !=1)) {
              if((map.canMove(x/2, y, 1, 0) == 2)) { terminal.clearScreen(); }
              terminal.moveCursor(x, y);
    					terminal.putCharacter(' ');
    					x += 2;
              encounter(map, terminal);
              terminal.clearScreen();
            }
          }

        }

        // initiating first battle (tutorial minus the things that make it a tutorial)
        if(f && (y <= 6)) {
          // spawn two MilitaryPolice for the first battle
          enemies.add(new MilitaryPolice(30, 6, 50, 4, 0, 0, 4));
          enemies.add(new MilitaryPolice(30, 6, 50, 4, 0, 0, 4));

          // never touch first battle ever again please no
          f = false;

          // change mode --> battle
          mode = 1;
        }

      // mode: battle
      } else if(mode == 1) {
        for(int i=0; i<players.size(); i++) {
          enemySelect(players.get(i), enemies, terminal);
          remove();
          updateScreen(terminal);
          wait(1000);
        }

        for(int i = 0; i < enemies.size(); i++) {
          enemies.get(k).attack(enemies.get(k).selectTarget(players), 5, 10);
          updateScreen(terminal);
          wait(1000);
        }

        battleEnd(terminal);

      }

    }

  }

}
