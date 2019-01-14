public class Enemy extends Squishy {

  public Enemy(int hitpoints, int strength, int dexterity, int vitality, int magic, int spirit, int luck) {
    super(hitpoints, strength, dexterity, vitality, magic, spirit, luck);

  }

  public void takeDamage(int damage) {
    // takes damage equal to given int damage

  }

  // in enemy function Squishy targetSelector(ArrayList)
  // void attack()
  //   Squishy target = targetSelector(ArrayList)
  //   int damage = calculations based on target and attacker
  //   target.takeDamage(damage)

}
