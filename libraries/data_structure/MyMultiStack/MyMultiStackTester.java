import java.util.*;

/*
	Classe principale
*/
public class MyMultiStackTester
{
	public static void main(String[] args)
	{
		// controllo sui parametri a riga di comando
		if(args.length < 1)
		{
			System.out.println("Errore: mancano parametri");
			System.exit(1);
		}

		// controllo sulla dimensione
		int dim = 0;
		try
		{
			dim = Integer.parseInt(args[0]);
		}
		catch(NumberFormatException e)
		{
			System.out.println("Errore: il primo parametro non e\' valido");
			System.exit(1);
		}

		// crazione del multistack
		MultiStack s = new MyMultiStack(dim);

		// inserimento degli eventuali parametri nello stack di "indice" massimo
		for (int i = 1; i < args.length; i++)
			s.add(args[i], (dim - 1));

		System.out.println("STATUS 1: " + s);

		// inserimento dei token dei parametri senza indicare la pila di destinazione
		// lettura dall'ingresso standard
		Scanner console = new Scanner(System.in);
		System.out.println("\nInserisci del testo (CTRL+Z + invio per terminare)");
		while(console.hasNextLine())
		{
			String str = console.nextLine();
			
			Scanner tokenizer = new Scanner(str);
			while(tokenizer.hasNext())
			{
				s.add(tokenizer.next());
			}
		}

		System.out.println("\nSTATUS 2: " + s);

		// esecuzione di due rimozioni
		s.remove();
		s.remove();

		// stampa dell'oggetto multistack
		System.out.println("\nSTATUS 3: " + s);		
	}
}