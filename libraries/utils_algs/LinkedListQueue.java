public class LinkedListQueue
{
	private LinkedList list;

	public LinkedListQueue()
	{
		this.list = new LinkedList();
	}

	public boolean isEmpty()
	{
		return this.list.isEmpty();
	}

	public void makeEmpty()
	{
		this.list.makeEmpty();
	}

	public void enqueue(Object x)
	{
		this.list.addLast(x);
	}

	public Object dequeue()
	{
		try
		{
			return this.list.removeFirst();
		}
		catch(EmptyLinkedListException e)
		{
			throw new EmptyQueueException();
		}
	}

	public Object getFront()
	{
		try
		{
			return this.list.getFirst();
		}
		catch(EmptyLinkedListException e)
		{
			throw new EmptyQueueException();
		}
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