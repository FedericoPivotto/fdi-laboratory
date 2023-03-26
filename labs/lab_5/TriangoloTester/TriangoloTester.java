import java.util.Scanner;
import java.util.Locale;

public class TriangoloTester
{
    public static void main (String[] args)
    {
		Scanner in = new Scanner(System.in);
		in.useLocale(Locale.US);

		// lettura dei dati da Standard Input
		System.out.print ("Introduci lati a, b, c: ");
		double a = in.nextDouble();
		double b = in.nextDouble();
		double c = in.nextDouble();

		// creazione di una istanza del triangolo
		Triangolo triangolo = null;
		try
		{
			triangolo = new Triangolo(a, b, c);
		}
		catch(IllegalArgumentException e)
		{
			System.out.println("\nI lati del trinagolo inseriti non formano un triangolo");

			// terminazione dolce
			System.exit(0);
		}
		
		// emissione a Standard Output dell'elaborazione
		System.out.println("\n" + triangolo +  ": " + triangolo.info());
		System.out.println("area = " + triangolo.area());
		System.out.println("h = " + triangolo.h());
	}
}