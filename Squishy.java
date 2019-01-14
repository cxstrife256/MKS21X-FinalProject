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

  public void attack (Squishy target, int value) {
    //The Physical Formula for Base Damage is defined as such:
    //Base Damage = Att + [(Att + Lvl) / 32] * [(Att * Lvl) / 32]
    int power = value;   //will equal something based on what attack they use
    int Base_Damage = attack + ( ( attack + 5) / 32 ) * (( attack * 5) / 32);
    int Damage = ( power * ( 512 - target.defense * Base_Damage) / ( 16 * 512));

    target.takeDamage( Damage);

  }

  public void takeDamage(int damage) {
    hitpoints -= damage;

  }

}
