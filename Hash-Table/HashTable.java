package Hash_table;

import java.util.*;


public class HashTable {
    public static String[] generateRandomWords(int numberOfWords)
    {
        String[] randomStrings = new String[numberOfWords];
        Random random = new Random();
        for(int i = 0; i < numberOfWords; i++)
        {
            char[] word = new char[7]; // words of length 7
            for(int j = 0; j < word.length; j++)
            {
                word[j] = (char)('a' + random.nextInt(26));
            }
            for(int k=0;k<randomStrings.length;k++){
                if(word.equals(randomStrings[i])){
                    continue;
                }
            }
            randomStrings[i] = new String(word);
        }
        return randomStrings;
    }

    public static void uniquehash(String[] st){
        Set<Integer> hashset=new HashSet<>();
        int n=st.length;

        HashTableChain ct=new HashTableChain(n);

        for(int i=0;i<st.length;i++){
            int x=ct.myhash(st[i]);
            hashset.add(x);
        }
        int res=n-hashset.size();
        System.out.println("non-unique values "+res);
        hashset.clear();
    }

    private static void chainingTimeCounting(int len,String[] st,int loadfactor){
        HashTableChain ct=new HashTableChain(len);
        Random rand=new Random();
        ArrayList<String> testwords=new ArrayList<>();
        ArrayList<String> deletedwords=new ArrayList<>();

        //Inserting
        for(int i=0;i<loadfactor;i++){
            int var=rand.nextInt(100);
            ct.insert(st[var],var);
            testwords.add(st[var]);
        }

        int num_of_test=loadfactor/10;
        long totaltime=0;
        //Searching
        for(int i=0;i<num_of_test;i++){
            int var=rand.nextInt(testwords.size());
            String key=testwords.get(var);
            ct.get(key);
            long starttime=System.nanoTime();
            ct.get(key);
            long endtime=System.nanoTime();
            totaltime=totaltime+(endtime-starttime);
        }
        long avgtime1=totaltime/num_of_test;

        totaltime=0;

        for(int i=0;i<num_of_test;i++){                      //Deleting
            int var=rand.nextInt(testwords.size());
            String key=testwords.get(var);
            ct.remove(key);
            testwords.remove(key);
            deletedwords.add(key);
        }

        //Searching after deletion
        int half=num_of_test/2;
        int rest_half=num_of_test-half;
        for(int i=0;i<half;i++){
            int var=rand.nextInt(testwords.size());
            String key=testwords.get(var);
            long starttime=System.nanoTime();
            ct.get(key);
            long endtime=System.nanoTime();
            totaltime=totaltime+(endtime-starttime);
        }

        //searching deleting words
        for(int i=0;i<rest_half;i++){
            int var=rand.nextInt(deletedwords.size());
            String key=deletedwords.get(var);
            long starttime=System.nanoTime();
            ct.get(key);
            long endtime=System.nanoTime();
            totaltime=totaltime+(endtime-starttime);
        }
        long avgtime2=totaltime/num_of_test;
        System.out.println(loadfactor+"                 "+avgtime1+"                                 "+avgtime2);
    }

    private static void LinearTimeCounting(int len, String[] st, int loadfactor) {
        LinearProbingHashTable ct=new LinearProbingHashTable(len);
        Random rand=new Random();
        ArrayList<String> testwords=new ArrayList<>();
        ArrayList<String> deletedwords=new ArrayList<>();

        //Inserting
        for(int i=0;i<loadfactor;i++){
            int var=rand.nextInt(100);
            ct.insert(st[var], String.valueOf(var));
            testwords.add(st[var]);
        }

        int num_of_test=loadfactor/10;
        long totaltime=0;
        //Searching
        for(int i=0;i<num_of_test;i++){
            int var=rand.nextInt(testwords.size());
            String key=testwords.get(var);
            ct.get(key);
            long starttime=System.nanoTime();
            ct.get(key);
            long endtime=System.nanoTime();
            totaltime=totaltime+(endtime-starttime);
        }
        long avgtime1=totaltime/num_of_test;
        double avg_num_of_probes1=ct.getNum_of_probes();

        totaltime=0;
        ct.clearNum_of_probes();

        for(int i=0;i<num_of_test;i++){                      //Deleting
            int var=rand.nextInt(testwords.size());
            String key=testwords.get(var);
            ct.remove(key);
            testwords.remove(key);
            deletedwords.add(key);
        }

        //Searching after deletion
        int half=num_of_test/2;
        int rest_half=num_of_test-half;
        for(int i=0;i<half;i++){
            int var=rand.nextInt(testwords.size());
            String key=testwords.get(var);
            long starttime=System.nanoTime();
            ct.get(key);
            long endtime=System.nanoTime();
            totaltime=totaltime+(endtime-starttime);
        }

        //searching deleting words
        for(int i=0;i<rest_half;i++){
            int var=rand.nextInt(deletedwords.size());
            String key=deletedwords.get(var);
            long starttime=System.nanoTime();
            ct.get(key);
            long endtime=System.nanoTime();
            totaltime=totaltime+(endtime-starttime);
        }
        long avgtime2=totaltime/num_of_test;
        double avg_num_of_probes2=ct.getNum_of_probes()/num_of_test;
        System.out.println(loadfactor+"                 "+avgtime1+"                "+String.format("%.3f",avg_num_of_probes1)+
                "                "+avgtime2+"                "+String.format("%.3f",avg_num_of_probes2));
    }

    private static void QuadraticTimeCounting(int len, String[] st, int loadfactor) {
        QuadraticProbingHashTable ct=new QuadraticProbingHashTable(len);
        Random rand=new Random();
        ArrayList<String> testwords=new ArrayList<>();
        ArrayList<String> deletedwords=new ArrayList<>();

        //Inserting
        for(int i=0;i<loadfactor;i++){
            int var=rand.nextInt(100);
            ct.insert(st[var], String.valueOf(var));
            testwords.add(st[var]);
        }

        int num_of_test=loadfactor/10;
        long totaltime=0;
        //Searching
        for(int i=0;i<num_of_test;i++){
            int var=rand.nextInt(testwords.size());
            String key=testwords.get(var);
            ct.get(key);
            long starttime=System.nanoTime();
            ct.get(key);
            long endtime=System.nanoTime();
            totaltime=totaltime+(endtime-starttime);
        }
        long avgtime1=totaltime/num_of_test;
        double avg_num_of_probes1=ct.getNum_of_probes()/num_of_test;


        totaltime=0;
        ct.clearNum_of_probes();

        for(int i=0;i<num_of_test;i++){                      //Deleting
            int var=rand.nextInt(testwords.size());
            String key=testwords.get(var);
            ct.remove(key);
            testwords.remove(key);
            deletedwords.add(key);
        }

        //Searching after deletion
        int half=num_of_test/2;
        int rest_half=num_of_test-half;
        for(int i=0;i<half;i++){
            int var=rand.nextInt(testwords.size());
            String key=testwords.get(var);
            long starttime=System.nanoTime();
            ct.get(key);
            long endtime=System.nanoTime();
            totaltime=totaltime+(endtime-starttime);
        }

        //searching deleting words
        for(int i=0;i<rest_half;i++){
            int var=rand.nextInt(deletedwords.size());
            String key=deletedwords.get(var);
            long starttime=System.nanoTime();
            ct.get(key);
            long endtime=System.nanoTime();
            totaltime=totaltime+(endtime-starttime);
        }
        long avgtime2=totaltime/num_of_test;
        double avg_num_of_probes2=ct.getNum_of_probes()/num_of_test;
        System.out.println(loadfactor+"                 "+avgtime1+"                "+String.format("%.3f",avg_num_of_probes1)+
                "                "+avgtime2+"                "+String.format("%.3f",avg_num_of_probes2));
    }

    private static void DoubleHashingTimeCounting(int len, String[] st, int loadfactor) {
        HashTableDouble ct=new HashTableDouble(len);
        Random rand=new Random();
        ArrayList<String> testwords=new ArrayList<>();
        ArrayList<String> deletedwords=new ArrayList<>();

        //Inserting
        for(int i=0;i<loadfactor;i++){
            int var=rand.nextInt(100);
            ct.insert(st[var], var);
            testwords.add(st[var]);
        }

        int num_of_test=loadfactor/10;
        long totaltime=0;
        //Searching
        for(int i=0;i<num_of_test;i++){
            int var=rand.nextInt(testwords.size());
            String key=testwords.get(var);
            long starttime=System.nanoTime();
            ct.get(key);
            long endtime=System.nanoTime();
            totaltime=totaltime+(endtime-starttime);
        }
        double avg_num_of_probes1=ct.getNum_of_probes()/num_of_test;
        long avgtime1=totaltime/num_of_test;

        totaltime=0;
        ct.clearNum_of_probes();

        for(int i=0;i<num_of_test;i++){                      //Deleting
            int var=rand.nextInt(testwords.size());
            String key=testwords.get(var);
            ct.remove(key);
            testwords.remove(key);
            deletedwords.add(key);
        }

        //Searching after deletion
        int half=num_of_test/2;
        int rest_half=num_of_test-half;
        for(int i=0;i<half;i++){
            int var=rand.nextInt(testwords.size());
            String key=testwords.get(var);
            long starttime=System.nanoTime();
            ct.get(key);
            long endtime=System.nanoTime();
            totaltime=totaltime+(endtime-starttime);
        }

        //searching deleting words
        for(int i=0;i<rest_half;i++){
            int var=rand.nextInt(deletedwords.size());
            String key=deletedwords.get(var);
            long starttime=System.nanoTime();
            ct.get(key);
            long endtime=System.nanoTime();
            totaltime=totaltime+(endtime-starttime);
        }
        long avgtime2=totaltime/num_of_test;
        double avg_num_of_probes2=ct.getNum_of_probes()/num_of_test;
        System.out.println(loadfactor+"                 "+avgtime1+"                "+String.format("%.3f",avg_num_of_probes1)+
                "                "+avgtime2+"                "+String.format("%.3f",avg_num_of_probes2));
    }

    public static void main(String[] args) {
        int len=100;
        String[] st=generateRandomWords(len);
        uniquehash(st);

        /**Chaining time calculation**/
        System.out.println("Chaining\n"+"Load factor     Search Time                    After Deletion,Time");
        int loadfactor=40;
        for(int i=0;i<6;i++){
            chainingTimeCounting(len,st,loadfactor);
            loadfactor+=10;
        }

        /**Linear time calculation*/
        System.out.println();
        System.out.println("Linear Probing\n"+"Load factor      Search Time      Avg Probes     After Deletion,Time          Probes");
        loadfactor=40;
        for(int i=0;i<6;i++){
            LinearTimeCounting(len,st,loadfactor);
            loadfactor+=10;
        }

        /**Quadratic time calculation*/
        System.out.println();
        System.out.println("Quadratic Probing\n"+"Load factor      Search Time      Avg Probes     After Deletion,Time          Probes");
        loadfactor=40;
        for(int i=0;i<6;i++){
            QuadraticTimeCounting(len,st,loadfactor);
            loadfactor+=10;
        }

        /**DoubleHashing time calculation*/
        System.out.println();
        System.out.println("Double Hashing\n"+"Load factor      Search Time      Avg Probes     After Deletion,Time          Probes");
        loadfactor=40;
        for(int i=0;i<6;i++){
            DoubleHashingTimeCounting(len,st,loadfactor);
            loadfactor+=10;
        }

        loadfactor=40;
        System.out.println();
        System.out.println("Load factor      Search Time      Avg Probes     After Deletion,Time          Probes");
        System.out.println("Separate chaining");
        chainingTimeCounting(len,st,loadfactor);
        System.out.println("Linear Probing");
        LinearTimeCounting(len,st,loadfactor);
        System.out.println("Quadratic Probing");
        QuadraticTimeCounting(len,st,loadfactor);
        System.out.println("Double Hashing");
        DoubleHashingTimeCounting(len,st,loadfactor);
    }
}
