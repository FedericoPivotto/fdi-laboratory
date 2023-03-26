/*
	Classe dell'eccezione coda piena
*/
public class FullQueueException extends RuntimeException
{
	public String toString()
	{
		return "Exception detected: FullQueueException";
	}
}