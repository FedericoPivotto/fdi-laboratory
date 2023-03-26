import java.util.*;

public class SortLines
{
	public static void main(String[] args)
	{
		// scanner
		Scanner console = new Scanner(System.in);

		// creazione di un'insieme ordinato
		SortedSet s = new ArraySortedSet();
		
		// riempimento dell'insieme
		while(console.hasNextLine())
		{
			String line = console.nextLine();
			s.add(line);
		}

		// stampa dell'insieme ordinato
		Comparable[] v = s.toSortedArray();
		for(int i = 0; i < v.length; i++)
		{
			System.out.println(v[i]);
		}
	}
}