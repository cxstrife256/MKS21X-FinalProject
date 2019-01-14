public class Map_Driver{

  public static void main(String[] args) {

    // init instance "MapExample" of Map
    // 10 rows, 7 cols
    Map ROOM_1 = new Map(10,7);
    System.out.println(ROOM_1);

    /*
      . . . . . . .
      . . . . . . .
      . . . . . . .
      . . . . . . .
      . . . . . . .
      . . . . . . .
      . . . . . . .
      . . . . . . .
      . . . . . . .
      . . . . . . .
    */

    // set unique field characters
    ROOM_1.setMap("|-----|,|.....|,>.....|,|.....|,|.....|,|.....|,|.....|,|.....|,|.....|,|-----|");
    System.out.println(ROOM_1);
    /*
      | — — — — — |
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

  }
}
