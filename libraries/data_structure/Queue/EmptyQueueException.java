/*
	Classe dell'eccezione coda vuota
*/
public class EmptyQueueException extends RuntimeException
{
	public String toString()
	{
		return "Exception detected: EmptyQueueException";
	}
}