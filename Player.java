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

  public void attack(Squishy target, int power) {
    if(name.equals("Cloud")) {
      // enemy gets one shotted basically
      super.attack(target, level, power);

    } else if(name.equals("Barret")) {
      // enemy gets kinda one shotted but honestly not really
      // Barret with a capital F to pay respects
      super.attack(target, level, power);

    } else {
      // you don't belong in this world, monster! - richter belmont

    }

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

}
