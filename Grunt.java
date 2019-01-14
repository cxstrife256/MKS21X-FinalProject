public class Grunt extends Enemy {

  public Grunt(int hitpoints, int strength, int dexterity, int vitality, int magic, int spirit, int luck) {
    super(hitpoints, strength, dexterity, vitality, magic, spirit, luck);

  }

  public void attack() {
    int power = 10;
    attack(targetSelector(Players), power);
  }

}
