import java.util.Scanner;

public class StampaNumeriPrimi
{
	public static void main(String[] args)
	{
		// variabili
		Scanner console = new Scanner(System.in);
		int num, count = 0;

		// lettura
		do
		{
			System.out.print("Inserisci numero: ");
			num = console.nextInt();
		} while(num < 0);

		// stampa numeri primi
		for(int i = 0; i <= num; i++)
		{
			// verifico se numero primo
			for(int j = 1; j <= i; j++)
			{
				// verifico la divisibilita'
				if((i % j) == 0)
				{
					// incremento contatore divisori
					count++;
				}
			}

			// stampa numero primo
			if(count == 1 || count == 2)
			{
				System.out.println(i);
			}

			// azzera contatore divisori
			count = 0;
		}
	}
}