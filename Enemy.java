import java.util.ArrayList;

public class Enemy extends Squishy {

  public Enemy(int hitpoints, int strength, int dexterity, int vitality, int magic, int spirit, int luck) {
    super(hitpoints, strength, dexterity, vitality, magic, spirit, luck);

  }

  public String attack(Squishy target, int power) {
    return super.attack(target, 5, power);

  }

  public Squishy selectTarget(ArrayList<Player> targets) {
    return targets.get((int)(Math.random() * 10000) % targets.size());

  }

  public String getName() {
    return " ";
  }

}
