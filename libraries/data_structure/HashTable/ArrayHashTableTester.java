import java.util.*;

public class ArrayHashTableTester
{
	public static void main(String[] args)
	{
		// creazione hash table
		HashTable ht = new ArrayHashTable();

		// inserimenti a caso
		Random rand = new Random();
		for(int i = 0; i < 20; i++)
			ht.insert(rand.nextInt(100), rand.nextInt(10));
		System.out.println("STATUS 1\n" + ht);

		// inserimento scelto
		Integer key = new Integer(23);
		ht.insert(key, 99);
		System.out.println("\nSTATUS 2\n" + ht);

		// rimozione di una key a caso
		// l'esito è casuale a causa del random
		ht.remove(24);
		System.out.println("\nSTATUS 3\n" + ht);

		// ricerca della chiave
		Object found = ht.find(30);
		System.out.println("FOUND: " + found);

		// HASH TABLE
		// void insert(Object key, Object value);
		// void remove(Object key);
		// Object find(Object key);

		// NB: il lancio dell'eccezione a caso e' corretto
		//	   ed accade per colpa del riempimento random
	}
}