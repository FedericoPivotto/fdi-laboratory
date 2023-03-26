/*
	Interfaccia delle proprieta' astratte comuni a tutti i contenitori
*/
public interface Container
{
	/*
		Verifica se il contenitore e' vuoto
	*/
	boolean isEmpty();

	/*
		Svuota il contenitore
	*/
	void makeEmpty();
}