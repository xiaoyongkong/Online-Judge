import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
  
  public static void flip (int [] ints, int targetIndex) {
    int [] temp = Arrays.copyOf(ints, ints.length);
    int endIndex = ints.length-targetIndex;
    
    for (int i = 0; i <= endIndex; i++)
      ints[i] = temp[endIndex - i];
  }
  
  public static void main(String[]args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String s;
    while ((s = br.readLine())!=null) {
      System.out.println(s);
      StringTokenizer st = new StringTokenizer(s);
      ArrayList<Integer> list = new ArrayList<>();
      
      while (st.hasMoreTokens()) list.add(Integer.parseInt(st.nextToken()));
      int [] ints = new int [list.size()];
      
      for (int i = 0; i < list.size(); i++) 
        ints[i]=list.get(i);
      int [] intsSorted = Arrays.copyOf(ints, ints.length);
      Arrays.sort(intsSorted);
      StringBuilder sb = new StringBuilder();
      
      for (int i = ints.length - 1; i >= 0; i--) {
        if (ints[i] != intsSorted[i]) {
          if (ints[0] != intsSorted[i]) {
            int index = i - 1;
            for (;index >= 0; index--) {
              if (ints[index] == (intsSorted[i])) break;
            }
            flip(ints, (ints.length - index));
            sb.append((ints.length - index) + " ");
          }
          flip(ints, (ints.length - i));
          sb.append((ints.length - i) + " ");
        }
      }
      sb.append("0");
      System.out.println(sb.toString());
    }
  }
}