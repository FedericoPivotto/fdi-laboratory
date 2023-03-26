import java.util.Scanner;

public class Fibonacci
{
	public static long recursiveFib(int n)
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

		return (long) (recursiveFib(n - 1) + recursiveFib(n - 2));
	}

	public static long iterativeFib(int n)
	{
		// pre-condizione
		if(n < 0)
		{
			throw new IllegalArgumentException();
		}

		long n1 = 1, n2 = 1;
		for (int i = 1; i < n && n > 1; i++)
		{
			long tmp = n1;
			n1 = n1 + n2;
			n2 = tmp;
		}

		return n1;
	}

	public static void main(String[] args)
	{
		// scanner
		Scanner console = new Scanner(System.in);

		// lettura n
		System.out.print("Inserisci n: ");
		int n = console.nextInt();

		// misurazione tempo versione iterattiva
		long start1  = System.currentTimeMillis();
		long fib1 	 = iterativeFib(n);
		long end1 	 = System.currentTimeMillis();
		double time1 = (end1 - start1) / 1000.0;

		// misurazione tempo versione ricorsiva
		long start2  = System.currentTimeMillis();
		long fib2 	 = recursiveFib(n);
		long end2 	 = System.currentTimeMillis();
		double time2 = (end2 - start2) / 1000.0;

		// stampa dei risultati
		System.out.print("\nTempo [s] di iterativeFib(" + n + ")=" + fib1 + ": " + time1);
		System.out.print("\nTempo [s] di recursiveFib(" + n + ")=" + fib2 + ": " + time2);
	}
}