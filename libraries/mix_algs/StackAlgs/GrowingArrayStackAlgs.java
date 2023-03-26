public class GrowingArrayStackAlgs
{
	/*
		O(n*log(n))
	*/
	public static void mergeSort(Stack s)
	{
		if (s == null || s.isEmpty())
			return; // caso base
		
		Object temp = s.pop();
		if (s.isEmpty())
		{
			s.push(temp); // altro caso base: dimensione 1
			return;
		}

		// dividiamo (circa) a meta’
		Stack left  = new GrowingArrayStack();
		Stack right = new GrowingArrayStack();
		left.push(temp);
		boolean flag = true;
		while (!s.isEmpty())
		{
			if (flag)
				right.push(s.pop());
			else
				left.push(s.pop());

			flag = !flag; // trucco
		}

		mergeSort(left);
		mergeSort(right);
		// fusione: si osservi che s è rimasta vuota
		merge(s, left, right);
	}

	/*
		O(n)
	*/
	private static void merge(Stack s, Stack left, Stack right)
	{
		Stack temp = new GrowingArrayStack();

		while (!left.isEmpty() && !right.isEmpty())
		{
			Comparable x = (Comparable) left.top();
			Comparable y = (Comparable) right.top();
			
			if (x.compareTo(y) < 0)
				temp.push(left.pop());
			else
				temp.push(right.pop());
		}

		while (!left.isEmpty())
			temp.push(left.pop());
		
		while (!right.isEmpty())
			temp.push(right.pop());
		
		while (!temp.isEmpty()) // inverte il contenuto
			s.push(temp.pop());
	}
}