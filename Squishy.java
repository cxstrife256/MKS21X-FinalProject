public abstract class Squishy {

  private int hitpoints;
  private int strength;
  private int dexterity;
  private int vitality;
  private int magic;
  private int spirit;
  private int luck;

  private int attack;              // strength          + weapon attack bonus
  private int attack_percent;      //                     weapon attack% bonus
  private int defense;             // vitality          + armour defense bonus
  private int defense_percent;     // [ dexterity / 4 ] + armour defense% bonus
  private int magic_atk;           // magic
  private int magic_def;           // spirit            + armour mdefense bonus
  private int magic_def_percent;   //                     armour mdefense% bonus

  public Squishy(int hitpoints, int strength, int dexterity, int vitality, int magic, int spirit, int luck) {
    this.hitpoints = hitpoints;
    this.strength = strength;
    this.dexterity = dexterity;
    this.vitality = vitality;
    this.magic = magic;
    this.spirit = spirit;
    this.luck = luck;

  }

  public void attack(Squishy other) {
    // do the attack thingy
    // count the dead

  }

  public int getAttack() {
    return attack;

  }

  public int getDefense(){
    return this.vitality; // returns viatlity, weapons not yet added send help

  }

  public void setHitpoints(int value) {
    hitpoints = value; //

  }

  public int getHitpoints() {
    return hitpoints;
    
  }

  public void takeDamage(int damage) {
    // reduce hitpoints by amount int damage

  }

}
