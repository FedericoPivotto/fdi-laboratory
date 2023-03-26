public class QuadraticEquation
{
	/* VARIABILI DI ESEMPLARE*/
	// eventualmente final per rendere l'oggetto immutabile
	private final double a; // primo coefficiente
	private final double b; // secondo coefficiente
	private final double c; // terzo coefficiente

	

	/* COSTRUTTORI */
	public QuadraticEquation(double a, double b, double c)
	{
		this.a = a;
		this.b = b;
		this.c = c;
	}


	/* METODI PRINCIPALI */
	/* Calcola le soluzioni dell'equazione */
	public String calculateSolution()
	{
		// casi particolari
		if(this.equalDouble(a, 0) && this.equalDouble(this.b, 0)) // nessuna soluzione
		{
			if(this.equalDouble(this.c, 0)) // infinite soluzioni
			{
				return "L'equazione ha infinite soluzioni";
			}
			else
			{
				return "L'equazione non ha soluzione";
			}
		}
		else if(this.equalDouble(this.a, 0) && !this.equalDouble(this.b, 0)) // una soluzione
		{
			double x = (-1) * (this.c / this.b);

			return "x = " + x;
		}
		else
		{
			// equazione di secondo grado
			double delta = Math.pow(this.b, 2) - (4 * this.a * this.c);
			if(delta > 0) // due soluzioni
			{
				double x1 = ((-1)*this.b + Math.sqrt(delta)) / (2 * this.a);
				double x2 = ((-1)*this.b - Math.sqrt(delta)) / (2 * this.a);

				return "x1 = " + x1 + "\nx2 = " + x2;
			}
			else if(delta == 0) // una soluzione
			{
				double x = ((-1)*this.b) * (2 * this.a);

				return "x = " + x;
			}
			else // nessuna soluzione
			{
				return "L'equazione non ha soluzione";
			}
		}
	}

	/* Restituisce l'equazione in stringa */
	public String toString()
	{
		return this.a + "x\u00B2 + " + this.b + "x + " + this.c + " = 0";
	}

	/* Verifica con tolleranza se due dobule sono uguali */
	private boolean equalDouble(double n1, double n2)
    {
        // double ha 15 cifre significative
        final double EPSILON = 10e-14;

        // distanza tra i due reali <= E * massimo tra il valore assoluto dei due reali
        return Math.abs(n1 - n2) <= EPSILON * Math.max(Math.abs(n1), Math.abs(n2));
    }



	/* METODI DI ACCESSO IN LETTURA */
	/* Restituisce a*/
	public double getA()
	{
		return this.a;
	}

	/* Restituisce b*/
	public double getB()
	{
		return this.b;
	}

	/* Restituisce c*/
	public double getC()
	{
		return this.c;
	}
}