import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {
  
  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int testCaseCount = Integer.parseInt(br.readLine());
    
    for (int testCase = 0; testCase < testCaseCount; testCase++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int v = Integer.parseInt(st.nextToken());
      int e = Integer.parseInt(st.nextToken());      
      boolean [][] mat = new boolean [v][v];
      
      for (int i = 0; i < e; i++) {
        st = new StringTokenizer(br.readLine());
        int src = Integer.parseInt(st.nextToken());
        int dest = Integer.parseInt(st.nextToken());
        
        if (src != dest && src >= 0 && src < v && dest >= 0 && dest < v) {
          mat[src][dest] = true;
          mat[dest][src] = true;
        }
      }
      int [] color = new int [v];
      Arrays.fill(color, Integer.MAX_VALUE);
      LinkedList<Integer> queue = new LinkedList<>();
      int count = 0;
      boolean flag = true;
      
      for (int i = 0; i < v; i++) {
        if (color[i] == Integer.MAX_VALUE) {
          queue.add(i);
          color[i] = 1;
          int [] tempC = new int [2];
          
          while (!queue.isEmpty()) {
            int id = queue.removeFirst();
            tempC[color[id]]++;
            
            for (int i2 = 0; i2 < v; i2++) {
              if (mat[id][i2]) {
                if (color[i2] == Integer.MAX_VALUE) {
                  color[i2] = 1 - color[id];
                  queue.addLast(i2);
                } else if (color[i2] == color[id]) flag=false;
              }
            }
          }
          if (tempC[0] + tempC[1] == 1) count++;
          else count += Math.min(tempC[0], tempC[1]);
        }
      }
      if (flag) System.out.println(count);
      else System.out.println(-1);
    }
  }
}