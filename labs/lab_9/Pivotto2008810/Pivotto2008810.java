import java.util.*;

/*
	Classe principale
*/
public class Pivotto2008810
{
	public static void main(String[] args)
	{
		// controllo sui parametri a riga di comando
		if(args.length < 1)
		{
			System.out.println("Errore: mancano parametri");
			System.exit(1);
		}

		// controllo sulla dimensione
		int dim = 0;
		try
		{
			dim = Integer.parseInt(args[0]);
		}
		catch(NumberFormatException e)
		{
			System.out.println("Errore: il primo parametro non e\' valido");
			System.exit(1);
		}

		// crazione del multistack
		MyMultiStack s = new MyMultiStack(dim);

		// inserimento degli eventuali parametri nello stack di "indice" massimo
		for (int i = 1; i < args.length; i++)
			s.add(args[i], (dim - 1));

		System.out.println("STATUS 1: " + s);

		// inserimento dei token dei parametri senza indicare la pila di destinazione
		// lettura dall'ingresso standard
		Scanner console = new Scanner(System.in);
		System.out.println("\nInserisci del testo (CTRL+Z + invio per terminare)");
		while(console.hasNextLine())
		{
			String str = console.nextLine();
			
			Scanner tokenizer = new Scanner(str);
			while(tokenizer.hasNext())
			{
				s.add(tokenizer.next());
			}
		}

		System.out.println("\nSTATUS 2: " + s);

		// esecuzione di due rimozioni
		s.remove();
		s.remove();

		// stampa dell'oggetto multistack
		System.out.println("\nSTATUS 3: " + s);		
	}
}

/*
	Interfaccia Container
*/
interface Container
{
	/*
		Svuota il contenitore
	*/
	void makeEmpty();

	/*
		Controlla se il contenitore e' vuoto
	*/
	boolean isEmpty();
}

/*
	Tipo di dato astratto Stack
*/
interface Stack extends Container
{
	/*
		Inserimento in cima alla pila dell'oggetto
	*/
	void push(Object obj);

	/*
		Rimozione in cima alla pila e restituzione dell'oggetto
	*/
	Object pop();

	/*
		Ispezionamento dell'oggetto in cima alla pila
	*/
	Object top();
}

/*
	Classe dell'eccezione EmptyStackException
*/
class EmptyStackException extends RuntimeException
{
	public String toString()
	{
		return "Exception detected: EmptyStackException";
	}
}

/*
	Struttura dati MyStack che realizza Stack
*/
class MyStack implements Stack
{
	/*
		Variabili di esemplare
		 - v: array riempito solo in parte
		 - vSize: dimensione dell'array riempito solo in parte
	*/
	private Object[] v;
	private int vSize;

	/*
		Metodo costruttore
	*/
	public MyStack()
	{
		this.v = new Object[10];
		this.makeEmpty();
	}

	/*
		Svuota il contenitore
	*/
	public void makeEmpty()
	{
		this.vSize = 0;
	}

	/*
		Controlla se il contenitore e' vuoto
	*/
	public boolean isEmpty()
	{
		return (this.vSize == 0);
	}

	/*
		Inserimento in cima alla pila dell'oggetto
	*/
	public void push(Object obj)
	{
		if(this.vSize == this.v.length)
			v = resize(v, v.length*2);
		v[vSize++] = obj;
	}

	/*
		Rimozione in cima alla pila e restituzione dell'oggetto
	*/
	public Object pop()
	{
		Object obj = this.top();
		vSize--;

		return obj;
	}

	/*
		Ispezionamento dell'oggetto in cima alla pila
	*/
	public Object top()
	{
		if(isEmpty())
			throw new EmptyStackException();

		return v[vSize - 1];
	}

	/*
		Ridimensiona l'array alla nuova dimensione
	*/
	protected Object[] resize(Object v[], int newLength)
	{
		// pre-condizione
		if (newLength < v.length)
        	throw new IllegalArgumentException();

		Object[] newV = new Object[newLength];
		System.arraycopy(v, 0, newV, 0, v.length);
		/*for (int i = 0; i < v.length; i++)
			newV[i]	= v[i];*/

		return newV;
	}
}

/*
	Tipo di dato astratto MultiStack
*/
interface MultiStack extends Container
{
	/*
		Inserimento nello stack con il minor numero di elementi
		 - Aggiunge x nella pila della multipila che contiene il minor numero di elementi
		 - Richiede x!= null
	*/
	void add(Object obj);

	/*
		Inserimento nello stack di indice index
		 - Aggiunge x nella pila di indice i della multipila
		 - Richiede x!= null
		 - Lancia IllegalArgumentException se il valore di i non è corretto
	*/
	void add(Object x, int i);

	/*
		Rimozione nello stack con il massimo numero di elementi
		 - Viene eliminato il dato dalla pila che contiene il maggior numero di elementi
		 - Richiede che la multipila non sia vuota
	*/
	void remove();

	/*
		Conteggia gli oggetti dello stack di un certo indice
		 - Restituisce la dimensione della pila di indice i, cioe' il numero di elementi
		   contenuti nella pila di indice i;lancia IllegalArgumentException se il valore di i non è corretto
	*/
	int dim(int i);
}

/*
	Struttura dati MyMultiStack che realizza MultiStack
*/
class MyMultiStack implements MultiStack
{
	/*
		Variabili di esemplare
		 - array: array di Stack identificati da un indice
	*/
	private Stack[] v;

	/*
		Metodo costruttore
	*/
	public MyMultiStack(int size)
	{
		// pre-condizione
		if(size < 2)
			throw new IllegalArgumentException();

		// creazione dell'array di stack
		this.v = new Stack[size];

		// creazione degli stack vuoti
		for (int i = 0; i < this.v.length; i++)
			this.v[i] = new MyStack();
	}

	/*
		Svuota il contenitore
	*/
	public void makeEmpty()
	{
		// svuoto ogni singola pila senza ricreare la multipila
		for (int i = 0; i < this.v.length; i++)
			this.v[i].makeEmpty();
	}

	/*
		Controlla se il contenitore e' vuoto
	*/
	public boolean isEmpty()
	{
		for (int i = 0; i < this.v.length; i++)
		{
			if(! this.v[i].isEmpty())
			{
				return false;
			}
		}

		return true;
	}

	/*
		Inserimento nello stack con il minor numero di elementi
	*/
	public void add(Object x)
	{
		this.add(x, this.getIndexMinStack());
	}

	/*
		Inserimento nello stack di indice i
	*/
	public void add(Object x, int i)
	{
		// pre-condizione
		if(x == null || i < 0 || i >= this.v.length)
			throw new IllegalArgumentException();

		this.v[i].push(x);
	}

	/*
		Rimozione nello stack con il massimo numero di elementi
	*/
	public void remove()
	{
		this.v[this.getIndexMaxStack()].pop();
	}

	/*
		Conteggio degli oggetti di uno stack
	*/
	public int dim(int i)
	{
		// pre-condizione
		if(i < 0 || i >= this.v.length)
			throw new IllegalArgumentException();

		// creazione stack temporaneo
		Stack tmp = new MyStack();
		// definizione contatore
		int count = 0;

		// svuoto stack, riempio tmp
		while(! this.v[i].isEmpty())
			tmp.push(this.v[i].pop());

		// svuoto tmp, riempio stack e conteggio gli oggetti
		while(! tmp.isEmpty())
		{
			this.v[i].push(tmp.pop());
			count++;
		}

		return count;
	}

	/*
		Sovrascrittura del metodo toString()
	*/
	public String toString()
	{
		String str = "";
		for (int i = 0; i < this.v.length; i++)
			str += this.dim(i) + " ";

		return str;
	}

	/*
		Restituisce l'indice di una delle eventuali pile con il minor numero di elementi
	*/
	private int getIndexMinStack()
	{
		int indexMinStack = 0;
		for (int i = 1; i < this.v.length; i++)
		{
			if(this.dim(i) < this.dim(indexMinStack))
			{
				indexMinStack = i;
			}
		}

		return indexMinStack;
	}

	/*
		Restituisce l'indice di una delle eventuali pile con il maggior numero di elementi
	*/
	private int getIndexMaxStack()
	{
		int indexMaxStack = 0;
		for (int i = 1; i < this.v.length; i++)
		{
			if(this.dim(i) > this.dim(indexMaxStack))
			{
				indexMaxStack = i;
			}
		}

		return indexMaxStack;
	}
}