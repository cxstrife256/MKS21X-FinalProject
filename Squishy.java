import java.util.ArrayList;

abstract class Squishy {

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

    attack = strength;
    attack_percent = 100;
    defense = vitality;
    defense_percent = 100;
    magic_atk = magic;
    magic_def = spirit;
    magic_def_percent = 100;

  }

  public void attack(Squishy target, int level, int power) {
    // the Physical Formula for Base Damage is defined as such:
    // Base Damage = Att + [(Att + Lvl) / 32] * [(Att * Lvl) / 32]
    int base_damage = attack + ((attack + level) / 32) * ((attack * level) / 32);

    // Ability Power and the Defense Stat are then used:
    // Damage = [(Power * (512 - Def) * Base Damage) / (16 * 512)]
    int damage = ((power * (512 - target.defense) * base_damage) / (16 * 512));

    target.takeDamage(damage);

  }

  public void magic(Squishy target, int level, int power) {
    // the Magical Formula for Base Damage is defined as such:
    // Base Damage = 6 * (MAt + Lvl)
    int base_damage = 6 * (magic_atk + level);

    // Ability Power and the defense stat are then used
    //Damage = [(Power * (512 - MDf) * Base Damage) / (16 * 512)]
    int damage = ((power * ( 512 - target.magic_def) * base_damage) / (16 * 512));

    target.takeDamage(damage);
  }

  public void takeDamage(int damage) {
    hitpoints -= damage;

  }

  // basic accessor methods, test purposes
  public int getHitpoints() {
    return hitpoints;

  }

  // what in tarnation

  // get Player name
  public String getName() {
    return " ";

  }

  // select target at random
  public Squishy selectTarget(ArrayList<Squishy> targets) {
    return new Enemy(10, 5, 5, 5, 5, 5, 5);

  }


  public int getMaxHitpoints() {
    return 0;

  }

  public int getManaPoints()  {
    return 0;
  }

  public int getDamage_taken() {
    return 0;

  }

}
