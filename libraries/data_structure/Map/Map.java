public interface Map extends Container
{
	// l’inserimento va sempre a buon fine;
	// se la chiave non esiste, la coppia
	// key/value viene aggiunta alla mappa;
	// se la chiave esiste già, il valore ad
	// essa associato viene sovrascritto con
	// il nuovo valore
	void insert(Comparable key, Object value);

	// la rimozione della chiave rimuove anche
	// il corrispondente valore
	void remove(Comparable key);
	
	// la ricerca per chiave restituisce soltanto
	// il valore ad essa associato
	Object find(Comparable key);
}