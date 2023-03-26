import java.util.Scanner;

public class IterativeBinarySearchAndBubbleSort
{
	public static void main(String[] args)
	{
		// creazione scanner
		Scanner console = new Scanner(System.in);

		// creazione array
		String[] v = {"Ciao", ",", "come", "stai", "?"};

		// stampa dell'array non ordinato
		System.out.println("Array non ordinato\n\t" + StringArrayAlgs.toString(v, v.length));

		// stampa dell'array ordinato
		StringArrayAlgs.bubbleSort(v, v.length);
		System.out.println("\nArray ordinato con BubbleSort\n\t" + StringArrayAlgs.toString(v, v.length));
		
		// stampa dell'array ordinato
		StringArrayAlgs.insertionSort(v, v.length);
		System.out.println("\nArray ordinato con InsertionSort\n\t" + StringArrayAlgs.toString(v, v.length));

		// ricerca binaria iterativa
		System.out.print("\nInserisci la stringa da ricerca: ");
		String value = console.nextLine();
		int index = StringArrayAlgs.iterativeBinarySearch(v, v.length, value);
		System.out.println("La stringa \"" + value + "\" si trova in posizione " + index);
	}
}