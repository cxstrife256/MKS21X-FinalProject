public class Sweeper extends Enemy {

  public Sweeper(int hitpoints, int strength, int dexterity, int vitality, int magic, int spirit, int luck) {
    super(hitpoints, strength, dexterity, vitality, magic, spirit, luck);

  }

  public String attack(Squishy target) {
    int count = (int)(Math.random() * 10000) % 4;

    if(count == 0) {
      // Smoke Shot
      return super.attack(target, 11);

    } else if(count == 1) {
      // Machine Gun
      return super.attack(target, 7);

    } else {
      // W Machine Gun
      return super.attack(target, 14);

    }

  }

  public String getName() {
    return "S";

  }

}
