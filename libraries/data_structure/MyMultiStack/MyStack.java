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