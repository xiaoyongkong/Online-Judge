import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.StringTokenizer;

class Main {
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int testCaseCount = Integer.parseInt(br.readLine());
    
    for (int testCase = 0; testCase < testCaseCount; testCase++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      String s1 = st.nextToken();
      
      if (s1.charAt(0) == '.' && s1.length() == 1) s1 += "0";
      BigDecimal d1 = new BigDecimal(s1);
      String s2 = st.nextToken();
      if (s2.charAt(0) == '.' && s2.length() == 1) s2 += "0";
      BigDecimal d2 = new BigDecimal(s2);
      String result =d1.add(d2).toPlainString();
      
      if (result.indexOf('.') == -1) result = result + ".0";
      else {
        int lastZero = result.length() - 1;
        while (result.charAt(lastZero) == '0') lastZero--;
        result = result.substring(0, lastZero + 1);
        if (result.indexOf('.') == result.length() - 1) result += '0';
      }
      System.out.println(result);
    }
  }
}