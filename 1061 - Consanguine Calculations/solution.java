import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {
  
  public static void main (String [] args) throws Exception {
    
    HashMap<String,Integer> btToIndex = new HashMap<>();
    int testCase = 1;

    btToIndex.put("A", 0);
    btToIndex.put("B", 1);
    btToIndex.put("O", 3);
    btToIndex.put("AB", 2);
    
    String [][] btTable = new String [4][4];
    btTable[0][0] = "A, O,";
    btTable[0][1] = "A, B, AB, O,";
    btTable[0][2] = "A, B, AB,";
    btTable[0][3] = "A, O,";
    
    btTable[1][0] = "A, B, AB, O,";
    btTable[1][1] = "B, O,";
    btTable[1][2] = "A, B, AB,";
    btTable[1][3] = "B, O,";
    
    btTable[2][0] = "A, B, AB,";
    btTable[2][1] = "A, B, AB,";
    btTable[2][2] = "A, B, AB,";
    btTable[2][3] = "A, B,";
    
    btTable[3][0] = "A, O,";
    btTable[3][1] = "B, O,";
    btTable[3][2] = "A, B,";
    btTable[3][3] = "O,";
    
    BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

    while (true) {

      StringTokenizer strTokenizer = new StringTokenizer(buffer.readLine());
      String p1 = strTokenizer.nextToken();
      String p2 = strTokenizer.nextToken();
      String child = strTokenizer.nextToken();
      if (p1.equals("E") && p2.equals("N") && child.equals("D")) break;
      
      if (p1.equals("?") || p2.equals("?")) {
        
        String p=p1.equals("?") ? p2 : p1;
        char pRH=p.charAt(p.length()-1);
        char cRH=child.charAt(child.length()-1);
        ArrayList<String> ppRH=new ArrayList<>();
        
        ppRH.add("+");
        if (!(pRH == '-' && cRH == '+')) ppRH.add("-");
          
        String pBT = p.substring(0,p.length()-1);
        String cBT = child.substring(0,child.length()-1)+",";
        ArrayList<String> ppBT = new ArrayList<>();
        
        for (String testParent : btToIndex.keySet()) {

          String possible = btTable[btToIndex.get(pBT)][btToIndex.get(testParent)];
          if (possible.contains(cBT)) ppBT.add(testParent);
        }
        
        String ans = "";
        if (ppBT.size() > 0 && ppRH.size() > 0) {

          StringBuilder sb = new StringBuilder();
          if (ppBT.size() != 1 || ppRH.size() != 1) {
            
            sb.append("{");
            for (String bt : ppBT) for (String rh : ppRH) {

              sb.append(bt);
              sb.append(rh);
              sb.append(", ");
            }
            sb.setLength(sb.length()-2);
            sb.append("}");

          } else {

            sb.append(ppBT.get(0));
            sb.append(ppRH.get(0));
          }
          ans=sb.toString();
          
        } else ans="IMPOSSIBLE";
        
        if (p1.equals("?")) p1=ans;
        else p2=ans;
      } else {
        char p1RH=p1.charAt(p1.length()-1), p2RH=p2.charAt(p2.length()-1);
        ArrayList<String> pcRH=new ArrayList<>();
        pcRH.add("-");
        if (p1RH!='-' || p2RH!='-') pcRH.add("+");
        
        String p1BT=p1.substring(0, p1.length()-1);
        String p2BT=p2.substring(0, p2.length()-1);
        
        ArrayList<String> pcBT=new ArrayList<>();
        for (String split : btTable[btToIndex.get(p1BT)][btToIndex.get(p2BT)].split(",")) if (split.length()>0) pcBT.add(split);
        
        if (pcBT.size()>0 && pcRH.size()>0) {
          StringBuilder sb=new StringBuilder();
          if (pcBT.size()!=1 || pcRH.size()!=1) {
            sb.append("{");
            for (String bt : pcBT) for (String rh : pcRH) {
              sb.append(bt);
              sb.append(rh);
              sb.append(", ");
            }
            sb.setLength(sb.length()-2);
            sb.append("}");
          } else {

            sb.append(pcBT.get(0));
            sb.append(pcRH.get(0));
          }

          child = sb.toString();
        } else child="IMPOSSIBLE";
      }
      
      System.out.printf("Case %d: %s %s %s\n", testCase++, p1, p2, child);
    }
  }

}