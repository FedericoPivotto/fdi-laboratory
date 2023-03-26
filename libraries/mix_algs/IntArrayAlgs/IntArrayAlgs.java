import java.util.*;

/*
	Classe contente algoritmi utili per gli array di interi 

	NOTA BENE
	 - Se questo fosse codice professionale, tutti i metodi seguenti
	   conterrebbero un controllo delle pre-condizioni e lancerebbero
	   eccezioni nel caso le pre-condizioni non fossero verificate.
	   In questo caso abbiamo evitato di appesantire il codice con il
	   controllo delle pre-condizioni, perche` ci interessa soprattutto
	   studiare il funzionamento dei vari algoritmi di inserimento,
	   rimozione, ricerca
*/
public class IntArrayAlgs
{
	/*
		Rimuove dall'array v l'elemento v[index] e lo sostituisce con l'ultimo
		elemento valido di v
	*/
	public static void remove(int[] v, int vSize, int index)
	{
		v[index] = v[vSize - 1];
	}

	/*
		Rimuove dall'array v l'elemento v[index] mantenendo l'ordine degli 
		elementi di indici piu` alti
	*/
	public static void removeSorted(int[] v, int vSize, int index)
	{
		for (int i=index; i<vSize-1; i++)
		{
			v[i] = v[i + 1];
		}
	}

	/*
		Inserisce nell'array v il valore value, nella posizione index
	*/
	public static int[] insert(int[] v, int vSize, int index, int value)
	{
		if (vSize == v.length)
		{
			v = resize(v, 2*v.length);
		}
		for (int i = vSize; i > index; i--)
		{
			v[i] = v[i - 1];
		}
		v[index] = value;

		return v;
	}

	/*
		Cerca e restituisce il valore minimo tra quelli presenti nell'array v
	*/
	public static int findMin(int[] v, int vSize)
	{
		int min = v[0];
		for (int i = 1; i < vSize; i++)
		{
			if (v[i] < min)
			{
				min = v[i];
			}
		}

		return min;
	}

	/*
		Cerca e restituisce il valore massimo tra quelli presenti nell'array v
	*/
	public static int findMax(int[] v, int vSize)
	{
		int max = v[0];
		for (int i = 1; i < vSize; i++)
		{
			if (v[i] > max)
			{
				max = v[i];
			}
		}

		return max;
	}

	/*
		Ridimensiona l'array oldv attribuendogli la lunghezza newLength
	*/
	public static int[] resize(int[] oldv, int newLength)
	{
		// pre-condizione
		if (newLength < 0 || oldv == null)
		{
			throw new IllegalArgumentException();
		}

		int[] newv = new int[newLength];
		int count = oldv.length;
		if (newLength < count)
		{
			count = newLength;
		}
		for (int i = 0; i < count; i++)
		{
			newv[i] = oldv[i];
		}

		return newv;
	}

	/*
		Costruisce un array contenente valori casuali compresi tra 0 e n-1
	*/
	public static int[] randomIntArray(int length, int n)
	{
		int[] v = new int[length];
		Random gen = new Random();
		for (int i = 0; i < v.length; i++)
		{
			v[i] = gen.nextInt(n);  //cfr. documentazione di Random
		}

		return v;
	}

	/*
		Stampa tutti gli elementi di un array
	*/
	public static String printArray(int[] v, int vSize)
	{
		String s = "[";
		for (int i = 0; i<vSize; i++)
		{
			s = s + v[i] + " ";
		}
		s = s + "\b]";

		return s;
	}

	/*
		Controlla se l'array è ordinato
	*/
	public static boolean isSorted(int[] v, int vSize)
	{
		boolean flag = true;
		for (int i = 0; i < (vSize - 1); i++)
		{
			if(!(v[i] <= v[i+1]))
			{
				flag = false;
			}
		}

		return flag;
	}
}