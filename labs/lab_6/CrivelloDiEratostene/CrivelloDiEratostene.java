import java.util.Scanner;

public class CrivelloDiEratostene
{
	public static void main(String[] args)
	{
		// scanner
		Scanner console = new Scanner(System.in);

		// lettura MAX
		int MAX;
		do
		{
			System.out.print("Inserisci MAX: ");
			MAX = console.nextInt();
		} while(MAX < 0);

		// array di primi (primo: false, non primo: true)
		boolean array[] = new boolean[MAX];

		// algoritmo Crivello di Erastotene
		for(int i = 2; i < array.length; i++)
		{
			for(int j = 0; j < array.length; j++)
			{
				if((j != i) && ((j % i) == 0))
				{
					array[j] = true;
				}
			}
		}
		array[0] = true;

		// stampa indici a false, ovvero i numeri primi
		for (int i = 0; i < array.length; i++)
		{
			if(!array[i])
			{
				System.out.print(i + " ");
			}
		}
	}
}