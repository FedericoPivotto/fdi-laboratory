/*
	RICORDA
	 - Inserire le pre-condizioni
	 - Negli ordinamenti individua il cambiamento per ottenere
	   l'ordinamento decrescente
*/

public class UtilsAlgs
{
	/*
		Resize per array int[]
		 - O(n)
	*/
	public static int[] resize(int[] oldV, int newLength)
	{
		if(oldV == null || newLength < 0)
			throw new IllegalArgumentException();

		int[] newV = new int[newLength];

		if(newV.length > oldV.length)
			System.arraycopy(oldV, 0, newV, 0, oldV.length);
		else
			System.arraycopy(oldV, 0, newV, 0, newV.length);

		return newV;
	}

	/*
		Ricerca del minimo
		 - O(n) in tutti i casi
	*/
	public static int findPosMin(int[] v, int vSize)
	{
		int posMin = 0;
		for(int i = 1; i < vSize; i++)
		{
			if(v[posMin] > v[i])
				posMin = i;
		}

		return posMin;
	}

	/*
		Ricerca del massimo
		 - O(n) in tutti i casi
	*/
	public static int findPosMax(int[] v, int vSize)
	{
		int posMax = 0;
		for(int i = 0; i < vSize; i++)
		{
			if(v[posMax] < v[i])
				posMax = i;
		}

		return posMax;
	}

	/*
		Rimozione da array non ordinato riempito completamente
		 - O(n) per il resize
	*/
	public static int[] removeArrayNC(int[] v, int indexToRemove)
	{
		v[indexToRemove] = v[v.length-1];
		
		return resize(v, v.length-1);
	}

	/*
		Rimozione da array ordinato riempito completamente
		 - O(n) per lo shift e il resize
	*/
	public static int[] removeArrayOC(int[] v, int indexToRemove)
	{
		for(int i = indexToRemove; i < v.length-1; i++)
			v[i] = v[i+1];

		return resize(v, v.length-1);
	}

	/*
		Rimozione da array non ordinato riempito solo in parte
		 - O(1)
	*/
	public static void removeArrayNP(int[] v, int vSize, int indexToRemove)
	{
		// ATTENZIONE: si dovrebbe agire sulla variabile di esemplare vSize
		v[indexToRemove] = v[--vSize];
	}

	/*
		Rimozione da array ordinato riempito solo in parte
		 - O(n) per lo shift
	*/
	public static void removeArrayOP(int[] v, int vSize, int indexToRemove)
	{
		// ATTENZIONE: si dovrebbe agire sulla variabile di esemplare vSize
		for(int i = indexToRemove; i < vSize-1; i++)
			v[i] = v[i+1];
		vSize--;
	}

	/*
		Inserimento in array non ordinato riempito completamente
		 - O(n) per il resize
	*/
	public static int[] insertArrayNC(int[] v, int value)
	{
		int[] newV = resize(v, v.length+1);
		newV[newV.length-1] = value;

		return newV;
	}

	/*
		Inserimento in array ordinato riempito completamente
		 - O(n) per il resize e lo shift
	*/
	public static int[] insertArrayOC(int[] v, int value)
	{
		int[] newV = resize(v, v.length+1);
		int i;
		for(i = newV.length-1; i > 0 && value < v[i-1]; i--)
			v[i] = v[i-1];
		v[i] = value;

		return newV;
	}

	/*
		Inserimento in array non ordinato riempito solo in parte
		 - O(1) + eventuale resize
	*/
	public static void insertArrayNP(int[] v, int vSize, int value)
	{
		// ATTENZIONE: si dovrebbe agire sulla variabile di esemplare vSize
		v[vSize++] = value;
	}

	/*
		Inserimento in array ordinato riempito solo in parte
		 - O(n) per lo shift + eventuale resize
	*/
	public static void insertArrayOP(int[] v, int vSize, int value)
	{
		// ATTENZIONE: si dovrebbe agire sulla variabile di esemplare vSize
		vSize++;
		int i;
		for(i = vSize-1; i > 0 && value < v[i-1]; i--)
			v[i] = v[i-1];
		v[i] = value;
	}

	/*
		Selection Sort (con metodi ausiliari)
		 - O(n^2) in tutti i casi
		   NB: si supponga che venga sempre eseguito l'enunciato if
	*/
	public static void selectionSort(int[] v, int vSize)
	{
		for(int i = 0; i < vSize-1; i++)
		{
			int posMin = findPosMinFrom(v, vSize, i);
			if(posMin != i)
				swap(v, vSize, posMin, i);
		}
	}

	private static int findPosMinFrom(int[] v, int vSize, int from)
	{
		int posMin = from;
		for(int i = from + 1; i < vSize; i++)
		{
			if(v[posMin] > v[i])
				posMin = i;	
		}

		return posMin;
	}

	private static void swap(int[] v, int vSize, int i, int j)
	{
		int tmp = v[i];
		v[i] = v[j];
		v[j] = tmp;
	}

	/*
		Selection Sort (compresso)
		 - O(n^2) in tutti i casi
		   NB: si supponga che venga sempre eseguito l'enunciato if
	*/
	public static void selectionSortComp(int[] v, int vSize)
	{
		for(int i = 0; i < vSize-1; i++)
		{
			int posMin = i;
			for (int j = i+1; j < vSize; j++)
			{
				if(v[posMin] > v[j])
					posMin = i;	
			}

			if(posMin != i)
			{
				int tmp = v[i];
				v[i] = v[posMin];
				v[posMin] = tmp;
			}
		}
	}

	/*
		Bubble Sort
		 - O(n^2)
	*/
	public static void bubbleSort(int[] v, int vSize)
	{
		for(int i = 0; i < vSize; i++)
		{
			for(int j = vSize-1; j > i; j--)
			{
				if(v[j-1] > v[j])
				{
					int tmp = v[j-1];
					v[j-1] = v[j];
					v[j] = tmp;
				}
			}
		}
	}

	/*
		Fattoriale iterativo
		 - Prevede l'analisi matematica della definizione di fattoriale
	*/
	public static int factorialIterative(int n)
	{
		int f = 1;
		for (int i = 1; i <= n; i++)
			f *= i;

		return f;
	}

	/*
		Fattoriale ricorsivo
		 - Applicazione diretta della definizione di fattoriale
	*/
	public static int factorialRecursive(int n)
	{
		if(n == 0)
			return 1;

		return n * factorialRecursive(n-1);
	}

	/*
		Merge Sort + Merge
		 - O(n*log(n))
	*/
	public static void mergeSort(int[] v, int vSize)
	{
		if(vSize < 2)
			return;

		int mid = (vSize + 1) / 2;
		int[] left = new int[mid];
		int[] right = new int[vSize-mid];

		System.arraycopy(v, 0, left, 0, left.length);
		System.arraycopy(v, mid, right, 0, right.length);

		mergeSort(left, left.length);
		mergeSort(right, right.length);

		merge(v, left, right);
	}

	private static void merge(int[] a, int[] b, int[] c)
	{
		int ia = 0, ib = 0, ic = 0;
		
		while(ib < b.length && ic < c.length)
		{
			if(b[ib] < c[ic])
				a[ia++] = b[ib++];
			else
				a[ia++] = c[ic++];
		}

		while(ib < b.length)
			a[ia++] = b[ib++];

		while(ic < c.length)
			a[ia++] = c[ic++];
	}

	/*
		Insertion Sort
		 - O(n):   caso migliore
		 - O(n^2): caso medio e peggiore
	*/
	public static void insertionSort(int[] v, int vSize)
	{
		for(int i = 1; i < vSize; i++)
		{
			int tmp = v[i];
			
			int j;
			for(j = i; j > 0 && tmp < v[j-1]; j--)
				v[j] = v[j-1];
			v[j] = tmp;
		}
	}

	/*
		Ricerca lineare (o sequenziale)
		 - O(n)
	*/
	public static int linearSearch(int[] v, int vSize, int value)
	{
		for(int i = 0; i < vSize; i++)
		{
			if(v[i] == value)
				return i;
		}

		return -1;
	}

	/*
		Ricerca binaria (o dicotomica)
		 - O(log(n))
	*/
	public static int binarySearch(int[] v, int vSize, int value)
	{
		return binSearch(v, 0, vSize-1, value);
	}

	private static int binSearch(int[] v, int from, int to, int value)
	{
		if(from > to)
			return -1;

		int mid = (from + to) / 2;
		
		if(v[mid] == value)
			return mid;
		else if(v[mid] > value)
			return binSearch(v, from, mid-1, value);
		else
			return binSearch(v, mid+1, to, value);
	}

	/*
		Inverte una stringa ricorsivamente
	*/
	public static String reverseString(String str)
	{
		if(str.length() == 0)
			return str;

		return reverseString(str.substring(1)) + str.charAt(0);
	}
}