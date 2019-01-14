public class Map {

  private char[][] data;

  public Map(int rows, int cols) {
    data = new char[rows][cols];
    clear();

  }

  private void clear() {
    for(int i=0; i<data.length; i++) {
      for(int j=0; j<data[i].length; j++) {
        data[i][j] = '.';
      }
    }

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

  public int move(int x, int y, int xdirec, int ydirec) {
    /*
      0 if you can't go there
      1 if it's just a dot
      2 if it's a staircase
    */
    if(data[x+xdirec][y+ydirec] == '>') {
      return 1;
    } else if(data[x+xdirec][y+ydirec] == '<') {
      return -1;
    } else if(data[x+xdirec][y+ydirec] == '.') {
      return 2;
    } else {
      return 0;
    }

  }

}
