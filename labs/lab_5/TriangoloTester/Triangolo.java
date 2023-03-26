public class Triangolo
{
	/*
		Construttore della classe Triangolo
	*/
	public Triangolo (double la, double lb, double lc)
	{
		// (la >= (lb + lc) || lb >= (la + lc) || lc >= (la + lb)) // e' sufficiente uno solo di questi controlli
		if(la >= (lb + lc)) 
		{
			throw new IllegalArgumentException();
		}

		// assegno i lati in ordine crescente
		double minABC = Math.min(la, Math.min(lb, lc));
		if(this.equalDouble(la, minABC))
		{
			this.la = la;
			if(this.equalDouble(lb, Math.min(lb, lc)))
			{
				this.lb = lb;
				this.lc = lc;
			}
			else
			{
				this.lb = lc;
				this.lc = lb;
			}
		}
		else if(this.equalDouble(lb, minABC))
		{
			this.la = lb;
			if(this.equalDouble(la, Math.min(la, lc)))
			{
				this.lb = la;
				this.lc = lc;
			}
			else
			{
				this.lb = lc;
				this.lc = la;
			}
		}
		else
		{
			this.la = lc;
			if(this.equalDouble(la, Math.min(la, lb)))
			{
				this.lb = la;
				this.lc = lb;
			}
			else
			{
				this.lb = lb;
				this.lc = la;
			}
		}
	}

	/*
		restituisce informazioni sul triangolo. le informazioni sono relative
		ai lati:     equilatero,  isoscele,    scaleno.
		agli angoli: acutangolo,  rettangolo,  ottusangolo.
		Esempio: per il triangolo di lati 3, 4, 5 restituisce la stringa
		"scaleno rettangolo".
	*/
	public String info()
	{
		return "Triangolo " + this.latiTriangolo() + " " + this.angoliTriangolo();
	}

	/*
		resituisce il tipo triangolo per lati
	*/
	private String latiTriangolo()
	{
		if((this.equalDouble(this.la, this.lb)) || (this.equalDouble(this.lb, this.lc)) || (this.equalDouble(this.lc, this.la)))
		{
			if((this.equalDouble(this.la, this.lb)) && (this.equalDouble(this.lb, this.lc)))
			{
				return "Equilatero";
			}
			else
			{
				return "Isoscele";
			}
		}
		else
		{
			return "Scaleno";
		}
	}

	/*
		resituisce il tipo triangolo per angoli
		(perfeziona uguaglianza)
	*/
	private String angoliTriangolo()
	{
		double tester = Math.pow(this.lc, 2) - Math.pow(this.la, 2) - Math.pow(this.lb, 2);
		
		if(this.equalDouble(tester, 0))
		{
			return "Rettangolo";
		}
		else if(tester > 0)
		{
			return "Ottusangolo";
		}
		else // tester < 0
		{
			return "Acutangolo";
		}

		/*// METODO ALTERNATIVO (Teorema di Carnot)
		double a1 = Math.toDegrees(Math.acos((Math.pow(this.lb, 2) + Math.pow(this.lc, 2) - Math.pow(this.la, 2)) / (2 * lb * lc)));
		double a2 = Math.toDegrees(Math.acos((Math.pow(this.la, 2) + Math.pow(this.lc, 2) - Math.pow(this.lb, 2)) / (2 * la * lc)));
		double a3 = Math.toDegrees(Math.acos((Math.pow(this.la, 2) + Math.pow(this.lb, 2) - Math.pow(this.lc, 2)) / (2 * la * lb)));

		if((a1 == 90) || (a2 == 90) || (a3 == 90))
		{
			return "Rettangolo";
		}
		else if((a1 > 90) || (a2 > 90) || (a3 > 90))
		{
			return "Ottusangolo";
		}
		else
		{
			return "Acutangolo";
		}*/
	}

	/*
		restituisce una stringa contenente una descrizione testuale dell'oggetto
		nel formato T(a, b, c)
		Esempio "T(3, 4, 5)"
	*/
	public String toString ()
	{
		return "T(" + this.la + ", " + this.lb + ", " + this.lc + ")";
	}

	/* Verifica con tolleranza se due dobule sono uguali */
	private boolean equalDouble(double n1, double n2)
    {
        // double ha 15 cifre significative
        final double EPSILON = 10e-14;

        // distanza tra i due reali <= E * massimo tra il valore assoluto dei due reali
        return Math.abs(n1 - n2) <= EPSILON * Math.max(Math.abs(n1), Math.abs(n2));
    }

	/*
		calcola e restituisce l'area del triangolo.
		Usa la formula di Erone:
					area * area = p * (p - a) * (p - b) * (p - c)
		dove p e` il semiperimetro, ovvero p = (a + b + c) / 2
	*/
	public double area()
	{
		double p = this.semiPerimetro();
		return Math.sqrt(p * (p - this.la) * (p - this.lb) * (p - this.lc)); 
	}

	/*
		restituisce il semiperimetro
	*/
	private double semiPerimetro()
	{
		return (this.la + this.lb + this.lc) / 2;
	}

	/*
		calcola e restituisce l'altezze del triangolo relativa al lato maggiore:
	*/
	public double h()
	{
		double max = Math.max(this.la, Math.max(this.lb, this.lc));
		return this.area() * 2 / max;
	}



	/* campi di esemplare */
	private double la;
	private double lb;
	private double lc;
}