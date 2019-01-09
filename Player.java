public class Player extends Squishy {
  
  private int damage_taken;             // Damage taken to calculate limit
  private int manapoints;               // mana_points

  public void attack(Squishy A) {
    //do the attack thingy
  }

  public Player(int hitpoints, int strength, int dexterity, int vitality, int magic, int spirit, int luck, int mana_points) {
    hitpoints = hitpoints;
    strength = strength;
    dexterity = dexterity;
    vitality = vitality;
    magic = magic;
    spirit = spirit;
    luck = luck;
    mana_points = mana_points;

  }

}
