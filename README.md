# MKS21X-FinalProject

# Instructions

## 1.) Open terminal in full screen
## 2.) Run bash complie.sh
## 3.) Enjoy the 70 second opening scene

# 1/5/19

## Timothy
 Added Audio Files for future audio integration

## Cody
 Dying from College Essays

# 1/6/19

## Timothy
Modified AudioPlayer and a proof of concept Menu and temporary Driver for menu testing

## Cody
Dying from 17 page Sci-fi Essay ; legends say he still has not recovered

# 1/7/19

## Timothy
Added Classes for Enemies and Player

## Cody
Added Classes for Enemies and Player

# 1/8/19

## Timothy
Initialized some of the Classes and fixed formatting

## Cody  
Initialized some Classes

# 1/9/19

## Timothy
Initialized the other classes and fixed formatting

## Cody
Initialized the other classes, added Squishy

# 1/10/19

## Timothy
Fixed the class Structure. Now Player and Enemy extend Squishy. All the enemies extend enemies, and the players are instances of the Player class.

## Cody  
Initialized remaining classes and added attack for all classes, and takeDamage()

# 1/13/19

**Overdrive Time**

## Timothy
Finished class structure, added a driver, fixed formatting on the driver. Added Map class and constructor. Added the map_archive, which is 2d String array of all the maps and their sizes.

## Cody
Finished class structure, added a driver and tested to make sure the numbers were logical. Added Map class and constructor. Added a enemy select target, which allows the enemy to randomly choose a player to attack

# 1/14/19

## Timothy
Finished Map.java and methods to change the room. Added a driver to test the map.

## Cody
added canMove() which checks to see if the players move is valid, not walking into a wall, or if they are walking into a stair, which requires a room change.

# 1/15/19

## Timothy
worked on integrating lanterna into the Game.java file to create a working menu for combat.

## Cody
made changeRoomDebug to test the changing of rooms, added Game file, and added the player and enemies list

# 1/16/19

## Timothy
Modified canMove to return ints, fixed wall detection, added new branch: combat-system, clears the screen after the battle

## Cody
Added a Driver to test moving around, wall detection, stair detection, and room changing. Working terminal is almost a thing

# 1/17/19

## Timothy
unnecessary commit to bolster his already massive amount of commits

## Cody
Studying like a good student, but failing the test like a bad one.

# 1/18/19

## Timothy
Mental Health Day

## Cody
Recovering from 3 back to back finals

# 1/19/19

## Timothy
Added BattleStart, which is now how we start the battles, added a preliminary wait function to have a delay

## Cody
Added encounter method to do a check to see if the player should encounter a fight. Updated enemySetup to now include all types of normal enemies

# 1/20/19

## Timothy
Changed wait to still almost work, but not quite. Changed how enemy setup worked to be more readable and logical. Added preliminary boss battle setup.

## Cody
Added getName for all normal enemies and updated getName in Squishy Added boolean for firstbattle

# 1/21/19 - 1/22/19

## Timothy
Added movement within the menus. Added Screen, and people who said that using screen would be easy are liars. We had to make our own putString: printstr() to print our map because putString doesn’t handle newline well(read: at all). Removed unneeded variables. Added the flavor text for the battles. Fixed the many bugs that existed within our battle system.The madman actually did it, he added audio at 4 in the morning and a opening scence.

## Cody
Added max hitpoints, getManaPoints(), getDmgTaken(). Combat is now actually playable. There is a menu at the bottom to select which enemy to attack, and what attack to use. Made wait() into something that worked. Added Magical Attack() and the formula to calculate that. Added Guard Scorpion and its boss battle setup
