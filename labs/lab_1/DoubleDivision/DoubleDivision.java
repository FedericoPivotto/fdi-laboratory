public class DoubleDivision {
	
	public static void main(String[] args) {
		// dichiarazione variabili
		double x, y, div;

		// divisione
		x = 7; y = 0;
		div = x / y;
		// stampa risultati
		System.out.println("x = " + x + "  y = " + y);
		System.out.println("Risultato: " + div + "\n");

		// divisione
		x = -7; y = 0;
		div = x / y;
		// stampa risultati
		System.out.println("x = " + x + "  y = " + y);
		System.out.println("Risultato: " + div + "\n");

		// divisione
		x = 0; y = 0;
		div = x / y;
		// stampa risultati
		System.out.println("x = " + x + "  y = " + y);
		System.out.println("Risultato: " + div);
	}
}


/*
	x = 7.0  y = 0.0
	Risultato: Infinity

	x = -7.0  y = 0.0
	Risultato: -Infinity

	x = 0.0  y = 0.0
	Risultato: NaN
*/