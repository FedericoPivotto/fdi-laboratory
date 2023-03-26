import java.util.Scanner;

public class EsercizioUnoV1 {

	public static void main(String[] args) {
		// costante
		final int MAX_DIGITS = 5;

		// lettura numero intero
		Scanner console = new Scanner(System.in);
		System.out.print("Inserisci un numero: ");
		int num = console.nextInt();

		// verifica positivita
		if (num >= 0) {
			// verifica cifre
			if ( (num / (int) Math.pow(10, MAX_DIGITS)) == 0 ) {
				// elaborazione cifre numero
				int exp, digit, tmp = num;

				// ALTERNATIVA CON CICLO FOR
				/*for(int i = 4; i >= 0; i--) {
					exp   = (int) Math.pow(10, i); 	// potenza
				 	digit = tmp / exp; 				// parte intera
				 	tmp   = num % exp; 				// parte decimale

				 	System.out.print(digit + " ");
				}*/

				// ALTERNATIVA CON IF
				// Iterazione 4
				exp   = (int) Math.pow(10, 4); 	// potenza
				digit = tmp / exp; 				// parte intera
				tmp   = num % exp; 				// parte decimale
				System.out.print(digit + " ");
				// Iterazione 3
				exp   = (int) Math.pow(10, 3); 	// potenza
				digit = tmp / exp; 				// parte intera
				tmp   = num % exp; 				// parte decimale
				System.out.print(digit + " ");
				// Iterazione 2
				exp   = (int) Math.pow(10, 2); 	// potenza
				digit = tmp / exp; 				// parte intera
				tmp   = num % exp; 				// parte decimale
				System.out.print(digit + " ");
				// Iterazione 1
				exp   = (int) Math.pow(10, 1); 	// potenza
				digit = tmp / exp; 				// parte intera
				tmp   = num % exp; 				// parte decimale
				System.out.print(digit + " ");
				// Iterazione 0
				exp   = (int) Math.pow(10, 0); 	// potenza
				digit = tmp / exp; 				// parte intera
				tmp   = num % exp; 				// parte decimale
				System.out.print(digit + " ");
			} else {
				System.out.println("Il numero inserito supera le 5 cifre");
			}
		} else {
			System.out.println("Il numero inserito \u00E8 negativo");
		}
	}
}