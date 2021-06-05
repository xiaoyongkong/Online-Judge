import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class Main {
  
  public static char [] alphabetList;
  public static int alphabetListCount;
  public static boolean [] visited;
  public static boolean [][] connected;
  
  public static void dfs (int id) {
    if (!visited[id]) {
      visited[id] = true;
      
      for (int i = 0; i < 26; i++) {
        if (connected[id][i]) dfs(i);
      }
      alphabetList[alphabetListCount++] = (char)(id + 'A');
    }
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    alphabetList = new char [26];
    alphabetListCount = 0;
    visited = new boolean [26];
    connected = new boolean [26][26];
    Arrays.fill(visited, true);
    ArrayList<String> strlist = new ArrayList<>();

    while (true) {
      String s = br.readLine();
      if (s.equals("#")) break;
      strlist.add(s);
    }
    char [][] mat = new char [strlist.size()][];
    
    for (int i = 0; i < mat.length; i++) {
      mat[i] = strlist.get(i).toCharArray();
      for (char c : mat[i]) visited[c - 'A'] = false;
    }
    ArrayList<Integer> nodeList = new ArrayList<>();
    
    for (int i = 1; i < mat.length; i++) {
      int index = 0;
      while (index < Math.min(mat[i - 1].length, mat[i].length) && mat[i][index] == mat[i - 1][index])
        index++;
      if (index == Math.min(mat[i - 1].length, mat[i].length))
        continue;
      char prev = mat[i - 1][index];
      char next = mat[i][index];
      int prevIndex = prev - 'A';
      int nextIndex = next - 'A';
      connected[prevIndex][nextIndex] = true;
      if (!nodeList.contains(mat[i - 1][index] - 'A'))
        nodeList.add(mat[i - 1][index] - 'A');
      if (!nodeList.contains(mat[i][index] - 'A'))
        nodeList.add(mat[i][index] - 'A');
    }
    
    for (int i = 0; i < 26; i++) {
      if (!visited[i])
        dfs(i);
    }
    
    for (int i = alphabetListCount - 1; i >= 0; i--)
      System.out.print(alphabetList[i]);
    System.out.println();
  }
}