public class LinkedList implements List
{
	private ListNode head;
	private ListNode tail;

	public LinkedList()
	{
		this.makeEmpty();
	}

	public boolean isEmpty()
	{
		return (this.head == this.tail);
	}

	public void makeEmpty()
	{
		this.head = this.tail = new ListNode();
	}

	public Object getFirst()
	{
		if(this.isEmpty())
			throw new EmptyLinkedListException();

		return this.head.getNext().getElement();
	}

	public Object getLast()
	{
		if(this.isEmpty())
			throw new EmptyLinkedListException();

		return this.tail.getElement();
	}

	public void addFirst(Object element)
	{
		this.head.setElement(element);
		this.head = new ListNode(null, this.head);
	}

	public void addLast(Object element)
	{
		ListNode node = new ListNode(element, null);
		this.tail.setNext(node);
		this.tail = this.tail.getNext(); 
	}

	public Object removeFirst()
	{
		Object x = this.getFirst();

		this.head = this.head.getNext();
		this.head.setElement(null);

		return x;
	}

	public Object removeLast()
	{
		Object x = this.getLast();

		// ATTENTO: NON this.head.getNext(), ma this.head
		// altrimenti salteresti un nodo
		ListNode tmp = this.head;
		while(tmp.getNext() != tail)
			tmp = tmp.getNext();
		this.tail = tmp;
		this.tail.setNext(null);

		return x;
	}

	// METODO NON RICHIESTO
	public int size()
	{
		ListNode node = this.head.getNext();
		int size = 0;

		while(node != null)
		{
			size++;
			node = node.getNext();
		}

		return size;
	}

	public ListIterator getIterator()
	{
		return new LinkedListIterator(this.head);
	}

	private class ListNode
	{
		private Object element;
		private ListNode next;

		public ListNode()
		{
			this(null, null);
		}

		public ListNode(Object element, ListNode next)
		{
			this.element = element;
			this.next = next;
		}

		public Object getElement()
		{
			return this.element;
		}

		public ListNode getNext()
		{
			return this.next;
		}

		public void setElement(Object element)
		{
			this.element = element;
		}

		public void setNext(ListNode next)
		{
			this.next = next;
		}
	}

	private class LinkedListIterator implements ListIterator
	{
		private ListNode current;
		private ListNode previous;

		public LinkedListIterator(ListNode head)
		{
			this.current = head;
			this.previous = null;
		}

		public boolean hasNext()
		{
			return (this.current.getNext() != null);
		}

		public Object next()
		{
			if(! this.hasNext())
				throw new IllegalStateException();

			this.previous = this.current;
			this.current = this.current.getNext();

			return this.current.getElement();
		}

		public void add(Object x)
		{
			ListNode node = new ListNode(x, this.current.getNext());
			this.current.setNext(node);
			this.previous = this.current;
			this.current = this.current.getNext();

			if(! this.hasNext())
				LinkedList.this.tail = this.current;
		}

		public void remove()
		{
			if(this.previous == null)
				throw new IllegalStateException();

			this.previous.setNext(this.current.getNext());
			this.current = this.previous;
			this.previous = null;

			if(! this.hasNext())
				LinkedList.this.tail = this.current;
		}
	}
}

interface Container
{
	boolean isEmpty();
	void makeEmpty();
}

interface List extends Container
{
	ListIterator getIterator();
}

interface ListIterator
{
	boolean hasNext();
	Object next();
	void add(Object x);
	void remove();
}

class EmptyLinkedListException extends RuntimeException
{
	public String toString()
	{
		return "Exception caught: EmptyLinkedListException";
	}
}