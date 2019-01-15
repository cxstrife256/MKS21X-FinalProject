import java.util.ArrayList;
public class ClassStructure_Driver {

  public static void main(String[] args) {

    ArrayList<Squishy> players = new ArrayList<Squishy>();

    // init instance "Cloud" of Player
    Player Cloud = new Player("Cloud", 314, 20, 6, 16, 19, 17, 14, 54, 6);
    players.add(Cloud);

    // init instance "Woof" of GuardDog of Enemy
    GuardDog Woof = new GuardDog(42, 8, 64, 2, 4, 2, 6);

    // select Player at random, only "Cloud"
    Woof.attack(Woof.selectTarget(players));
    // check for reduced hitpoints
    System.out.println(Cloud.getHitpoints());

    // init instance "Barret" of Player
    Player Barret = new Player("Barret", 222, 15, 5, 13, 11, 9, 13, 15, 1);
    players.add(Barret);

    // select Player at random, "Cloud"/"Barret"
    Woof.attack(Woof.selectTarget(players));
    // check for reduced hitpoints
    System.out.println(Cloud.getHitpoints());
    System.out.println(Barret.getHitpoints());

    // select Enemy, only "Woof"
    Cloud.attack(Woof, 39);

    // check for reduced hitpoints
    System.out.println(Woof.getHitpoints());

  }

}
