import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);  
    while (true) {
      int n = s.nextInt();
      int k = s.nextInt();
          
      if (n == 0 && k == 0) break;
      int[] dead = new int[n];
      for (int i = 0; i < n; ++i) dead[i] = i + 1;
      int kill = k - 1;
      int bury = kill + k;
      bury %= n;
      kill %= n;
      int killed = 0;
      while (true) {
        ++killed;
        
        if (killed >= n - 1) break;          
        dead[kill] = dead[bury];
        dead[bury] = -1;
        
        for (int i = 0; i < k; ++i) {
          ++kill;
          kill %= n;
          if (dead[kill] == -1) --i;
        }
        dead[kill] = -1;
        for (int i = 0; i < k; ++i) {
          ++bury;
          bury %= n;
          if (dead[bury] == -1) --i;
        }
      }
      int survivor = 0;
      for (int i = 0; i < n; ++i) {
        if (dead[i] != -1) {
          survivor = dead[i] - 1;
          break;
        }
      }  
      int lower = 0;
      int upper = survivor;
      while (upper != 0) {
        ++lower;
        ++upper;
        lower %= n;
        upper %= n;
      }
      System.out.println(lower + 1);
    }
  }
}