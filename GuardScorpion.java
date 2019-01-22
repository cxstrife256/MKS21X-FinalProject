public class GuardScorpion extends Enemy {

  private boolean tail_raised;
  private boolean scanner;

  public GuardScorpion(int hitpoints, int strength, int dexterity, int vitality, int magic, int spirit, int luck) {
    super(hitpoints, strength, dexterity, vitality, magic, spirit, luck);
    tail_raised = false;
    scanner = false;

  }

  public String attack(Squishy target) {
    if (tail_raised) {
      tail_raised = false;
      return "" + super.attack(target, 8, 30);
    } else {
      if(scanner) {
        scanner = false;
        return "" + super.attack(target, 8, 16);
      } else {
        scanner = true;
        return "scanning";
      }
    }

  }


  public void takeDamage(int damage) {
    super.takeDamage(damage);
    tail_raised = true;

  }

}
