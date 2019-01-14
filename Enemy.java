import java.io.*;  //too hard to actually remember which one is ArrayList
import java.util.*;

public class Enemy extends Squishy {

  public Enemy(int hitpoints, int strength, int dexterity, int vitality, int magic, int spirit, int luck) {
    super(hitpoints, strength, dexterity, vitality, magic, spirit, luck);

  }

  public void takeDamage(int damage) {
    // takes damage equal to given int damage

  }

  public Squishy targetSelector( ArrayList<Squishy> targets){
    if( targets.size() ==1 || ( ((Math.random() * 10000) % 2 ) == 0 )){
      return targets.get(0);
    }
    return targets.get(1);
    
  }

  // in enemy function Squishy targetSelector(ArrayList)
  // void attack()
  //   Squishy target = targetSelector(ArrayList)
  //   int damage = calculations based on target and attacker
  //   target.takeDamage(damage)

}
