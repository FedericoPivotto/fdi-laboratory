public class RipassoSort
{
	/*
		ORDINAMENTI
	*/

	/*
		SELECTION SORT
		 - O(n^2) in tutti i casi
	*/
	public void selectionSort(int[] v, int vSize)
	{
		for(int i = 0; i < vSize-1; i++) // vSize-1 essendo che il minimo di un array di una cella e' la cella stessa
		{
			int minPos = getMinPos(v, i, vSize-1); // cerca l'indice del valore minimo del sottoarray [from, to]
			if(minPos != i) // se la posizione e' diversa da quella originale, allora fai lo scambio
				swap(v, minPos, i);
		}
	}

	private static int getMinPos(int[] v, int from, int to)
	{
		int minPos = from;
		for(int i = from+1; i <= to; i++)
		{
			if(v[minPos] > v[i])
				minPos = i;
		}

		return minPos;
	}

	private static void swap(int[] v, int i, int j)
	{
		int tmp = v[i];
		v[i] = v[j];
		v[j] = tmp;
	}

	/*
		MERGE SORT
		 - O(n*log(n)) in tutti i casi
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

		merge(v, left, right); // non serve passare vSize in quanto i conti sui length sono giusti
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
		INSERTION SORT
		 - O(n)   nel caso migliore
		 - O(n^2) nel caso medio e peggiore
	*/
	public static void insertionSort(int[] v, int vSize)
	{
		for(int i = 1; i < vSize; i++) // suppongo che nella cella v[0] ci sia gia' il valore minimo
		{
			int tmp = v[i];
			
			int j;
			for(j = i; j > 0 && tmp < v[j-1]; j--) // devo fare spazio al valore da spostare
				v[j] = v[j-1]; // faccio fare un passo a destra alle celle
			v[j] = tmp; // inserisco in posizione il valore
		}
	}

	/*
		BUBBLE SORT
		 - O(n^2) in tutti i casi
	*/
	public static void bubbleSort(int[] v, int vSize)
	{
		for(int i = 0; i < vSize; i++)
		{
			for(int j = vSize-1; j > i; j--)
			{
				if(v[j] < v[j-1])
				{
					int tmp = v[j];
					v[j] = v[j-1];
					v[j-1] = tmp;
				}
			}
		}
	}



	/*
		RICERCHE
	*/

	/*
		LINEAR SEARCH
		 - O(n) in tutti i casi
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
		BINARY SEARCH
		 - O(log(n)) in tutti i casi
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

		else if(v[mid] < value) // cerco a destra
			return binSearch(v, mid+1, to, value);

		else // cerco a sinistra
			return binSearch(v, from, mid-1, value);
	}

	public static int iterativeBinarySearch(int[] v, int vSize, int value)
	{
		int from = 0, to = vSize-1;

		while(! (from > to))
		{
			int mid = (from + to) / 2;
			
			if(v[mid] == value)
				return mid;

			else if(v[mid] < value) // cerco a destra
				from = mid + 1;

			else // cerco a sinistra
				to = mid - 1;
		}

		return -1;
	}

	// controllo della crescenza degli elementi dell'array
	public static boolean isSorted(int[] v, int vSize)
	{
		for(int i = 0; i < vSize-1; i++)
		{
			if(v[i] > v[i+1])
				return false;
		}

		return true;
	}
}