public class ArrayHashTable implements HashTable
{
	private LinkedList[] v;
	private int count; // count rende isEmpty O(1)

	public ArrayHashTable()
	{
		makeEmpty();
	}

	public void makeEmpty()
	{
		v = new LinkedList[10];
		count = 0;
	}

	public boolean isEmpty()
	{
		return (count == 0);
	}

	public void insert(Object key, Object value)
	{
		// da appurare se la key deve essere Comparable o Object
		if(key == null || !(key instanceof Comparable))
			throw new IllegalArgumentException();

		int index = getReducedKey(key);
		if(v[index] == null)
			v[index] = new LinkedList();

		Pair pair = new Pair((Comparable) key, value);
		v[index].addLast(pair);
		count++;
	}

	public void remove(Object key)
	{
		if(isEmpty())
			throw new HashTableItemNotFoundException();

		int index = getReducedKey(key);
		if(v[index] == null)
			throw new IllegalArgumentException();

		ListIterator iter = v[index].getIterator();
		while(iter.hasNext())
		{
			Pair tmp = (Pair) iter.next();
			if(tmp.getKey().equals(key))
			{
				iter.remove();
				count--;
				return;
			}
		}

		throw new HashTableItemNotFoundException();
	}
	
	public Object find(Object key)
	{
		if(isEmpty())
			throw new HashTableItemNotFoundException();

		int index = getReducedKey(key);
		if(v[index] == null)
			throw new IllegalArgumentException();

		ListIterator iter = v[index].getIterator();
		while(iter.hasNext())
		{
			Pair tmp = (Pair) iter.next();
			if(tmp.getKey().equals(key))
				return tmp.getValue();
		}

		throw new HashTableItemNotFoundException();
	}

	private int getReducedKey(Object key)
	{
		// insert, remove e find delegano il controllo
		// della key == null a questo metodo
		if(key == null)
			throw new IllegalArgumentException();

		return Math.abs(key.hashCode() % v.length);
	}

	public String toString()
	{
		String str = "";
		for (int i = 0; i < v.length; i++)
		{
			if(v[i] != null)
			{
				str += "["+ i +"] --> ";
				ListIterator iter = v[i].getIterator();
				while(iter.hasNext())
				{
					Pair tmp = (Pair) iter.next();
					str += "(" + tmp.getKey() + ", " + tmp.getValue() + ") ";
				}
				str += "\n";
			}	
		}

		return str;
	}

	private class Pair // classe interna alla hash table
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