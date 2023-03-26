public class FixedArrayQueue implements Queue
{
	protected Object[] v;
	protected int front, back;
	
	public FixedArrayQueue()
	{
		v = new Object[100];
		makeEmpty();
	}
	
	public void makeEmpty()
	{
		front = back = 0;
	}
	
	public boolean isEmpty()
	{
		return (back == front);
	}
	
	public void enqueue(Object obj)
	{
		if (back == v.length)
			throw new FullQueueException();
		v[back++] = obj;
	}
	
	public Object getFront()
	{
		if (isEmpty())
			throw new EmptyQueueException();
		
		return v[front];
	}
	
	public Object dequeue()
	{
		Object obj = getFront();
		front++;
		
		return obj;
	}
}