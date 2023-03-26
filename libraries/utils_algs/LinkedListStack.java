public class LinkedListStack implements Stack
{
	private LinkedList list;

	public LinkedListStack()
	{
		this.list = new LinkedList();
	}

	public void makeEmpty()
	{
		this.list.makeEmpty();
	}

	public boolean isEmpty()
	{
		return this.list.isEmpty();
	}

	public void push(Object x)
	{
		this.list.addFirst(x);
	}

	public Object pop()
	{
		try
		{
			return this.list.removeFirst();
		}
		catch(EmptyLinkedListException e)
		{
			throw new EmptyStackException();
		}
	}

	public Object top()
	{
		try
		{
			return this.list.getFirst();
		}
		catch(EmptyLinkedListException e)
		{
			throw new EmptyStackException();
		}
	}
}

/*interface Container
{
	boolean isEmpty();
	void makeEmpty();
}*/

interface Stack extends Container
{
	void push(Object x);
	Object pop();
	Object top();
}

class EmptyStackException extends RuntimeException
{
	public String toString()
	{
		return "Exception caught: EmptyStackException";
	}
}

class FullStackException extends RuntimeException
{
	public String toString()
	{
		return "Exception caught: FullStackException";
	}
}