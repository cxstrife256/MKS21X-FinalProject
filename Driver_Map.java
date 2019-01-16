public class Driver_Map {

  public static void main(String[] args) {

    // init a map
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
    System.out.println(mappy.canMove(4, 3, 1, 0));    // true

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
    System.out.println(mappy.canMove(1, 1, 0, -1));   // false
    System.out.println(mappy.canMove(1, 1, -1, 0));   // false

    // boring normal tiles
    System.out.println(mappy.canMove(1, 1, 1, 1));    // true

  }

}
