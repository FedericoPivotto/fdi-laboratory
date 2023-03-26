import java.util.*;
import java.io.*;

public class Pivotto2008810
{
	public static void main(String[] args) throws IOException
	{
		// controllo parametri
		if(args.length != 1)
		{
			System.out.println("Errore: parametri non validi");
			System.exit(1);
		}

		// apertura file
		FileReader fr = null;
		try
		{
			fr = new FileReader(args[0]);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Errore: file non trovato");
			System.exit(1);
		}

		// lettura file
		String str = "";
		Scanner reader = new Scanner(fr);
		while(reader.hasNextLine())
			str += reader.nextLine() + "\n";

		// chiusura file
		reader.close();
		fr.close();

		// creazione dizionario
		Dictionary dict = new MyDictionary();

		// aggiustaggio stringa
		char[] v = str.toCharArray();
		for(int i = 0; i < v.length; i++)
		{
			if(! Character.isLetter(v[i]))
			{
				v[i] = ' ';
			}	
		}

		// inserimento parole nel dizionario
		String[] word = (new String(v)).split(" ");

		for(int i = 0; i < word.length; i++)
		{
			String input = word[i].trim();
			if(input.length() > 0)
				dict.insert(input);
		}

		// stampa del dizionario ordinato
		System.out.print("DIZIONARIO:\n" + dict);
	}
}

interface Dictionary
{
	// il metodo insert aggiunge un’unità al conteggio associato alla parola word (conteggio che è zero se la parola non è presente nel dizionario)
	void insert(String word);

	// il metodo find restituisce il conteggio associato alla parola word all’interno del dizionario
	// O(log(n))
	int find(String word);

	// entrambi lanciano IllegalArgumentException se word è null oppure se si riferisce ad una stringa di lunghezza zero; non lanciano nessun’altra eccezione
}

class MyDictionary implements Dictionary
{
	private class Coppia implements Comparable<Coppia>
	{
		private String element;
		private int    molt;

		public Coppia(String element, int molt)
		{
			this.element = element;
			this.molt 	 = molt;
		}

		public int compareTo(Coppia couple)
		{
			return this.element.compareTo(couple.element);
		}
	}

	private Coppia[] v;
	private int 	 vSize;

	public MyDictionary()
	{
		this.v 	   = new Coppia[10];
		this.vSize = 0;
	}

	// il metodo insert aggiunge un’unità al conteggio associato alla parola word (conteggio che è zero se la parola non è presente nel dizionario)
	public void insert(String word)
	{
		// pre-condizione
		if(word == null || word.length() == 0)
			throw new IllegalArgumentException();

		Coppia couple = binarySearch(this.v, 0, this.vSize - 1, word);

		if(couple == null)
		{
			Coppia tmp = new Coppia(word, 1); // nuovo elemento da inserire
			this.vSize++;
			// j va definita fuori dal ciclo perche'
			// il suo valore finale viene usato in seguito
			int j;
			// sposta a destra di un posto tutti gli el. a
			// sin. di tmp e > di tmp, partendo da destra
			for (j = this.vSize-1; j > 0 && tmp.compareTo((Coppia) v[j-1]) < 0; j--)
				v[j] = v[j-1];

			v[j] = tmp; // inserisci tmp in posizione

			return;
		}

		for(int i = 0; i < this.vSize; i++)
		{
			if(v[i].equals(couple))
			{
				v[i].molt++;
				return;
			}
		}
	}

	// il metodo find restituisce il conteggio associato alla parola word all’interno del dizionario
	// O(log(n))
	public int find(String word)
	{
		// pre-condizione
		if(word == null || word.length() == 0)
			throw new IllegalArgumentException();

		// ricerca dicotomica
		Coppia tmp = binarySearch(this.v, 0, this.vSize - 1, word);

		if(tmp == null)
			return 0;

		return tmp.molt;
	}

	private static Coppia binarySearch(Coppia[] v, int from, int to, String value)
	{  
		if (from > to)
			return null; // elemento non trovato

		int mid = (from + to) / 2; // circa in mezzo
		String middle = v[mid].element;
		if (middle.equals(value))
			return v[mid]; // elemento trovato
		
		else if (middle.compareTo(value) < 0)  //cerca a destra
			return binarySearch(v, mid + 1, to, value);
		
		else // cerca a sinistra
			return binarySearch(v, from, mid - 1, value);
   	}

	// sovrascrivi il metodo toString in modo che restituisca una stringa composta da una riga di testo per ogni parola presente nel dizionario (la stringa vuota se il dizionario è vuoto), ogni riga composta da una parola seguita da uno spazio e dal relativo conteggio, con le parole in ordine lessicografico decrescente
	public String toString()
	{
		String str = "";
		for (int i = this.vSize - 1; i >= 0; i--)
			str += v[i].element + " " + v[i].molt + "\n";

		return str;
	}
}