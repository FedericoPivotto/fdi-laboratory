/*
	Classe dell'eccezione elemento non trovato nella hash table
*/
public class HashTableItemNotFoundException extends RuntimeException
{
	public String toString()
	{
		return "Exception detected: HashTableItemNotFoundException";
	}
}