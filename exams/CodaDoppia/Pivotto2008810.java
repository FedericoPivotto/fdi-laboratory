import java.util.*;

public class Pivotto2008810
{
	public static void main(String[] args)
	{
		// code doppie
		CD uno = new MiaCD();
		CD due = new MiaCD();
		CD tre = new MiaCD();

		// riempimento uno
		Scanner console = new Scanner(System.in);
		while(console.hasNextInt())
			uno.addLast(console.nextInt());

		// trasferimento da uno a due
		while(! uno.isEmpty())
			due.addFirst(uno.removeLast());

		// trasferimento da due a tre
		while(! due.isEmpty())
			tre.addLast(due.removeFirst());

		// trasferimento da tre all'uscita standard
		while(! tre.isEmpty())
			System.out.println(tre.removeFirst());

		// expected: stesso ordine dei dati in ingresso
	}
}

// INTERFACCIA NON MODIFICABILE
interface CD
{
	int size();
	boolean isEmpty();
	void addFirst(Object x); // aggiunge all'inizio della coda
	void addLast(Object x); // aggiunge alla fine della coda
	Object removeFirst() throws EmptyCDException; // toglie dall'inizio
	Object removeLast() throws EmptyCDException; // toglie dalla fine
	Object getFirst() throws EmptyCDException; // elemento all'inizio
	Object getLast() throws EmptyCDException; // elemento alla fine
}

class EmptyCDException extends RuntimeException
{
	public String toString()
	{
		return "Exception caught: EmptyCDException";
	}
}

// primitive O(1) o O(1) ammortizzato
class MiaCD implements CD
{
	private ListNode head;
	private ListNode tail;
	private int 	 cdSize;

	public MiaCD()
	{
		this.head = this.tail = new ListNode();
		this.cdSize = 0;
	}

	public int size()
	{
		return this.cdSize;
	}

	public boolean isEmpty()
	{
		return (this.head == this.tail);
	}
	
	public void addFirst(Object x) // aggiunge all'inizio della coda
	{
		this.head.element = x;
		ListNode node = new ListNode(null, null, this.head);
		this.head.prev = node;
		this.head = node;
		this.cdSize++;
	}
	
	public void addLast(Object x) // aggiunge alla fine della coda
	{
		ListNode node = new ListNode(x, this.tail, null);
		this.tail.next = node;
		this.tail = node;
		this.cdSize++;
	}
	
	public Object removeFirst() throws EmptyCDException // toglie dall'inizio
	{
		Object obj = this.getFirst();
		
		this.head = this.head.next;
		this.head.element = null;
		this.head.prev = null;
		this.cdSize--;

		return obj;
	}
	
	public Object removeLast() throws EmptyCDException // toglie dalla fine
	{
		Object obj = this.getLast();

		this.tail = this.tail.prev;
		this.tail.next = null;
		this.cdSize--;

		return obj;
	}
	
	public Object getFirst() throws EmptyCDException // elemento all'inizio
	{
		if(this.isEmpty())
			throw new EmptyCDException();

		return this.head.next.element;
	}
	
	public Object getLast() throws EmptyCDException // elemento alla fine
	{
		if(this.isEmpty())
			throw new EmptyCDException();

		return this.tail.element;
	}

	// CLASSE INTERNA
	private class ListNode
	{
		private Object 	 element;
		private ListNode next;
		private ListNode prev;

		public ListNode()
		{
			this(null, null, null);
		}

		public ListNode(Object element, ListNode prev, ListNode next)
		{
			this.element = element;
			this.prev 	 = prev;
			this.next 	 = next;
		}
	}
}