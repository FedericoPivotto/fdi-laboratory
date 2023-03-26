public class ArrayTable implements Table
{
	private Object[] v;
	private int count; 

	public ArrayTable()
	{
		this.makeEmpty();
	}

	public void makeEmpty()
	{
		this.v = new Object[100]; // chiavi da 0 a 99 estremi inclusi
		this.count = 0;
	}

	public boolean isEmpty()
	{
		return (this.count == 0);
	}

	private void check(int key)
	{
		if(key < 0 || key >= this.v.length)
			throw new IllegalArgumentException();
	}

	public void insert(int key, Object value)
	{
		this.check(key);

		if(this.v[key] == null)
			count++;

		this.v[key] = value;
	}

	public void remove(int key)
	{
		this.check(key);

		if(this.v[key] != null)
			count--;

		this.v[key] = null; // sarebbe piu' corretto metterlo solo dentro all'enunciato if
	}

	// NOTA BENE: puo' restituire anche il riferimento null
	public Object find(int key)
	{
		this.check(key);

		return this.v[key];
	}
}

interface Container
{
	boolean isEmpty();
	void makeEmpty();
}

interface Table extends Container
{
	void insert(int key, Object value);
	void remove(int key);
	Object find(int key);
}