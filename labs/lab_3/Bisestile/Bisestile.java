import java.util.Scanner;

public class Bisestile {
	
	public static void main(String[] args) {
		// variabili
		Scanner console = new Scanner(System.in);
		int year;

		// lettura anno
		do {
			year = console.nextInt();
		} while(year < 0);

		// decommenta il metodo che vuoi usare

		/* METODO 1
		if ( ( ((year % 4) == 0) && ((year % 100) != 0) ) || (((year % 400) == 0) && ((year % 100) != 0)) ) {
			System.out.println("L'anno inserito e\' bisestile");
		} else {
			System.out.println("L'anno inserito NON e\' bisestile");
		} */

		/* METODO 2
		boolean bisestile1 = (year % 4 == 0) && (year % 100 != 0);
		boolean bisestile2 = (year % 400 == 0) && (year % 100 != 0);
		boolean isBisestile = bisestile1 || bisestile2;

		if (isBisestile) {
			System.out.println("L'anno inserito e\' bisestile");
		} else {
			System.out.println("L'anno inserito NON e\' bisestile");
		} */
	}
}