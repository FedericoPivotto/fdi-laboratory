import java.util.Scanner;

public class EsercizioUnoV2 {

	public static void main(String[] args) {
		// costante
		final int MAX_DIGITS = 5;

		// lettura numero intero
		Scanner console = new Scanner(System.in);
		System.out.print("Inserisci un numero: ");
		int num = console.nextInt();

		// verifica positivita
		if (num >= 0) {
			// trasformazione
			String strNum = Integer.toString(num);
			int strLen = strNum.length();

			// verifica cifre
			if ( strLen <= MAX_DIGITS ) {
				// elaborazione cifre numero
				String result = "";

				// ALTERNATIVA CON CICLO FOR
				/*for(int i = 0; i < MAX_DIGITS; i++) {
					if ( i == (strLen - 1) ) {
						result += strNum.substring(i);
					} else if( i < strLen ) {
						result += strNum.substring(i, (i + 1)) + " ";
					} else {
						result = "0 " + result;
					}
				}*/

				// ALTERNATIVA CON IF
				// Iterazione 0
				if ( 0 == (strLen - 1) ) {
					result += strNum.substring(0);
				} else if( 0 < strLen ) {
					result += strNum.substring(0, (0 + 1)) + " ";
				} else {
					result = "0 " + result;
				}
				// Iterazione 1
				if ( 1 == (strLen - 1) ) {
					result += strNum.substring(1);
				} else if( 1 < strLen ) {
					result += strNum.substring(1, (1 + 1)) + " ";
				} else {
					result = "0 " + result;
				}
				// Iterazione 2
				if ( 2 == (strLen - 1) ) {
					result += strNum.substring(2);
				} else if( 2 < strLen ) {
					result += strNum.substring(2, (2 + 1)) + " ";
				} else {
					result = "0 " + result;
				}
				// Iterazione 3
				if ( 3 == (strLen - 1) ) {
					result += strNum.substring(3);
				} else if( 3 < strLen ) {
					result += strNum.substring(3, (3 + 1)) + " ";
				} else {
					result = "0 " + result;
				}
				// Iterazione 4
				if ( 4 == (strLen - 1) ) {
					result += strNum.substring(4);
				} else if( 4 < strLen ) {
					result += strNum.substring(4, (4 + 1)) + " ";
				} else {
					result = "0 " + result;
				}

				System.out.println(result);
			} else {
				System.out.println("Il numero inserito supera le 5 cifre");
			}
		} else {
			System.out.println("Il numero inserito \u00E8 negativo");
		}
	}
}