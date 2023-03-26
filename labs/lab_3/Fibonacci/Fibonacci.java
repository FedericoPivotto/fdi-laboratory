import java.util.Scanner;
public class Fibonacci {
	
	public static void main(String[] args) {
		// variabili
		Scanner console = new Scanner(System.in);
		int position, n1 = 1, n2 = 1, result = 0;

		// lettura
		do {
			position = console.nextInt();
		} while(position < 1);

		// calcolo fibonacci
		if((position != 1) && (position != 2)) {
			for(int i = 0; i < (position - 2); i++) {
				result = n1 + n2; 	// aggiorno result
				n1 = n2; 			// n1 diventa n2
				n2 = result; 		// n2 diventa result
			}
		} else {
			result = 1;
		}

		// stampa n-esimo di fibonacci
		System.out.println("\nFibonacci in posizione " + position + ": " + result);
	}
}