import java.util.Scanner;

public class ElencoNumeri
{
	public static void main(String[] args)
	{
		// costante
		final int MIN_NUMBER = 10;
		// variabili
		Scanner console = new Scanner(System.in);

		// lettura
		System.out.print("Inserisci numero: ");
		int num = console.nextInt();

		// stampa numeri
		if(num >= MIN_NUMBER)
		{
			for(int i = MIN_NUMBER; i <= num; i++)
			{
				System.out.println(i);
			}
		}
	}
}