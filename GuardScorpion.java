public class GuardScorpion extends Squishy {

  private boolean tail_raised;

  public GuardScorpion(int hitpoints, int strength, int dexterity, int vitality, int magic, int spirit, int luck) {
    super(hitpoints, strength, dexterity, vitality, magic, spirit, luck);
    tail_raised = false;

  }

  public void attack(Squishy other) {
    // do the attack thingy

  }

  public void tailLaser(Squishy other) {
    // do the attack with the tail Laser
    // when attacked when tailUp is true, do tailLaser
  }

}
