public class ArrayStack implements Stack
{
	private Object[] v;
	private int vSize;

	public ArrayStack()
	{
		this.v = new Object[1];
		this.makeEmpty();
	}

	public void makeEmpty()
	{
		this.vSize = 0;
	}

	public boolean isEmpty()
	{
		return (this.vSize == 0);
	}

	public void push(Object x)
	{
		if(this.vSize == this.v.length)
			this.v = resize(this.v, 2*this.v.length);
			// ALTERNATIVA: throw new FullStackException();

		this.v[vSize++] = x;
	}

	private static Object[] resize(Object[] oldV, int newLength)
	{
		if(oldV == null || newLength < 0)
			throw new IllegalArgumentException();

		Object[] newV = new Object[newLength];

		if(newV.length < oldV.length)
			System.arraycopy(oldV, 0, newV, 0 , newV.length);
		else
			System.arraycopy(oldV, 0, newV, 0, oldV.length);

		return newV;
	}

	public Object pop()
	{
		Object x = this.top();
		this.vSize--;

		return x;
	}

	public Object top()
	{
		if(this.isEmpty())
			throw new EmptyStackException();

		return this.v[this.vSize-1];
	}
}

interface Container
{
	boolean isEmpty();
	void makeEmpty();
}

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