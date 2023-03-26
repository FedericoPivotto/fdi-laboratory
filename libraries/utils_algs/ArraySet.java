// ATTENZIONE: non sono previsti metodi di rimozione se non il substract tra insiemi

public class ArraySet implements Set
{
	private Object[] v;
	private int vSize;

	public ArraySet()
	{
		this.v = new Object[1];
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

	public Object[] toArray()
	{
		Object[] objV = new Object[this.vSize];
		System.arraycopy(this.v, 0, objV, 0, this.vSize);

		return objV;
	}

	public void add(Object x)
	{
		if(this.contains(x))
			return; // silenziosamente

		if(this.vSize == this.v.length)
			this.v = resize(this.v, 2*this.v.length);

		this.v[this.vSize++] = x;
	}

	public static Object[] resize(Object[] oldV, int newLength)
	{
		if(oldV == null || newLength < 0)
			throw new IllegalArgumentException();

		Object[] newV = new Object[newLength];

		if(oldV.length < newV.length)
			System.arraycopy(oldV, 0, newV, 0, oldV.length);
		else
			System.arraycopy(oldV, 0, newV, 0, newV.length);

		return newV;
	}

	public boolean contains(Object x)
	{
		for(int i = 0; i < this.vSize; i++)
		{
			if(this.v[i].equals(x))
				return true;	
		}

		return false;
	}

	public static Set union(Set s1, Set s2)
	{
		Set x = new ArraySet();

		Object[] v1 = s1.toArray();
		for(int i = 0; i < v1.length; i++)
			x.add(v1[i]);

		Object[] v2 = s2.toArray();
		for(int i = 0; i < v2.length; i++)
			x.add(v2[i]);

		return x;
	}

	public static Set intersection(Set s1, Set s2)
	{
		Set x = new ArraySet();
		Object[] v1 = s1.toArray();
		for(int i = 0; i < v1.length; i++)
		{
			if(s2.contains(v1[i]))
				x.add(v1[i]);	
		}

		return x;
	}

	public static Set substract(Set s1, Set s2)
	{
		Set x = new ArraySet();
		Object[] v1 = s1.toArray();
		for(int i = 0; i < v1.length; i++)
		{
			if(! s2.contains(v1[i]))
				x.add(v1[i]);
		}

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