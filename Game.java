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

import java.util.concurrent.TimeUnit;

public class Game {

  // display
  /* 0 : world map
     1 : battle
     2 : menu
  */
  private static int mode;
  private static ArrayList<Squishy> players;
  private static ArrayList<Squishy> enemies;

  public static void putString(int row, int col, Terminal t, String str) { // replace with screen
		t.moveCursor(row, col);
		for(int i=0; i<str.length(); i++) {
			t.putCharacter(str.charAt(i));
		}

	}

  public static void wait(int sec) {
    try {
      TimeUnit.SECONDS.sleep(1);
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

  public static void main(String[] args) {

    mode = 0;
    players = new ArrayList<Squishy>();
    enemies = new ArrayList<Squishy>();

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

    int x = 12;
    int y = 12;

    // first battle
    boolean f = true;

    while(true) {
      //terminal.clearScreen();

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
          if(key.getKind() == Key.Kind.ArrowUp) {
            if((map.canMove(x/2, y, 0, -1) != 1)) {
              if((map.canMove(x/2, y, 0, -1) == 2)) { terminal.clearScreen(); }
              terminal.moveCursor(x, y);
    					terminal.putCharacter(' ');
    					y -= 1;
              encounter(map, terminal);
              terminal.clearScreen();
            }
  				}

  				if(key.getKind() == Key.Kind.ArrowDown) {
            if((map.canMove(x/2, y, 0, 1) != 1)) {
              if((map.canMove(x/2, y, 0, 1) == 2)) { terminal.clearScreen(); }
              terminal.moveCursor(x, y);
    					terminal.putCharacter(' ');
    					y += 1;
              encounter(map, terminal);
              terminal.clearScreen();
            }
  				}

          if (key.getKind() == Key.Kind.ArrowLeft) {
            if((map.canMove(x/2, y, -1, 0) != 1)) {
              if((map.canMove(x/2, y, -1, 0) == 2)) { terminal.clearScreen(); }
              terminal.moveCursor(x, y);
    					terminal.putCharacter(' ');
    					x -= 2;
              encounter(map, terminal);
              terminal.clearScreen();
            }
          }

          if (key.getKind() == Key.Kind.ArrowRight) {
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

        putString(6, 5 + (enemies.size() * 3), terminal, "---------------------------------------");

        for(int i=0; i<players.size(); i++) {
          putString(8, 7 + i, terminal, players.get(i).getName());
          putString(27, 7 + i, terminal, "HP " + players.get(i).getHitpoints() + " / " + players.get(i).getMaxHitpoints() + "    MP " + players.get(i).getManaPoints() + "    LIMIT " + players.get(i).getDamage_taken() + " / 100");
        }

        Cloud.attack(enemies.get(0), 12);
        remove();
        wait(1);
        /*
        for(int i = 0; i < players.length; i++) { 
        Squishy target = getTarget();
          players[i].attack(target, power)
          remove();
        }

        MP   <                C
        16                    296


        MP   <
        30

        ---------------------------------------
        for(int i=0; i<players.size(); i++) {
          putString(8, 7 + i, terminal, players[i].getName());
          putString(27, 7 + i, "HP " + players[i].getHitpoints() " / " + players.getMaxHitpoints + "    MP " + player.getManaPoints() + "    LIMIT " + players[i].getDamage_taken() + " / 100");
        }
        Cloud                   HP 350 / 350    MP 47    LIMIT 0 / 100
        Barret                  HP 350 / 350    MP 37    LIMIT 0 / 100

      public static Squishy getTarget() {
      allows player to select a target
      a menu on the bottom screen below the -----------------------
      boolean selection = true
      while(selection) {
      terminal.clearScreen()
      int pos = 0;
      for( int i = 0; i < enemies.size(); i++ ) {
        pos = 8 + i + (enemies.size() * 3
        putString(6, pos, terminal, enemies.getName());
      }
      have the same arrow moving up and down methods and other one to get ENTER

      have the cursor move acording the name position,
      when moving up, check to see if y cord of the cursor is at 8 + (enemies.size() * 3), if not than move up can do stuff
      when moving down, check to see if the y cord of the cursors is at pos , if not then move down can do stuff

      when enter is press, the follow math thing is done

      int enemythin = ( y - 8 - enemies.size() * 3)

      return enemies[enemythin];
    }



    }




        */

        for(int i=0; i<enemies.size(); i++) {
          enemies.get(i).attack(enemies.get(i).selectTarget(players), 5, 10);
          remove();
        }
        wait(1);

        battleEnd(terminal);

      }

    }

  }

}
