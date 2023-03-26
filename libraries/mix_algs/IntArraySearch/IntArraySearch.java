/*
	Classe contente algoritmi di ricerca per gli array di interi 

	NOTA BENE
	 - Se questo fosse codice professionale, tutti i metodi seguenti
	   conterrebbero un controllo delle pre-condizioni e lancerebbero
	   eccezioni nel caso le pre-condizioni non fossero verificate.
	   In questo caso abbiamo evitato di appesantire il codice con il
	   controllo delle pre-condizioni, perche` ci interessa soprattutto
	   studiare il funzionamento dei vari algoritmi di inserimento,
	   rimozione, ricerca
*/
public class IntArraySearch
{
	/*
		RICERCA LINEARE
		 - Cerca il valore value nell'array v tramite l'algoritmo di "ricerca 
		   lineare". Se trovato, restituisce l'indice corrispondente, altrimenti 
		   restituisce -1
	*/
	public static int linearSearch(int[] v, int vSize, int value)
	{
		for (int i = 0; i < vSize; i++)
		{
			if (v[i] == value)
			{
				return i; // trovato valore
			}
		}

		return -1; // valore non trovato
	}

	// ---------------------------------------------------------------------------

	/*
		RICERCA BINARIA RICORSIVA
		 - Cerca il valore value nell'array v tramite l'algoritmo di "ricerca 
		   binaria". Se trovato, restituisce l'indice corrispondente, altrimenti 
		   restituisce -1
		 - Richiede che l'array sia gia' ordinato
	*/
	public static int binarySearch(int[] v, int vSize, int value)
	{
		// pre-condizione
		if(v == null || vSize < 0 || !isSorted(v, vSize))
		{
			throw new IllegalArgumentException();
		}

		return binSearch(v, 0, vSize-1, value);
	}

	/*
		Metodo ausiliario per consentire chiamate ricorsive
		 - Attenzione: e' un algoritmo con ricorsione SEMPLICE
	*/
	private static int binSearch(int[] v, int from, int to, int value)
	{  
		if (from > to)
		{
			return -1; // elemento non trovato
		}

		int mid = (from + to) / 2; // circa in mezzo
		int middle = v[mid];
		if (middle == value)
		{
			return mid; // elemento trovato
		}
		else if (middle < value)  //cerca a destra
		{
			return binSearch(v, mid + 1, to, value);
		}
		else // cerca a sinistra
		{
			return binSearch(v, from, mid - 1, value);
		}
   	}

	/*
		RICERCA BINARIA ITERATIVA
		 - Richiede che l'array sia gia' ordinato
	*/
	public static int iterativeBinarySearch(int[] v, int vSize, int value)
	{
		// pre-condizione
		if(v == null || vSize < 0 || !isSorted(v, vSize))
		{
			throw new IllegalArgumentException();
		}

		int from = 0;
		int to 	 = vSize - 1;
		while(!(from > to))
		{
			// calcolo indice medio
			int mid = (from + to) / 2;

			// controllo di value con l'array
			if(v[mid] < value) // cerco a destra
			{
				from = mid + 1;
			}
			else if(v[mid] > value) // cerco a sinistra
			{
				to = mid - 1;
			}
			else // if(v[mid] == value) // cerco in mid
			{
				return mid;
			}
		}

		return -1;
	}

	/*
		Controlla se l'array e' gia' ordinato
	*/
	public static boolean isSorted(int[] v, int vSize)
	{
		boolean flag = true;
		for (int i = 0; i < (vSize - 1); i++)
		{
			if(!(v[i] <= v[i + 1]))
			{
				flag = false;
			}
		}

		return flag;
	}
}