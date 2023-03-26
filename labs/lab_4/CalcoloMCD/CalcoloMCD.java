import java.util.Scanner;

public class CalcoloMCD
{
	public static void main(String[] args)
	{
		// costanti
		final int NUMBERS_TO_READ = 2;

		// variabili
		Scanner console = new Scanner(System.in);
		int num, m = 0, n = 0, tmp, rest;

		// lettura
		for(int i = 0; i < NUMBERS_TO_READ; i++)
		{
			do
			{
				System.out.print("Inserisci numero: ");
				num = console.nextInt();
			} while(num < 0);

			if(i == 0)
			{
				m = num;
			}
			else
			{
				n = num;
			}
		}

		// eventuale scambio
		if (m < n)
		{
			tmp = n;
			n 	= m;
			m 	= tmp;
		}

		// calcolo MCD con algoritmo di Euclide
		for(int i = 0; (rest = m % n) != 0; i++)
		{
			// m = precedente valore di n
			m = n;
			// n = resto della divisione del precedente m per il precedente n
			n = rest;
		}

		System.out.println("\nMCD: " + n);
	}
}