import java.io.IOException;
import java.util.Scanner;

class Main {
  
  public static void main(String[]args) throws IOException {
    Scanner sc = new Scanner(System.in);
    int size = 0;
    
    while ((size = sc.nextInt()) != 0) {
      int max = 0;
      int curr = 0;
      
      for (int i = 0; i < size; i++) {
        curr += sc.nextInt();
        if (curr < 0) curr=0;
        max = Math.max(max, curr);
      }
      if (max > 0) System.out.println("The maximum winning streak is " + max + ".");
      else System.out.println("Losing streak.");
    }
    sc.close();
  }
}