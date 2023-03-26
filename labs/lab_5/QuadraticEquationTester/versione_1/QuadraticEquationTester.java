import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Locale;

public class QuadraticEquationTester
{
	public static void main(String[] args)
	{
		// variabili di servizio
		Scanner console = new Scanner(System.in);
		console.useLocale(Locale.US);
		boolean done;

		// variabili utili
		double a = 0, b = 0, c = 0;

		// stampa di cortesia
		System.out.println("****** STRUTTURA EQUAZIONE ******");
		System.out.println("ax\u00B2 + bx + c = 0");

		// lettura coefficiente a
		System.out.println("\n****** COEFFICENTE \'a\' ******");
		done = false;
		while(!done)
		{
			try
			{
				// lettura di a
				System.out.print("Inserisci a: ");
				a = console.nextDouble();

				// esco dal ciclo
				done = true;
			}
			catch(InputMismatchException e)
			{
				// segnalazione errore
				System.out.println("Input non valido, riprova.\n");

				// svuoto lo standard input
				console.nextLine();
			}
		}

		// lettura coefficiente b
		System.out.println("\n****** COEFFICENTE \'b\' ******");
		done = false;
		while(!done)
		{
			try
			{
				// lettura di b
				System.out.print("Inserisci b: ");
				b = console.nextDouble();

				// esco dal ciclo
				done = true;
			}
			catch(InputMismatchException e)
			{
				// segnalazione errore
				System.out.println("Input non valido, riprova.\n");

				// svuoto lo standard input
				console.nextLine();
			}
		}

		// lettura coefficiente c
		System.out.println("\n****** COEFFICENTE \'c\' ******");
		done = false;
		while(!done)
		{
			try
			{
				// lettura di b
				System.out.print("Inserisci c: ");
				c = console.nextDouble();

				// esco dal ciclo
				done = true;
			}
			catch(InputMismatchException e)
			{
				// segnalazione errore
				System.out.println("Input non valido, riprova.\n");

				// svuoto lo standard input
				console.nextLine();
			}
		}

		// creazione dell'equazione
		QuadraticEquation eq = new QuadraticEquation(a, b, c);

		// stampa dell'equazione inserita
		System.out.println("\n****** EQUAZIONE INSERITA ******");
		System.out.println(eq.toString());

		// stampa dei risultati
		System.out.println("\n****** SOLUZIONI EQUAZIONE ******");
		System.out.println(eq.calculateSolution());
	}
}