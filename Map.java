public class Map {

  private char[][] data;            // this is what the rooms are made of
  private String[][] map_archive;   // this is where we are putting all the Strings for setMap
  private int room_ID;              // this is how we keep track of which room we are in

  public Map() {
    //                 room setMap() string                                                              rows  cols
    map_archive = {
                      {"|-----|,|.....|,>.....|,|.....|,|.....|,|.....|,|.....|,|.....|,|.....|,|-----|", "10", "7"},
                      {"|----|,|....|,|....|,|....<,|----|"                                             , "5" , "6"}
                  };
    // default first room
    room_ID = 0;

    // set map (data) according to room_ID
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
    resize((int)map_archive[room_ID][1], (int)map_archive[room_ID][2];
    setMap(map_archive[room_ID][0]);

  }

  public void resize(int rows, int cols) {
    data = new char[rows][cols];

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

  public boolean canMove(int x, int y, int xdirec, int ydirec) {
    char destination = data[x+xdirec][y+ydirec];

    // encounter door/stair to next room
    if(destination == '>') {
      room_ID += 1;
      resetMap();
      return true;

    // encounter door/stair to prev room
  } else if(destination == '<') {
      room_ID -= 1;
      resetMap();
      return true;

    // encounter normal tile, checking if destination is a legal move
    } else {
      return destination == '.';

    }

  }


}
