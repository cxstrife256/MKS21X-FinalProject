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
    char temp = '.';
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

  /*
  public boolean canMove(int x, int y, int xdirec, int ydirec) {
    return data[x+xdirec][y+ydirec] == '.';

  }
  */

}
