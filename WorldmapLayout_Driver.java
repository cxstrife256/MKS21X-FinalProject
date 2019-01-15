public class WorldmapLayout_Driver {

  public static void main(String[] args) {

    Map mappy = new Map();

    // should be the first rooms
    System.out.println(mappy);

    mappy.changeRoomDebug(1);

    // should now be the second room 
    System.out.println(mappy);
  }

}
