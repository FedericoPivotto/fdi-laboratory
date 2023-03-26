public class SizeLinkedList
{
	public static int sizeLinkedListWithIter(LinkedList list)
	{
		ListIterator iter = list.getIterator();
		int size = 0;

		while(iter.hasNext())
		{
			size++;
			iter.next();
		}

		return size;
	}
}