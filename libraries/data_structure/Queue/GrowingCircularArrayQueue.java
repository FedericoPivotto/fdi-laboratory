public class GrowingCircularArrayQueue extends FixedCircularArrayQueue
{
	public void enqueue(Object obj)
	{
		if (increment(back) == front)
		{
			v = resize(v, 2*v.length);
			// se si ridimensiona l’array e la zona utile
			// della coda si trova attorno alla sua fine,
			// la seconda metà del nuovo array rimane vuota
			// e provoca un malfunzionamento della coda,
			// che si risolve spostandovi la parte della
			// coda che si trova all’inizio dell’array
			if (back < front)
			{
				System.arraycopy(v, 0, v, v.length/2, back);
				back += v.length/2;
			}
		}
		
		super.enqueue(obj);
	}

	/*
		Ridimensiona l'array oldv attribuendogli la lunghezza newLength
	*/
	protected static Object[] resize(Object[] oldV, int newLength)
	{
		// pre-condizione
		if (newLength < 0 || oldV == null)
			throw new IllegalArgumentException();

		Object[] newV = new Object[newLength];
		int count = oldV.length;

		if (newLength < count)
			count = newLength;
		
		System.arraycopy(oldV, 0, newV, 0, oldV.length);
		/*for (int i = 0; i < count; i++)
			newV[i] = oldV[i];*/

		return newV;
	}
}