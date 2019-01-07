public abstract class Attackable{
  private int hitpoints;
  private int strength;
  private int dexterity;
  private int vitality;
  private int magic;
  private int spirit;
  private int luck;

}
  /*Hit% = ([Attacker's Dex / 4] + At%) + Attacker's Df% - Target's Df%
If Attacker is under Fury Status: Hit% = Hit% - [Hit% * 3 / 10]
Lucky Hit:
   The Attacker has a [Lck / 4]% chance of landing a Lucky Hit.  If this is
   successful, then Hit% is immediately increased to 255%
Random = [Rnd(0..65535) * 99 / 65535] + 1
        If (Random < Hit%) Then Attack Hits
In summary, the procedure for calculating a PAt%-based hit is as follows:

   Check for Auto Hit Conditions
     If Auto Hit, Hit% = 255
     If not Auto Hit, Calculate Hit% and check for Fury
   Check for Lucky Hit and Lucky Evade
     If not a Lucky Hit or Evade, use Hit% to decide success of attack
If Attacker is under Fury Status: MAt% = MAt% - [MAt% * 3 / 10]
Hit% = MAt% + Attacker's Lvl - [Target's Lvl / 2] - 1
        Random = Rnd(0..99)
        If (Random < Hit%) Then Attack Hits
Check for Auto Hit Conditions
     If Auto Hit, attack hits
     If not Auto Hit:
        Apply Fury Modifier
        Check for MD%-based Evasion
        Calculate Hit%
        Use Hit% to determine success of attack
The Physical Formula for Base Damage is defined as such:
  Base Damage = Att + [(Att + Lvl) / 32] * [(Att * Lvl) / 32]
Ability Power and the Defense Stat are then used:
  Damage = [(Power * (512 - Def) * Base Damage) / (16 * 512)]
Crit% = [(Attacker's Lck + Attacker's Lvl - Target's Lvl) / 4]
      If Attacker is Party Member: Crit% = Crit% + Weapon's Crit% Modifier
Random = [Rnd(0..65535) * 99 / 65535] + 1
        If (Random <= Crit%) Then Critical Hits
https://gamefaqs.gamespot.com/ps/197341-final-fantasy-vii/faqs/22395
*/
