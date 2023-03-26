import java.util.Scanner;

public class SortStack
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

		// ordinamento pila
		betterSort(p);

		// stampa elementi pila
		System.out.println("\n******** Pila ordinata ********");
		while(! p.isEmpty())
			System.out.println(p.pop());
	}

	/*
		Nota bene: da esercizio si suppone che la pila contenga oggetti confrontabili,
				   ovvero che realizzano l'interfaccia Comparable sebbene in questo
				   caso il cast a Comparable risulti molto pericoloso
	*/
	public static void worstSort(Stack s)
	{
		// creazione pile con ridimensionamento
		Stack tmp1 = new GrowingArrayStack();
		Stack tmp2 = new GrowingArrayStack();

		// svuoto p, riempio tmp1
		while(! s.isEmpty())
			tmp1.push(s.pop());

		// algoritmo
		while(! tmp1.isEmpty())
		{
			// estraggo il maggiore temporaneo
			Comparable max = (Comparable) tmp1.pop();

			// svuoto tmp1 tranne il maggiore, riempio tmp2
			while(! tmp1.isEmpty())
			{
				// estraggo elemento per il confronto
				Comparable el = (Comparable) tmp1.pop();

				if(max.compareTo(el) < 0) // se e' un nuovo massimo, inserisci vecchio max e aggiornalo
				{
					tmp2.push(max);
					max = el;
				}
				else // se non e' un nuovo massimo, inserisci l'elemento appena estratto
				{
					tmp2.push(el);
				}
			}

			// inserimento massimo nella pila s
			s.push(max);

			// svuoto tmp2, riempio tmp1
			Stack exc = tmp1;
			tmp1 = tmp2;
			tmp2 = exc;
			/*// metodo alternativo
			while(! tmp2.isEmpty())
				tmp1.push(tmp2.pop());*/
		}
	}

	/*
		Nota bene: da esercizio si suppone che la pila contenga oggetti confrontabili,
				   ovvero che realizzano l'interfaccia Comparable sebbene in questo
				   caso il cast a Comparable risulti molto pericoloso
	*/
	public static void betterSort(Stack s)
	{
		// creazione pila temporanea con ridimensionamento
		Stack tmp = new GrowingArrayStack();

		// calcolo del numero di elementi della pila
		int size = 0;
		while(! s.isEmpty())
		{
			tmp.push(s.pop());
			size++;
		}
		// risistemo la pila s
		while(! tmp.isEmpty())
		{
			s.push(tmp.pop());
		}

		// ordinamento basato sul selection sort
		for (int i = size; i > 0; i--) // ad ogni iterazione il numero di confronti da fare diminuisce di uno
		{
			// ricerca del minimo sulla pila s
			Comparable max = (Comparable) s.pop();
			for (int j = i - 1; j > 0 ; j--) // faccio i-1 per eliminare il confronto superfluo di max
			{
				// estraggo l'elemento del confronto
				Comparable elem = (Comparable) s.pop();
				// se l'elemento estratto e' il nuovo massimo, aggiornalo
				if(max.compareTo(elem) < 0)
				{
					// inserisco il vecchio massimo nella pila tmp
					tmp.push(max);

					// scambio il vecchio massimo con quello nuovo
					Comparable objTmp = max;
					max = elem;
					elem = objTmp;
				}
				// altrimenti
				else
				{
					// inserisco l'elemento estratto
					tmp.push(elem);
				}
			}

			// inserimento il massimo nella pila s
			s.push(max);
			// riempio nuovamente la pila s con gli elementi attuali di tmp
			while(! tmp.isEmpty())
			{
				s.push(tmp.pop());
			}
		}
	}
}