public class Player extends Attackable{
  private int damage_taken;

  public void attack( Attackable A ) {
    //attack
  }

  public Player( int hp, int atk, int spd, int def, int mag, int res, int skill){
    int hitpoints = hp;
    int strength = atk;
    int dexterity = spd;
    int vitality = def;
    int magic = mag;
    int spirit = res;
    int luck = skill;
  }
}
