/*
	Interfaccia delle proprieta' astratte comuni alle pile
	 - Estende l'interfaccia Container
*/
public interface Stack extends Container
{
	/*
		Inserisce un oggetto in cima alla pila
	*/
	void   push(Object obj);

	/*
		Estrae l'oggetto che si trova in cima alla pila
	*/
	Object pop();

	/*
		Ispeziona l'oggetto che si trova in cima alla pila, senza estrarlo
	*/
	Object top();
}