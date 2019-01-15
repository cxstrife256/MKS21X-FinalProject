public class Driver_Map {

  public static void main(String[] args) {

    Map mappy = new Map();

    // should be the first room
    System.out.println(mappy);

    mappy.changeRoomDebug(1);

    // should now be the second room
    System.out.println(mappy);

  }

}
