import java.util.Scanner;

public class ReverseString
{
	/* trasforma una stringa in un array */
	public static char[] toArray(String str)
	{
		// pre-condizione
		if(str == null)
		{
			throw new IllegalArgumentException();
		}

		// elaborazione
		char[] array = new char[str.length()];
		for(int i= 0; i < str.length(); i++)
		{
			array[i] = str.charAt(i);
		}

		return array;
	}

	/* controlla se le stringhe ricevute sono inverse */
	public static boolean reverseString(char[] array1, char[] array2)
	{
		// pre-condizione
		if(array1 == null || array2 == null)
		{
			throw new IllegalArgumentException();
		}

		// elaborazione
		if(array1.length == array2.length)
		{
			int j = array2.length - 1;
			for(int i = 0; i < array1.length && j > -1; i++, j--)
			{
				if(array1[i] != array2[j])
				{
					return false;
				}
			}

			return true;
		}

		return false;
	}

	public static String[] increases(String array[], int n)
    {
		String newArray[] = new String[array.length + n];
		for(int i = 0; i < array.length; i++)
		{
			newArray[i] = array[i];
		}

		return newArray;	
	}

	public static void main(String[] args)
	{
		// lettura stringhe
		Scanner console = new Scanner(System.in);
		String[] input = args;

		// controllo input
		if(input.length > 2)
		{
			System.out.println("Errore in fase di input");
			System.exit(1);
		}
		else if(input.length == 1)
		{
			input = increases(input, 1);
			System.out.print("Inserisci la seconda stringa: ");
			input[1] = console.nextLine();
		}
		else if(input.length == 0) // non viene mai invocato
		{
			input = increases(input, 2);
			System.out.print("Inserisci la prima stringa: ");
			input[0] = console.nextLine();
			System.out.print("Inserisci la seconda stringa: ");
			input[1] = console.nextLine();
		}

		// salvataggio in variabili
		String str1 = input[0];
		String str2 = input[1];

		// stampa esito del confronto
		if(reverseString(toArray(str1), toArray(str2)))
		{
			System.out.println("Le stringhe \"" + str1 + "\" e \"" + str2 + "\" sono una l'inversa dell'altra");
		}
		else
		{
			System.out.println("Le stringhe \"" + str1 + "\" e \"" + str2 + "\" non sono una l'inversa dell'altra");
		}
	}
}