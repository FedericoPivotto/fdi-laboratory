public class ArrayMap implements Map
{
	// realizziamo con array non ordinato
	private Pair[] p = new Pair[1]; // qualsiasi non 0
	private int pSize = 0;

	public void makeEmpty()
	{
		pSize = 0;
	}

	public boolean isEmpty()
	{
		return (pSize == 0);
	}

	public void insert(Comparable key, Object value)
	{
		if (key == null)
			throw new IllegalArgumentException();
		
		try
		{ remove(key); }
		catch (MapItemNotFoundException e)
		{}
		
		if(pSize == p.length)
			p = resize(p, 2*pSize);
		p[pSize++] = new Pair(key, value);
	}

	public void remove(Comparable key)
	{
		for (int i = 0; i < pSize; i++)
		{
			if (p[i].getKey().equals(key))
			{
				p[i] = p[--pSize];
				return;
			}
		}
		
		throw new MapItemNotFoundException();
	}
	
	public Object find(Comparable key)
	{
		for (int i = 0; i < pSize; i++)
		{
			if (p[i].getKey().equals(key))
			{
				return p[i].getValue();
			}
		}
	
		throw new MapItemNotFoundException();
	}

	private static Pair[] resize(Pair[] oldV, int newLength)
	{
		// pre-condizione
		if (newLength < 0 || oldV == null)
			throw new IllegalArgumentException();

		Pair[] newV = new Pair[newLength];
		int count = oldV.length;

		if (newLength < count)
			count = newLength;
		
		System.arraycopy(oldV, 0, newV, 0, oldV.length);
		
		return newV;
	}

	private class Pair // interna alla mappa
	{
		private Comparable key;
		private Object value;
		
		public Pair(Comparable k, Object v)
		{
			setKey(k);
			setValue(v);
		}
	
		public Comparable getKey()
		{
			return key;
		}
	
		public Object getValue()
		{
			return value;
		}
	
		public void setKey(Comparable k)
		{
			key = k;
		}
	
		public void setValue(Object v)
		{
			value = v;
		}
	}
}