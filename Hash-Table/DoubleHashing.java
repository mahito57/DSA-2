package Hash_table;

class HashEntry
{
    String key;
    int value;

    /* Constructor */
    HashEntry(String key, int value)
    {
        this.key = key;
        this.value = value;
    }
}

/* Class HashTable */
class HashTableDouble
{
    private int TABLE_SIZE;
    private int size;
    private HashEntry[] table;
    private int primeSize;
    private double num_of_probes;

    /* Constructor */
    public HashTableDouble(int ts)
    {
        size = 0;
        TABLE_SIZE = ts;
        table = new HashEntry[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++)
            table[i] = null;
        primeSize = getPrime();
        num_of_probes=0;
    }
    /* Function to get prime number less than table size for myhash2 function */
    public int getPrime()
    {
        for (int i = TABLE_SIZE - 1; i >= 1; i--)
        {
            int fact = 0;
            for (int j = 2; j <= (int) Math.sqrt(i); j++)
                if (i % j == 0)
                    fact++;
            if (fact == 0)
                return i;
        }
        /* Return a prime number */
        return 3;
    }
    /* Function to get number of key-value pairs */
    public int getSize()
    {
        return size;
    }
    public boolean isEmpty()
    {
        return size == 0;
    }
    /* Function to clear hash table */
    public void makeEmpty()
    {
        size = 0;
        for (int i = 0; i < TABLE_SIZE; i++)
            table[i] = null;
    }
    /* Function to get value of a key */
    public int get(String key)
    {
        int hash1 = myhash1( key );
        int hash2 = myhash2( key );
        int count=0;
        num_of_probes++;

        while (table[hash1] != null && !table[hash1].key.equals(key) && count<TABLE_SIZE)
        {
            hash1 += hash2;
            hash1 %= TABLE_SIZE;
            num_of_probes++;
            count++;
        }
        if(table[hash1]==null) return -1;
        return table[hash1].value;
    }
    /* Function to insert a key value pair */
    public void insert(String key, int value)
    {
        if (size == TABLE_SIZE)
        {
            System.out.println("Table full");
            return;
        }
        if((double) size/(double) TABLE_SIZE>0.5){
            TABLE_SIZE=TABLE_SIZE*2;
            primeSize=getPrime();
            HashEntry[] temp=table;
            table=new HashEntry[TABLE_SIZE];
            for(int j=0;j<temp.length;j++){
                if(temp[j]!=null){
                    insert(temp[j].key,temp[j].value);
                }
            }
        }

        int hash1 = myhash1( key );
        int hash2 = myhash2( key );
        int count=0;
        while (table[hash1] != null && count<TABLE_SIZE)
        {
            hash1 += hash2;
            hash1 %= TABLE_SIZE;
            count++;
        }
        if(count==TABLE_SIZE) return;
        table[hash1] = new HashEntry(key, value);
        size++;
    }
    /* Function to remove a key */
    public void remove(String key)
    {
        int hash1 = myhash1( key );
        int hash2 = myhash2( key );
        int count=0;
        while (table[hash1] != null && !table[hash1].key.equals(key) && count<TABLE_SIZE)
        {
            hash1 += hash2;
            hash1 %= TABLE_SIZE;
            count++;
        }
        table[hash1] = null;
        size--;
        for(hash1=(hash1+hash2)%TABLE_SIZE;table[hash1]!=null;hash1=(hash1+hash2)%TABLE_SIZE){
            String tmp1=table[hash1].key;
            int tmp2=table[hash1].value;
            table[hash1]=null;
            size--;
            insert(tmp1,tmp2);
        }
    }
    /* Function myhash which gives a hash value for a given string */
    private int myhash1(String x )
    {
        int hashVal = x.hashCode();
        hashVal %= TABLE_SIZE;
        if (hashVal < 0)
            hashVal += TABLE_SIZE;
        return hashVal;
    }
    /* Function myhash function for double hashing */
    private int myhash2(String x )
    {
        int hashVal = x.hashCode( );
        hashVal %= TABLE_SIZE;
        if (hashVal < 0)
            hashVal += TABLE_SIZE;
        return primeSize - hashVal % primeSize;
    }
    /* Function to print hash table */
    public void printHashTable()
    {
        System.out.println("\nHash Table");
        for (int i = 0; i < TABLE_SIZE; i++)
            if (table[i] != null)
                System.out.println(table[i].key +" "+table[i].value);
    }

    public double getNum_of_probes() {
        return num_of_probes;
    }

    public void clearNum_of_probes() {
        this.num_of_probes = 0;
    }
}

public class DoubleHashing {
}
