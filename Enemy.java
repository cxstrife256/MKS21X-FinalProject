import java.util.ArrayList;

public class Enemy extends Squishy {

  public Enemy(int hitpoints, int strength, int dexterity, int vitality, int magic, int spirit, int luck) {
    super(hitpoints, strength, dexterity, vitality, magic, spirit, luck);

  }


  public Squishy targetSelector(ArrayList<Squishy> targets) {
    if(targets.size() == 1 || (((Math.random() * 10000) % 2 ) == 0)) {
      return targets.get(0);
    }
    return targets.get(1);

  }

  // in enemy function Squishy targetSelector(ArrayList)
  // void attack(Squishy target)
  //   int damage = calculations based on target and attacker
  //   target.takeDamage(damage)

}
