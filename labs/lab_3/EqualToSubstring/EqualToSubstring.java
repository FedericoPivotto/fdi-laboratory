import java.util.Scanner;

public class EqualToSubstring {
	
	public static void main(String[] args) {
		// costanti
		final int MAX_STRING = 2;

		// variabili
		Scanner console = new Scanner(System.in);
		String s1 = "", s2 = ""; // s1: stringa, s2: sottostringa
		boolean isEqual = false; // false di default
		int start = 0, end = 0;

		// lettura stringhe
		for(int i = 0; i < MAX_STRING; i++) {
			String s = console.nextLine();
			if(i == 0) {
				s1 = s;
			} else {
				s2 = s;
			}
		}

		// controllo sottostringa uguale
		if(!s2.equals("")) {
			if(s1.length() >= s2.length()) {
				for(int i = 0; i < s1.length() && !isEqual; i++) {
					for(int j = 0, k = i; j < s2.length(); j++, k++) {
						if(j == 0) { // salvataggio indici
							start = k;
							end = start + s2.length(); 
						}

						if(s1.charAt(k) != s2.charAt(j)) {
							isEqual = false; break;
						} else {
							isEqual = true;
						}
					}
				}
			}
		} else { // stringa vuota e' sottostringa di qualsiasi stringa
			isEqual = true;
		}

		// stampa del risultato
		if(isEqual) {
			System.out.println("\ns2=\""+ s2 +"\" e\' una sottostring di s1=\"" + s1 + "\"");
			System.out.println("N.B: s2=\""+ s2 + "\" mentre substring(" + start + ", " + end + ")=\"" + s1.substring(start, end) + "\"");
		} else {
			System.out.println("\ns2=\""+ s2 +"\" NON e\' una sottostring di s1=\"" + s1 + "\"");
		}
	}
}