import java.util.ArrayList;
public class Driver_Game{

  private static int mode;
  private static ArrayList<Squishy> Enemies;
  private static ArrayList<Squishy> Players;

  // starts a battle
  public static void battleStart() {
    int enemycount = (int)(Math.random() * 10000) % 2;
    for( int i = 0;i < enemycount; i++){
      Enemies.add( new MilitaryPolice(30, 6, 50, 4, 0, 0, 4)); // for now the defaul enemy is MilitaryPolice
      }
      mode++;
      mode = mode % 2;
    }

  // ends a battle
  public static boolean battleEnd() {
    if(Enemies.isEmpty()){ // check to see if all the enemies are dead
      mode++;
      mode = mode % 2;
      return true;
    }
    return false;

  }


  public static void main(String[] args){
    Players = new ArrayList<Squishy>();

    // instance of Player "Cloud"
    //                        name     hp     atk  dex  vit  mag  spt  lck  mnp  lvl
    Player Cloud = new Player("Cloud", 314,   20,  6,   16,  19,  17,  14,  54,  6  );
    // add Cloud to list of Players
    Players.add(Cloud);

//  Player Barret = new Player("Barret",   222,  15,  5,  13,  11,  9,  13,  15,  1  );
//  Players.add(Barret);

    Enemies = new ArrayList<Squishy>();

    // instances of two MilitaryPolice as they are the first enemies that Cloud encounters
    MilitaryPolice Emmet = new MilitaryPolice(30, 6, 50, 4, 0, 0, 4);
    MilitaryPolice Bernard = new MilitaryPolice(30, 6, 50, 4, 0, 0, 4);

    Enemies.add(Emmet);
    Enemies.add(Bernard);

    // init map
    Map map = new Map();

    mode = 0;

    System.out.println(map);

    battleStart();

    System.out.println(mode);         // should be 1

    System.out.println(Enemies);      // should be an amount of enemies

    System.out.println(battleEnd());  // should be false

    System.out.println(mode);         // should be 1

    Enemies.clear();                  // "kills" the enemies haha

    System.out.println(battleEnd());  // should be true

    System.out.println(mode);         // should be 0
  }
}
