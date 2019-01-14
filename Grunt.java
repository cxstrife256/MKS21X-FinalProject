public class Grunt extends Enemy {

  public Grunt(int hitpoints, int strength, int dexterity, int vitality, int magic, int spirit, int luck) {
    super(hitpoints, strength, dexterity, vitality, magic, spirit, luck);

  }

  public void attack(Squishy target) {
    if((int)Math.random() * 10000 % 2 == 0){
      super.attack(target, 7);   //handclaw
    }else{
      super.attack(target, 10);  //beam gun
    }
    
  }

}
