import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  
  public static void main (String [] abc) throws IOException  {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String s;
    
    while ((s = br.readLine()) != null) {
      StringTokenizer st = new StringTokenizer(s);
      long num1 = Long.parseLong(st.nextToken());
      long num2 = Long.parseLong(st.nextToken());
      
      if (num1 == 0 && num2 == 0) break;
      int carryCount = 0;
      long currCarry = 0;
      
      while (num1 > 0 && num2 > 0) {
        long curr = (num1 % 10) + (num2 % 10) + currCarry;
        currCarry = curr/10;
        if (currCarry > 0) carryCount++;
        num1 /= 10;
        num2 /= 10;
      }
      
      while (num1 > 0) {
        long curr = (num1 % 10) + currCarry;
        currCarry = curr/10;
        if (currCarry > 0) carryCount++;
        num1 /= 10;
      }
      
      while (num2 > 0) {
        long curr = (num2 % 10) + currCarry;
        currCarry = curr/10;
        if (currCarry > 0) carryCount++;
        num2 /= 10;
      }
      
      if (carryCount == 0) System.out.println("No carry operation.");
      else if (carryCount == 1) System.out.println("1 carry operation.");
      else System.out.println(carryCount+" carry operations.");
    }
  }
}