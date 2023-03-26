/*
Esempi di arrotondamenti e conversioni tra tipi numerici
*/

public class NumTypeTester
{
    public static void main(String[] args)
    {

	    System.out.println(
	        "\n************ overflow in somme di int *********************");

	    int verylargeint = 2100000000; // se verylargeint ha un valore vicino a
	                                   // 2^31 (ovvero 2147500000) la somma
	                                   // verylargeint+verylargeint produce un 
	                                   // risultato inaspettato. Perche`?.
	             					   // 
	    							   // RISPOSTA: 4 byte (int) non sono sufficienti per memorizzare un numero intero così grande
	    System.out.println("verylargeint: " + verylargeint);

	    verylargeint = verylargeint + verylargeint;
	    System.out.println("verylargeint + verylargeint: " + verylargeint);




	    System.out.println(
	       "\n************ conversione da double a int ********************");

	    double smalldouble =1.93;

	    System.out.println("smalldouble: " + smalldouble);

	    // int smallint = smalldouble; //cosi` non funziona (provare). Perche`?
	    								//
	    								// RISPOSTA: 8 Byte (double) non sono contenibili in 4 Byte (int),
	    								//			 inoltre non avviene in automatico alcuna operazione di
	    								// 			 promozione o casting (che si rende necessaria a int),
	    								// 			 l'operazione di casting potrebbe comportare perdita di informazioni
	    								// 			 "INCOMPATIBLE TYPES"
	    int smallint = (int) smalldouble; //cosi` si tronca la parte decimale 
	    System.out.println("smallint con cast: " + smallint);

	    // smallint = Math.round(smalldouble); //cosi` non funziona (provare).
	                                           // Perche`?
	    									   // 
	    									   // RISPOSTA: 8 Byte (long) non sono contenibili in 4 Byte (int),
	    									   //			inoltre non avviene in automatico alcuna operazione di
	    									   // 			promozione o casting (che si rende necessaria a long),
	    									   //			l'operazione di casting potrebbe comportare perdita di informazioni
	    									   //			"INCOMPATIBLE TYPES"
	    smallint = (int) Math.round(smalldouble); //Cosi` si approssima all'int
	                                              //piu` vicino. Notare che
	                                              //Math.round applicato ad un
	                                              //numero double restituisce 
	                                              //un numero in formato long
	    System.out.println("smallint con Math.round e cast: " + smallint);




	    System.out.println(
	       "\n************ conversione da double a long ********************");

	    double largedouble =3e9;
	    System.out.println("largedouble: " + largedouble);

	    int largeint = (int) largedouble; // se largedouble e` piu` grande di
	                                      // 2^31 (ovvero di 2.1475e9) il cast
	                                      // produce un risultato inaspettato.
	                                      // Perche`?.
	    								  //
	    								  // RISPOSTA: 4 Byte (int) non sono sufficienti a memorizzare un numero double (in questo caso intero) così grande
	    								  // 	       
	    System.out.println("largeint con cast: " + largeint);

	    long largelong = (long) largedouble; // in questo caso il cast funziona
	                                         // (e tronca alla parte intera).
	                                         // Perche`?
	    									 // 
	    									 // RISPOSTA: il tipo di dato long, essendo utilizzato per memorizzare numeri interi,
	    									 // 		  messo in un'operazione di casting come questa, tronca la parte decimale
	    									 // 		  di un dato di tipo double
	    System.out.println("largelong con cast: " + largelong);

	    largelong = Math.round(largedouble); // non e` necessario effettuare
	                                         // il cast a long. Perche`?
	    									 //
	    									 // RISPOSTA: il metodo restituisce un dato di tipo long che
	    									 // 		  successivamente viene assegnato a un altro dato dello stesso tipo
	    System.out.println("largelong con cast: " + largelong);



	    /////////////////////////////
	    /* Sarebbe un errore arrotondare il double, tuttavia se voglio la parte intera
	       di quel numero con rappresentazione binaria periodica, faro' "int num = (int) Math.round(periodicdouble);" */
		System.out.println(
	       "\n*********** errori di arrotondamento in numeri double *********");

	    double periodicdouble = 4.35; // 4.35 ha una rappresentazione periodica
	                                  // in base 2 (provare per credere)
	    System.out.println("periodicdouble: " + periodicdouble);
	    //l'approssimazione introdotta dalla rappresentazione periodica diventa
	    //evidente se moltiplico questo numero per cento volte
	    System.out.print("100*periodicdouble: ");
	    System.out.println(100*periodicdouble);
	    //ancora peggio: se converto in formato int il numero double 100*4.35
	    //ottengo il numero intero 434 invece che 435!
	    int periodicint = (int) (100*periodicdouble);//ci vogliono le par.tonde
	    System.out.println("(int) (100*periodicdouble): " + periodicint);



	    ///////////////////// La mantissa va in overflow (la precisione decimale sarebbe troppa)
	    System.out.println(
	        "\n*********** operazioni tra double \"molto grandi\" **************");

	    double verylargedouble1 = 1e15; 
	    double verylargedouble2 = verylargedouble1 +1;
	    double result = verylargedouble2-verylargedouble1;
	    System.out.println("verylargedouble1: " + verylargedouble1);
	    System.out.println("verylargedouble2: " + verylargedouble2);
	    System.out.println("verylargedouble2-verylargedouble1: " + result);
	    //Provare con verylargedouble1 piu` grande di 1e16. In questo caso
	    //succede che result vale 0 invece che 1. Come e` possibile?
	    // 
	    // RISPOSTA: il tipo di dato double tiene in considerazione al massimo 15 cifre significative dopo la virgola,
	    // 			 dalla 16esima cifra, in un'operazione fra double, non se ne tiene conto



	    ///////////////////// L'esponente va in overflow (al suo aumentare, diminuisce la precisione del numero)
	    System.out.println(
	        "\n*********** overflow con double \"molto molto grandi\" **********");

	    double veryverylargedouble1 = 1e308;
	    double veryverylargedouble2 = veryverylargedouble1 * 2;
	    System.out.println("veryverylargedouble1: " + veryverylargedouble1);
	    System.out.println("veryverylargedouble * 2: " + veryverylargedouble2);

	    //Provare con veryverylargedouble1 = 1e308. Cosa succede e perche'?
	    // 
	    // RISPOSTA: niente perchè il tipo di dato double e' sufficiente per memorizzare
	    //  		 il numero 1e308
	    // Poi provare con veryverylargedouble1 = 2e308. Cosa succede e perche'?
	    //
	    // RISPOSTA: si ottiene "Infinity" perche si sta tentando di stampare un numero
	    // 		     troppo grande e che supera il valore massimo ammissibile dal tipo di dato double
    }
}