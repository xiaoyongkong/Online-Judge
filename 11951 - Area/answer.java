import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  
  public static void main (String [] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int testCaseCount=Integer.parseInt(br.readLine());
    
    for (int testCase = 1; testCase <= testCaseCount; testCase++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int X = Integer.parseInt(st.nextToken());
      int Y = Integer.parseInt(st.nextToken());
      long Z = Long.parseLong(st.nextToken());
      long [][] value=new long [X][Y];
      
      for (int n = 0; n < X; n++) {
        st = new StringTokenizer(br.readLine());
        for (int m = 0; m < Y; m++) value[n][m] = Integer.parseInt(st.nextToken());
      }
      long [] ans = {0,0};
      
      for (int colStart = 0; colStart < Y; colStart++) {
        long [] temp = new long [X];
        for (int colEnd = colStart; colEnd < Y; colEnd++) {
          for (int row = 0; row < X; row++) temp[row] += value[row][colEnd];
          long sum = 0;
          int firstIndex = 0;
          
          for (int row = 0; row < X; row++) {
            sum += temp[row];
            while (sum > Z && firstIndex < X) {
              sum -= temp[firstIndex];
              firstIndex++;
            }
            int size = (row - firstIndex + 1) * (colEnd - colStart + 1);
            
            if (ans[0] == 0 || size > ans[0] || (size == ans[0] && sum < ans[1])) {
              ans[0] = size;
              ans[1] = sum;
            }
          }
        }
      }
      System.out.printf("Case #%d: %d %d\n", testCase, ans[0], ans[1]);
    }
  }
}