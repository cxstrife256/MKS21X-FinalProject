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

import com.googlecode.lanterna.screen.*;

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

  public static void printStr(Screen screen, int col, int row, String str, Terminal.Color foregroundColor, Terminal.Color backgroundColor) { // replace with screen
    int l = 0;
		for(int i=0; i<str.length(); i++) {
      screen.putString(col+l, row, "" + str.charAt(i), foregroundColor, backgroundColor);
      l++;
      if(str.charAt(i) == '\n') {
        l = 0;
        row += 1;
      }
		}

	}

  public static void wait(int time) {
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
  public static void encounter(Map map, Screen screen) {
    if(map.getID() != 0) {  // enemies do not spawn in the first room
      if(((int)(Math.random() * 10000) % 100) < 4) {  // 4% chance upon step to get it spicy
        battleStart(screen);
      }

    }
  }

  // starts a random encounter
  public static void battleStart(Screen screen) {
    // change mode --> battle
    mode = 1;

    screen.clear();
    screen.refresh();
    wait(500);
    enemySetup();

  }

  // check enemy count, if == 0, end battle, change mode
  public static void battleEnd(Screen screen) {
    if(enemies.isEmpty()) {
      // change mode --> world map
      screen.clear();
      screen.refresh();
      mode = 0;

    }

  }

  public static void update(Screen screen) {
    screen.clear();

    // loops through list of enemies and places them on the map
    for(int i=0; i<enemies.size(); i++) {
      printStr(screen, 8, 5 + (i * 3), enemies.get(i).getName(), Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
      if(enemies.get(i).getHitpoints() < 0) {
        printStr(screen, 8, 6 + (i * 3), "0", Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
      } else {
        printStr(screen, 8, 6 + (i * 3), "" + enemies.get(i).getHitpoints(), Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
      }
    }

    // loops through list of players and places them on the map
    for(int i=0; i<players.size(); i++) {
      printStr(screen, 30, 5 + (i * 3), "" + players.get(i).getName().charAt(0), Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
      printStr(screen, 30, 6 + (i * 3), "" + players.get(i).getHitpoints(), Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
    }

    printStr(screen, 6, 5 + (enemies.size() * 3), "------------------------------------------------------", Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);

    for(int i=0; i<players.size(); i++) {
      printStr(screen, 8, 9 + (enemies.size() * 3) + i, players.get(i).getName(), Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
      printStr(screen, 27, 9 + (enemies.size() * 3) + i, "HP " + players.get(i).getHitpoints() + " / " + players.get(i).getMaxHitpoints() + "    MP " + players.get(i).getManaPoints() + "    LIMIT " + players.get(i).getDamage_taken() + " / 100", Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
    }

    printStr(screen, 6, 7 + (enemies.size() * 3), "> " , Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
  }

  public static void enemySelect(Player player, ArrayList<Enemy> enemies, Terminal terminal, Screen screen) {
    int cursor_xpos = 14;
    int cursor_ypos = 9 + (enemies.size() * 3);
    String cursor = "\u261B";   // default right pointing cursor
    boolean physical = true;    // determines what type of attack

    String flavortext = "> " + player.getName() + " : ";
    String temp = "";

    screen.clear();

    update(screen);

    printStr(screen, cursor_xpos, cursor_ypos, cursor, Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);          // puts the cursor
    printStr(screen, 6, 7 + (enemies.size() * 3), flavortext, Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);   // puts the flavortext

    printStr(screen, 17, 9 + (enemies.size() * 3), "Attack", Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
    printStr(screen, 17, 10 + (enemies.size() * 3),  "Magic", Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);

    screen.refresh();

    while(true) {
      printStr(screen, cursor_xpos, cursor_ypos, cursor, Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
      printStr(screen, 6, 7 + (enemies.size() * 3), flavortext, Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);

      Key key = terminal.readInput();

      printStr(screen, 17, 9 + (enemies.size() * 3), "Attack", Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
      printStr(screen, 17, 10 + (enemies.size() * 3),  "Magic", Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);

      if(key != null) {

        if(key.getCharacter() == 'w') {
          if(cursor.equals("\u261B")) {    // if up action is permitted
            if(cursor_ypos == 10 + (enemies.size() * 3)) {
              cursor_ypos -= 1;

              update(screen);
              printStr(screen, cursor_xpos, cursor_ypos, cursor, Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
              printStr(screen, 6, 7 + (enemies.size() * 3), flavortext, Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);

              printStr(screen, 17, 9 + (enemies.size() * 3), "Attack", Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
              printStr(screen, 17, 10 + (enemies.size() * 3),  "Magic", Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
              screen.refresh();
            }
          } else {
            if(cursor_ypos != 5) {
              cursor_ypos -= 3;
              flavortext = temp;
              flavortext += enemies.get((cursor_ypos - 5) / 3).getName() + " " + ((cursor_ypos - 5) / 3) + " : ";

              update(screen);
              printStr(screen, cursor_xpos, cursor_ypos, cursor, Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
              printStr(screen, 6, 7 + (enemies.size() * 3), flavortext, Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);

              printStr(screen, 17, 9 + (enemies.size() * 3), "Attack", Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
              printStr(screen, 17, 10 + (enemies.size() * 3),  "Magic", Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
              screen.refresh();

              flavortext += enemies.get((cursor_ypos - 5) / 3).getName() + " " + ((cursor_ypos - 5) / 3) + " : ";
            }
          }
        }

        if(key.getCharacter() == 's') {
          if(cursor.equals("\u261B")) {   // if down action is permitted
            if(cursor_ypos == 9 + (enemies.size() * 3)) {
                cursor_ypos += 1;

                update(screen);
                printStr(screen, cursor_xpos, cursor_ypos, cursor, Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
                printStr(screen, 6, 7 + (enemies.size() * 3), flavortext, Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);

                printStr(screen, 17, 9 + (enemies.size() * 3), "Attack", Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
                printStr(screen, 17, 10 + (enemies.size() * 3),  "Magic", Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
                screen.refresh();
            }
          } else {
            if(cursor_ypos != 2 + (enemies.size() * 3)) {
              cursor_ypos += 3;
              flavortext = temp;
              flavortext += enemies.get((cursor_ypos - 5) / 3).getName() + " " + ((cursor_ypos - 5) / 3) + " : ";

              update(screen);
              printStr(screen, cursor_xpos, cursor_ypos, cursor, Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
              printStr(screen, 6, 7 + (enemies.size() * 3), flavortext, Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);

              printStr(screen, 17, 9 + (enemies.size() * 3), "Attack", Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
              printStr(screen, 17, 10 + (enemies.size() * 3),  "Magic", Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);

              screen.refresh();
            }
          }
        }

        if(key.getCharacter() == 'j') {    // selection
          if(cursor.equals("\u261B")) {
            if(cursor_ypos == 10 + (enemies.size() * 3)) {
              physical = false;
              flavortext += "MAGIC : ";
              temp = flavortext;
            } else {
              physical = true;
              flavortext += "ATTACK : ";
              temp = flavortext;
            }

            cursor = "\u261A";

            // sets the cursor location to enemy selection
            cursor_xpos = 15;
            cursor_ypos = 5;
            update(screen);
            printStr(screen, cursor_xpos, cursor_ypos, cursor, Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
            printStr(screen, 6, 7 + (enemies.size() * 3), flavortext, Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);

            printStr(screen, 17, 9 + (enemies.size() * 3), "Attack", Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
            printStr(screen, 17, 10 + (enemies.size() * 3),  "Magic", Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
            screen.refresh();

            flavortext = temp;
            flavortext += enemies.get((cursor_ypos - 5) / 3).getName() + " " + ((cursor_ypos - 5) / 3) + " : ";
            update(screen);
            printStr(screen, cursor_xpos, cursor_ypos, cursor, Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
            printStr(screen, 6, 7 + (enemies.size() * 3), flavortext, Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);

            printStr(screen, 17, 9 + (enemies.size() * 3), "Attack", Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
            printStr(screen, 17, 10 + (enemies.size() * 3),  "Magic", Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
            screen.refresh();
          } else {
            if(physical) {
              flavortext += player.attack(enemies.get((cursor_ypos - 5) / 3));
              printStr(screen, 6, 7 + (enemies.size() * 3), flavortext, Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
              update(screen);
              printStr(screen, cursor_xpos, cursor_ypos, cursor, Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
              printStr(screen, 6, 7 + (enemies.size() * 3), flavortext, Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);

              printStr(screen, 17, 9 + (enemies.size() * 3), "Attack", Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
              printStr(screen, 17, 10 + (enemies.size() * 3),  "Magic", Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
              screen.refresh();
              wait(1000);
            } else {
              flavortext += player.magicAttack(enemies.get((cursor_ypos - 5) / 3));
              printStr(screen, 6, 7 + (enemies.size() * 3), flavortext, Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
              update(screen);
              printStr(screen, cursor_xpos, cursor_ypos, cursor, Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
              printStr(screen, 6, 7 + (enemies.size() * 3), flavortext, Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);

              printStr(screen, 17, 9 + (enemies.size() * 3), "Attack", Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
              printStr(screen, 17, 10 + (enemies.size() * 3),  "Magic", Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
              screen.refresh();
              wait(1000);
            }
            return;
          }
        }

        if(key.getCharacter() == 'k') {   // go back to select an attack
          if(cursor.equals("\u261A")) {
            cursor = "\u261B";

            flavortext = "> " + player.getName() + " : ";

            // sets the cursor location to attack selection
            cursor_xpos = 14;
            cursor_ypos = 9 + (enemies.size() * 3);
          }
          update(screen);
          printStr(screen, cursor_xpos, cursor_ypos, cursor, Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
          printStr(screen, 6, 7 + (enemies.size() * 3), flavortext, Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);

          printStr(screen, 17, 9 + (enemies.size() * 3), "Attack", Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
          printStr(screen, 17, 10 + (enemies.size() * 3),  "Magic", Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
          screen.refresh();
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
    Screen screen = new Screen(terminal);
    screen.startScreen();

    TerminalSize terminalSize = terminal.getTerminalSize();
    terminal.setCursorVisible(false);

    int x = 8;   // default position of cloud
    int y = 12;

    // first battle
    boolean f = true;

    // boos battle
    boolean b = true;


    while(true) {

      // mode: world map
      if(mode == 0) {
        screen.clear();
        printStr(screen, 0, 0, map.toString(), Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);

        printStr(screen, x, y, "C", Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);  // cloud
        screen.refresh();

        Key key = terminal.readInput();  // gets key inputs

        if(key != null) {

          if(key.getKind() == Key.Kind.Escape) {  // escape to exit
  					terminal.exitPrivateMode();
  					System.exit(0);
  				}

          // arrow control scheme
          if(key.getCharacter() == 'w') {            // w to move up
            if((map.canMove(x/2, y, 0, -1) != 1)) {
              if((map.canMove(x/2, y, 0, -1) == 2)) { screen.clear(); }
    					y -= 1;
              encounter(map, screen);                //  chance to encounter a battle
              screen.refresh();
            }
  				}

  				if(key.getCharacter() == 's') {            // s to move down
            if((map.canMove(x/2, y, 0, 1) != 1)) {
              if((map.canMove(x/2, y, 0, 1) == 2)) { screen.clear(); }
    					y += 1;
              encounter(map, screen);                // chance to encounter a battle
              screen.refresh();
            }
  				}

          if (key.getCharacter() == 'a') {            // a to move left
            if((map.canMove(x/2, y, -1, 0) != 1)) {
              if((map.canMove(x/2, y, -1, 0) == 2)) { screen.clear(); }
    					x -= 2;
              encounter(map, screen);                 // chance to encounter a battle
              screen.refresh();
            }
          }

          if (key.getCharacter() == 'd') {             // d to move right
            if((map.canMove(x/2, y, 1, 0) !=1)) {
              if((map.canMove(x/2, y, 1, 0) == 2)) { screen.clear(); }
    					x += 2;
              encounter(map, screen);                  // chance to encounter a battle
              screen.refresh();
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
          screen.clear();
          screen.refresh();
          wait(500);
          mode = 1;
        }

        if(b && (y <= 4) && map.getID(3) == 0) {
          //Spawn in a GuardScorpion
          enemies.add(new GuardScorpion(800, 30, 60, 40, 15, 256, 1));

          // never touch boss battle ever again please no
          b = false;

          // change mode --> battle
          screen.clear();
          screen.refresh();
          wait(500);
          mode = 1;
        }

      // mode: battle
      } else if(mode == 1) {
        update(screen);

        //loops thought players and gives them the chance to attack
        for(int i=0; i<players.size(); i++) {
          enemySelect(players.get(i), enemies, terminal, screen);
          remove();
          update(screen);
          screen.refresh();

          // checks to see if the battle should end
          battleEnd(screen);

          screen.refresh();
          wait(1000); // delay
        }

        //loops through enemies and gives them the chance to attack
        for(int i=0; i<enemies.size(); i++) {
          Squishy target = enemies.get(i).selectTarget(players);
          printStr(screen, 6, 7 + (enemies.size() * 3), "> " + enemies.get(i).getName() + " " + i + " : ATTACK : " + target.getName() + " : " + enemies.get(i).attack(target, 5, 10), Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
          screen.refresh();
          wait(1500);
        }

        screen.clear();

      }

    }

  }

}
