public class FixedCircularArrayQueue extends FixedArrayQueue
{
	// il metodo increment fa avanzare un indice di una
	// posizione, tornando all’inizio dell’array se si supera
	// la fine
	protected int increment(int index)
	{
		return (index + 1) % v.length;
	}
	
	public void enqueue(Object obj)
	{
		if(increment(back) == front)
			throw new FullQueueException();
		
		v[back] = obj;
		back = increment(back);
	}

	public Object dequeue()
	{
		Object obj = getFront();
		front = increment(front);
		
		return obj;
	}
	
	// non serve sovrascrivere getFront perché non modifica
	// le variabili back e front
}
