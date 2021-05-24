import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  
  public static char [][] mat;
  
  public static void main(String[] args)  throws IOException {
    char [][] turn = new char [128][128];
    turn['N']['L'] = 'W';
    turn['W']['L'] = 'S';
    turn['S']['L'] = 'E';
    turn['E']['L'] = 'N';
    turn['N']['R'] = 'E';
    turn['E']['R'] = 'S';
    turn['S']['R'] = 'W';
    turn['W']['R'] = 'N';
    int [] moveX = new int [128];
    int [] moveY = new int [128];
    moveX['N'] = -1;
    moveX['S'] = 1;
    moveY['E'] = 1;
    moveY['W'] = -1;
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int testCaseCount = Integer.parseInt(br.readLine());
    br.readLine();
    
    for (int testCase = 0; testCase < testCaseCount; testCase++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      mat = new char [Integer.parseInt(st.nextToken())][];
      for (int i = 0; i < mat.length; i++) {
        mat[i] = br.readLine().toCharArray();
      }
      char facing='N';
      st = new StringTokenizer(br.readLine());
      int currX = Integer.parseInt(st.nextToken())-1;
      int currY = Integer.parseInt(st.nextToken())-1;
      boolean quit = false;
      String s;
      
      while ((s = br.readLine()) != null && !s.isEmpty() && !quit) {
        char [] command = s.toCharArray();
        for (char c : command) {
          if (c == 'Q') {
            quit = true;
            break;
          } else if (c == 'L' || c == 'R') {
            facing = turn[facing][c];
          } else if (c == 'F') {
            int newX = currX+moveX[facing];
            int newY = currY+moveY[facing];
            if (newX >= 0 && newX < mat.length && newY >= 0 && newY < mat[0].length && mat[newX][newY] != '*') {
              currX = newX;
              currY = newY;
            }
          }
        }
      }
      System.out.println((currX+1) + " " + (currY + 1) + " " + facing);
      if (testCase < testCaseCount - 1) System.out.println();
    }
  }
}