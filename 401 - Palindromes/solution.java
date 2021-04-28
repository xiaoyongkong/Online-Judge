import java.util.Scanner;

class Main {

	public static void main(String[] args) {
		
    Scanner in = new Scanner(System.in);
		boolean mirrored, regular;
    char[] str;
		String s;

		while(in.hasNext())
		{
		    s = in.nextLine();
		    str = s.toCharArray();
		    mirrored = true;
        regular = true;

		    for (int i = 0; i < str.length/2; i++) {

          if (regular) 
            if (str[i]!=str[str.length-i-1]) regular=false;

          if (mirrored) {
            if (str[i] == 'E') str[i] = '3';
            else if (str[i] == 'J') str[i] = 'L';
            else if (str[i] == 'S') str[i] = '2';
            else if (str[i] == 'Z') str[i] = '5';
            else if (str[i] == '3') str[i] = 'E';
            else if (str[i] == 'L') str[i] = 'J';
            else if (str[i] == '2') str[i ]= 'S';
            else if (str[i] == '5') str[i ]= 'Z';
            if (str[i] != str[str.length - i - 1] || str[i]=='B' || str[i]=='C' 
                  || str[i] == 'D' || str[i] == 'F' || str[i] == 'G'
                  || str[i] == 'K' || str[i] == 'N' || str[i] == 'P' 
                  || str[i] == 'Q' || str[i] == 'R' || str[i] == '4' 
                  || str[i] == '6' || str[i] == '7' || str[i] == '9'
            ) mirrored=false;
          }
        }
      
        if (mirrored && (str.length % 2) == 1) {

          int mid = str.length/2;

          if (!(str[mid] == 'A' || str[mid] == 'H' || str[mid]=='I' || str[mid] == 'T' 
                  || str[mid] == 'Y' || str[mid] == 'U' || str[mid] == 'O'
                  || str[mid] == 'X' || str[mid] == 'M' || str[mid] == 'V' 
                  || str[mid] == '8' || str[mid] == 'W' || str[mid] == '1'
              )) mirrored=false;
        }
			if (mirrored && regular) System.out.println(s + " -- is a mirrored palindrome.");
			else if (mirrored) System.out.println(s + " -- is a mirrored string.");
			else if (regular) System.out.println(s + " -- is a regular palindrome.");
			else System.out.println(s + " -- is not a palindrome.");
			
      System.out.println();

		}
	}
}