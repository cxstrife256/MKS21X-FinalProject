public class GuardDog extends Enemy{

  public GuardDog(int hitpoints, int strength, int dexterity, int vitality, int magic, int spirit, int luck) {
    super(hitpoints, strength, dexterity, vitality, magic, spirit, luck);

  }

  public void attack(Squishy target) {
    super.attack(target, 10);

  }
}
