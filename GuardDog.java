public class GuardDog extends Enemy{

  public GuardDog(int hitpoints, int strength, int dexterity, int vitality, int magic, int spirit, int luck) {
    super(hitpoints, strength, dexterity, vitality, magic, spirit, luck);

  }

  public String attack(Squishy target) {
    if((int)(Math.random() * 10000) % 3 == 0) {
      // Tentacle
      return super.attack(target, 10);

    } else {
      // Bite
      return super.attack(target, 10);

    }

  }

  public String getName() {
    return "GD";

  }

}
