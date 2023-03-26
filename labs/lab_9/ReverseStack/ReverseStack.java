import java.util.Scanner;

public class ReverseStack
{
	public static void main(String[] args)
	{
		// scanner
		Scanner console = new Scanner(System.in);

		// creazione pila con ridimensionamento
		Stack p = new GrowingArrayStack();
		Stack s = new GrowingArrayStack();

		// inserimento dati
		System.out.println("******** Inserimento dati ********");
		while(console.hasNextInt())
		{
			// lettura numero intero
			int n = console.nextInt();

			// inserimento numero intero
			p.push(n);
			s.push(n);
		}

		// inversione stringa
		worstReverseStack(p);
		s = betterReverseStack(s);

		// stampa elementi pila
		System.out.println("\n******** Pila p invertita ********");
		while(! p.isEmpty())
			System.out.println(p.pop());

		// inversione stringa migliore
		System.out.println("\n\n******** Pila s invertita ********");
		while(! s.isEmpty())
			System.out.println(s.pop());
	}

	/*
		Analisi asintotica (check)
		T(n) = 3 * (n*O(1) + n*O(1) + n*O(1)) = O(n)
	*/
	public static void worstReverseStack(Stack p)
	{
		// pile con ridimensionamento temporanee
		Stack tmp1 = new GrowingArrayStack();
		Stack tmp2 = new GrowingArrayStack();

		// svuoto p, riempio tmp1
		while(! p.isEmpty())
			tmp1.push(p.pop());

		// svuoto tmp1, riempio tmp2
		while(! tmp1.isEmpty())
			tmp2.push(tmp1.pop());

		// svuoto tmp2, riempio p
		while(! tmp2.isEmpty())
			p.push(tmp2.pop());
	}

	/*
		Analisi asintotica (check)
		T(n) = n*(O(1) + O(1) + O(1)) = O(n)
	*/
	public static Stack betterReverseStack(Stack p)
	{
		// pila con ridimensionamento temporanee
		Stack tmp1 = new GrowingArrayStack();

		// svuoto p, riempio tmp1
		while(! p.isEmpty())
			tmp1.push(p.pop());

		return tmp1;
	}

	// SOLUZIONE PROF: O(n^2)
	// L'osservazione che consente di delineare un
	// algoritmo e' la seguente: l'elemento che inizialmente si
	// trova in cima alla pila, dovra' trovarsi in fondo alla pila
	// al termine dell'inversione dell'ordine.
	// Per far questo, occorre estrarre dalla pila l'elemento che
	// vi si trova in cima e fare in modo che raggiunga il fondo
	// della pila:
	// *) si estrae l'elemento
	// *) lo si memorizza in una variabile temporanea
	// *) si estraggono tutti gli altri elementi, inserendoli nella
	//    pila temporanea
	// *) si inserisce nella pila originaria l'elemento memorizzato
	// *) si estraggono tutti gli elementi dalla pila temporanea,
	//    inserendoli di nuovo nella pila originaria
	// A questo punto la pila originaria ha tutti gli elementi nello
	// stesso ordine, tranne l'elemento che si trovava in cima, che
	// ora si trova in fondo alla pila.
	// Se ora applichiamo lo stesso algoritmo alla sotto-pila che
	// si trova sopra all'elemento che gia' e' in posizione corretta,
	// otteniamo che gli ultimi due elementi della pila si troveranno
	// nella posizione corretta. La difficolta' di questo secondo passo
	// e' decidere quando fermarsi nell'estrarre elementi dalla pila
	// originaria, perche' non bisogna vuotarla completamente
	// (un elemento e' gia' in posizione): per far questo, calcoliamo
	// il numero di elementi iniziali nella pila e decrementiamo tale
	// valore ad ogni iterazione del ciclo.
	public static void reverse(Stack s)
	{  
		// creiamo la pila temporanea
		Stack temp1 = new GrowingArrayStack();
		// contiamo gli elementi presenti nella pila originaria:
		// per farlo occorre trasferire tutti gli elementi nella
		// pila temporanea, contandoli; al termine del conteggio
		// gli elementi verranno riportati nella pila originaria
		int count = 0;
		while (!s.isEmpty())
		{
			temp1.push(s.pop());
			count++;
		}
		while (!temp1.isEmpty())
			s.push(temp1.pop());
		// ciclo principale
		while (count > 0)
		{  
			// decremento della lunghezza della pila temporanea
			count--;
			// memorizzazione dell'elemento in cima alla pila
			Object obj = s.pop();
			// trasferimento di tutti gli altri elementi
			for (int i = 0; i < count; i++)
				temp1.push(s.pop());
			// inserimento nella pila originaria dell'elemento
			// memorizzato
			s.push(obj);
			// trasferimento di tutti gli altri elementi
			for (int i = 0; i < count; i++)
				s.push(temp1.pop());
		}
	}
}