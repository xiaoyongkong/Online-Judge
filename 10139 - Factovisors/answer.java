import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.StringTokenizer;

class Main {

  public static int getPow(int n, int p) {
    int cnt = 0;
    
    for (long power = p; power <= n; power *= p) {
      cnt += n/power;
    }
    return cnt;
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String s;
    while ((s = br.readLine())!=null) {
      StringTokenizer st = new StringTokenizer(s);
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int n2 = n; int m2 = m;
      boolean ans = false;
      
      if (n >= m) ans=true;
      else {
        LinkedHashMap<Integer,Integer> pfListMCnt = new LinkedHashMap<>();
        
        for (int i = 2; i * i <= m;) {
          if ((m % i) == 0) {
            while ((m % i) == 0) {
              pfListMCnt.put(i, 1 + pfListMCnt.getOrDefault(i, 0));
              m /= i;
            }	
          }
          if (i == 2) i++;
          else i += 2;
        }
        if (m > 1) pfListMCnt.put(m, 1);
        ans = true;
        Iterator<Integer> it = pfListMCnt.keySet().iterator();
        while (it.hasNext() && ans) {
          int key = it.next();
          ans = (pfListMCnt.getOrDefault(key, 0) <= getPow(n, key));
        }
      }
      if (ans) System.out.println(m2 + " divides " + n2 + "!");
      else System.out.println(m2 + " does not divide " + n2 + "!");
    }
  }
}