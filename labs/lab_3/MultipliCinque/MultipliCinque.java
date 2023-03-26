public class MultipliCinque {

	public static void main(String[] args) {
		// costanti
		final int MIN_NUMBER = 10;
		final int MAX_NUMBER = 100;
		final int MULTIPLE = 5;

		// decommenta il metodo che vuoi utilizzare		

		/* METODO 1 - CICLO WHILE
		int counter = 0, result = 0;
		while(result < MAX_NUMBER) {
			// calcolo
			result = MULTIPLE * counter++;

			// controllo multiplo
			if(result >= MIN_NUMBER) {
				System.out.println(result);
			}
		} */

		/* METODO 1 - CICLO FOR
		int result = 0;
		for(int counter = 0; result < MAX_NUMBER; counter++) {
			// calcolo
			result = MULTIPLE * counter;

			// controllo multiplo
			if(result >= MIN_NUMBER) {
				System.out.println(result);
			}
		} */

		/* METODO 2 - CICLO WHILE
		int counter = MAX_NUMBER / 5, result = MAX_NUMBER;
		while(result > MIN_NUMBER) {
			// calcolo
			result = MULTIPLE * counter--;

			// controllo multiplo
			if(result <= MAX_NUMBER) {
				System.out.println(result);
			}
		} */

		/* METODO 2 - CICLO FOR
		int result = MAX_NUMBER;
		for(int counter = MAX_NUMBER / 5; result > MIN_NUMBER; counter--) {
			// calcolo
			result = MULTIPLE * counter;

			// controllo multiplo
			if(result <= MAX_NUMBER) {
				System.out.println(result);
			}
		} */
	}
}