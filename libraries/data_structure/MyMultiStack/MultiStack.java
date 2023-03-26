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