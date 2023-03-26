/*
    Un multiinsieme è un contenitore di elementi in ordine qualunque e di molteplicita' qualunque.

    Ogni elemento e' caratterizzato dalla sua molteplicità all'interno del contenitore, con il seguente comportamento:
        - il valore null non fa parte di un mulltiinsieme
        - l'inserimento avviene in una qualsiasi posizione del contenitore, incrementando di un'unita' la molteplicita' del dato inserito
        - l'estrazione di un dato dal multiinsieme avviene eliminando una qualunque (e una sola) occorrenza del dato indicato, cioe' decrementando di uno la sua molteplicità

    Un multiinsieme è quindi un insieme che contiene coppie del tipo (dato, suaMolteplicità)
*/

// NON MODIFICABILE
public class Pivotto2008810 // MODIFICARE CognomeMatricola, ma NON il contenuto
{
    public static void main(String[] args)
    {
        MultiSet s = new MyMultiSet();

        String s1 = "ok";
        String s2 = "no";
        
        s.add("ok");
        s.add("ok");
        s.add("ok");
        s.add("no");
        s.add("no");
        s.remove("ok");
        s.remove("no");

        System.out.println("molt(ok): " + s.molt(s1)); // deve visualizzare 2
        System.out.println("molt(no): " + s.molt(s2)); // deve visualizzare 1
        System.out.println("\nmolt(ok) + molt(no): " + (s.molt(s1) + s.molt(s2)));  // deve visualizzare 3
    }
}

// INTERFACCIA NON MODIFICABILE
interface Container // comportamento definito a lezione
{
    void makeEmpty();
    boolean isEmpty();
}

// INTERFACCIA NON MODIFICABILE
interface MultiSet extends Container
{
    //aggiunge x in una posizione qualunque di this; richiede x!= null;
    void add(Object x);
    //viene eliminata una qualunque (e una sola) occorrenza di x; richiede x!= null;
    void remove(Object x) throws IllegalStateException;
    //restituisce la molteplicità di x, cioe' il numero di elementi E di this per i //quali x.equals(E) vale true; richiede x!= null;
    int molt(Object x);
}

// INTERFACCIA NON MODIFICABILE
interface Set extends Container // comportamento definito a lezione
{
    void add(Object x);
    boolean contains(Object x);
    Object[] toArray();
}

// CLASSE DA COMPLETARE
class MySet implements Set
{
    private Object[] v;
    private int      vSize;

    public MySet()
    {
        this.v = new Object[10];
        this.makeEmpty();
    }

    public void makeEmpty()
    {
        this.vSize = 0;
    }

    public boolean isEmpty()
    {
        return (this.vSize == 0);
    }

    public void add(Object x)
    {
        if(this.contains(x))
            return;

        if(this.vSize == this.v.length)
            this.v = resize(v, 2*this.v.length);

        this.v[this.vSize++] = x;
    }

    public boolean contains(Object x)
    {
        for(int i = 0; i < this.vSize; i++)
        {
            if(this.v[i].equals(x))
            {
                return true; 
            }
        }

        return false;
    }

    public Object[] toArray()
    {
        Object[] newV = new Object[this.vSize];
        System.arraycopy(this.v, 0, newV, 0, this.vSize);

        return newV;
    }

    private Object[] resize(Object[] oldV, int newLength)
    {
        // pre-condizione
        if(newLength < 0 || oldV == null)
            throw new IllegalStateException();

        Object[] newV = new Object[newLength];
        System.arraycopy(oldV, 0, newV, 0, newV.length);

        return newV;
    }
}

// CLASSE DA COMPLETARE
class MyMultiSet implements MultiSet
{
    private MySet s; // non ci possono essere altre variabili di esemplare
                     // questa classe non può avere variabili statiche;

    public MyMultiSet()
    {
        this.makeEmpty();
    }

    public void makeEmpty()
    {
        this.s = new MySet();
    }

    public boolean isEmpty()
    {
        return this.s.isEmpty();
    }

    //aggiunge x in una posizione qualunque di this; richiede x!= null;
    public void add(Object x)
    {
        if(x == null)
            throw new IllegalStateException();

        if(this.s.contains(x))
        {
            Object[] vSet = this.s.toArray();
            for(int i = 0; i < vSet.length; i++)
            {
                Coppia tmp = (Coppia) vSet[i];
                if(x.equals(tmp.obj))
                {
                    tmp.i++;
                    return;
                }    
            }
        }
        
        Coppia couple = new Coppia(x, 1);
        this.s.add(couple);
    }

    //viene eliminata una qualunque (e una sola) occorrenza di x; richiede x!= null;
    public void remove(Object x) throws IllegalStateException
    {
        if(x == null)
            throw new IllegalStateException();

        if(this.s.contains(x))
        {
            Object[] vSet = this.s.toArray();
            for(int i = 0; i < vSet.length; i++)
            {
                Coppia tmp = (Coppia) vSet[i];
                if(x.equals(tmp.obj))
                {
                    tmp.i--;
                    if(tmp.i == 0)
                    {
                        vSet[i] = null;
                    }
                    return;
                }    
            }
        }
    }

    //restituisce la molteplicità di x, cioe' il numero di elementi E di this per i
    //quali x.equals(E) vale true; richiede x!= null;
    public int molt(Object x)
    {
        if(x == null)
            throw new IllegalStateException();

        int count = 0;
        Object vSet[] = this.s.toArray();
        for(int i = 0; i < vSet.length; i++)
        {
            Coppia tmp = (Coppia) vSet[i];
            if(x.equals(tmp.obj))
            {
                count += tmp.i;
            }
        }

        return count;
    }

    // CLASSE NON MODIFICABILE
    private class Coppia // classe interna a MyMultiSet
    {
        // rappresenta una coppia (Object,int)
        Object obj;
        int    i;

        Coppia(Object o, int m)
        {
            this.obj = o;
            this.i   = m;
        }
    
        // METODO DA COMPLETARE
        public boolean equals(Object z) // e' necessario sovrascrivere equals
        {
            if(this.obj.equals(z))
                return true;

            return false;
        }
    }    
}