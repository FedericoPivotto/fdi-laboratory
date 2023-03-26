import java.util.Scanner;
import java.io.FileReader;

public class SMTester //-- Classe di prova della pila multipla
{
	public static void main(String[] args) throws java.io.IOException
	{
		Scanner in = new Scanner(new FileReader(args[0]));
		Stack s = new S();                   			// pila per memorizzazione temporanea

		while(in.hasNext())                 
		{
			Scanner tok = new Scanner(in.nextLine()); 	// lettura di dati da file
			Stack tmp = new S();              			// altra pila per memorizzazione temporanea
			while (tok.hasNext())
				tmp.push(tok.next());        			// prova del metodo push    
			s.push(tmp);                 
			tok.close();   
		}      
		in.close();

		Stack[] sAr = new S[s.size()];        			// creazione di un array di pile
		int i = 0;
		while (!s.isEmpty())                  			// copia nell'array di pile
			sAr[i++] = (Stack)s.pop();         			// prova del metodo pop

		SM sm1 = new SM(sAr);                 			// pila multipla popolata con l'array sAr  
		SM sm2 = new SM(sAr.length);          			// pila multipla vuota di lunghezza pari a sm1

		System.out.println("***TOTALSIZE SM1 " + sm1.totalSize()); // prova del metodo totalSize
		System.out.println("***SM1***");      			// estrazione e stampa degli elementi di sm1
		while (!sm1.isTotallyEmpty())
		{
			Object x = sm1.maxPop();           			// estrazione  da sm1 e prova del metodo maxPop
			sm2.minPush((Comparable)x);        			// inserimento in sm2 e prova del metodo minPush
			s.push((Comparable)x);             			// inserimento in s
			System.out.println(x);             			// stampa del valore estratto      
		}
		System.out.println("***TOTALSIZE SM1 " + sm1.totalSize()); // prova del metodo totalSize
		System.out.println("***TOTALSIZE SM2 " + sm2.totalSize()); // prova del metodo totalSize
		System.out.println("***SM2***");      			// estrazione e stampa degli elementi di sm2
		while (!sm2.isTotallyEmpty())
			System.out.println(sm2.maxPop());  // prova del motodo maxPop
		System.out.println("***TOTALSIZE SM2 " + sm2.totalSize()); // prova del metodo totalSize

		System.out.println("***SORT***");
		Object[] v = s.toSortedArray();       			// prova del metodo toSortedArray
		for(i = 0; i < v.length; i++)
			System.out.println(v[i]);                          
	}
}

interface Stack extends Comparable  //--ADT pila comparabile
{   
	/** 
		@return true se il contenitore e' vuoto, false altrimenti
	*/
	boolean isEmpty();

	/**
		ispeziona, estraendolo, l'elemento in cima alla pila
		@return l'elemento in cima alla pila
		@throws java.util.EmptyStackException se la pila e' vuota
	*/
	Object pop() throws java.util.EmptyStackException;

	/**
		inserisce l'elemento specificato in cima alla pila
		@param x elemento specificato.
		@throws java.lang.IllegalArgumentException se x e' null   
	*/
	void push(Comparable x);

	/** 
		@return numero di elementi presenti nella pila
	*/
	int size(); 

	/**
		ispeziona, senza estrarlo, l'elemento in cima alla pila
		@return l'elemento in cima alla pila
		@throws java.util.EmptyStackException se la pila e' vuota
	*/
	Object top() throws java.util.EmptyStackException;

	/**
		restituisce un array contenente gli elementi presenti nella pila ordinati in senso crescente
		@return array contenente gli elementi presenti nella pila in ordine crescente
	*/
	Object[] toSortedArray();   
}

class S implements Stack
{
	private Comparable[] v;
	private int 		 vSize;

	public S()
	{
		this.v 	   = new Comparable[10];
		this.vSize = 0;
	}

	/** 
		@return true se il contenitore e' vuoto, false altrimenti
	*/
	public boolean isEmpty()
	{
		return (this.vSize == 0);
	}

	/**
		ispeziona, estraendolo, l'elemento in cima alla pila
		@return l'elemento in cima alla pila
		@throws java.util.EmptyStackException se la pila e' vuota
	*/
	public Object pop() throws java.util.EmptyStackException
	{
		Object obj = top();
		this.vSize--;

		return obj;
	}

	/**
		inserisce l'elemento specificato in cima alla pila
		@param x elemento specificato.
		@throws java.lang.IllegalArgumentException se x e' null   
	*/
	public void push(Comparable x)
	{
		if(x == null)
			throw new java.lang.IllegalArgumentException();

		if(this.vSize == this.v.length)
			this.v = resize(this.v, 2*this.v.length);

		this.v[vSize++] = x;
	}

	private static Comparable[] resize(Comparable[] oldV, int newLength)
	{
		if(oldV == null || newLength < 0)
			throw new IllegalStateException();

		Comparable[] newV = new Comparable[newLength];
		System.arraycopy(oldV, 0, newV, 0, newV.length);

		return newV;
	}

	/** 
		@return numero di elementi presenti nella pila
	*/
	public int size()
	{
		return this.vSize;
	}

	/**
		ispeziona, senza estrarlo, l'elemento in cima alla pila
		@return l'elemento in cima alla pila
		@throws java.util.EmptyStackException se la pila e' vuota
	*/
	public Object top() throws java.util.EmptyStackException
	{
		if(this.isEmpty())
			throw new java.util.EmptyStackException();

		return this.v[this.vSize - 1];
	}

	/**
		restituisce un array contenente gli elementi presenti nella pila ordinati in senso crescente
		@return array contenente gli elementi presenti nella pila in ordine crescente
	*/
	public Object[] toSortedArray()
	{
		Comparable[] vObj = new Comparable[this.vSize];
		
		// copia
		System.arraycopy(this.v, 0, vObj, 0, vObj.length);

		// ordinamento per fusione: O(n*log(n))
		mergeSort(vObj, vObj.length);

		return vObj;
	}

	/*
		MergeSort
	*/
	private static void mergeSort(Comparable[] v, int vSize)
	{
		if (vSize < 2)
			return; // caso base

		int mid = vSize / 2; //dividiamo circa a meta`
		Comparable[] left = new Comparable[mid];
		Comparable[] right = new Comparable[vSize - mid];
		System.arraycopy(v, 0, left, 0, mid);
		System.arraycopy(v, mid, right, 0, vSize-mid);

		// passi ricorsivi: ricorsione multipla (doppia)
		mergeSort(left, mid);
		mergeSort(right, vSize-mid);

		// fusione (metodo ausiliario)
		merge(v, left, right);
	}

	/*
		Metodo ausiliario per la fusione di due array ordinati v1 e v2
	*/
	private static void merge(Comparable[] v, Comparable[] v1, Comparable[] v2)
	{  
		int i = 0, i1 = 0, i2 = 0;

		while (i1 < v1.length && i2 < v2.length)
		{
			if (v1[i1].compareTo(v2[i2]) < 0) // prima si usa i, poi lo si incrementa...
				v[i++] = v1[i1++];
			else
				v[i++] = v2[i2++];
		}

		while (i1 < v1.length)
			v[i++] = v1[i1++];

		while (i2 < v2.length)
			v[i++] = v2[i2++];
	}

	public int compareTo(Object x)
	{
		if(! (x instanceof Stack))
			throw new IllegalArgumentException();

		Stack tmp = (Stack) x;

		if(this.size() > tmp.size())
			return 1;
		else if(this.size() < tmp.size())
			return -1;
		else
			return 0;
	}
}

class SM //-- Pila Comparabile Multipla
{  
	// parte privata
	private Stack[] v; 

	/** costruttore: inizializza una pila multipla formata da k pile comparabili vuote
		@param k numero di pile comparabili
		@throws java.lang.IllegalArgumentException se k < 0
	*/
	public SM(int k)
	{
		if(k < 0)
			throw new java.lang.IllegalArgumentException();

		this.v = new Stack[k];
		for(int i = 0; i < this.v.length; i++)
			this.v[i] = new S();
	}
		  
	/**	costruttore: inizializza una pila multipla con le pile comparabili presenti nell'array
		specificato (copia le pile dell'array sAr nella struttura interna)
		@param sAr array specificato di pile comparabili
		@throws java.lang.IllegalArgumentException se il riferimento sAr vale null o se l'array sAr contiene elementi di valore null
	*/
	public SM(Stack[] sAr)
	{
		if(sAr == null)
			throw new java.lang.IllegalArgumentException();

		for(int i = 0; i < sAr.length; i++)
		{
			if(sAr[i] == null)
				throw new java.lang.IllegalArgumentException();
		}

		this.v = new Stack[sAr.length];
		System.arraycopy(sAr, 0, this.v, 0, this.v.length);
	}
   
	/** @return true se tutte le pile della pila multipla sono vuote o altrimenti false
	*/
	public boolean isTotallyEmpty()
	{
		for(int i = 0; i < this.v.length; i++)
		{
			if(! this.v[i].isEmpty())
				return false;
		}

		return true;
	}        
   
	/**	estrae l'elemento in cima alla pila massima (nel senso dell'ordinamento naturale)
		@return elemento in cima alla pila massima 
	*/
	public Object maxPop()
	{
		int iMax = 0;
		for(int i = 1; i < this.v.length; i++)
		{
			if(v[iMax].size() < v[i].size())
				iMax = i;	
		}

		Object obj = v[iMax].pop();

		return obj;
	} 
   
	/**	inserisce l'elemento specificato nella pila minima (nel senso dell'ordinamento naturale)
		@param x elemento specificato 
	*/
	public void minPush(Comparable x)
	{
		int iMin = 0;
		for(int i = 1; i < this.v.length; i++)
		{
			if(v[iMin].size() > v[i].size())
				iMin = i;	
		}

		v[iMin].push(x);
	}
		  
	/** @return numero totale di elementi presenti nelle pile comparabili della pila multipla
	*/
	public int totalSize()
	{
		int size = 0;
		for(int i = 0; i < this.v.length; i++)
			size += v[i].size();

		return size;
	}
}