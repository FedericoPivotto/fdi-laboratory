public class ArraySortedMap implements Map
{
	private class Pair
	{
		private Comparable key;
		private Object value;

		public Pair(Comparable key, Object value)
		{
			this.setKey(key);
			this.setValue(value);
		}

		public void setValue(Object value)
		{
			this.value = value;
		}

		public void setKey(Comparable key)
		{
			this.key = key;
		}

		public Comparable getKey()
		{
			return this.key;
		}

		public Object getValue()
		{
			return this.value;
		}
	}

	private Pair[] v;
	private int vSize;

	public ArraySortedMap()
	{
		this.v = new Pair[1];
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

	public void insert(Comparable key, Object value)
	{
		// delego a remove il controllo key == null
		try
		{ this.remove(key); }
		catch(MapItemNotFoundException e)
		{}

		if(this.vSize == this.v.length)
			this.v = resize(this.v, 2*this.v.length);

		int i;
		this.vSize++;
		for(i = this.vSize-1; i > 0 && key.compareTo(this.v[i-1].getKey()) < 0; i--)
			this.v[i] = this.v[i-1];
		this.v[i] = new Pair(key, value);
	}

	private static Pair[] resize(Pair[] oldV, int newLength)
	{
		if(oldV == null || newLength < 0)
			throw new IllegalArgumentException();

		Pair[] newV = new Pair[newLength];

		if(oldV.length < newV.length)
			System.arraycopy(oldV, 0, newV, 0, oldV.length);
		else
			System.arraycopy(oldV, 0, newV, 0, newV.length);

		return newV;
	}

	public void remove(Comparable key)
	{
		if(key == null)
			throw new IllegalArgumentException();

		int pos = binarySearch(v, 0, this.vSize-1, key);
		
		if(pos == -1)
			throw new MapItemNotFoundException();
		
		for(int i = pos; i < this.vSize-1; i++)
			this.v[i] = this.v[i+1];

		this.vSize--;
	}

	public Object find(Comparable key)
	{
		if(key == null)
			throw new IllegalArgumentException();

		int pos = binarySearch(this.v, 0, this.vSize-1, key);

		if(pos == -1)
			throw new MapItemNotFoundException();

		return this.v[pos].getValue();
	}

	private static int binarySearch(Pair[] v, int from, int to, Comparable key)
	{
		if(from > to)
			return -1;

		int mid = (from + to) / 2;
		Pair middle = v[mid];

		if(middle.getKey().equals(key))
			return mid;
		else if(middle.getKey().compareTo(key) > 0) // cerco a sinistra
			return binarySearch(v, from, mid-1, key);
		else // cerco a destra
			return binarySearch(v, mid+1, to, key);
	}
}

interface Map extends Container
{
	void insert(Comparable key, Object value);
	void remove(Comparable key);
	Object find(Comparable key);
}

interface Container
{
	boolean isEmpty();
	void makeEmpty();
}

class MapItemNotFoundException extends RuntimeException
{
	public String toString()
	{
		return "Exception caught: MapItemNotFoundException";
	}
}