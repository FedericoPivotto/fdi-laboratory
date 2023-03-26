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
		for (i = this.vSize-1; i > 0 && tmp.compareTo(this.v[i-1]) < 0; i--)
			this.v[i] = this.v[i-1];
		this.v[i] = tmp;
	}

	/*
		O(n*log(n))
	*/
	public static SortedSet union(SortedSet s1, SortedSet s2)
	{
		SortedSet x = new ArraySortedSet();
		Comparable[] v1 = s1.toSortedArray();
		Comparable[] v2 = s2.toSortedArray();
		
		int i = 0, j = 0;
		while(i < v1.length && j < v2.length)
		{
			if(v1[i].compareTo(v2[j]) < 0)
			{
				x.add(v1[i++]);
			}
			else if(v1[i].compareTo(v2[j]) > 0)
			{
				x.add(v2[j++]);
			}
			else // sono uguali
			{
				x.add(v1[i++]);
				j++;
			}
		}

		while(i < v1.length)
			x.add(v1[i++]);

		while(j < v2.length)
			x.add(v2[j++]);

		return x;	
	}

	/*
		O(n*log(n))
	*/
	public static SortedSet intersection(SortedSet s1, SortedSet s2)
	{
		SortedSet x = new ArraySortedSet();
		Comparable[] v1 = s1.toSortedArray();
		Comparable[] v2 = s2.toSortedArray();

		for(int i = 0, j = 0; i < v1.length; i++)
		{
			while(j < v2.length && v1[i].compareTo(v2[j]) > 0)
				j++;

			if(j == v2.length)
				break;

			if(v1[i].compareTo(v2[j]) == 0)
			{
				x.add(v1[i]);
				j++;
			}
		}

		return x;
	}

	/*
		O(n*log(n))
	*/
	public static SortedSet subtract(SortedSet s1, SortedSet s2)
	{
		SortedSet x = new ArraySortedSet();
		Comparable[] v1 = s1.toSortedArray();
		Comparable[] v2 = s2.toSortedArray();

		int i, j;
		for(i = 0, j = 0; i < v1.length; i++)
		{
			while(j < v2.length && v1[i].compareTo(v2[j]) > 0)
				j++;

			if(j == v2.length)
				break;

			if(v1[i].compareTo(v2[j]) != 0)
				x.add(v1[i]);
		}

		while(i < v1.length)
			x.add(v1[i++]);

		return x;
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