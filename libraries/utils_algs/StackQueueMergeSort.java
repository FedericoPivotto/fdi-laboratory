public class StackQueueMergeSort
{
	public void mergeSortStack(Stack s)
	{
		if(s == null || s.isEmpty())
			return;

		Object tmp = s.pop();
		if(s.isEmpty())
		{
			s.push(tmp);
			return;
		}

		Stack left = new ArrayStack();
		Stack right = new ArrayStack();

		left.push(tmp);
		boolean flag = true;
		while(! s.isEmpty())
		{
			if(flag = !flag)
				left.push(s.pop());
			else
				right.push(s.pop());
		}

		mergeSortStack(left);
		mergeSortStack(right);

		mergeStack(s, left, right);
	}

	private void mergeStack(Stack s, Stack left, Stack right)
	{
		Stack tmp = new ArrayStack();

		while(!left.isEmpty() && !right.isEmpty())
		{
			Comparable x = (Comparable) left.top();
			Comparable y = (Comparable) right.top();

			if(x.compareTo(y) < 0)
				tmp.push(left.pop());
			else
				tmp.push(right.pop());
		}

		while(! left.isEmpty())
			tmp.push(left.pop());

		while(! right.isEmpty())
			tmp.push(right.pop());

		while(! tmp.isEmpty())
			s.push(tmp.pop());
	}

	public void mergeSortQueue(Queue q)
	{
		if(q == null || q.isEmpty())
			return;

		Object tmp = q.dequeue();
		if(q.isEmpty())
		{
			q.enqueue(tmp);
			return;
		}

		Queue left = new CircularArrayQueue();
		Queue right = new CircularArrayQueue();

		left.enqueue(tmp);
		boolean flag = true;
		while(! q.isEmpty())
		{
			if(flag = !flag)
				left.enqueue(q.dequeue());
			else
				right.enqueue(q.dequeue());
		}

		mergeSortQueue(left);
		mergeSortQueue(right);

		mergeQueue(q, left, right);
	}

	public void mergeQueue(Queue q, Queue left, Queue right)
	{
		while(!left.isEmpty() && !right.isEmpty())
		{
			Comparable x = (Comparable) left.getFront();
			Comparable y = (Comparable) right.getFront();

			if(x.compareTo(y) < 0)
				q.enqueue(left.dequeue());
			else
				q.enqueue(right.dequeue());
		}

		while(! left.isEmpty())
			q.enqueue(left.dequeue());

		while(! right.isEmpty())
			q.enqueue(right.dequeue());
	}
}