public class ArrayTable100 implements Table
{
	private Object[] v;
	private int count; // count rende isEmpty O(1)
	
	public ArrayTable100()
	{
		makeEmpty();
	}

	public void makeEmpty()
	{
		v = new Object[100];
		count = 0;
	}

	public boolean isEmpty()
	{
		return (count == 0);
	}

	private void check(int key)
	{
		if (key < 0 || key >= v.length)
			throw new InvalidPositionTableException();
	}

	public void insert(int key, Object value)
	{
		check(key);
		if (v[key] == null)
			count++;
		v[key] = value;
	}

	public void remove(int key)
	{
		check(key);
		if (v[key] != null)
		{
			count--;
			v[key] = null;
		}
	}

	public Object find(int key)
	{
		check(key);
		
		return v[key];
	}
}