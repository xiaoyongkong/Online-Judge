import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

  public static int [][] dangerous = new int [8][8];
  
  public static void queen (int x, int y, int value) {

    for (int i = 0; i < dangerous.length; i++) {
      dangerous[x][i]=dangerous[x][i]+value;
    }

    for (int i = 0; i < dangerous.length; i++) {
      dangerous[i][y] = dangerous[i][y] + value;
    }
    dangerous[x][y] = dangerous[x][y] - value;
    
    int tempx = x - 1;
    int tempy= y - 1;

    while (tempx >= 0 && tempy >= 0) {
      dangerous[tempx][tempy] = dangerous[tempx][tempy] + value;
      tempx--;
      tempy--;
    }
    
    tempx= x + 1;
    tempy= y + 1;

    while (tempx < dangerous.length && tempy < dangerous.length) {
      dangerous[tempx][tempy] = dangerous[tempx][tempy] + value;
      tempx++;
      tempy++;
    }
    
    
    tempx = x + 1;
    tempy = y - 1;

    while (tempx < dangerous.length && tempy >= 0) {
      dangerous[tempx][tempy] = dangerous[tempx][tempy] + value;
      tempx++;
      tempy--;
    }
    
    tempx= x - 1;
    tempy= y + 1;

    while (tempx >= 0 && tempy < dangerous.length) {
      dangerous[tempx][tempy] = dangerous[tempx][tempy] + value;
      tempx--;
      tempy++;
    }
  }
  
  public static int [][] solutions = new int [100][8];
  public static int solutionsCount = 0;
  
  public static void dfs (int currCol, boolean [] rowFlag, int [] value) {
    if (currCol == rowFlag.length) {
      solutions[solutionsCount++] = Arrays.copyOf(value, value.length);
    } else {
      for (int i = 0; i < rowFlag.length; i++) {
        if (!rowFlag[i] && dangerous[i][currCol] == 0) {
          rowFlag[i] = true;
          queen(i, currCol,1);
          value[currCol] = i;
          dfs(currCol + 1, rowFlag, value);
          value[currCol] =- 1;
          queen(i, currCol, -1);
          rowFlag[i] = false;
        }
      }
    }
  }
  
  public static void main (String [] abc) throws IOException  {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    dfs(0, new boolean [8], new int [8]);
    String s;
    int caseCount = 1;

    while ((s = br.readLine()) != null) {
      StringTokenizer st = new StringTokenizer(s);
      int [] in = new int [8];
      
      for (int i = 0; i < in.length; i++) {
        in[i] = Integer.parseInt(st.nextToken())-1;
      }
      int minDiff = Integer.MAX_VALUE;

      for (int i = 0; i < solutionsCount; i++) {
        int diff = 0;
        for (int i2 = 0; i2 < in.length; i2++) {
          if (in[i2] != solutions[i][i2]) diff++;
        }
        minDiff = Math.min(diff, minDiff);
      }
      System.out.println("Case "+caseCount+": "+minDiff);
      caseCount++;
    }
  }
}