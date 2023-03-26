public class ArrayHashTable
{
	private class Pair
	{
		private Object key;
		private Object value;

		public Pair(Object key, Object value)
		{
			this.setKey(key);
			this.setValue(value);
		}

		public void setValue(Object value)
		{
			this.value = value;
		}

		public void setKey(Object key)
		{
			this.key = key;
		}

		public Object getKey()
		{
			return this.key;
		}

		public Object getValue()
		{
			return this.value;
		}
	}

	private LinkedList[] v;
	private int count; // rende isEmpty() O(1)

	public ArrayHashTable()
	{
		this.v = new LinkedList[10];
		for(int i = 0; i < this.v.length; i++)
			this.v[i] = new LinkedList();

		this.makeEmpty();
	}

	public void makeEmpty()
	{
		for(int i = 0; i < this.v.length; i++)
			this.v[i].makeEmpty();

		this.count = 0;
	}

	public boolean isEmpty()
	{
		return (this.count == 0);
	}

	private int getReducedKey(Object key)
	{
		if(key == null)
			throw new IllegalArgumentException();

		return Math.abs(key.hashCode()) % this.v.length;
	}

	public void insert(Object key, Object value)
	{
		this.v[this.getReducedKey(key)].addLast(new Pair(key, value));
		this.count++;
	}

	public void remove(Object key)
	{
		ListIterator iter = this.v[this.getReducedKey(key)].getIterator();
		while(iter.hasNext())
		{
			Pair tmp = (Pair) iter.next();
			if(tmp.getKey().equals(key))
			{
				iter.remove();
				this.count--;
				return;
			}
		}

		throw new HashTableItemNotFoundException();
	}

	public Object find(Object key)
	{
		ListIterator iter = this.v[this.getReducedKey(key)].getIterator();
		while(iter.hasNext())
		{
			Pair tmp = (Pair) iter.next();
			if(tmp.getKey().equals(key))
				return tmp.getValue();
		}

		throw new HashTableItemNotFoundException();
	}
}

interface HashTable extends Container
{
	void insert(Object key, Object value);
	void remove(Object key);
	Object find(Object key);
}

/*interface Container
{
	boolean isEmpty();
	void makeEmpty();
}*/

class HashTableItemNotFoundException extends RuntimeException
{
	public String toString()
	{
		return "Exception caught: HashTableItemNotFoundException";
	}
}