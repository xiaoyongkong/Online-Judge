import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

  public static class Edge implements Comparable<Edge> {

    private int cost, src, dest;
    
    public Edge (int s, int d, int c) {
      this.src = s;
      this.cost = c;
      this.dest = d;
    }
    
    public int compareTo (Edge e) {
      return this.cost - e.cost;
    }
  }
  
  public static void djikstra(ArrayList<Edge> [] edgeList, int [] lowestCost, int src, int dest) {

    lowestCost[src] = 0;
    
    PriorityQueue<Edge> queue = new PriorityQueue<>();
    queue.add(new Edge(src, src, 0));
    
    while (!queue.isEmpty()) {

      Edge e = queue.poll();
      if (e.dest == dest) break;
      else if (edgeList[e.dest] != null) {

        for (Edge ed : edgeList[e.dest]) {
          if (lowestCost[ed.dest] > lowestCost[ed.src] + ed.cost) {
            lowestCost[ed.dest] = lowestCost[ed.src] + ed.cost;
            queue.offer(new Edge(ed.src, ed.dest, lowestCost[ed.dest]));
          }
        }
      }
    }
  }
  
  public static void main (String [] args) throws Exception {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int testCount = Integer.parseInt(br.readLine());
    
    for (int test = 1; test <= testCount; test++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int S = Integer.parseInt(st.nextToken());
      int T = Integer.parseInt(st.nextToken());
      
      ArrayList<Edge> [] edgeList = new ArrayList [n];
      
      for (int i = 0; i < m; i++) {

        st = new StringTokenizer(br.readLine());
        int src = Integer.parseInt(st.nextToken());
        int dest = Integer.parseInt(st.nextToken());
        int cost = Integer.parseInt(st.nextToken());
        
        if (edgeList[src] == null) edgeList[src] = new ArrayList<>();
        edgeList[src].add(new Edge(src, dest, cost));
        
        if (edgeList[dest] == null) edgeList[dest] = new ArrayList<>();
        edgeList[dest].add(new Edge(dest, src, cost));
      }
      
      int [] cost = new int [n];
      Arrays.fill(cost, Integer.MAX_VALUE);
      djikstra(edgeList, cost, S, T);
      
      if (cost[T] != Integer.MAX_VALUE) System.out.println("Case #" + test + ": "+cost[T]);
      else  System.out.println("Case #" + test + ": unreachable");
    }
  }
  
}