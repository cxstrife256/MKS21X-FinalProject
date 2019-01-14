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

  public void takeDamage(int damage) {
    hitpoints -= damage;

  }

}
