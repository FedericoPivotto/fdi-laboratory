public class SommaProdotto
{
	public static void main(String[] args)
	{
		// costanti
		final int MAX_NUMBER = 10;
		
		// variabili
		int sum = 0, mul = 1;

		for(int i = 1; i <= MAX_NUMBER; i++)
		{
			sum += i;
			mul *= i;
		}

		System.out.println("Somma: " + sum + "\nProdotto: " + mul);
	}
}