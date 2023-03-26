/*
	Classe della lista semplicemente concatenata
	 - Realizza l'interfaccia Container
*/
public class DoublyLinkedList implements Container
{
	/*
		Variabili di esemplare della classe DoublyLinkedList
		 - head: nodo di intestazione
		 - tail: ultimo nodo
	*/
	private ListNode head;
	private ListNode tail;

	/*
		Costruttore della classe DoublyLinkedList che crea una lista vuota
	*/
	public DoublyLinkedList()
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
		// aggiornamento del riferimento prev
		this.head.setPrev(node);
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
		// aggiornamento del riferimento prev
		ListNode node = new ListNode(element, null, this.tail);
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
		// aggiornamento del riferimento prev
		this.head.setPrev(null);

		// restituzione dell'elemento rimosso
		return element;
	}

	/*
		Rimuove un elemento in coda alla lista e lo restituisce
		 - O(1)
	*/
	public Object removeLast()
	{
		// delego al metodo getLast il controllo di lista vuota e salvo l'elemento
		Object element = this.getLast();
		// aggiorno tail al nodo precedente
		this.tail = this.tail.getPrev();
		// aggiornamento del riferimento prev
		this.head.setNext(null);

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
			throw new EmptyDoublyLinkedListException();

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
			throw new EmptyDoublyLinkedListException();

		// restituzione dell'ultimo elemento della lista
		return this.tail.getElement();
	}

	public String toString()
	{
		ListNode tmp = this.head.getNext();
		String str = "";
		while(tmp != null)
		{
			str += tmp.getElement() + " ";
			tmp = tmp.getNext();
		}

		return str;
	}

	/*
		Classe interna a DoublyLinkedList del nodo della lista doppiamente concatenata
	*/
	private class ListNode
	{
		/*
			Variabili di esemplare
			 - element: informazione contenuta nel nodo
			 - next: riferimento al nodo successivo
			 - prev: riferimento al nodo precedente
		*/
		private Object 	 element;
		private ListNode next;
		private ListNode prev;

		/*
			Costruttore della classe ListNode che crea un nodo vuoto
		*/
		public ListNode()
		{
			this(null, null, null);
		}

		/*
			Costruttore della classe ListNode che inizializza il nodo con un elemento e i riferimenti ai nodo
		*/
		public ListNode(Object element, ListNode next, ListNode prev)
		{
			this.element = element;
			this.next 	 = next;
			this.prev 	 = prev;
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
			Restituisce il riferimento al nodo precedente
		*/
		public ListNode getPrev()
		{
			return this.prev;
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

		/*
			Imposta un nuovo riferimento a un nodo nella variabile di esemplare prev
		*/
		public void setPrev(ListNode prev)
		{
			this.prev = prev;
		}
	}
}