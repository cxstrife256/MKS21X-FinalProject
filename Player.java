public class Player extends Squishy {

  private String name;

  private int level;                // playable character level
  private int damage_taken;         // damage taken to calculate limit
  private int experiencepoints;
  private int manapoints;
  private int maxHitpoints;

  public Player(String name, int hitpoints, int strength, int dexterity, int vitality, int magic, int spirit, int luck, int manapoints, int level) {
    super(hitpoints, strength, dexterity, vitality, magic, spirit, luck);
    this.name = name;
    this.manapoints = manapoints;
    this.level = level;
    damage_taken = 0;
    maxHitpoints = hitpoints;

  }

  public int attack(Squishy target) {
    if(name.equals("Cloud")) {
      // enemy gets one shotted basically
      return super.attack(target, level, 26);

    } else if(name.equals("Barret")) {
      // enemy gets kinda one shotted but honestly not really
      // Barret with a capital F to pay respects
      return super.attack(target, level, 18);

    } else {
      // you don't belong in this world, monster! - richter belmont

    }

    return 0;

  }

  public int magicAttack(Squishy target) {
    if(name.equals("Cloud")) {
      // enemy gets one shotted basically
      return super.magicAttack(target, level, 100);

    } else if(name.equals("Barret")) {
      // enemy gets kinda one shotted but honestly not really
      // Barret with a capital F to pay respects
      return super.magicAttack(target, level, 100);

    } else {
      // you again? Come, I'll vanquish you every time! - simon belmont

    }

    return 0;

  }



  public void takeDamage(int damage) {
    super.takeDamage(damage);
    damage_taken += damage;
    // do something (who knows) with limit

  }

  public String getName() {
    return name;

  }

  public int getMaxHitpoints() {
    return maxHitpoints;

  }

  public int getManaPoints()  {
    return manapoints;
  }

  public int getDamage_taken() {
    return damage_taken / 2;
  }

}
