import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String s;
    
    while (!(s = br.readLine()).equals("0")) {
      long l = Long.parseLong(s);
      if (l < 0) l *= -1;
      BigInteger bi = BigInteger.valueOf(l);
      
      if (bi.isProbablePrime(10)) System.out.println(-1);
      else {
        long max = 0;
        int primeFactorCount = 0;
        
        if (l % 2 == 0) {
          max = 2;
          primeFactorCount++;
        }

        while (l % 2==0) l /= 2;
        for (long factor = 3; factor * factor <= l; factor +=2) {
          if (l % factor == 0) {
            max = Math.max(max, factor);
            primeFactorCount++;
          }
          while (l % factor == 0) l /= factor;
        }
        if (l > 1) primeFactorCount++;
        max = Math.max(max, l);
        if (primeFactorCount > 1) System.out.println(max);
        else System.out.println(-1);
      }
    }
  }
}