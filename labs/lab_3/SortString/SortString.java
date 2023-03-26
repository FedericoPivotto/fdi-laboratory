import java.util.Scanner;

public class SortString {

	public static void main(String[] args) {
		// costanti
		final int MAX_STRING = 3;

		// variabili
		Scanner console = new Scanner(System.in);
		String str1 = "", str2 = "", str3 = "";

		// lettura
		System.out.println("**** Lettura stringhe ****");
		for(int i = 0; i < MAX_STRING; i++) {
			String str = console.nextLine();
			if(i == 0) {
				str1 = str;
			} else if (i == 1) {
				str2 = str;
			} else {
				str3 = str;
			}
		}
 		
 		// Negativo --> <
 		// Positivo --> >
 		// Uguale	--> ==

		// ordinamento *MIGLIORABILE*
		System.out.println("\n**** Ordinamento lessicografico ****");
		if(str1.compareTo(str2) < 0 && str1.compareTo(str3) < 0 && str2.compareTo(str3) < 0) { // str1 < str2 < str3
			System.out.println(str1 + "\n" + str2 + "\n" + str3);
		} else if(str1.compareTo(str3) < 0 && str1.compareTo(str2) < 0 && str3.compareTo(str2) < 0) { // str1 < str3 < str2
			System.out.println(str1 + "\n" + str3 + "\n" + str2);
		} else if(str2.compareTo(str1) < 0 && str2.compareTo(str3) < 0 && str1.compareTo(str3) < 0) { // str2 < str1 < str3
			System.out.println(str2 + "\n" + str1 + "\n" + str3);
		} else if(str2.compareTo(str3) < 0 && str2.compareTo(str1) < 0 && str3.compareTo(str1) < 0) { // str2 < str3 < str1
			System.out.println(str2 + "\n" + str3 + "\n" + str1);
		} else if(str3.compareTo(str1) < 0 && str3.compareTo(str2) < 0 && str1.compareTo(str2) < 0) { // str3 < str1 < str2
			System.out.println(str3 + "\n" + str1 + "\n" + str2);
		} else if(str3.compareTo(str2) < 0 && str3.compareTo(str1) < 0 && str2.compareTo(str1) < 0) { // str3 < str2 < str1
			System.out.println(str3 + "\n" + str2 + "\n" + str1);
		}
	}
}