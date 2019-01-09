public abstract class Squishy {

  public int hitpoints;
  public int strength;
  public int dexterity;
  public int vitality;
  public int magic;
  public int spirit;
  public int luck;

  public int attack;              // strength          + weapon attack bonus
  public int attack_percent;      //                     weapon attack% bonus
  public int defense;             // vitality          + armour defense bonus
  public int defense_percent;     // [ dexterity / 4 ] + armour defense% bonus
  public int magic_atk;           // magic
  public int magic_def;           // spirit            + armour mdefense bonus
  public int magic_def_percent;   //                     armour mdefense% bonus

  public void attack(Squishy other) {
    // do the attack thingy

  }

}
