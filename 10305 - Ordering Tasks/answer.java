import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  
  public static int [][] edges;
  public static int [] edgesCount;
  public static int [] ints;
  public static int intsCount;

  public static boolean [] visited;
  
  public static void topologicalSort (int id) {
    if (!visited[id]) {
      visited[id] = true;
      
      for (int i = 0; i < edgesCount[id]; i++)
        topologicalSort(edges[id][i]);
      ints[intsCount++] = id;
    }
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    while (true) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      
      if (x == 0 && y == 0) break;
      edges = new int [x + 1][x + 1];
      edgesCount = new int [x + 1];
      
      for (int i = 0; i < y; i++) {
        st = new StringTokenizer(br.readLine());
        int src = Integer.parseInt(st.nextToken());
        int dest = Integer.parseInt(st.nextToken());
        edges[src][edgesCount[src]++] = dest;
      }
      visited = new boolean [x+1];
      ints = new int [x];
      intsCount = 0;
      for (int i = 1; i <= x; i++) topologicalSort(i);
      for (int i = intsCount - 1; i >= 0; i--) {
        System.out.print(ints[i]);
        if (i == 0) System.out.println();
        else System.out.print(" ");
      }
    }
  }
}