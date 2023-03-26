import java.util.*;

public class ArraySortedSet implements SortedSet
{
	private Comparable[] v 	   = new Comparable[100];
	private int 		 vSize = 0;

	/*
		O(1)
	*/
	public void makeEmpty()
	{
		this.vSize = 0;
	}

	/*
		O(1)
	*/
	public boolean isEmpty()
	{
		return (this.vSize == 0);
	}

	/*
		O(n)
	*/
	public Comparable[] toSortedArray()
	{
		Comparable[] vObj = new Comparable[this.vSize];
		System.arraycopy(this.v, 0, vObj, 0, this.vSize);

		return vObj;
	}

	/*
		O(n)
	*/
	public Object[] toArray()
	{
		return this.toSortedArray();
	}

	/*
		O(log(n))
	*/
	public boolean contains(Object obj)
	{
		int status = this.binarySearch(this.v, this.vSize, (Comparable) obj);

		if(status == -1)
			return false;
		
		return true;
	}

	public void add(Object obj)
	{
		throw new IllegalArgumentException();
	}

	/*
		O(n)
	*/
	public void add(Comparable obj)
	{
		if(this.contains(obj))
			return;

		if(this.vSize == this.v.length)
			this.v = resize(this.v, 2*this.v.length);

		this.v[this.vSize++] = obj;

		// usiamo insertion sort che e' O(n)
		// perche inseriamo in un array ordinato
		Comparable tmp = this.v[this.vSize-1];
		int i;

		// ordine per numero di parole
		//	- se il numero di parole e' uguale --> numero minore di caratteri
		//		- se il numero di caratteri e' uguale --> confronto lessico grafico

		for (i = this.vSize-1; i > 0 && sortString((String) tmp, (String) this.v[i-1]); i--)
			this.v[i] = this.v[i-1];
		this.v[i] = tmp;
	}

	/*
		Se s1 < s2 restituisce true
		Se s1 > s2 restituisce false	
	*/
	private static boolean sortString(String s1, String s2)
	{
		// confronto numero parole
		if(s1.split(" ").length < s2.split(" ").length)
		{
			return true;
		}
		else if(s1.split(" ").length > s2.split(" ").length)
		{
			return false;
		}
	
		// confronto lunghezza stringhe
		if(s1.length() < s2.length())
		{
			return true;
		}
		else if(s1.length() > s2.length())
		{
			return false;
		}
		
		// confronto lessicografico stringhe
		if(s1.compareTo(s2) < 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/*
		Ricerca binaria (o dicotomica)
	*/
	private static int binarySearch(Comparable[] v, int vSize, Comparable value)
	{
		// pre-condizione
		if(v == null || vSize < 0)
			throw new IllegalArgumentException();

		return binSearch(v, 0, vSize-1, value);
	}

	private static int binSearch(Comparable[] v, int from, int to, Comparable value)
	{  
		if (from > to)
			return -1; // elemento non trovato

		int mid = (from + to) / 2; // circa in mezzo
		Comparable middle = v[mid];

		if (middle.compareTo(value) == 0)
			return mid; // elemento trovato
		
		else if (middle.compareTo(value) < 0)  //cerca a destra
			return binSearch(v, mid + 1, to, value);

		else // cerca a sinistra
			return binSearch(v, from, mid - 1, value);
   	}

   	/*
		Ridimensiona l'array oldv attribuendogli la lunghezza newLength
	*/
	private static Comparable[] resize(Comparable[] oldV, int newLength)
	{
		// pre-condizione
		if (newLength < 0 || oldV == null)
			throw new IllegalArgumentException();

		Comparable[] newV = (Comparable[]) new Object[newLength];
		int count = oldV.length;

		if (newLength < count)
			count = newLength;
		
		System.arraycopy(oldV, 0, newV, 0, oldV.length);
		/*for (int i = 0; i < count; i++)
			newV[i] = oldV[i];*/

		return newV;
	}
}