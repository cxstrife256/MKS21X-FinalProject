public class GuardScorpion extends Squishy {

  private boolean tail_raised;
  private boolean scanner;

  public GuardScorpion(int hitpoints, int strength, int dexterity, int vitality, int magic, int spirit, int luck) {
    super(hitpoints, strength, dexterity, vitality, magic, spirit, luck);
    tail_raised = false;

  }

  public String attack(Squishy target) {
    if (tail_raised){
      return "" + super.attack(target, 8, 30);
      tail_raised = false;
    } else {
      if( scanner == true) {
        return "" + super.attack(target, 8, 16);
      } else {
        scanner = false;
        return "Scanning";
      }
    }

  }


  public void takeDamage(int damage) {
    super.takeDamage(damage); 
    tail_raised = true;

  }

}
