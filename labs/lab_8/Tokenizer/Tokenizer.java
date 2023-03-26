import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Tokenizer
{
	public static void main(String[] args) throws IOException
	{
		// scanner
		Scanner console = new Scanner(System.in);

		// riceva dall'input standard due nomi di file di testo, uno in lettura ed uno in scrittura
		// lettura del nome del file da leggere
		System.out.print("Inserisci il nome del file da leggere: ");
		String inputFileName = console.nextLine();
		// lettura del nome del file in cui scrivere
		System.out.print("Inserisci il nome del file in cui scrivere: ");
		String outputFileName = console.nextLine();
		
		// apra in lettura il primo file e ne legga il contenuto
		FileReader reader = null;
		try
		{
			reader = new FileReader(inputFileName);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Errore: il file da leggere non e\' stato trovato trovato");
			System.exit(1);
		}
		Scanner fileReader = new Scanner(reader);
		String text = "";
		// leggi finche il file ha righe
		while(fileReader.hasNextLine())
		{
			// concatenazione riga letta
			text += fileReader.nextLine();
		}

		// chiusura dei flussi di lettura
		fileReader.close();
		reader.close();
		
		// elaborazione del testo letto
		Scanner in = new Scanner(text);
		Scanner tk; 
		String newText = "";
		while(in.hasNextLine())
		{
			// lettura riga
			tk = new Scanner(in.nextLine());
			tk.useDelimiter(" |\'");
			while(tk.hasNext())
			{
				// lettura token
				String token = tk.next().toLowerCase();
				newText += Character.toUpperCase(token.charAt(0)) + token.substring(1);

				// per stampare i token finali che altrimenti andrebbero persi
                String ap = tk.findInLine(" |\'");
                if(ap != null)
                {
                    newText += ap;
                }
			}
		}

		// creo e apro in scrittura il secondo file
		FileWriter writer = new FileWriter(outputFileName);
		PrintWriter filePrinter = new PrintWriter(writer);

		// copio nel secondo file il contenuto del primo, opportunamente modificato in modo che tutte le parole abbiano la prima lettera maiuscola e le seguenti minuscole
		filePrinter.print(newText);

		// chiusura dei flussi di scrittura
		filePrinter.close();
		writer.close();
	}
}