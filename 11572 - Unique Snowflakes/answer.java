import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

class Main {
  
  public static void main (String [] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int testCaseCount = Integer.parseInt(br.readLine());
    for (int testCase = 0; testCase < testCaseCount; testCase++) {
      int n = Integer.parseInt(br.readLine());
      int [] id = new int [n];
      for (int i = 0; i < n; i++)  
        id[i] = Integer.parseInt(br.readLine().trim());
      int max = 0;
      if (n > 0) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int [] dp = new int [n];
        dp[0] = 1;
        int startIndex = 0;
        map.put(id[0],0);
        
        for (int i = 1; i < n; i++) {
          if (map.containsKey(id[i])) {
            int endIndex = map.get(id[i]);
            for (int i2 = startIndex;i2<=endIndex;i2++) 
              map.remove(id[i2]);
            startIndex = endIndex + 1;
            dp[i] = i-startIndex + 1;
          } else dp[i] = dp[i - 1] + 1;
          map.put(id[i], i);
        }
        for (int i : dp) max=Math.max(i, max);
      }
      System.out.println(max);
    }
  }
}