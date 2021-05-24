import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  
  public static char [][] mat;
  public static boolean [][] visited;
  public static int [][] neighbour={{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
  
  public static int getSize (int x, int y, char c) {
    if (x >= 0 && x < mat.length && y >= 0 && y < mat[x].length && mat[x][y] == c && !visited[x][y]) {
      visited[x][y] = true;
      int count = 1;
      
      for (int i = 0; i < neighbour.length; i++) {
        if (y + neighbour[i][1] == mat[x].length) {
          count += getSize(x + neighbour[i][0], 0, c);
        } else if (y + neighbour[i][1] == -1) {
          count += getSize(x + neighbour[i][0], mat[x].length - 1, c);
        } else {
          count += getSize(x + neighbour[i][0], (y + neighbour[i][1]), c);
        }
      }
      return count;
    }
    return 0;
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String s;
    
    while ((s = br.readLine()) != null) {
      StringTokenizer st = new StringTokenizer(s);
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      
      mat = new char [x][];
      visited = new boolean [x][];
      for (int i = 0; i < x; i++) {
        mat[i] = br.readLine().toCharArray();
        visited[i] = new boolean [mat[i].length];
      }
      
      st = new StringTokenizer(br.readLine());
      int sx = Integer.parseInt(st.nextToken());
      int sy = Integer.parseInt(st.nextToken());
      
      getSize(sx, sy, mat[sx][sy]);
      
      int max = 0;
      
      for (int i = 0; i < x; i++) {
        for (int i2 = 0; i2 < y; i2++) {
          if (mat[i][i2] == mat[sx][sy]) {
            max = Math.max(max, getSize(i, i2, mat[i][i2]));
          }
        }
      }
      System.out.println(max);
      br.readLine();
    }
  }
}