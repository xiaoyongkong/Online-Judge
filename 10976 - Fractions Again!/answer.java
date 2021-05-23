import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
  
  public static void main (String [] abc) throws IOException  {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String s;
    
    while ((s = br.readLine()) != null) {
      int k = Integer.parseInt(s);
      StringBuilder sb = new StringBuilder();
      int count = 0;
      
      for (double y = k + 1; y <= 2 * k; y++) {
        double x=(k * y)/(y - k);
        
        if ((int)(double)x == x) {
          sb.append("1/" + k + " = 1/" + (int)x + " + 1/" + (int)y +"\n");
          count++;
        }
      }
      System.out.println(count);
      System.out.print(sb.toString());
    }
  }
}