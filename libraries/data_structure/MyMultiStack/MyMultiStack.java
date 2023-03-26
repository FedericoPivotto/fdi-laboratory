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
		// pre-condizione
		if(this.isEmpty())
			throw new IllegalStateException();

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