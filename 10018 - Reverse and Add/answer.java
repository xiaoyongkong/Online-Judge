import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
  
  public static long reverseNumber (long num) {
    long reversed = 0;
    
    while (num > 0) {
      reversed = (reversed * 10) + (num % 10);
      num /= 10;
    }
    return reversed;
  }
  
  public static void main (String [] abc) throws IOException  {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int testCaseCount = Integer.parseInt(br.readLine());
    
    for (int testCase = 0; testCase < testCaseCount; testCase++) {
      int repeatCount = 0;
      long num = Long.parseLong(br.readLine());
      long reversed = reverseNumber(num);
      
      while (num != reversed) {
        num += reversed;
        reversed = reverseNumber(num);
        repeatCount++;
      }
      System.out.println(repeatCount + " " + num);
    }
  }
}