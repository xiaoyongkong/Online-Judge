import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

class Main {
  
    public static void main(String[] args) throws IOException {

      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String str;
      HashMap<String, ArrayList<String>> words = new HashMap<>();
      while (!(str = br.readLine()).equals("#")) {
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) {
          String w = st.nextToken();
          char [] c = w.toLowerCase().toCharArray();
          Arrays.sort(c);
          String key = new String(c);
          ArrayList<String> keyWords = null;
          if (words.containsKey(key)) keyWords = words.get(key);
          else {
            keyWords = new ArrayList<>();
            words.put(key, keyWords);
          }
          keyWords.add(w);
        }
      }
      Iterator<String> it = words.keySet().iterator();
      ArrayList<String> display = new ArrayList<>();
      while (it.hasNext()) {
        
        String key = it.next();
        if (words.get(key).size() == 1) display.add(words.get(key).get(0));
      }

      Collections.sort(display);
      StringBuilder sb = new StringBuilder();
      it = display.iterator();

      while (it.hasNext()) {
        
        sb.append(it.next() + "\n");
      }
      System.out.printf(sb.toString());
    }
}