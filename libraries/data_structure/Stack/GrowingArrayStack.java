/*
	Classe della pila con lunghezza fissa
*/
public class GrowingArrayStack extends FixedArrayStack
{
	/*
		Inserisce un oggetto in cima alla pila
		 - Se la pila è piena si procede prima con il resize
	*/
	public void push(Object obj)
	{
		if (vSize == v.length)
			v = resize(v, 2*vSize);
		super.push(obj);
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