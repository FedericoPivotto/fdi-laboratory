import java.util.*;

public class Pivotto2008810
{
	public static void main(String[] args)
	{
		// creazione catena circolare
		CircularChain circ = new MyCircularChain();
		
		// inserimenti
		Scanner console = new Scanner(System.in);
		int count = 0;
		while(console.hasNextLine())
		{
			circ.add(console.nextLine());
			count++;
		}

		// stampa a video
		Object[] v = circ.toArray();
		System.out.println("STATUS 1");
		for(int i = 0; i < v.length; i++)
			System.out.println("[" + i + "]: " + v[i]);

		// rimozioni
		int rem = count / 3; // divisione intera: comporta l'arrotondamento per difetto
		for(int i = 0; i < rem; i++)
			circ.remove();

		// stampa a video
		v = circ.toArray();
		System.out.println("\nSTATUS 2");
		for(int i = 0; i < v.length; i++)
			System.out.println("[" + i + "]: " + v[i]);

		// ordinamento
		/*Object[]*/ v = circ.toArray();
		for(int i = 1; i < v.length; i++)
		{
			String tmp = (String) v[i];
			
			int j;
			for (j = i; j > 0 && tmp.compareTo((String) v[j-1]) < 0; j--)
				v[j] = v[j-1];

			v[j] = tmp; // inserisci temp in posizione
		}

		// stampa a video
		System.out.println("\nSTATUS 3");
		for(int i = 0; i < v.length; i++)
			System.out.println("[" + i + "]: " + v[i]);
	}
}

interface CircularChain
{
	// O(1)
	void add(Object x);

	// O(1)
	void remove();

	// O(n)
	Object[] toArray();
}

class MyCircularChain implements CircularChain
{
	private class ChainNode
	{
		// riferimenti di default a null
		private Object 	  element;
		private ChainNode prev;
		private ChainNode next;	
	}

	private ChainNode head;

	public MyCircularChain()
	{
		this.head = null;
	}

	// O(1)
	public void add(Object x)
	{
		ChainNode node = new ChainNode();
		node.element = x;

		// se head e' null, inserisci x
		if(this.head == null)
		{
			// collego l'unico nodo a se stesso
			node.prev = node.next = node;
		}
		else
		{
			// aggiunta del nuovo nodo
			node.prev = this.head.prev;
			node.next = this.head;
			this.head.prev.next = node;
			this.head.prev = node;
		}

		// aggiornamento di head
		this.head = node;
	}
	
	// O(1)
	public void remove()
	{
		// pre-condizione
		if(this.head == null)
			throw new IllegalStateException();

		if(this.head.prev == this.head && this.head.next == this.head)
		{
			this.head = null;
		}
		else
		{
			ChainNode newLast = this.head.prev.prev;
			newLast.next = this.head;
			this.head.prev = newLast;
		}
	}

	// O(n)
	public Object[] toArray()
	{
		// pre-condizione
		if(this.head == null)
			throw new IllegalStateException();

		// calcolo dimensione catena circolare
		ChainNode tmp = null;

		int dim = 1;
		tmp = this.head.next;
		while(tmp != head)
		{
			tmp = tmp.next;
			dim++;
		}

		// creazione array
		Object[] v = new Object[dim];
		tmp = this.head;
		for(int i = 0; i < dim; i++)
		{
			v[i] = tmp.element;
			tmp = tmp.next;
		}

		return v;
	}
}