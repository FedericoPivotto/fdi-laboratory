public class Stringa
{
	// fatto
	private char[] array;

	// fatto
	public Stringa(String str)
	{
		if(str == null)
		{
			throw new IllegalArgumentException();
		}

		this.array = str.toCharArray();
	}

	// costruttore aggiunto
	public Stringa(char[] array)
	{
		if(array == null)
		{
			throw new IllegalArgumentException();
		}

		this.array = array;
	}

	// fatto
	public char charAt(int index)
	{
		if(index < 0 || index >= this.length())
		{
			throw new IllegalArgumentException();
		}

		return this.array[index];
	}

	// fatto
	public int compareTo(Stringa s)
	{
		if(s == null)
		{
			throw new IllegalArgumentException();
		}

		int min = Math.min(this.length(), s.length());

		for(int i = 0; i < min; i++)
		{
			if(this.charAt(i) < s.charAt(i))
			{
				return -1;
			}
			else if(this.charAt(i) > s.charAt(i))
			{
				return 1;
			}
		}

		if(this.length() < s.length())
		{
			return -1;
		}
		else if(this.length() > s.length())
		{
			return 1;
		}

		return 0;
	}
	
	// fatto
	public Stringa concat(Stringa s)
	{
		if(s == null)
		{
			throw new IllegalArgumentException();
		}

		char[] newArray = new char[this.length() + s.length()];

		// copia primo array
		for (int i = 0; i < this.length(); i++)
		{
			newArray[i] = this.charAt(i);
		}

		// copia secondo array
		int k = 0;
		for (int i = this.length(); i < newArray.length; i++, k++)
		{
			newArray[i] = s.charAt(k);
		}

		return new Stringa(newArray);
	}
	
	// fatto
	public boolean endsWith(Stringa s)
	{
		if(s == null)
		{
			throw new IllegalArgumentException();
		}

		if(s.length() <= this.length())
		{
			Stringa end = substring(this.length() - s.length());
			if(end.compareTo(s) == 0)
			{
				return true;
			}
		}

		return false;
	}
	
	// fatto
	public int indexOf(Stringa s)
	{
		if(s == null)
		{
			throw new IllegalArgumentException();
		}

		if(s.length() > this.length())
		{
			return -1;
		}

		for(int i = 0; (i < this.length()) && ((i + s.length()) < this.length()) ; i++)
		{
			if(this.substring(i, i + s.length()).compareTo(s) == 0)
			{
				return i;
			}
		}

		return -1;
	}
	
	// fatto
	public int indexOf(Stringa s, int fromIndex)
	{
		if(s == null)
		{
			throw new IllegalArgumentException();
		}

		if(fromIndex < 0 || fromIndex > this.length())
		{
			throw new IllegalArgumentException();
		}

		if(s.length() > this.length())
		{
			return -1;
		}

		for(int i = fromIndex; (i < this.length()) && ((i + s.length()) < this.length()) ; i++)
		{
			if(this.substring(i, i + (s.length())).compareTo(s) == 0)
			{
				return i;
			}
		}

		return -1;
	}
	
	// fatto
	public int lastIndexOf(Stringa s)
	{
		if(s == null)
		{
			throw new IllegalArgumentException();
		}

		if(s.length() > this.length())
		{
			return -1;
		}

		int index = -1;
		for(int i = 0; (i < this.length()) && ((i + s.length()) < this.length()) ; i++)
		{
			if(this.substring(i, i + s.length()).compareTo(s) == 0)
			{
				index = i;
			}
		}

		return index;
	}
	
	// fatto
	public int lastIndexOf(Stringa s, int fromIndex)
	{
		if(s == null)
		{
			throw new IllegalArgumentException();
		}

		if(fromIndex < 0 || fromIndex > this.length())
		{
			throw new IllegalArgumentException();
		}

		if(s.length() > this.length())
		{
			return -1;
		}

		int index = -1;
		for(int i = fromIndex; (i < this.length()) && ((i + s.length()) < this.length()) ; i++)
		{
			if(this.substring(i, i + s.length()).compareTo(s) == 0)
			{
				index = i;
			}
		}

		return index;
	}
	
	// fatto
	public int length()
	{
		return this.array.length;
	}
	
	// fatto
	public Stringa substring(int beginIndex)
	{
		if(beginIndex < 0 || beginIndex > this.length())
		{
			throw new IllegalArgumentException();
		}

		char[] newArray = new char[this.length() - beginIndex];
		int k = 0;
		for(int i = beginIndex; i < this.length(); i++, k++)
		{
			newArray[k] = this.charAt(i);
		}

		return new Stringa(newArray);
	}
	
	// fatto
	public Stringa substring(int beginIndex, int endIndex)
	{
		if(beginIndex < 0 || beginIndex > this.length())
		{
			throw new IllegalArgumentException();
		}

		if(endIndex < 0 || endIndex > this.length())
		{
			throw new IllegalArgumentException();
		}

		if(beginIndex > endIndex)
		{
			throw new IllegalArgumentException();
		}

		char[] newArray = new char[endIndex - beginIndex];
		int k = 0;
		for(int i = beginIndex; i < endIndex; i++, k++)
		{
			newArray[k] = this.charAt(i);
		}

		return new Stringa(newArray);
	}
}