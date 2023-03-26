import java.util.Random;

public class Massimo
{
	public static int massimo(int[] array)
	{
		// pre-condizione
		if(array == null || array.length == 0)
		{
			throw new IllegalArgumentException();
		}

		if(array.length == 2)
		{
			return max(array[0], array[1]);
		}
		else if(array.length == 1)
		{
			return array[0];
		}

		int[] newArray = new int[array.length - 1];
		System.arraycopy(array, 0, newArray, 0, array.length - 1);
		return max(massimo(newArray), array[array.length - 1]);
	}

	private static int max(int n1, int n2)
	{
		return Math.max(n1, n2);
	}

	public static void main(String[] args)
	{
		// generatore random
		Random rand = new Random();

		// array
		int[] array = new int[10];

		// riempimento array
		for(int i = 0; i < array.length; i++)
		{
			array[i] = rand.nextInt(50);
		}

		// stampa dell'array
		for(int i = 0; i < array.length; i++)
		{
			System.out.print(array[i] + " ");
		}	

		// stampa del massimo
		System.out.println("\n\nMassimo: " + massimo(array));
	}
}