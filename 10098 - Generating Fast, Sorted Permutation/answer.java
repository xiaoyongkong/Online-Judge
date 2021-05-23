import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

class Main {
  
  public static HashMap<String,Boolean> generate;
  public static StringBuilder display;
  
  public static void main(String[] args) throws IOException {
      
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int testCaseCount = Integer.parseInt(br.readLine());
      
      for (int testCase = 0; testCase < testCaseCount; testCase++) {
        char [] c = br.readLine().toCharArray();
        Arrays.sort(c);
        generate = new HashMap<>();
        display = new StringBuilder();
        permutation(c, new boolean [c.length], "");
        System.out.println(display.toString());
      }
    }
  
  public static void permutation (char [] c, boolean [] flag, String result) {
    
    if (result.length() == c.length) display.append(result + "\n");
    else {
      for (int i = 0; i < c.length; i++) {
        if (!flag[i]) {
          String s = result + c[i];
          
          if (generate.get(s) == null) {
            flag[i] = true;
            generate.put(s, true);
            permutation(c,flag,s);
            flag[i] = false;
          }
        }
      }
    }
  }
}