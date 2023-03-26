public class GrowingCircularArrayQueueAlgs
{
	/*
		O(n*log(n))
	*/
	public static void mergeSort(Queue q)
	{
		if (q == null || q.isEmpty())
			return; // caso base
		
		Object temp = q.dequeue();
		if (q.isEmpty())
		{
			q.enqueue(temp); // altro caso base: dimensione 1
			return;
		}
		
		// dividiamo (circa) a meta’
		Queue left = new GrowingCircularArrayQueue();
		Queue right = new GrowingCircularArrayQueue();
		left.enqueue(temp);
		boolean flag = true;
		while (!q.isEmpty())
		{
			if (flag = !flag)
				left.enqueue(q.dequeue());
			else
				right.enqueue(q.dequeue());
		}

		// passi ricorsivi per problemi piu’ semplici
		mergeSort(left);
		mergeSort(right);
		// fusione: si osservi che ora q è vuota
		merge(q, left, right);
	}

	/*
		O(n)
	*/
	private static void merge(Queue q, Queue left, Queue right)
	{
		while (!left.isEmpty() && !right.isEmpty())
		{
			Comparable x = (Comparable) left.getFront();
			Comparable y = (Comparable) right.getFront();
			
			if (x.compareTo(y) < 0)
				q.enqueue(left.dequeue());
			else
				q.enqueue(right.dequeue());
		}

		while (!left.isEmpty())
			q.enqueue(left.dequeue());
		
		while (!right.isEmpty())
			q.enqueue(right.dequeue());
	}
}