import java.util.Scanner;

public class ModificaStringhe
{
	public static void main(String[] args)
	{
		// variabili
		Scanner console = new Scanner(System.in);
		String str = "", str1 = "", str2 = "", str3 = "";

		// lettura aggettivo
		do
		{
			System.out.print("Inserisci aggettivo: ");
			str = console.nextLine();
		} while (str.charAt(str.length() - 1) != 'o' && str.charAt(str.length() - 1) != 'a');

		// aggettivo inserito, con solo il primo carattere maiuscolo
		str1 = str.toUpperCase().charAt(0) + str.substring(1, str.length()).toLowerCase();

		// forma diminutiva (-ino / -ina) dell'aggettivo inserito
		str2 = str1.substring(0, (str.length() - 1)) + "in" + str1.charAt(str1.length() - 1);

		// grado superlativo assoluto (-issimo / -issima) dell'aggettivo inserito
		str3 = str1.substring(0, (str.length() - 1)) + "issim" + str1.charAt(str1.length() - 1);

		// stampa stringhe
		System.out.println("\nAggettivo inserito:\t" 		+ str1);
		System.out.println("Forma diminutiva:\t" 		+ str2);
		System.out.println("Superlativo assoluto:\t" 	+ str3);
	}
}