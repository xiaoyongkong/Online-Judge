import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
  
  public static class Box implements Comparable<Box> {

    int id; 
    int[] size;
    
    public int compareTo(Box b) {
      for (int i = size.length - 1; i >= 0; i--) {
        int diff = this.size[i] - b.size[i];
        if (diff != 0) return diff;
      }
      return 0;
    }
    
    public String toString() {

      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < size.length; i++) {
        sb.append(size[i]+" ");
      }
      return sb.toString();
    }
  }
  
  public static boolean canAdd (Box b1, Box b2) {

    for (int i = 0; i < b1.size.length; i++) {

      if (b1.size[i] >= b2.size[i]) return false;
    }
    return true;
  }
  
  public static void main(String[] args)  throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String s;

    while ((s = br.readLine()) != null) {

      StringTokenizer st = new StringTokenizer(s);
      int N = Integer.parseInt(st.nextToken());
      int D = Integer.parseInt(st.nextToken());
      
      Box [] b = new Box [N];
      for (int i = 0; i < N; i++) {
        b[i] = new Box();
        b[i].id = i+1;
        b[i].size = new int [D];
        
        st = new StringTokenizer(br.readLine());
        for(int i2 = 0; i2 < D; i2++) b[i].size[i2]=Integer.parseInt(st.nextToken());
        Arrays.sort(b[i].size);
      }
      Arrays.sort(b);
      
      int[] lis = new int [N];
      lis[0] = 1;
      for (int i = 1; i < N; i++) {
        lis[i] = 1;
        for (int j = 0; j < i; j++)
          if (canAdd(b[j], b[i])) lis[i] = Math.max(1+lis[j], lis[i]);
      }
      
      int max = 0;
      for (int i = 0; i < lis.length; i++) {
        max = Math.max(max, lis[i]);
      }
      System.out.println(max);
      
      int [] solution = new int [max];
      Box last = null;
      for (int i = lis.length-1; i >= 0 && max > 0; i--) {

        if (lis[i] == max && (last == null || canAdd(b[i],last))) {
          solution[max-1]=i;
          max--;
          last=b[i];
        }
      }
      
      for (int i = 0; i < solution.length; i++) {
        
        System.out.print(b[solution[i]].id);
        if (i < solution.length-1) {
          System.out.print(" ");
        }
      }
      System.out.println();
    }
  }
}