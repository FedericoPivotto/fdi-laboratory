import java.util.*;

public class Pivotto2008810
{
	public static void main(String[] args)
	{
		IndiceAnalitico ai = new MyIndiceAnalitico();
		String str = "";
		
		// lettura testo
		Scanner console = new Scanner(System.in);
		for(int count = 1; console.hasNextLine(); count++)
		{
			String[] tmp = getSplitLine(console.nextLine());
			for(int i = 0; i < tmp.length; i++)
			{
				if(tmp[i].length() > 3)
					ai.insert(tmp[i], count);
			}
		}

		System.out.print(ai);
	}

	public static String[] getSplitLine(String str)
	{
		// eliminazione caratteri inutili
		char[] vStr = str.toCharArray();
		for(int i = 0; i < vStr.length; i++)
		{
			if(! Character.isLetter(vStr[i]))
				vStr[i] = ' ';
		}

		String toSplit = "";
		Scanner tk = new Scanner(new String(vStr));
		while(tk.hasNext())
		{
			toSplit += tk.next() + " ";
		}

		return toSplit.split(" ");
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

interface IndiceAnalitico
{
	void insert(String str, int row);
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
			this.next = next;
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

class MyIndiceAnalitico implements IndiceAnalitico
{
	private class Pair implements Comparable<Pair>
	{
		private String 	   word;
		private LinkedList rows;

		public Pair(String word, int firstRow)
		{
			this.word = word;
			this.rows = new LinkedList();
			this.rows.addLast(firstRow);
		}

		public int compareTo(Pair x)
		{
			return this.word.compareTo(x.word);
		}
	}

	private Pair[] v;
	private int    vSize;

	public MyIndiceAnalitico()
	{
		this.v 	   = new Pair[1];
		this.vSize = 0;
	}

	public void insert(String str, int row)
	{
		for(int i = 0; i < this.vSize; i++)
		{
			if(v[i].word.equals(str))
			{
				ListIterator iter = v[i].rows.getIterator();
				while(iter.hasNext())
				{
					Integer n = (Integer) iter.next();
					if(n.equals(row))
						return; 
				}

				v[i].rows.addLast(row);
				return;
			}	
		}

		if(this.vSize == this.v.length)
			this.v = resize(this.v, 2*this.v.length);

		v[this.vSize++] = new Pair(str, row);
		Pair tmp = v[this.vSize-1]; // nuovo elemento da inserire
		// j va definita fuori dal ciclo perche'
		// il suo valore finale viene usato in seguito
		int j;
		// sposta a destra di un posto tutti gli el. a
		// sin. di tmp e > di tmp, partendo da destra
		for (j = this.vSize-1; j > 0 && tmp.compareTo(v[j-1]) < 0; j--)
			v[j] = v[j-1];
		v[j] = tmp; // inserisci tmp in posizione
	}

	private static Pair[] resize(Pair[] oldV, int newLength)
	{
		if(oldV == null || newLength < 0)
			throw new IllegalArgumentException();

		Pair[] newV = new Pair[newLength];
		System.arraycopy(oldV, 0, newV, 0, oldV.length);

		return newV;
	}

	public String toString()
	{
		String str = "";
		for(int i = 0; i < this.vSize; i++)
		{
			str += this.v[i].word + ": [";
			
			ListIterator iter = this.v[i].rows.getIterator();
			while(iter.hasNext())
			{
				Object obj = iter.next();
				if(! iter.hasNext())
				{
					str += obj + "]\n";
					break;
				}

				str += obj + ", ";
			}
		}

		return str;
	}
}

