public class MilitaryPolice extends Enemy {

  public MilitaryPolice(int hitpoints, int strength, int dexterity, int vitality, int magic, int spirit, int luck) {
    super(hitpoints, strength, dexterity, vitality, magic, spirit, luck);

  }

  public void attack(Squishy target) {
    if( Math.random() * 10000 % 2 == 0){
      super.attack(target, 10);
    } else {
      super.attack(target, 13);
    }

  }

}
