import java.util.ArrayList;
public class Driver {

  public static void main(String[] args) {

    Player Cloud = new Player("Cloud", 314, 20, 6, 16, 19, 17, 14, 54, 6);
     // tonfa does 7 dmg
     // machine gun does 4
     // basic does 38/39 ouch
     // write this down somewhere

     ArrayList<Squishy> Players = new ArrayList<Squishy>();
     Players.add(Cloud);

     GuardDog Woof = new GuardDog(100, 10, 4, 13, 15, 14, 13);

     Woof.attack(Woof.selectTarget(Players));

     System.out.println(Cloud.getHitpoints());

     Player Barret = new Player("Barret", 300, 20, 6, 16, 19, 17, 14, 54, 6);
     Players.add(Barret);


     Woof.attack(Woof.selectTarget(Players));



     System.out.println(Cloud.getHitpoints());
     System.out.println(Barret.getHitpoints());

  }

}
