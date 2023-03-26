import java.util.*;
import java.io.*;

public class Pivotto2008810
{
	public static void main(String[] args) throws IOException
	{
		// controllo sui parametri a riga di comando
		if(args.length < 2)
		{
			System.out.println("Errore: mancano parametri");
			System.exit(1);
		}

		/*
			- crea un esemplare s1 della classe ArrayStudentSet e vi inserisce
			tuttigli studenti presenti in un file di testo il cui nome viene passato
			come primo argomento da riga di comando;
		*/
		StudentSet s1 = createStudentSet(args[0]);
		System.out.println(args[0] + ": " + s1);

		/*
			- crea un esemplare s2 della classe ArrayStudentSet e vi inserisce tuttigli
			studenti presenti in un file di testo il cui nome viene passato come
			secondo argomento da riga di comando;
		*/
		StudentSet s2 = createStudentSet(args[1]);
		System.out.println(args[1] + ": " + s2);

		/*
			- calcola l'insieme degli studenti che frequentano sia il corso
			di Fond. di Informatica che il corso di Analisi Matematica 1, invocando
			il metodo intersection, e visualizza l'elenco dei numeri di matricola di
			tali studenti sull'output standard.
		*/
		StudentSet s3 = s1.intersection(s2);
		System.out.println("Intersezione: " + s3);
	}

	public static StudentSet createStudentSet(String fileName) throws IOException
	{
		// insieme di studenti
		StudentSet s = new ArrayStudentSet();

		// lettura studenti
		FileReader reader = null;
		try
		{
			reader = new FileReader(fileName);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Errore: file non trovato");
			System.exit(1);
		}

		Scanner scanner = new Scanner(reader);
		while(scanner.hasNextLine())
		{
			String line = scanner.nextLine();
			Scanner tokenizer = new Scanner(line);
			String matricola = "", cognome = "", nome = "";
			for(int i = 0; i < 3 && tokenizer.hasNext(); i++)
			{
				if(i == 0)
					matricola = tokenizer.next();
				else if(i == 1)
					cognome = tokenizer.next();
				else
					nome = tokenizer.next();		
			}

			Student stud = new Student(matricola, cognome, nome);
			s.add(stud);
		}

		// chiusura del lettore
		reader.close();

		return s;
	}
}

class Student implements Comparable<Student>
{
	private String matricola;  //identificatore univoco
    private String cognome;
    private String nome;

    public Student (String m, String c, String n)
    {
		matricola = m;
		cognome = c;
		nome = n;
    }

    public String getMatricola()
    { 
		return matricola;
    }

    public String getCognome()
    { 
		return cognome;
    }

    public String getNome()
    { 
		return nome;
    }

    public int compareTo(Student stud)
    {
    	if(this.getCognome().compareTo(stud.getCognome()) < 0)
    		return -1;

    	else if(this.getCognome().compareTo(stud.getCognome()) > 0)
    		return 1;
    	
    	else
    		return 0;
    }

    public boolean equals(Student stud)
    {
    	return this.getMatricola().equals(stud.getMatricola());
    }
}

interface StudentSet
{  	
	/*
		Restituisce un array di Student contenente i riferimenti 
		a tutti gli elementi presenti nell'insieme, in ordine 
		lessicografico crescente secondo il valore del cognome.
	*/
    Student[] toArray();
    
    /*
    	Restituisce true se e solo se lo studente stud appartiene
       	all'insieme.
	*/
    boolean contains(Student stud);
    
    /*
    	Inserisce lo studente stud nell'insieme, se non e' gia'
       	presente, altrimenti fallisce silenziosamente.
   	*/
    void add(Student Stud);
    
    /*
    	Riceve un riferimento ad un oggetto di tipo StudentSet e
		restituisce un nuovo esemplare della classe che rappresenta
		l'insieme risultante dall'intersezione (in senso insiemistico e,
		quindi, senza elementi duplicati) degli insiemi ricevuti come
		parametro implicito ed esplicito. Il metodo deve avere prestazioni
		O(n logn) dove n e' la somma del numero di elementi presenti nei 
		due insiemi
	*/
    StudentSet intersection(StudentSet s);
}


class ArrayStudentSet implements StudentSet
{
	/*
		Variabili di esemplare
	*/
	private Student[] v = new Student[100];
	private int vSize = 0;

	/*
		Restituisce un array di Student contenente i riferimenti 
		a tutti gli elementi presenti nell'insieme, in ordine 
		lessicografico crescente secondo il valore del cognome.
	*/
    public Student[] toArray()
    {
    	Student[] vObj = new Student[vSize];
    	System.arraycopy(v, 0, vObj, 0, vSize);

    	return vObj;
    }
    
    /*
    	Restituisce true se e solo se lo studente stud appartiene
       	all'insieme.
	*/
    public boolean contains(Student stud)
    {
    	int status = binarySearch(this.v, this.vSize, stud);
    	
    	if(status != -1)
    		return true;

    	return false;
    }

    private static int binarySearch(Student[] v, int vSize, Student value)
	{
		// pre-condizione
		if(v == null || vSize < 0)
			throw new IllegalArgumentException();

		return binSearch(v, 0, vSize - 1, value);
	}

	private static int binSearch(Student[] v, int from, int to, Student value)
	{  
		if (from > to)
			return -1; // elemento non trovato

		int mid = (from + to) / 2; // circa in mezzo
		Student middle = v[mid];

		if (middle.equals(value))
			return mid; // elemento trovato
		
		else if (middle.compareTo(value) < 0)  //cerca a destra
			return binSearch(v, mid + 1, to, value);

		else // cerca a sinistra
			return binSearch(v, from, mid - 1, value);
   	}
    
    /*
    	Inserisce lo studente stud nell'insieme, se non e' gia'
       	presente, altrimenti fallisce silenziosamente.
   	*/
    public void add(Student stud)
    {
    	if(this.contains(stud))
    		return;

    	if(this.vSize == this.v.length)
    		this.v = resize(this.v, this.v.length);

    	this.v[this.vSize++] = stud;

    	Student tmp = this.v[vSize-1];
    	int i;
    	for(i = this.vSize-1; i > 0 && tmp.compareTo(this.v[i-1]) < 0; i--)
    		this.v[i] = this.v[i-1];
    	this.v[i] = tmp;
    }

    private static Student[] resize(Student[] oldV, int newLength)
    {
    	if(newLength < oldV.length)
    		throw new IllegalArgumentException();

    	Student[] newV = new Student[newLength];
    	System.arraycopy(oldV, 0, newV, 0, oldV.length);

    	return newV;
    }
    
    /*
    	Riceve un riferimento ad un oggetto di tipo StudentSet e
		restituisce un nuovo esemplare della classe che rappresenta
		l'insieme risultante dall'intersezione (in senso insiemistico e,
		quindi, senza elementi duplicati) degli insiemi ricevuti come
		parametro implicito ed esplicito. Il metodo deve avere prestazioni
		O(n logn) dove n e' la somma del numero di elementi presenti nei 
		due insiemi
	*/
    public StudentSet intersection(StudentSet s)
    {
    	StudentSet x = new ArrayStudentSet();
    	Student[] v2 = s.toArray();

    	for (int i = 0, j = 0; i < this.vSize; i++)
    	{
    		while(j < v2.length && this.v[i].compareTo(v2[j]) > 0)
    			j++;
    		
    		if(j == v2.length)
    			break;

    		if(this.v[i].equals(v2[j]))
    		{
    			x.add(this.v[i]);
    			j++;
    		}
    	}

    	return x;
    }

    /*
    	Sovrascrittura del metodo toString
    */
    public String toString()
    {
    	String str = "";
    	for(int i = 0; i < this.vSize; i++)
    		str += this.v[i].getMatricola() + " ";

    	return str;
    }
}