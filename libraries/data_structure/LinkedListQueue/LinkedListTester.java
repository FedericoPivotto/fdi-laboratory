/*
	Classe di test della lista semplicemente concatenata
*/
public class LinkedListTester
{
	/*
		Restituisce la dimensione della lista semplicemente concatenata, escluso il nodo di intestazione
	*/
	public static int getSize(LinkedList list)
	{
		// creazione di un iteratore per la lista
		ListIterator iter = list.getIterator();
		// definizione della dimensione
		int size = 0;
		// scorrimento dell lista fino all'ultimo nodo
		while(iter.hasNext())
		{
			// incremento della dimensione
			size++;
			// posizionamento dell'iteratore nella posizione successiva
			iter.next();
		}

		// restituzione della dimensione della lista, escluso il nodo di intestazione
		return size;
	}

	/*
		Testa il funzionamento della lista semplicemente concatenata
	*/
	public static void main(String[] args)
	{
		// creazione di una lista semplicemente concatenata
		LinkedList list = new LinkedList();
		// aggiunta di un elemento in testa alla lista dopo il nodo di intestazione
		list.addFirst("A");
		// aggiunta di un elemento in coda alla lista
		list.addLast("B");
		// aggiunta di un elemento in testa alla lista dopo il nodo di intestazione
		list.addFirst("C");

		// creazione di un iteratore per la lista
		ListIterator iter = list.getIterator();
		// posizionamento dell'iteratore fra il primo nodo effettivo, dopo head, e il secondo
		iter.next();
		// aggiunta di un elemento prima dell'iteratore
		iter.add("I");
		// posizionamento dell'iteratore dopo l'ultimo nodo
		while (iter.hasNext())
			iter.next();
		// rimozione dell'elemento che precede l'iteratore
		iter.remove();
		// aggiunta di un elemenento in coda alla lista
		list.addLast("O");

		// creazione di un nuovo iteratore per la lista
		iter = list.getIterator();
		// calcolo della dimensione della lista
		int size = getSize(list);
		// stampa della lista a partire dal nodo dopo head
		while (iter.hasNext())
			System.out.print(iter.next());

		// stampa della dimensione della lista
		System.out.println(" (" + size + ")");
	}
}