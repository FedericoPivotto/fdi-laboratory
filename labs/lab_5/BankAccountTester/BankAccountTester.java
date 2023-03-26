import java.util.Scanner;

/* Classe BankAccountTester */
public class BankAccountTester
{
	public static void main(String[] args)
	{
		// costanti
		final String QUIT 			= "Q";
		final String BALANCE 		= "B";
		final String DEPOSIT_X 		= "D";
		final String WITHDRAW_X 	= "W";
		final String ADD_INTEREST_X = "A";

		// variabili
		Scanner console = new Scanner(System.in);
		BankAccount bill = new BankAccount();
		String cmd = "";

		// stampa menu operazioni di BankAccount
		System.out.println("\n******************** BankAccount ********************");
		System.out.println("Q   (Quit): termina il programma");
		System.out.println("B   (Balance): visualizza il saldo del conto");
		System.out.println("D x (Deposit): versa nel conto la somma x");
		System.out.println("W x (Withdraw): preleva dal conto la somma x");
		System.out.println("A x (Add interest): accredita sul conto gli interessi, calcolati in base alla percentuale x del saldo attuale (x deve essere maggiore di zero)\n");

		// lettura comandi
		// N.B: parseDouble() trimma gli spazi
		do
		{
			System.out.print("\nOperazione: ");
			cmd = console.nextLine();

			if(cmd.equals(QUIT))
			{
				break;
			}
			else if(cmd.equals(BALANCE))
			{
				System.out.printf("Saldo: %.2f \u20AC%n", bill.getBalance());
			} 
			else if(cmd.substring(0, 1).equals(DEPOSIT_X))
			{
				try
				{
					double num = Double.parseDouble(cmd.substring(1));
					if(!bill.deposit(num))
					{
						System.out.println("Il comando inserito \u00E8 errato, riprovare.");
					}
				}
				catch(NumberFormatException e)
				{
					System.out.println("Il comando inserito \u00E8 errato, riprovare.");
				}
			} 
			else if(cmd.substring(0, 1).equals(WITHDRAW_X))
			{
				try
				{
					double num = Double.parseDouble(cmd.substring(1));
					if(!bill.withdraw(num))
					{
						System.out.println("Il comando inserito \u00E8 errato, riprovare.");
					}
				}
				catch(NumberFormatException e)
				{
					System.out.println("Il comando inserito \u00E8 errato, riprovare.");
				}
			} 
			else if(cmd.substring(0, 1).equals(ADD_INTEREST_X))
			{
				try
				{
					double num = Double.parseDouble(cmd.substring(1));
					if(!bill.addInterest(num))
					{
						System.out.println("Il comando inserito \u00E8 errato, riprovare.");
					}
				}
				catch(NumberFormatException e)
				{
					System.out.println("Il comando inserito \u00E8 errato, riprovare.");
				}
			}
			else
			{
				System.out.println("Il comando inserito \u00E8 errato, riprovare.");
			}
		} while(true);
	}
}



/* Classe BankAccount */
class BankAccount
{  
	public double getBalance()
	{
		return balance;
	}

	public boolean deposit(double amount)
	{
		if(amount > 0)
		{
			balance += amount;
			return true;
		}
		return false;
	}

	public boolean withdraw(double amount)
	{
		if(amount > 0 && amount <= getBalance())
	  	{  
	  		balance -= amount;
			return true;
	  	}
	  	return false;
	}

	public boolean addInterest(double percentage)
	{
		if((percentage >= 0) && (percentage <= 100))
		{
			double interest = getBalance() * percentage;
			balance += interest;
			return true;
		}
		return false;
	}

	private double balance;
}