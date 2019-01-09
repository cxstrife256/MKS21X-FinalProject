public class Player extends Squishy {

  private int damage_taken;             // Damage taken to calculate limit
  private int manapoints;               // mana_points

  public void attack(Squishy A) {
    //do the attack thingy
  }

  public Player(int hitpoints, int strength, int dexterity, int vitality, int magic, int spirit, int luck, int manapoints) {
    this.hitpoints = hitpoints;
    this.strength = strength;
    this.dexterity = dexterity;
    this.vitality = vitality;
    this.magic = magic;
    this.spirit = spirit;
    this.luck = luck;
    this.manapoints = manapoints;

  }

}
