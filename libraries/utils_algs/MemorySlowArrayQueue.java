// ATTENZIONE: primitive O(1) ma grande spreco di memoria per le celle inutilizzate

public class MemorySlowArrayQueue implements Queue
{
	private Object[] v;
	private int front;
	private int back;

	public MemorySlowArrayQueue()
	{
		this.v = new Object[1];
		this.makeEmpty();
	}

	public boolean isEmpty()
	{
		return (this.front == this.back);
	}

	public void makeEmpty()
	{
		this.front = this.back = 0;
	}

	public Object getFront()
	{
		if(this.isEmpty())
			throw new EmptyQueueException();

		return this.v[this.front];
	}

	public void enqueue(Object x)
	{
		if(this.back == this.v.length)
			this.v = resize(this.v, 2*this.v.length);
			// ALTERNATIVA: throw new FullQueueException();

		this.v[this.back++] = x;
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

	public Object dequeue()
	{
		Object x = this.getFront();
		this.front++;

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