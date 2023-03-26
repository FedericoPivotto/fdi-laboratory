import java.util.Scanner;

public class MediaDeviazione {

	public static void main(String[] args) {
		// variabili
		Scanner console = new Scanner(System.in);
		double number, sum = 0, sumPow = 0, counter = 0;

		// lettura double e calcolo valori
		while(console.hasNextDouble()) {
			number = console.nextDouble();

			if(!Double.isNaN(number)) {
				sum += number;
				sumPow += Math.pow(number, 2);
				counter++;
			}
		}

		// calcolo valore medio
		double averageValue = sum / counter;

		// calcolo deviazione standard
		double standardDeviation = 0;
		if(counter > 1) {
			standardDeviation = Math.sqrt((sumPow - sum*sum/counter)/(counter - 1));
		}

		// stampa valore medio
		if(!Double.isNaN(averageValue)) {
			System.out.println("\nMedia: " + averageValue);
		} else {
			System.out.println("\nNon e\' possibile calcolare il valore medio");
		}

		// stampa deviazione standard
		if(counter >= 1) {
			System.out.println("Deviazione standard: " + standardDeviation);
		} else {
			System.out.println("Non e\' possibile calcolare la deviazione standard");
		}
	}
}