import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Main {
  static class Edge {
    Edge dual;
    int s, t, cap;

    public Edge(int s, int t, int cap) {
      this.s = s;
      this.t = t;
      this.cap = cap;
    }
  }

  public static int maxflow(int s, int t, LinkedList<Edge>[] G) {
    Edge[] parent = new Edge[G.length];
    int[] flow = new int[G.length];
    int result = 0;
    
    while (bfs(s, t, G, flow, parent) != 0) {
      result += flow[t];
      augment(s, t, flow[t], parent);
    }
    return result;
  }

  public static void augment(int s, int t, int flow, Edge[] parent) {
    while (t != s) {
      parent[t].cap -= flow;
      parent[t].dual.cap += flow;
      t = parent[t].s;
    }
  }

  public static int bfs(int s, int t, LinkedList<Edge>[] G, int[] flow, Edge[] parent) {
    Queue<Integer> Q = new ArrayDeque<Integer>();
    Q.add(s);
    Arrays.fill(flow, 0);
    Arrays.fill(parent, null);
    flow[s] = Integer.MAX_VALUE;

    while (!Q.isEmpty()) {
      int n = Q.poll();
      if (n == t) return flow[t];
      for (Edge e : G[n]) {
        if (flow[e.t] == 0 && e.cap > 0) {
          flow[e.t] = Math.min(flow[n], e.cap);
          parent[e.t] = e;
          Q.add(e.t);
        }
      }
    }
      return 0;
  }

  public static void main(String[] args) throws Exception {

    InputReader in = new InputReader(System.in);
    while (true) {
      int c = in.nextInt();
      int p = in.nextInt();

      if (c == 0 && p == 0) break;

      LinkedList<Edge>[] G = new LinkedList[c + p + 2];
      for (int i = 0; i < G.length; i++) {
        G[i] = new LinkedList<Edge>();
      }
      int sum = 0;

      for (int i = 0; i < c; i++) {
        int w = in.nextInt();
        sum += w;
        Edge x = new Edge(0, i + 1, w);
        Edge y = new Edge(i + 1, 0, 0);
        x.dual = y;
        y.dual = x;
        G[0].add(x);
        G[i + 1].add(y);
      }
      for (int i = 0; i < p; i++) {
        int cnt = in.nextInt();

        for (int j = 0; j < cnt; j++) {
          int cat = in.nextInt();
          Edge x = new Edge(cat, c + i + 1, 1);
          Edge y = new Edge(c + i + 1, cat, 0);
          x.dual = y;
          y.dual = x;
          G[cat].add(x);
          G[c + i + 1].add(y);
        }
        Edge x = new Edge(c + i + 1, c + p + 1, 1);
        Edge y = new Edge(c + p + 1, c + i + 1, 0);
        x.dual = y;
        y.dual = x;
        G[c + i + 1].add(x);
        G[c + p + 1].add(y);
      }

      if (maxflow(0, p + c + 1, G) == sum) {
        System.out.println("1");
        for (int i = 1; i <= c; i++) {
          LinkedList<Integer> res = new LinkedList<Integer>();

          for (Edge e : G[i]) {
            if (e.cap == 0) res.add(e.t - c);
          }

          StringBuilder out = new StringBuilder();

          for (int k : res) out.append(k + " ");
          if (out.length() > 0) out.deleteCharAt(out.length() - 1);
          System.out.println(out);
          }
      } else System.out.println("0");
    }
  }

  static class InputReader {
    private BufferedReader reader;
    private StringTokenizer tokenizer;

    public InputReader(InputStream stream) {
      reader = new BufferedReader(new InputStreamReader(stream));
      tokenizer = null;
    }

    public String next() {
      while (tokenizer == null || !tokenizer.hasMoreTokens()) {
        try {
          tokenizer = new StringTokenizer(reader.readLine());
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
      return tokenizer.nextToken();
    }

    public int nextInt() {
      return Integer.parseInt(next());
    }
  }
}