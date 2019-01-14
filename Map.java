public class Map {

  private String[][] data;

  public Map(int rows, int cols) {
    data = new String[rows][cols];
    clear();

  }

  private void clear() {
    for(int i=0; i<data.length; i++) {
      for(int j=0; j<data[i].length; j++) {
        data[i][j] = '.' + " ";
      }
    }

  }

  /*
    | — — — — — |
    | . . . . . |
    > . . . . . |
    | . M . M . |
    | . . . . . |
    | . . . . . |
    | . . . . . |
    | . . C . . |
    | . . . . . |
    | - - - - - |
  */

}
