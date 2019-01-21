public class GuardDog extends Enemy{

  public GuardDog(int hitpoints, int strength, int dexterity, int vitality, int magic, int spirit, int luck) {
    super(hitpoints, strength, dexterity, vitality, magic, spirit, luck);

  }

  public void attack(Squishy target) {
    if((int)(Math.random() * 10000) % 3 == 0) {
      // Tentacle
      super.attack(target, 10);

    } else {
      // Bite
      super.attack(target, 10);

    }

  }

  public String getName() {
    return "GD";

  }

}
