public class Map {

  private char[][] data;            // this is what the rooms are made of
  private String[][] map_archive;   // this is where we are putting all the Strings for setMap
  private int room_ID;              // this is how we keep track of which room we are in

  public Map() {
    //                      room setMap() string                                                                                                      rows  cols
    String[][] maps = {
                          {"|-------|,|.......|,>.......|,|.......|,|.......|,|.......|,|.......|,|.......|,|.......|,|.......|,|.......|,|-------|", "12", "9"},
                          {"|----|,|....|,|....|,|....<,|----|"                                             , "5" , "6"}

                        };
    map_archive = maps;

    // default first room
    room_ID = 0;

    // set map (data) according to room_ID
    resize(Integer.parseInt(map_archive[room_ID][1]), Integer.parseInt(map_archive[room_ID][2]));
    setMap(map_archive[room_ID][0]);

    resetMap();

  }

  public String toString() {
    String output = "";
    for(int i=0; i<data.length; i++) {
      for(int j=0; j<data[i].length; j++) {
        output += data[i][j] + " ";
      }
      output += "\n";
    }

    return output;

  }

  public void resetMap() {
    clear();
    resize(Integer.parseInt(map_archive[room_ID][1]), Integer.parseInt(map_archive[room_ID][2]));
    setMap(map_archive[room_ID][0]);

  }

  public int getId() {
    return room_ID;
  }

  public void resize(int rows, int cols) {
    data = new char[rows][cols];

  }

  private void clear() {
    for(int i=0; i<data.length; i++) {
      for(int j=0; j<data[i].length; j++) {
        data[i][j] = ' ' ;
      }
    }

  }

  public void setMap(String layout) {
    int row, index;
    row = index = 0;
    char temp = ' ';

    for(int i=0; i<layout.length(); i++) {
      temp = layout.charAt(i);
      if(temp == ',') {
        row += 1;
        index = 0;
      } else {
        data[row][index] = temp;
        index++;
      }
    }

  }

  public int canMove(int x, int y, int xdirec, int ydirec) {
    char destination = data[y+ydirec][x+xdirec];

    // encounter door/stair to next room
    if(destination == '>') {
      room_ID += 1;
      resetMap();
      return 2;

    // encounter door/stair to prev room
    } else if(destination == '<') {
      room_ID -= 1;
      resetMap();
      return 2;

    // encounter normal tile, checking if destination is a legal move
    } else if(destination == '.') {
      return 0;

    // false otherwise
    } else {
      return 1;
    }

  }

  // debug function for us to test room changing
  public void changeRoomDebug( int index) {
    room_ID += index;
    resetMap();
  }

}
