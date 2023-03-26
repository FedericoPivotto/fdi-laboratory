/** Questa classe contiene metodi statici per l'uso di array: 
      leggi, stampa, raddoppio, copia, ridimensiona, aumenta 
*/

import java.util.Scanner;

public class AlgoritmiArray{
	
/** Metodi per la lettura di un array di tipo intero: 
si costruisce un metodo  leggi che restituisce il riferimento all'array (riempito completamente) che e' stato acquisito.
Si forniscono due diverse possibilita' di lettura con la variabile di nome in e di tipo Scanner come variabile locale o argomento del metodo
*/

//Ci sono due metodi di nome leggi: esempio di sovraccarico 

/** 
	public static int[] leggi(int [] v);
 
      	restituisce il riferimento all'array acquisito; 
        la variabile in (di tipo Scanner) e' locale al metodo; 
        la lettura termina con q oppure Q 
*/

	public static int [] leggi(int v[]) 
       {
 		Scanner in =new Scanner(System.in);

		String st;
		int i=0;
		System.out.println("inserire dei numeri interi " +
			" per terminare battere q o Q");
		while(!((st = in.next()).equalsIgnoreCase( "q")) ) 
               {
	         /** acquisizione dei dati ed eventuale ridimensionamento*/
		       if(i == v.length) v=raddoppio(v);
                       v[i]=Integer.parseInt(st);
			i++;
		}//fine while
     
	/* se i < dimensione di v, togliamo gli spazi vuoti (array riempito completamente)*/
		if(i < v.length)
			v= ridimensiona(v, i);
	 	return v;
	}//fine leggi


/* 
	public static int[] leggi(int [] v, Scanner in);
  
    	restituisce la referenza dell'array letto; 
      	la variabile in (di tipo Scanner) e' passata dal metodo chiamante; 
        la lettura termina con q oppure Q
*/

	public static int [] leggi(int v[], Scanner in) 
       {
		String st;
		int i=0;
		System.out.println("inserire dei numeri interi " +
			" per terminare battere q o Q");
		while(!((st = in.next()).equalsIgnoreCase( "q")) ) 
               {
	        /** acquisizione dei dati ed eventuale ridimensionamento*/
			if(i == v.length)
				v=raddoppio(v);
                        v[i]=Integer.parseInt(st);
			i++;
		}//fine while
     
	/* se n < dimensione di v, togliamo gli spazi vuoti */
		if(i < v.length)
			v= ridimensiona(v, i);
	 	return v;
	}//fine leggi

/** 
	public static void stampa(int t[]);

        si effettua la stampa dell'array, scrivendo 8 elementi per riga 
*/

	public static void stampa(int t[]) {
		for (int i=0; i < t.length ; i++) 
                {
 			System.out.print(t[i] + "\t");
			if ((i+1) % 8 == 0 ) 
				System.out.println();
		}
		System.out.println();
 	}//fine stampa

/** 
	public static int[] raddoppio(int t[]);

	raddoppio dell'array, viene restituita il riferimento all'array di dimensione doppia
*/
	public static int[] raddoppio(int t[])
       {
		int w[] = new int[t.length*2];
		for(int i=0; i < t.length; i++)
			w[i] = t[i];
		return w;	
	}//fine raddoppio

/** 
	public static int[] ridimensiona(int t[], int n);
 
    	ridimensionamento: l'array viene reso "pieno", copiandolo su un array di 	   dimensione uguale al numero di elementi effettivamente inseriti 
*/

	public static int[] ridimensiona(int t[], int n)
       {
		int w[] = new int[n];
			for(int i=0; i < n; i++)
				w[i]=t[i];
		return w;	
	}//fine ridimensiona

/** 
	public static int[] aumenta(int t[], int n);
	
	copia l'array in un array piu' grande (es. n = t.length+1) per poter inserire una componente in un array pieno 
*/

	public static int[] aumenta(int t[], int n)
       {
		int w[] = new int[n];
			for(int i=0; i < t.length; i++)
				w[i]=t[i];
		return w;	
	}//fine aumenta

/** 
	public static void copia(int t[], int w[]);

	copia i valori dell'array  t in un nuovo array w della stessa dimensione e creato precedentemente 
*/
	public static void copia(int t[], int w[]){
		for(int i=0; i < t.length; i++)
			w[i]=t[i];
	}//fine copiarray

}//fine classe AlgoritmiArray