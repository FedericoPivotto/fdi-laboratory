public class IntDivision {
	
	public static void main(String[] args) {
		// dichiarazione variabili
		int m = 17, n = 4;

		// divisione intera
		int div  = m / n; 
		int rest = m % n;

		// stampa risultati
		System.out.println("m = " + m + "  n = " + n);
		System.out.println("Risultato: " + div + " con resto " + rest);
	}
}


/*
	m = 17  n = 4
	Risultato: 4 con resto 1
*/