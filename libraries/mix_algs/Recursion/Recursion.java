/*
	Classe contente algoritmi ricorsivi

	NOTA BENE
	 - Se questo fosse codice professionale, tutti i metodi seguenti
	   conterrebbero un controllo delle pre-condizioni e lancerebbero
	   eccezioni nel caso le pre-condizioni non fossero verificate
*/
public class Recursion
{
	/*
		Fibonacci ricorsivo
	*/
	public static int recursiveFib(int n)
	{
		// pre-condizione
		if(n < 0)
		{
			throw new IllegalArgumentException();
		}

		if(n == 0 || n == 1)
		{
			return 1;
		}

		return (recursiveFib(n - 1) + recursiveFib(n - 2));
	}

	/*
		Massimo di un array
	*/
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

	/*
		Metodo ausiliario del metodo massimo
	*/
	private static int max(int n1, int n2)
	{
		return Math.max(n1, n2);
	}

	/*
		Rovescia una stringa ricorsivamente
	*/
	public static String reverse(String str)
	{
		// pre-condizione
		if(str == null)
		{
			throw new IllegalArgumentException();
		}

		if(str.length() == 0 || str.length() == 1)
		{
			return str;
		}

		return reverse(str.substring(1)) + str.charAt(0);
	}

	/* 
		MCD ricorsivo mediante l'algoritmo di Euclide
		 - if(!(m > n))			-->	 swap
		 - if((m % n) == 0) 	-->	 return n 					= MCD(m, n)
		 - else 				-->  return MCD(n, (m % n)) 	= MCD(m, n)
	*/
	public static int recursiveMCD(int m, int n)
	{
		// eventuale swap
		if(!(m > n))
		{
			int tmp = m;
			m = n;
			n = m;
		}

		if((m % n) == 0)
		{
			return n;
		}

		return recursiveMCD(n, (m % n));
	}
}