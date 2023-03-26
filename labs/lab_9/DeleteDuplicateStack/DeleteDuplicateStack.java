import java.util.Scanner;

public class DeleteDuplicateStack
{
	public static void main(String[] args)
	{
		// scanner
		Scanner console = new Scanner(System.in);

		// creazione pila con ridimensionamento
		Stack p = new GrowingArrayStack();

		// inserimento dati
		System.out.println("******** Inserimento dati ********");
		while(console.hasNextInt())
		{
			// lettura numero intero
			int n = console.nextInt();

			// inserimento numero intero
			p.push(n);
		}

		// elimina duplicati pila
		betterDeleteDuplicate(p);

		// stampa elementi pila
		System.out.println("\n******** Pila senza duplicati ********");
		while(! p.isEmpty())
			System.out.println(p.pop());
	}

	public static void worstDeleteDuplicate(Stack p)
	{
		// creazione pile temporaneee con ridimensionamento
		Stack tmp1 = new GrowingArrayStack();
		Stack tmp2 = new GrowingArrayStack();

		// svuoto p, riempio tmp1
		while(! p.isEmpty())
			tmp1.push(p.pop());

		// eliminazione duplicati
		while(! tmp1.isEmpty())
		{
			// salvo l'elemento da analizzare
			Object dup = tmp1.pop();
			// lo inserisco nella pila p una sola volta
			p.push(dup);

			// analizziamo gli altri elementi della pila tmp1
			while(! tmp1.isEmpty())
			{
				// estrazione oggetto dalla pipla 
				Object el = tmp1.pop();
				// riempimento con oggetti diversi dal duplicato
				if(! el.equals(dup))
					tmp2.push(el);
			}

			// aggiornamento della pila tmp1 senza il duplicato
			while(! tmp2.isEmpty())
				tmp1.push(tmp2.pop());
		}
	}

	// Analisi asintotica (circa)
	// T1(n) = 2*n*(3*O(1)) = O(n)
	// T2(n) = n*(O(1) + (n-1)*(2*O(1)) + O(1) + n*(3*O(1)))
	//		 = n*(O(n-1) + O(n)) = O(n^2)
	// T(n)  = T1(n) + T2(n) = O(n) + O(n^2) = O(n^2)
	public static void betterDeleteDuplicate(Stack p)
	{
		// creazione pila temporanea con ridimensionamento
		Stack tmp = new GrowingArrayStack();

		// calcolo del numero di elementi della pila
		int size = 0;
		while(! p.isEmpty())
		{
			tmp.push(p.pop());
			size++;
		}
		// risistemo la pila p
		while(! tmp.isEmpty())
		{
			p.push(tmp.pop());
		}

		// per ogni elemento diverso della pila p
		for(int i = size; i > 0; i--)
		{
			// salvo l'elemento principale del confronto
			Object perno = p.pop();

			// inserisco nella pila tmp i non duplicati
			for(int j = i - 1; j > 0; j--)
			{
				// estraggo un elemento dalla pila p
				Object elem = p.pop();

				// se non e' un duplicato, inseriscilo nella pila tmp
				if(! perno.equals(elem))
					tmp.push(elem);
				// se e' un duplicato, decrementa il numero di controlli sugli elementi della pila p
				else
					i--;
			}

			// inserisco una sola volta il perno nella pila p
			p.push(perno);

			// ri-riempio la pila p con i non duplicati di perno
			while(! tmp.isEmpty())
				p.push(tmp.pop());
		}
	}
}