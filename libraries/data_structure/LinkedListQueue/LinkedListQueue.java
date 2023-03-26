public class LinkedListQueue implements Queue
{
	private LinkedList list = new LinkedList();

	/*
		O(1)
	*/
	public void enqueue(Object obj)
	{
		this.list.addLast(obj);
	}

	/*
		O(1)
	*/
	public Object dequeue()
	{
		return this.list.removeFirst();
	}

	/*
		O(1)
	*/
	public Object getFront()
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