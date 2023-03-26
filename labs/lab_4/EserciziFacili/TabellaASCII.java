public class TabellaASCII
{
	public static void main(String[] args)
	{
		// costanti
		final int MAX_ASCII_VALUE = 128;

		// stampa tabella ASCII estesa
		/*for(char ch = 0; ch < MAX_ASCII_VALUE; ch++)
		{
			System.out.printf("Value: %h\t-->  %c%n", ch, ch);
		}*/

		for(char ch = 0; ch < MAX_ASCII_VALUE; ch++)
		{
			char k = ch;
			for(int i = 0; i < 3; i++)
			{
				System.out.printf("Value: %h\t-->\t%c       ", k, k);
				k += 32;
			}

			System.out.println("\n");
		}
	}
}