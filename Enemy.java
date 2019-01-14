import java.util.ArrayList;

public class Enemy extends Squishy {

  public Enemy(int hitpoints, int strength, int dexterity, int vitality, int magic, int spirit, int luck) {
    super(hitpoints, strength, dexterity, vitality, magic, spirit, luck);

  }

  public void attack(Squishy target, int power) {
    // the Physical Formula for Base Damage is defined as such:
    // Base Damage = Att + [(Att + Lvl) / 32] * [(Att * Lvl) / 32]
    int base_damage = getAttack() + ((getAttack() + 5) / 32) * ((getAttack() * 5) / 32);

    // Ability Power and the Defense Stat are then used:
    // Damage = [(Power * (512 - Def) * Base Damage) / (16 * 512)]
    int damage = ((power * (512 - target.getDefense()) * base_damage) / (16 * 512));

    target.takeDamage(damage);

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
