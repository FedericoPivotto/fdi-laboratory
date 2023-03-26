
// Se al momento di un prelievo il numero di mesi di deposito vincolato e` ancora positivo, allora bisogna addebitare la penale

public class TimeDepositAccount extends SavingsAccount
{  
    public TimeDepositAccount(double rate, int months)
    {
        super(rate);               // costruttore della superclasse
        monthsCount = months;
    }

    public TimeDepositAccount(double initialBalance, double rate, int months)
    {
        super(initialBalance, rate); // costruttore della superclasse
        monthsCount = months;
    }

    //accredita gli interessi al termine del mese. Attenzione: usa il metodo
    //deposit della superclasse, altrimenti verrebbe addebitata la penale FEE
    public void addInterest()     //NUOVO METODO
    {
        super.addInterest();
        if(monthsCount > 0)
        {
            monthsCount--;
        }
    }

    public void withdraw(double amount) //SOVRASCRITTO
    {
        super.withdraw(amount); // sottrai amount dal saldo
        this.deductPenal();
    }

    //applica la penale per lo sformaento mesi
    public void deductPenal()  //NUOVO METODO
    {
        if (monthsCount > 0)
        {
            super.withdraw(PENAL);
        }
    }

    public int getMonthsCount()
    {
        return this.monthsCount;
    }

    // ------ metodi di Object sovrascritti ---------
    // ........... toString, equals .................
    public String toString()
    {
        return "TimeDepositAccount[balance=" + this.getBalance() + ", interestRate=" + this.getInterestRate() + ", monthsCount=" + this.getMonthsCount() + "]";
    }

    public boolean equals(TimeDepositAccount obj)
    {
        return (super.equals(obj) && (this.getMonthsCount() == obj.getMonthsCount()));
    }


    //-------- nuovi campi di esemplare ----------------

    private int monthsCount;
    public final int PENAL = 20;
}