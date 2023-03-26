public class RecursiveReverseString
{
	public static String reverse(String str)
	{
		// pre-condizione
		if(str == null)
		{
			throw new IllegalArgumentException();
		}

		if(str.length() == 0 || str.length() == 1)
		{
			return str;
		}

		return reverse(str.substring(1)) + str.charAt(0);
	}

	public static void main(String[] args)
	{
		if(args.length > 0)
		{
			System.out.println(reverse(args[0]));
		}
	}
}