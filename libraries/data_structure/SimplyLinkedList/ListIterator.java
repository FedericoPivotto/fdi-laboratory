/*
	Interfaccia delle proprieta' astratte comuni agli iteratori delle liste
*/
public interface ListIterator
{
	/*
		Comportamento del costruttore di una
		classe che realizza questa interfaccia:
		quando viene costruito, l'iteratore si
		trova nella sua prima posizione valida,
		cioe' PRIMA del primo elemento; l'ultima
		posizione valida dell'iteratore e' quella
		che si trova DOPO l'ultimo elemento
	*/

	/*
		Se l'iteratore si trova nella sua ultima
		posizione valida, lancia IllegalStateException;
		altrimenti restituisce un riferimento all'oggetto
		che si trova nella posizione successiva alla
		posizione dell'iteratore e fa avanzare di una
		posizione l'iteratore
	*/
	Object next();

	/*
		Restituisce true se e solo se e' possibile invocare 
		next senza che venga lanciata un'eccezione
	*/
	boolean hasNext();

	/*
		Inserisce l'oggetto ricevuto in un nuovo nodo che
		viene collocato PRIMA della posizione dell'iteratore,
		senza modificare la posizione dell'iteratore
	*/
	void add(Object obj);

	/*
		Se, a partire dal momento della costruzione dell'iteratore
		o dall'ultima invocazione del metodo remove, non e' stato invocato
		il metodo next o il metodo add, lancia IllegalStateException;
		altrimenti elimina il nodo che si trova nella posizione che
		precede la posizione dell'iteratore (cioe' il nodo piu'
		recentemente esaminato da next o inserito da add), senza
		modificare la posizione dell'iteratore (cioe' il nodo che
		verra' restituito da una successiva invocazione di next e'
		lo stesso che sarebbe stato restituito se non fosse stato
		invocato il metodo remove)
	*/
	void remove();
}