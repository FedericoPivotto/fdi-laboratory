public class ArrayMap
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

	public ArrayMap()
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

		this.v[this.vSize++] = new Pair(key, value);
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

		for(int i = 0; i < this.vSize; i++)
		{
			if(this.v[i].getKey().equals(key))
			{
				this.v[i] = this.v[--this.vSize];
				return;
			}
		}

		throw new MapItemNotFoundException();
	}

	public Object find(Comparable key)
	{
		if(key == null)
			throw new IllegalArgumentException();

		for(int i = 0; i < this.vSize; i++)
		{
			if(this.v[i].getKey().equals(key))
				return this.v[i].getValue();
		}

		throw new MapItemNotFoundException();
	}
}

interface Container
{
	boolean isEmpty();
	void makeEmpty();
}

interface Map extends Container
{
	void insert(Comparable key, Object value);
	void remove(Comparable key);
	Object find(Comparable key);
}

class MapItemNotFoundException extends RuntimeException
{
	public String toString()
	{
		return "Exception caught: ItemNotFoundException";
	}
}