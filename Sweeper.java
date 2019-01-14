public class Sweeper extends Enemy {

  public Sweeper(int hitpoints, int strength, int dexterity, int vitality, int magic, int spirit, int luck) {
    super(hitpoints, strength, dexterity, vitality, magic, spirit, luck);

  }

  public void attack(Squishy target) {
    int counter = Math.abs((int)Math.random() * 10000) % 3;

    if(counter == 0){
      super.attack(target, 10); //this seems pointless, however flavor text will set them appart soon
    }
    if(counter == 1){
      super.attack(target, 10);
    }
    if(counter == 2){
      super.attack(target, 14);
    }

  }

}
