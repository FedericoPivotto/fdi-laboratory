// ATTENZIONE: nemmeno l'insieme ordinato prevede un metodo di rimozione ma solo la sottrazione fra insiemi

public class ArraySortedSet implements SortedSet
{
	private Comparable[] v;
	private int vSize;

	public ArraySortedSet()
	{
		this.v = new Comparable[1];
		this.makeEmpty();
	}

	public void makeEmpty()
	{
		this.vSize = 0;
	}

	public boolean isEmpty()
	{
		return (this.vSize == 0);
	}

	public void add(Comparable x)
	{
		if(this.contains(x))
			return;

		if(this.vSize == this.v.length)
			this.v = resize(this.v, 2*this.v.length);

		int i;
		this.vSize++;
		for(i = this.vSize-1; i > 0 && x.compareTo(v[i-1]) < 0; i--)
			this.v[i] = this.v[i-1];
		this.v[i] = x;
	}

	public static Comparable[] resize(Comparable[] oldV, int newLength)
	{
		if(oldV == null || newLength < 0)
			throw new IllegalArgumentException();

		Comparable[] newV = new Comparable[newLength];

		if(oldV.length < newV.length)
			System.arraycopy(oldV, 0, newV, 0, oldV.length);
		else
			System.arraycopy(oldV, 0, newV, 0, newV.length);

		return newV;
	}

	public void add(Object x)
	{
		throw new IllegalArgumentException();
	}

	public boolean contains(Object x)
	{
		if(! (x instanceof Comparable))
			throw new IllegalArgumentException();

		return binarySearch(this.v, 0, this.vSize-1, (Comparable) x);
	}

	private static boolean binarySearch(Comparable[] v, int from, int to, Comparable value)
	{
		if(from > to)
			return false;

		int mid = (from + to) / 2;
		Comparable middle = v[mid];

		if(middle.compareTo(value) == 0)
			return true;
		else if(middle.compareTo(value) < 0) // cerco a destra
			return binarySearch(v, mid+1, to, value);
		else // cerco a sinistra
			return binarySearch(v, from, mid-1, value);
	}

	public Comparable[] toSortedArray()
	{
		Comparable[] objV = new Comparable[this.vSize];
		System.arraycopy(this.v, 0, objV, 0, this.vSize);

		return objV;
	}

	public Object[] toArray()
	{
		return this.toSortedArray();
	}

	public static SortedSet union(SortedSet s1, SortedSet s2)
	{
		SortedSet x = new ArraySortedSet();
		
		Comparable[] v1 = s1.toSortedArray();
		Comparable[] v2 = s2.toSortedArray();

		int i = 0, j = 0;
		while(i < v1.length && j < v2.length)
		{
			if(v1[i].compareTo(v2[j]) < 0)
				x.add(v1[i++]);
			else if(v1[i].compareTo(v2[j]) > 0)
				x.add(v2[j++]);
			else // sono uguali
			{
				x.add(v1[i]);
				i++;
				j++;
			}
		}

		while(i < v1.length)
			x.add(v1[i++]);

		while(j < v2.length)
			x.add(v2[j++]);

		return x;
	}

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

			if(v1[i].equals(v2[j]))
				x.add(v2[j++]);
		}

		return x;
	}

	public static SortedSet substract(SortedSet s1, SortedSet s2)
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
}

interface Container
{
	boolean isEmpty();
	void makeEmpty();
}

interface Set extends Container
{
	Object[] toArray();
	void add(Object x);
	boolean contains(Object x);
}

interface SortedSet extends Set
{
	Comparable[] toSortedArray();
	void add(Comparable x);
}