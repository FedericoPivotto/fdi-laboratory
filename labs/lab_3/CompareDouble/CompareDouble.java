import java.util.Scanner;

public class CompareDouble {

	public static void main(String[] args) {
		final int MAX_DOUBLE = 2;
		final int DECIMAL = 2;
		final double EPSILON = Math.pow(10, (DECIMAL + 1) * (-1)); // 10^((decimali + 1)*(-1))
		// precisione di minore di 0,01
		// per essere uguali due numeri arrotondati
		// rispettare la seguente conidizione
		// ( |n1-n2| < 0.01 )

		// ESEMPIO
		// 2.0 e 1.99998: uguali
		// 0.999 e 0.991: diversi

		// variabili
		System.out.println("**** Lettura numeri frazionari ****");
		Scanner console = new Scanner(System.in);
		double n1 = 0, n2 = 0;

		// lettura
		for(int i = 0; i < MAX_DOUBLE; i++) {
			double n = console.nextDouble();
			if(i == 0) {
				n1 = n;
			} else {
				n2 = n;
			}
		}

		// ordinamento
		System.out.println("\n**** Esito del confronto ****");
		if( Math.abs(n1 - n2) <= (EPSILON * Math.max(Math.abs(n1), Math.abs(n2))) ) {
			System.out.println("I due numeri sono UGUALI");
		} else {
			System.out.println("I due numero sono DIVERSI");
		}
	}
}