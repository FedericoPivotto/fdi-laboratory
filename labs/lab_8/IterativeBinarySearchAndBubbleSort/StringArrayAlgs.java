public class StringArrayAlgs
{
	public static String toString(String[] v, int vSize)
	{
		// pre-condizione
		if(v == null || vSize < 0)
		{
			throw new IllegalArgumentException();
		}

		String str = "";
		if(vSize > 0)
		{
			str = "String[] = {";
			for(int i = 0; i < (vSize - 1); i++)
			{
				str += "\""+ v[i] + "\", ";
			}
			str += v[vSize - 1] + "}";
		}
		else
		{
			str = "String[] = {}";
		}

		return str;
	}

	public static void insertionSort(String[] v, int vSize)
	{   // il ciclo inizia da 1 perché il primo
		// elemento non richiede attenzione
		for (int i = 1; i < vSize; i++)
		{
			String temp = v[i]; // nuovo elemento da inserire
			// j va definita fuori dal ciclo perche'
			// il suo valore finale viene usato in seguito
			int j;
			// sposta a destra di un posto tutti gli el. a
			// sin. di temp e > di temp, partendo da destra
			for (j = i; j > 0 && temp.compareTo(v[j-1]) < 0; j--)
			{
				v[j] = v[j-1];
			}
			v[j] = temp; // inserisci temp in posizione
		}
	}

	public static boolean isSorted(String[] v, int vSize)
	{
		boolean flag = true;
		for (int i = 0; i < (vSize - 1); i++)
		{
			if(!(v[i].compareTo(v[i+1]) <= 0))
			{
				flag = false;
			}
		}

		return flag;
	}

	public static int iterativeBinarySearch(String[] v, int vSize, String value)
	{
		// pre-condizione
		if(v == null || vSize < 0 || !isSorted(v, vSize))
		{
			throw new IllegalArgumentException();
		}

		int index = -1, from = 0, to = vSize - 1;
		while(!(from > to))
		{
			// calcolo indice medio
			int mid = (from + to) / 2;

			// controllo di value con l'array
			if(v[mid].compareTo(value) < 0) // cerco a destra
			{
				from = mid + 1;
			}
			else if(v[mid].compareTo(value) > 0) // cerco a sinistra
			{
				to = mid - 1;
			}
			else if(v[mid].compareTo(value) == 0) // cerco in mid
			{
				index = mid;
				break;
			}
		}

		return index;
	}

	public static void bubbleSort(String[] v, int vSize)
	{
		for (int i = 0; i < vSize; i++)
		{
			for (int j = (vSize - 1); j > i; j--)
			{
				if(v[j - 1].compareTo(v[j]) > 0) // non rispetta l'ordinamento
				{
					// scambio
					String tmp = v[j];
					v[j] = v[j - 1];
					v[j - 1] = tmp;
				}
			}
		}
	}
}