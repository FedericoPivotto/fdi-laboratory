public class LinkedListStack implements Stack
{
	private LinkedList list = new LinkedList();

	/*
		O(1)
	*/
	public void push(Object obj)
	{
		this.list.addFirst(obj);
	}

	/*
		O(1)
	*/
	public Object pop()
	{
		return this.list.removeFirst();
	}

	/*
		O(1)
	*/
	public Object top()
	{
		return this.list.getFirst();
	}

	/*
		O(1)
	*/
	public void makeEmpty()
	{
		this.list.makeEmpty();
	}

	/*
		O(1)
	*/
	public boolean isEmpty()
	{
		return this.list.isEmpty();
	}
}