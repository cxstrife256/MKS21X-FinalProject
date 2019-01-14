public class Grunt extends Squishy {

  public Grunt(int hitpoints, int strength, int dexterity, int vitality, int magic, int spirit, int luck) {
    super(hitpoints, strength, dexterity, vitality, magic, spirit, luck);

  }

  public void attack (Squishy target) {
    //The Physical Formula for Base Damage is defined as such:
    //Base Damage = Att + [(Att + Lvl) / 32] * [(Att * Lvl) / 32]
    int power = 1;   //will equal something based on what attack they use
    int Base_Damage = getAttack() + ( ( getAttack() + 5) / 32 ) * ( (getAttack() * 5) / 32);
    int Damage = ( power * ( 512 - target.getDefense() * Base_Damage) / ( 16 * 512));

    target.takeDamage( Damage);
  }

}
