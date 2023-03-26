/*
	Classe della lista semplicemente concatenata
	 - Realizza l'interfaccia List la quale estende l'interfaccia Container
*/
public class LinkedList implements List
{
	/*
		Variabili di esemplare della classe LinkedList
		 - head: nodo di intestazione
		 - tail: ultimo nodo
	*/
	private ListNode head;
	private ListNode tail;

	/*
		Costruttore della classe LinkedList che crea una lista vuota
	*/
	public LinkedList()
	{
		// crea una lista vuota
		this.makeEmpty();
	}

	/*
		Verifica se il contenitore e' vuoto
	*/
	public boolean isEmpty()
	{
		// se head coincide con tail, allora la lista e' vuota
		return (this.head == this.tail);
	}

	/*
		Svuota il contenitore
	*/
	public void makeEmpty()
	{
		// assegnazione di uno stesso e nuovo nodo a head e tail
		this.head = this.tail = new ListNode();
	}

	/*
		Restituisce l'iteratore della lista sottoforma di riferimento all'interfaccia che realizza
	*/
	public ListIterator getIterator()
	{
		// restituzione di un nuovo riferimento iteratore
		return new LinkedListIterator(this.head);
	}

	/*
		Aggiunge un elemento in testa alla lista dopo il nodo di intestazione
		 - O(1)
	*/
	public void addFirst(Object element)
	{
		// inserimento dell'elemento nell'header attuale
		this.head.setElement(element);
		// creazione di un nodo vuoto
		ListNode node = new ListNode();
		// collegamento del nuovo nodo all'header attuale
		node.setNext(this.head);
		// il nuovo nodo diventa il nuovo header della lista
		this.head = node;
	}

	/*
		Aggiunge un elemento in coda alla lista
		 - O(1)
	*/
	public void addLast(Object element)
	{
		// creazione di un nodo con il nuovo elemento
		ListNode node = new ListNode(element, null);
		// collego il tail attuale con il nuovo nodo
		this.tail.setNext(node);
		// aggiorno tail al nuovo nodo inserito
		this.tail = tail.getNext();
	}

	/*
		Rimuove un elemento in testa alla lista dopo il nodo di intestazione e lo restituisce
		 - O(1)
	*/
	public Object removeFirst()
	{
		// delego al metodo getFirst il controllo di lista vuota e salvo l'elemento
		Object element = this.getFirst();
		// aggiorno l'header al nodo successivo
		this.head = this.head.getNext();
		// pongo a null l'elemento per definizione di header
		this.head.setElement(null);

		// restituzione dell'elemento rimosso
		return element;
	}

	/*
		Rimuove un elemento in coda alla lista e lo restituisce
		 - O(n)
	*/
	public Object removeLast()
	{
		// delego al metodo getLast il controllo di lista vuota e salvo l'elemento
		Object element = this.getLast();
		// bisogna cercare il penultimo nodo partendo dall'inizio e andando avanti finche' non si arriva alla fine della lista
		ListNode tmp = this.head;
		while(tmp.getNext() != this.tail)
			tmp = tmp.getNext();
		// ora aggiorno tail al penultimo elemento
		this.tail = tmp;
		// pongo a null il riferimento next per definizione di tail
		this.tail.setNext(null);

		// restituzione dell'elemento rimosso
		return element;
	}

	/*
		Restituisce il primo elemento dopo il nodo di intestazione della lista
		 - O(1)
	*/
	public Object getFirst()
	{
		// controllo di lista vuota
		if(this.isEmpty())
			throw new EmptyLinkedListException();

		// restituzione del primo elemento della lista dopo header
		return this.head.getNext().getElement();
	}

	/*
		Restituisce l'ultimo elemento della lista
		 - O(1)
	*/
	public Object getLast()
	{
		// controllo di lista vuota
		if(this.isEmpty())
			throw new EmptyLinkedListException();

		// restituzione dell'ultimo elemento della lista
		return this.tail.getElement();
	}

	/*
		Classe interna a LinkedList del nodo della lista semplicmente concatenata
	*/
	private class ListNode
	{
		/*
			Variabili di esemplare
			 - element: informazione contenuta nel nodo
			 - next: riferimento al nodo successivo
		*/
		private Object 	 element;
		private ListNode next;

		/*
			Costruttore della classe ListNode che crea un nodo vuoto
		*/
		public ListNode()
		{
			this(null, null);
		}

		/*
			Costruttore della classe ListNode che inizializza il nodo con un elemento e un riferimento a un nodo
		*/
		public ListNode(Object element, ListNode next)
		{
			this.element = element;
			this.next 	 = next;
		}

		/*
			Restituisce l'informazione contenuta nel nodo
		*/
		public Object getElement()
		{
			return this.element;
		}

		/*
			Restituisce il riferimento al nodo successivo
		*/
		public ListNode getNext()
		{
			return this.next;
		}

		/*
			Imposta una nuova informazione nella variabile di esemplare element
		*/
		public void setElement(Object element)
		{
			this.element = element;
		}

		/*
			Imposta un nuovo riferimento a un nodo nella variabile di esemplare next
		*/
		public void setNext(ListNode next)
		{
			this.next = next;
		}
	}

	/*
		Classe interna a LinkedList dell'iteratore della lista semplicmente concatenata
		 - Realizza l'interfaccia ListIterator
	*/
	private class LinkedListIterator implements ListIterator
	{
		/*
			Variabili di esemplare
			 - current: posizione (riferimento precedente per convenzione) dell'iteratore la quale non e' mai null
			 - previous: posizione precedente all'iteratore che puo' essere null
		*/
		private ListNode current;
		private ListNode previous;

		/*
			Costruttore della classe LinkedListIterator che istanzia un nodo inizializzato con le variabili di esemplari del nodo di intestazione
		*/
		public LinkedListIterator(ListNode head)
		{
			this.current  = head;
			this.previous = null;
		}

		/*
			Verifica l'esistenza di un elemento successivo all'iteratore
		*/
		public boolean hasNext()
		{
			// se il nodo successivo all'iteratore e' diverso da null, allora non siamo ancora arrivati all'ultimo nodo
			return (this.current.getNext() != null);
		}

		/*
			Aggiorna l'iteratore alla posizione successiva nella lista
		*/
		public Object next()
		{
			// se non esiste un nodo successivo alla posizione dell'iteratore, allora lancia l'eccezione
			if(!this.hasNext())
				throw new IllegalStateException();

			// aggiornamento della posizione precedente all'iteratore a quella dell'iteratore attuale
			this.previous = this.current;
			// aggiornamento dell'iteratore alla sua posizione successiva
			this.current = this.current.getNext();

			// restituzione dell'elemento contenuto nel nodo precedente alla posizione dell'iteratore
			return this.current.getElement();
		}

		/*
			Aggiunge un elemento prima della posizione dell'iteratore
			 - Non e' richiesta alcuna pre-condizione perche' l'aggiunta e' sempre consentita
		*/
		public void add(Object element)
		{
			// creazione di un nodo con il nuovo elemento e con il riferimento next al nodo successivo alla posizione dell'iteratore
			ListNode node = new ListNode(element, this.current.getNext());
			// collego il nodo dell'iteratore al nuovo nodo per collegarlo al nodo che ora lo precede nella lista
			this.current.setNext(node);
			// aggiornamento del riferimento previous alla posizione dell'iteratore non ancora aggiornato alla posizione successiva 
			this.previous = this.current;
			// avanzamento dell'iteratore di una posizione, ovvero dopo il nuovo nodo, dato che l'inserimento avviene prima della sua posizione
			this.current = this.current.getNext();
			// se il nodo inserito e' ora il nuovo ultimo nodo della lista, allora aggiorna il riferimento tail della lista
			if(!hasNext())
				LinkedList.this.tail = this.current;
		}
		
		/*
			Rimuove l'elemento prima della posizione dell'iteratore
		*/
		public void remove()
		{
			// se non e' stato ancora invocato il metodo next o add sull'iteratore creato, allora non e' possibile rimuovere alcun nodo
			if(this.previous == null)
				throw new IllegalStateException();

			// collego il riferimento al nodo previous al nodo successivo alla posizione dell'iteratore, essendo che quello che lo precede e' il nodo da rimuovere
			this.previous.setNext(this.current.getNext());
			// aggiorno la posizione dell'iteratore al nodo previous (convenzione), ovvero quello che ora precede la sua posizione, essendo che ora possiamo effettivamente eliminare il riferimento al nodo da rimuovere e che era contenuto in current
			this.current = this.previous;
			// Attenzione: non ho modo di aggiornare previous in modo da farlo puntare al nodo precedente, quindi lo pongo a null
	        // In questo modo, alla prossima invocazione del metodo remove l'iteratore si trova in un IllegalState a causa di previous posto a null
	        // Si noti inoltre (conseguenza) che alla nuova invocazione del metodo remove, non sarebbe nemmeno piu' possibile aggiornare il nodo current, ovvero l'iteratore
			this.previous = null;
			// se il nodo rimosso era l'ultimo nodo della lista, allora aggiorna il riferimento tail della lista
			if(!hasNext())
				LinkedList.this.tail = this.current;
		}
	}
}