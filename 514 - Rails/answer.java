import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {
  
  public static void main (String [] abc) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String s;
    
    while (!(s = br.readLine()).equals("0")) {
      int n = Integer.parseInt(s);
      while (!(s = br.readLine()).equals("0")) {
        LinkedList<Integer> incomingTrain = new LinkedList<>();
        for (int i = 1; i <= n; i++) incomingTrain.add(i);
        Stack<Integer> stationTrain = new Stack<>();
        StringTokenizer st = new StringTokenizer(s);
        boolean fail = false;
        
        while (st.hasMoreTokens()) {
          int queue = Integer.parseInt(st.nextToken());
          if (incomingTrain.contains(queue))
            while (incomingTrain.contains(queue)) 
              stationTrain.push(incomingTrain.removeFirst());
          if (stationTrain.size() > 0 && stationTrain.peek() == queue) 
            stationTrain.pop();
          else {
            fail = true;
            break;
          }
        }
        if (fail) System.out.println("No");
        else System.out.println("Yes");
      }
      System.out.println();
    }
  }
}