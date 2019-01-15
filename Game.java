import java.util.ArrayList;
public class Game{
  public static void main( String[] args) {

    ArrayList<Squishy> Players = new ArrayList<Squishy>();

    // init instance of player "Cloud" and add Cloud to list of players
    Player Cloud = new Player("Cloud", 314, 20, 6, 16, 19, 17, 14, 54, 6);
    Players.add(Cloud);

    // int instance of player "Barret" but not added to list of players as he is not in the party yet
    Player Barret = new Player("Barret", 222, 15, 5, 13, 11, 9, 13, 15, 1);

    ArrayList<Squishy> Enemies = new ArrayList<Squishy>();

    // int instances of two MilitaryPolice as they are the first enemies that Cloud encounters
    MilitaryPolice MP1 = new MilitaryPolice(30, 6, 50, 4, 0, 0, 4);
    MilitaryPolice MP2 = new MilitaryPolice(30, 6, 50, 4, 0, 0, 4);

    Enemies.add(MP1);
    Enemies.add(MP2);
  }
}
