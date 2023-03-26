public class RecursiveMCD
{
	/* ALGORITMO DI EUCLIDE
		m > n
		if((m % n) == 0) 	-->	 return n 					= MCD(m, n)
		else 				-->  return MCD(n, (m % n)) 	= MCD(m, n)
	*/
	public static int RecursiveMCD(int m, int n)
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

		return RecursiveMCD(n, (m % n));
	}

	public static void main(String[] args)
	{
		// controllo sul numero di parametri
		if(args.length != 2)
		{
			System.out.println("Errore: non sono stati inseriti due parametri");
			System.exit(1);
		}

		// controllo sulla validita dei parametri
		int n1 = 0, n2 = 0;
		try
		{
			n1 = Integer.parseInt(args[0]);
			n2 = Integer.parseInt(args[1]);
		}
		catch(NumberFormatException e)
		{
			System.out.println("Errore: i parametri non sono numerici");
			System.exit(1);
		}

		// stampa dell'MCD calcolato
		System.out.println("MCD(" + n1 + ", " + n2 + ") = " + RecursiveMCD(n1, n2));
	}
}