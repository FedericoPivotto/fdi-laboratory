import java.util.Scanner;
import java.util.Random;
import java.util.InputMismatchException;

public class ArrayAlgsTester
{
	public static void main(String[] args)
	{
		// costanti
		final String SS   = "s";
		final String IS   = "i";
		final String MS   = "m";
		final String LS   = "l";
		final String BS   = "b";
		final String QUIT = "q";

		// scanner
		Scanner console = new Scanner(System.in);

		// lettura dimensione array
		int length = 0;
		do
		{
			try
			{
				System.out.print("Inserisci dimensione: ");
				length = console.nextInt();

				// svuoto lo standard input
				console.nextLine();
			}
			catch(InputMismatchException e)
			{
				// svuoto lo standard input
				console.nextLine();

				System.out.println("Input non valido, riprovare.");
			}
		} while(length < 1);

		// lettura intervallo
		int n = 0;
		do
		{
			try
			{
				System.out.print("Inserisci intervallo: ");
				n = console.nextInt();

				// svuoto lo standard input
				console.nextLine();
			}
			catch(InputMismatchException e)
			{
				// svuoto lo standard input
				console.nextLine();

				System.out.println("Input non valido, riprovare.");
			}
		} while(n < 1);

		// creazione array riempito casualmente
		int[] array = ArrayAlgs.randomIntArray(length, n);

		// stampa dell'array
		System.out.println("\nARRAY: " + ArrayAlgs.printArray(array, array.length));

		// stampa del menu di ordinamento
		System.out.println("\n********** MENU DI SCELTA **********");
		System.out.println("S: SelectionSort\nI: InsertionSort\nM: MergeSort\nQ: Quit");

		// ciclo per la gestione degli ordinamenti
		boolean done = false;
		while(!done)
		{
			// lettura scelta
			System.out.print("\nInserisci scelta: ");
			String scelta = console.nextLine();

			if(scelta.equalsIgnoreCase(SS))
			{
				ArrayAlgs.selectionSort(array, array.length);
				System.out.println("SelectionSort: " + ArrayAlgs.printArray(array, array.length));
			}
			else if(scelta.equalsIgnoreCase(IS))
			{
				ArrayAlgs.insertionSort(array, array.length);
				System.out.println("InsertionSort: " + ArrayAlgs.printArray(array, array.length));
			}
			else if(scelta.equalsIgnoreCase(MS))
			{
				ArrayAlgs.mergeSort(array, array.length);
				System.out.println("MergeSort: " + ArrayAlgs.printArray(array, array.length));
			}
			else if(scelta.equalsIgnoreCase(QUIT))
			{
				done = true;
				System.out.println("Quit");
			}
			else
			{
				System.out.println("Comando errato, riprovare.");
			}
		}

		// stampa del menu di ricerca
		System.out.println("\n\n********** MENU DI SCELTA **********");
		System.out.println("L: LinearSearch\nB: BinarySearch\nQ: Quit");

		// ciclo per la gestione della ricerca
		boolean isValid = false;
		done = false;
		while(!done)
		{
			// lettura numero da ricercare
			int num = 0;
			isValid = false;
			do
			{
				try
				{
					System.out.print("\nInserisci numero: ");
					num = console.nextInt();
					isValid = true;

					// svuoto lo standard input
					console.nextLine();
				}
				catch(InputMismatchException e)
				{
					// svuoto lo standard input
					console.nextLine();

					System.out.println("Input non valido, riprovare.");
				}
			} while(!isValid);

			// lettura scelta
			isValid = false;
			do
			{
				System.out.print("Inserisci scelta: ");
				String scelta = console.nextLine();

				if(scelta.equalsIgnoreCase(LS) || scelta.equalsIgnoreCase(BS))
				{
					isValid = true;

					boolean flag = true;
					int index = -1;
					if(scelta.equalsIgnoreCase(LS))
					{
						index = ArrayAlgs.linearSearch(array, array.length, num);
					}
					else if(scelta.equalsIgnoreCase(BS))
					{
						if(ArrayAlgs.isSorted(array, array.length))
						{
							index = ArrayAlgs.binarySearch(array, array.length, num);
						}
						else
						{
							flag = false;
							System.out.println("Operazione vietata: l\'array non e\' ordinato");
						}
					}

					if(flag)
					{
						if(index == -1)
						{
							System.out.println("Il numero cercato non e\' presente");
						}
						else
						{
							System.out.println("Il numero cercato si trova in posizione " + index);
						}
					}
				}
				else if(scelta.equalsIgnoreCase(QUIT))
				{
					done = true;
					isValid = true;
					System.out.println("Quit");
				}
				else
				{
					System.out.println("Comando errato, riprovare.\n");
				}
			} while(!isValid);
		}
	}
}