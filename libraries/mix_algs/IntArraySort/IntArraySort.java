/*
	Classe contente algoritmi di ordinamento per array di interi
	
	NOTA BENE
	 - Se questo fosse codice professionale, tutti i metodi seguenti
	   conterrebbero un controllo delle pre-condizioni e lancerebbero
	   eccezioni nel caso le pre-condizioni non fossero verificate.
	   In questo caso abbiamo evitato di appesantire il codice con il
	   controllo delle pre-condizioni, perche` ci interessa soprattutto
	   studiare il funzionamento dei vari algoritmi di inserimento,
	   rimozione, ricerca
*/
public class IntArraySort
{
	/*
		ORDINAMENTO PER SELEZIONE
	*/
	public static void selectionSort(int[] v, int vSize)
	{
		for (int i = 0; i < vSize - 1; i++)
		{   
			int minPos = findMinPos(v, i, vSize-1);
			if (minPos != i) swap(v, minPos, i);
		}
	}

	/*
		Metodo ausiliario per lo scambio (swap) tra due valori
	*/
	private static void swap(int[] v, int i, int j)
	{   
		int temp = v[i];
		v[i] = v[j];
		v[j] = temp;
	}

	/*
		Metodo ausiliario per la ricerca dell'indice contenente il valore minimo
	*/
	private static int findMinPos(int[] v, int from, int to)
	{   
		int pos = from;
		int min = v[from];
		for (int i = from + 1; i <= to; i++)
		{
			if (v[i] < min)
			{
				pos = i;      
				min = v[i];
			}
		}

		return pos;
	}

	// ---------------------------------------------------------------------------------

	/*
		ORDINAMENTO PER FUSIONE
	*/
	public static void mergeSort(int[] v, int vSize)
	{
		if (vSize < 2)
		{
			return; // caso base
		}

		int mid = vSize / 2; //dividiamo circa a meta`
		int[] left = new int[mid];
		int[] right = new int[vSize - mid];
		System.arraycopy(v, 0, left, 0, mid);
		System.arraycopy(v, mid, right, 0, vSize-mid);

		// passi ricorsivi: ricorsione multipla (doppia)
		mergeSort(left, mid);
		mergeSort(right, vSize-mid);

		// fusione (metodo ausiliario)
		merge(v, left, right);
	}

	/*
		Metodo ausiliario per la fusione di due array ordinati v1 e v2
	*/
	private static void merge(int[] v, int[] v1, int[] v2)
	{  
		int i = 0, i1 = 0, i2 = 0;

		while (i1 < v1.length && i2 < v2.length)
		{
			if (v1[i1] < v2[i2])
			{
				// prima si usa i, poi lo si incrementa...
				v[i++] = v1[i1++];
			}
			else
			{
				v[i++] = v2[i2++];
			}
		}

		while (i1 < v1.length)
		{
			v[i++] = v1[i1++];
		}

		while (i2 < v2.length)
		{
			v[i++] = v2[i2++];
		}
	}

	// ---------------------------------------------------------------------------------

	/*
		ORDINAMENTO PER INSERIMENTO
	*/
	public static void insertionSort(int[] v, int vSize)
	{   // il ciclo inizia da 1 perché il primo
		// elemento non richiede attenzione
		for (int i = 1; i < vSize; i++)
		{
			int temp = v[i]; // nuovo elemento da inserire
			// j va definita fuori dal ciclo perche'
			// il suo valore finale viene usato in seguito
			int j;
			// sposta a destra di un posto tutti gli el. a
			// sin. di temp e > di temp, partendo da destra
			for (j = i; j > 0 && temp < v[j-1]; j--)
				v[j] = v[j-1];
			v[j] = temp; // inserisci temp in posizione
		}
	}

	// ---------------------------------------------------------------------------------

	/*
		ORDINAMENTO A BOLLA
	*/
	public static void bubbleSort(int[] v, int vSize)
	{
		for (int i = 0; i < vSize; i++)
		{
			for (int j = vSize-1; j > i; j--)
			{
				if(v[j-1] > v[j]) // se non rispetta l'ordinamento
				{
					// scambio
					int tmp = v[j];
					v[j] = v[j-1];
					v[j-1] = tmp;
				}
			}
		}
	}
}