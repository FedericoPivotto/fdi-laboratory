public class CircularArrayQueue implements Queue
{
	private Object[] v;
	private int front;
	private int back;

	public CircularArrayQueue()
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

	private int increment(int index)
	{
		return (index + 1) % this.v.length;
	}

	public Object getFront()
	{
		if(this.isEmpty())
			throw new EmptyQueueException();

		return this.v[front];
	}

	// ATTENZIONE: nel peggiore dei casi ha complessita' computazionale O(n) per colpa del resize
	public void enqueue(Object x)
	{
		if(this.increment(back) == front)
		{
			this.v = resize(this.v, 2*this.v.length);
			if(this.back < this.front)
			{
				System.arraycopy(this.v, 0, this.v, this.v.length/2, this.back);
				this.back = this.back + this.v.length/2;
			}

			// ALTERNATIVA: throw new FullQueueException();
		}

		this.v[this.back] = x;
		this.back = this.increment(this.back);
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
		this.front = this.increment(this.front);

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