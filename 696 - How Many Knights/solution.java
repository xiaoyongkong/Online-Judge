import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.Integer;

class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String result = null;
    String line = null;
    String[] tokens = null;
    int M, N;

    while((line = reader.readLine()) != null) {
      tokens = line.split("\\s+");
      N = Integer.parseInt(tokens[0]);
      M = Integer.parseInt(tokens[1]);
      
      if (N == 0 && M == 0) 
        break;

      result = " knights may be placed on a " + N +" row " + M + " column board.";
      if (N > M) {
        N ^= M;			
        M ^= N;
        N ^= M;
      }

      switch(N) {
        case 1:
          System.out.println(M + result);
          break;
        case 2:
          System.out.println(((M + 3)/4 + (M + 2)/4)*2 + result);
          break;
        default:
          System.out.println((((N + 1)/2) * ((M + 1)/2) + (N/2) * (M/2)) + result);	
      }
    }
  }
}