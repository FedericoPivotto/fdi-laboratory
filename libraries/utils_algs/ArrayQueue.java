public class ArrayQueue implements Queue
{
	private Object[] v;
	private int vSize;

	public ArrayQueue()
	{
		this.v = new Object[1];
		this.makeEmpty();
	}

	public boolean isEmpty()
	{
		return (this.vSize == 0);
	}

	public void makeEmpty()
	{
		this.vSize = 0;
	}

	public Object getFront()
	{
		if(this.isEmpty())
			throw new EmptyQueueException();

		return this.v[0];
	}

	public void enqueue(Object x)
	{
		if(this.vSize == this.v.length)
			this.v = resize(this.v, 2*this.v.length);
			// ALTERNATIVA: throw new FullQueueException();

		this.v[vSize++] = x;
	}

	private static Object[] resize(Object[] oldV, int newLength)
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

	// PROBLEMA DI QUESTA REALIZZAZIONE: il dequeue prevede complessità computazionale O(n) che non va bene
	public Object dequeue()
	{
		Object x = this.getFront();

		for(int i = 0; i < this.vSize-1; i++)
			this.v[i] = this.v[i+1];
		this.vSize--;

		return x;
	}
}

interface Container
{
	boolean isEmpty();
	void makeEmpty();
}

interface Queue extends Container
{
	void enqueue(Object x);
	Object dequeue();
	Object getFront();
}

class EmptyQueueException extends RuntimeException
{
	public String toString()
	{
		return "Exception caught: EmptyQueueException";
	}
}

class FullQueueException extends RuntimeException
{
	public String toString()
	{
		return "Exception caught: FullQueueException";
	}
}