import java.util.*;
import java.io.*;

public class Traduttore
{
	public static void main(String[] args) throws IOException
	{
		if(args.length < 2)
		{
			System.out.println("Errore: parametri non validi");
			System.exit(1);
		}

		String lang = args[0]; // -i: italiano || -e: inglese
		FileReader fr = null;
		try
		{
			fr = new FileReader(args[1] + ".txt");
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Errore: file non trovato");
			System.exit(1);
		}

		// creazione traduttore
		Traduci trad = new Traduci();

		// istruzione traduttore
		Scanner reader = new Scanner(fr);
		while(reader.hasNextLine())
		{
			String line = reader.nextLine();
			Scanner tk = new Scanner(line);
			String ita = "", eng = "";
			for(int i = 0; tk.hasNext() && i < 2; i++)
			{
				String word = tk.next();
				if(i == 0)
					ita = word;
				else
					eng = word;
			}

			trad.addTraduction(ita, eng);
		}

		// chiusura flussi
		reader.close();
		fr.close();

		// lettura testo da tradurre e traduzione
		String traduzione = "";
		reader = new Scanner(System.in);
		while(reader.hasNextLine())
		{
			String line = reader.nextLine();
			Scanner tk = new Scanner(line);
			while(tk.hasNext())
				traduzione += trad.translate(lang, tk.next()) + " ";
			traduzione += "\n";
		}
		reader.close();

		// stampa del testo tradotto
		System.out.println(traduzione);
	}
}

interface Container
{
	void 	makeEmpty();
	boolean isEmpty();
}

interface List extends Container
{
	void addLast(Object x);
}

interface ListIterator
{
	Object 	next();
	boolean hasNext();
}

class Traduci
{
	private class Pair
	{
		private String ita;
		private String eng;

		public Pair(String ita, String eng)
		{
			this.ita = ita;
			this.eng = eng;
		}
	}

	private LinkedList list;

	public Traduci()
	{
		this.list = new LinkedList();
	}

	public void addTraduction(String ita, String eng)
	{
		Pair traduction = new Pair(ita, eng);
		list.addLast(traduction);
	}

	public String translate(String lang, String word)
	{
		ListIterator iter = this.list.getIterator();
		while(iter.hasNext())
		{
			Pair tmp = (Pair) iter.next();
			if(lang.equals("-i"))
			{
				if(word.equals(tmp.ita))
					return tmp.eng;
			}
			else if(lang.equals("-e"))
			{
				if(word.equals(tmp.eng))
					return tmp.ita;
			}
		}

		return "*" + word + "*";
	}
}

class LinkedList implements List
{
	private ListNode head;
	private ListNode tail;

	public LinkedList()
	{
		this.makeEmpty();
	}

	public void makeEmpty()
	{
		this.head = this.tail = new ListNode();
	}

	public boolean isEmpty()
	{
		return (this.head == this.tail);
	}

	public void addLast(Object x)
	{
		ListNode node = new ListNode(x, null);
		this.tail.next = node;
		this.tail = node;
	}

	public ListIterator getIterator()
	{
		return new LinkedListIterator(this.head);
	}

	private class ListNode
	{
		private Object 	 element;
		private ListNode next;

		public ListNode()
		{
			this(null, null);
		}

		public ListNode(Object element, ListNode next)
		{
			this.element = element;
			this.next 	 = next;
		}
	}

	private class LinkedListIterator implements ListIterator
	{
		private ListNode current;

		public LinkedListIterator(ListNode head)
		{
			this.current = head;
		}

		public boolean hasNext()
		{
			return (this.current.next != null);
		}

		public Object next()
		{
			if(! this.hasNext())
				throw new IllegalStateException();

			this.current = this.current.next;

			return this.current.element;
		}
	}
}