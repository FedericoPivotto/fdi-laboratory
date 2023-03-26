public class ArraySet implements Set
{
	private Object[] v 	   = new Object[100];
	private int 	 vSize = 0;

	/*
		O(1)
	*/
	public void makeEmpty()
	{
		this.vSize = 0;
	}

	/*
		O(1)
	*/
	public boolean isEmpty()
	{
		return (this.vSize == 0);
	}

	/*
		O(n)
	*/
	public Object[] toArray()
	{
		Object[] vObj = new Object[this.vSize];
		System.arraycopy(this.v, 0, vObj, 0, this.vSize);

		return vObj;
	}

	/*
		O(n)
	*/
	public boolean contains(Object obj)
	{
		for (int i = 0; i < this.vSize; i++)
		{
			if(this.v[i].equals(obj))
			{
				return true;	
			}
		}

		return false;
	}

	/*
		O(n)
	*/
	public void add(Object obj)
	{
		if(this.contains(obj))
			return;

		if(this.vSize == this.v.length)
			this.v = resize(v, 2*this.v.length);
		
		this.v[this.vSize++] = obj;
	}

	/*
		O(n^2)
	*/
	public static Set union(Set s1, Set s2)
	{
		Set x = new ArraySet();
		// inseriamo gli elementi del primo insieme
		Object[] v = s1.toArray();
		for(int i = 0; i < v.length; i++)
			x.add(v[i]);
		// inseriamo tutti gli elementi del secondo
		// insieme, sfruttando le proprieta' di add
		// (niente duplicati)
		v = s2.toArray();
		for(int i = 0; i < v.length; i++)
			x.add(v[i]);

		return x;
	}

	/*
		O(n^2)
	*/
	public static Set intersection(Set s1, Set s2)
	{
		Set x = new ArraySet();
		Object[] v = s1.toArray();
		for(int i = 0; i < v.length; i++)
		{
			if(s2.contains(v[i]))
			{
				x.add(v[i]);
			}
		}

		return x;
	}

	/*
		O(n^2)
	*/
	public static Set subtract(Set s1, Set s2)
	{
		Set x = new ArraySet();
		Object[] v = s1.toArray();
		for(int i = 0; i < v.length; i++)
		{
			if(! s2.contains(v[i]))
			{
				x.add(v[i]);
			}
		}

		return x;
	}

	/*
		Ridimensiona l'array oldv attribuendogli la lunghezza newLength
	*/
	private static Object[] resize(Object[] oldV, int newLength)
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