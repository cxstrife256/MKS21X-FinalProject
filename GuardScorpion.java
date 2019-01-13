public class GuardScorpion extends Squishy {

  private boolean tail_raised;

  public GuardScorpion(int hitpoints, int strength, int dexterity, int vitality, int magic, int spirit, int luck) {
    super(hitpoints, strength, dexterity, vitality, magic, spirit, luck);
    tail_raised = false;

  }

  public void attack(Squishy other) {
    /*
      if tail_raised is true
        use tailLaser for attack
        reset tail_raised to false
      else
        deal dmg by formula
    */

  }

  private void tailLaser(Squishy other) {
    /*
      used for attack when tail_raised is true
      deal 1.5x standard attack dmg to all enemies
    */

  }

}
