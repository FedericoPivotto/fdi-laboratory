/*
	Classe di collaudo della classe DoublyLinkedList
	Riceve come argomento da riga di comando un numero intero n > 0, e collauda 
	i metodi della classe DoublyLinkedList
	- addFirst()/addLast(): inserimento al primo/ultimo posto di n elementi di 
							tipo new Integer(k), 0 <= k < n
	- getFirst()/getLast(): invio a standard output del primo/ultimo elemento
	- removeFirst()/removeLast(): estrazione di tutti gli elementi

	Collaudare con il comando seguente:
	$ java DoublyLinkedListTester n
		dove n e' un numero intero positivo.
*/

public class DoublyLinkedListTester
{   
	public static void main(String[] args)
	{   // Controllo delle precondizioni
		if (args.length < 1)
		{   System.out.println("uso: $java DoublyLinkedListTester <intero>");
			return;
		}
		int n = 0;
		try { n = Integer.parseInt(args[0]); }
		catch (NumberFormatException e)
		{   System.out.println("Non hai passato un numero intero!");
			return;
		}
		if ( n < 1)
		{   System.out.println("Valore non ammesso: deve essere n > 1");
			return;
		}

		DoublyLinkedList dl = new DoublyLinkedList();
		//Inserimento dati: prova del metodo addFirst()
		for (int i = 0; i < n; i++)
			dl.addFirst(new Integer(i));
		// prova del metodo getFirst()
		System.out.println("\n** getFirst(): PRIMO DATO=" +dl.getFirst()+" **");
		// prova del metodo getLast()
		System.out.println("\n** getLast(): ULTIMO DATO=" +dl.getLast()+" **");
		// prova del metodo toString()
		System.out.println("\n** toString(): CONTENUTO **\n" +dl+ "\n");

		// prova del metodo removeFirst()
		System.out.println("\n*** DATI INSERITI ***");
		while (!dl.isEmpty())
			System.out.println("removeFirst(): " + dl.removeFirst());

		//Inserimento dati: prova del metodo addLast()
		for (int i = 0; i < n; i++)
			dl.addLast(new Integer( n - i));
		// prova del metodo toString()
		System.out.println("\n** toString(): CONTENUTO **\n" +dl+ "\n");
		// prova del metodo removeLast()
		System.out.println("\n*** DATI INSERITI ***");
		while (!dl.isEmpty())
			System.out.println("removeLast(): " + dl.removeLast());
	}
}