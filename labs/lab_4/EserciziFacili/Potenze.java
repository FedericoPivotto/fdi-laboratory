public class Potenze
{
	public static void main(String[] args)
	{
		// variabili
		int pow = 0;

		// stampa potenze
		for(int x = 1; x <= 4; x++)
		{
			for(int y = 1; y <= 5; y++)
			{
				pow = (int) Math.pow(x, y);
				System.out.print(pow + "\t");
			}

			System.out.println("\n");
		}
	}
}