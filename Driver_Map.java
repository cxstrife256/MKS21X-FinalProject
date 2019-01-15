public class Driver_Map {

  public static void main(String[] args) {

    Map mappy = new Map();

    // should be the first room
    System.out.println(mappy);
    /*
      | - - - - - |
      | . . . . . |
      > . . . . . |
      | . . . . . |
      | . . . . . |
      | . . . . . |
      | . . . . . |
      | . . . . . |
      | . . . . . |
      | - - - - - |
    */

    // test changeRoomDebug()
    mappy.changeRoomDebug(1);

    // should now be the second room
    System.out.println(mappy);
    /*
      | - - - - |
      | . . . . |
      | . . . . |
      | . . . . <
      | - - - - |
    */

    // test canMove()
    // stairs
    System.out.println(mappy.canMove(3, 4, 0, 1));    // true

    // should be the first room
    System.out.println(mappy);
    /*
      | - - - - - |
      | . . . . . |
      > . . . . . |
      | . . . . . |
      | . . . . . |
      | . . . . . |
      | . . . . . |
      | . . . . . |
      | . . . . . |
      | - - - - - |
    */

    // walls
    System.out.println(mappy.canMove(1, 1, -1, 0));   // false
    System.out.println(mappy.canMove(1, 1, 0, -1));   // false

    // boring normal tiles
    System.out.println(mappy.canMove(1, 1, 1, 1));    // true

  }

}
