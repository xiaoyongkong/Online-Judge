import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

  public static long Max = 0;
  public static long [] Possibles = {0,0};

  public static void dfs (int currentMonth, long [] monthV, long months5V, long totalSum) {
    
    if (currentMonth <= 4) {
      for (long p : Possibles) {
        monthV[currentMonth] = p;
        if (currentMonth < 4 || months5V + p < 0) 
          dfs(currentMonth + 1, monthV, months5V + p, totalSum + p);
      }
    } else if (currentMonth >= 5 && currentMonth < monthV.length) {
      for (long p : Possibles) {
        long testValue = months5V - monthV[currentMonth - 5] + p;
        if (testValue < 0) {
          monthV[currentMonth] = p;
          dfs(currentMonth + 1, monthV, testValue, totalSum + p);
        }
      }
    } else Max = Long.max(Max, totalSum);
  }
  
  public static void main (String [] args) throws Exception {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String s;
    
    while ((s = br.readLine()) != null) {
      StringTokenizer st = new StringTokenizer(s);
      Possibles[0] = Long.parseLong(st.nextToken());
      Possibles[1] =- Long.parseLong(st.nextToken());
      Max = Possibles[1] * 12;
      
      dfs(0,new long [12], 0, 0);
      System.out.println(Max>=0 ? Max : "Deficit");
    }
  }
}