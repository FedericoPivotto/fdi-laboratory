/*
	Interfaccia delle proprieta' astratte comuni alle liste
	 - Estende l'interfaccia Container
*/
public interface List extends Container
{
	/*
		Restituisce l'iteratore della lista
	*/
	ListIterator getIterator();
}