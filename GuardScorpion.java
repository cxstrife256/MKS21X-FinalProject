public class GuardScorpion extends Enemy{

  private boolean tailUp;

  public void attack(Squishy other){
    //do the attack thingy
  }

  public void tailLaser(Squishy other){
    //do the attack with the tail Laser
    //when attacked when tailUp is true, do tailLaser
  }

  public GuardScorpion(int hitpoints, int strength, int dexterity, int vitality, int magic, int spirit, int luck) {
    this.hitpoints = hitpoints;
    this.strength = strength;
    this.dexterity = dexterity;
    this.vitality = vitality;
    this.magic = magic;
    this.spirit = spirit;
    this.luck = luck;

  }
}
