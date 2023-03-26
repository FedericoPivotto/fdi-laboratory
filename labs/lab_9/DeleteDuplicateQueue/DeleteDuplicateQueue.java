import java.util.Scanner;

public class DeleteDuplicateQueue
{
	public static void main(String[] args)
	{
		// scanner
		Scanner console = new Scanner(System.in);

		// creazione code circolare con ridimensionamento
		Queue q = new GrowingCircularArrayQueue();

		// inserimento dati
		System.out.println("******** Inserimento dati ********");
		while(console.hasNextInt())
		{
			// lettura numero intero
			int n = console.nextInt();

			// inserimento numero intero
			q.enqueue(n);
		}

		// elimina duplicati pila
		betterDeleteDuplicate(q);

		// stampa elementi pila
		System.out.println("\n******** Coda senza duplicati ********");
		while(! q.isEmpty())
			System.out.println(q.dequeue());
	}

	// Analisi asintotica (circa)
	// T(n) = O(1) + 3*(1) + O(1) = O(1)
	public static int getQueueSize(Queue q)
	{
		// calcolo il numero di elementi della coda
		int size = 0;
		q.enqueue(null);
		while(q.getFront() != null)
		{
			q.enqueue(q.dequeue());
			size++;
		}
		// elimino il riferimento null
		q.dequeue();

		return size;
	}

	// Analisi asintotica (circa)
	// T(n) = O(1) + n*(O(1) + (n-1)*(2*O(1)) + O(1))
	//		= n*(O(n-1)) = O(n*(n-1)) = O(n^2)
	public static void betterDeleteDuplicate(Queue q)
	{
		// calcolo del numero dib oggetti della coda
		int size = getQueueSize(q);

		// per ogni oggetto della coda
		for(int i = 0; i < size; i++)
		{
			// estrai l'oggetto da analizzare
			Object tmp = q.dequeue();

			// per tutti gli oggetti che gli succedono
			for(int j = i + 1; j < size; j++)
			{
				// estrai l'oggetto del confronto
				Object current = q.dequeue();

				// se sono diversi, reinseriscilo nella coda
				if(! tmp.equals(current))
					q.enqueue(current);
				else
					size--;
			}
			// a questo punto abbiamo una coda senza il duplicato

			// inseriamo una sola copia del duplicato
			q.enqueue(tmp);
		}
	}

	public static void worstDeleteDuplicate(Queue p)
	{
		// creazione pile temporaneee con ridimensionamento
		Queue tmp1 = new GrowingCircularArrayQueue();
		Queue tmp2 = new GrowingCircularArrayQueue();

		// svuoto p, riempio tmp1
		while(! p.isEmpty())
			tmp1.enqueue(p.dequeue());

		// eliminazione duplicati
		while(! tmp1.isEmpty())
		{
			// salvo l'elemento da analizzare
			Object dup = tmp1.dequeue();
			// lo inserisco nella pila p una sola volta
			p.enqueue(dup);

			// analizziamo gli altri elementi della pila tmp1
			while(! tmp1.isEmpty())
			{
				// estrazione oggetto dalla pipla 
				Object el = tmp1.dequeue();
				// riempimento con oggetti diversi dal duplicato
				if(! el.equals(dup))
					tmp2.enqueue(el);
			}

			// aggiornamento della pila tmp1 senza il duplicato
			while(! tmp2.isEmpty())
				tmp1.enqueue(tmp2.dequeue());
		}
	}
}