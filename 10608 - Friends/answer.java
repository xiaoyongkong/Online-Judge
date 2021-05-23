import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  
  private static int findSet(int [] parent, int id) {
    return (parent[id] == id) ? id : (parent[id] = findSet(parent, parent[id]));
  }
  
  public static void main (String [] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int testCaseCount = Integer.parseInt(br.readLine());
    
    for (int testCase = 0; testCase < testCaseCount; testCase++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int M = Integer.parseInt(st.nextToken());
      int N = Integer.parseInt(st.nextToken());
      int [] parent = new int [M];
      int [] rank = new int [M];
      int [] size = new int [M];
      
      for (int i = 0; i < parent.length; i++) {
        parent[i] = i;
        size[i] = 1;
      }
      int largest = 1;
      
      for (int n = 0; n < N; n++) {
        st = new StringTokenizer(br.readLine());
        int x = findSet(parent, Integer.parseInt(st.nextToken()) - 1);
        int y = findSet(parent, Integer.parseInt(st.nextToken()) - 1);
        
        if (x != y) {
          if (rank[x] > rank[y]) {
            parent[y] = x;
            size[x] += size[y];
            largest = Math.max(largest, size[x]);
          } else {
            if (rank[x] == rank[y]) rank[y]++;
            parent[x] = y;
            size[y] += size[x];
            largest = Math.max(largest, size[y]);
          }
        }
      }
      System.out.println(largest);
    }
  }
}