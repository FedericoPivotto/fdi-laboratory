/*
	Classe della pila con lunghezza fissa
*/
public class FixedArrayStack implements Stack
{
	/*
		Variabili di esemplare della classe FixedArrayStack
		 - v: 	  array riempito solo in parte
		 - vSize: dimensione dell'array riempito solo in parte
	*/
	protected Object[] v; // considerare protected
	protected int vSize;  // uguale a private

	/*
		Costruttore della classe FixedArrayStack che istanzia un array di lunghezza fissa e che crea una pila vuota
	*/
	public FixedArrayStack()
	{
		v = new Object[100]; // 100 è un numero scelto
		// a caso, va bene anche 1
		// per rendere vuota la struttura, invoco
		// il metodo makeEmpty, è sempre meglio evitare
		// di scrivere codice ripetuto
		makeEmpty();
	}

	/*
		Svuota il contenitore
	*/
	// dato che Stack estende Container,
	// occorre realizzare anche i suoi metodi
	public void makeEmpty()
	{
		vSize = 0;
	}

	/*
		Verifica se il contenitore e' vuoto
	*/
	public boolean isEmpty()
	{
		return (vSize == 0);
	}

	/*
		Inserisce un oggetto in cima alla pila
		 - Se la pila è piena viene lanciata l'eccesione FullStackException
	*/
	public void push(Object obj)
	{
		if (vSize == v.length)
			throw new FullStackException();

		v[vSize++] = obj;
	}

	/*
		Estrae l'oggetto che si trova in cima alla pila
	*/
	public Object pop()
	{
		Object obj = top();
		vSize--;

		return obj;
	}

	/*
		Ispeziona l'oggetto che si trova in cima alla pila, senza estrarlo
	*/
	public Object top()
	{
		if (isEmpty())
			throw new EmptyStackException();

		return v[vSize - 1];
	}
}