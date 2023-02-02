import java.io.IOException;
import java.math.BigInteger;
import java.util.Set;
import java.util.TreeSet;

/**
 * A fairly basic implementation of a separate chanining hashtable
 * @param <K> the tpe of key
 * @param <V> the type of value
 * Implements full separate chaining, but not rehashing.
 * Similarly, the size of the underlying table, once it is created, cannot
 * be changed.
 * @author gtowell
 * Created: April 25, 2020
 * Modified: Sep 23, 2020 to use ArrayList
 * Modeifed: Mar 6, 2021 to use Map206
 * Modified: Sep 27, 2021 to use Map151Interface
 * @Modifed by Cecilia Zhang (added a main method)
 * Modified: Feb 28, 2022
 * Modified: Mar 1, 2022
 */
public class SepChainHT<K,V> implements Map151Interface<K,V> {

    
    /** The array holding the hashtable data.  Yes, this is an array
     * of Map151 objects!!
     */
    private Map151<K,V>[] backingArray;

    /** The default size of the backing array */
    private static int DEFAULT_CAPACITY = 1009;

    /** The number of items in the hashtable */
    private int count;

    /** Default initialization */
    public SepChainHT() {
        this(DEFAULT_CAPACITY);
    }
    /**
     * Initialize a hashtable of the given size
     * @param size the size of the hashtable to create
     */
    @SuppressWarnings("unchecked")
    public SepChainHT(int size) {
        // Cannot make an array object in which you mention a parameterized type.
        // So just make the generic array.  This is a narrowing cast so it does not 
        // even need to be explicit.
        count = 0;
        backingArray = new Map151[size];
    }

    public BigInteger objectHasher(Object ob) {
        return stringHasher(ob.toString());
    }
    /**
     * Implemets Horner's on strings.
     * Since every object can be translated into a string This can be run
     * on an arbitrary object with no loss of generality.
     * @param ss the string to generate a hash value for
     * @return the hash value
     */
    public BigInteger stringHasher(String ss) {
        BigInteger mul = BigInteger.valueOf(23);
        BigInteger ll = BigInteger.valueOf(0);
        for (int i=0; i<ss.length(); i++) {
            ll = ll.multiply(mul);
            ll = ll.add(BigInteger.valueOf(ss.charAt(i)));
        }
        return ll;
    }

    private int h(K k) {
        return objectHasher(k).mod(BigInteger.valueOf(backingArray.length)).intValue();
    }

    /**
     * Add a key-value pair to the hashtable.  If the key is already in the
     * hashtable, then the old value is replaced.  Otherwise this adds a 
     * new key-value pair
     * @param key the key
     * @param value the value
     */
    @Override
    public void put(K key, V value) {
        int loc = h(key);
        if (backingArray[loc] == null) {
            backingArray[loc] = new Map151<>();
        }
        if (!backingArray[loc].containsKey(key)) {
            count++;
        }
        backingArray[loc].put(key, value);
    }

    /**
     * Get the value stored in the hashtable given the key.
     * @param key the key 
     * @return the value associated with the key
     */
    @Override
    public V get(K key) {
        int loc = h(key);
        if (backingArray[loc]==null) {
            return null;
        }
        return backingArray[loc].get(key);
    }

    /**
     * The number of distinct keys in the hshtable.
     * @return The number of distinct keys in the hashtable
     */
    @Override
    public int size() {
        return count;
    }


    @Override
    public boolean containsKey(K key) {
        int loc = h(key);
        if (backingArray[loc] == null) {
            return false;
        }
        return backingArray[loc].containsKey(key);
    }

    @Override
    public Set<K> keySet() {
        TreeSet<K> set = new TreeSet<>();
        for (int i = 0; i < backingArray.length; i++) {
            if (backingArray[i] != null)
                set.addAll(backingArray[i].keySet());
        }
        return set;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < backingArray.length; i++) {
            if (backingArray[i] != null) {
                sb.append(i);
                sb.append(" ");
                sb.append(backingArray[i].toString());
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * One of the controllers for Assignment 4 that counts the frequencies of unique words in given 
     * file and store pairs of word & frequency into a hashtable using separate chaining. 
     * This program records the time (4 decimal place) of storing unique words and their frequencies,
     * and it prints out the most common word with its exact frequency. 
     * No param or return. 
     */
    public static void main(String[] args) {
        /* Holds the starting time of storing words and frequencies into hashtable */
        long start = System.nanoTime();
        /* Holds the unique words and corresponding frequencies */
        SepChainHT<String, Integer> wordCount = new SepChainHT<>();

        String fileURL;
        ReadCSV file;

        //try to read file & catch exceptions (if any)
        try {
            //store the first runtime argument as the given file URL
            fileURL = args[0];
            //read file
            file = new ReadCSV(fileURL);
        }
        catch (IOException ioe) { 
            //when the given file is not readable, print error message & end program
            System.err.println("Cannot read. " + ioe.toString());
            return;
        }
        catch (ArrayIndexOutOfBoundsException aie){
            //when no runtime argument given, print error message & end program
            System.err.println("Cannot read runtime argument. " + aie.toString());
            return;
        }
        //try to store each unique words and their frequencies & catch exceptions (if any)
        try{
            //keep looping to store each word / update its frequency until no more new lines to read
            while (file.hasNext()){
                /* Holds the string of the word */
                String key = file.getLine()[0];
                /* Holds the frequency of the word */
                int count = 1;
                /* Holds the status of the key */
                boolean isEmpty = true;

                //check if the word is empty & update status
                if (key.length() > 0){
                    isEmpty = false;
                }
                //when the word has seen and is not empty
                if (wordCount.containsKey(key) && !isEmpty){
                    //update the frequency of this word in hashtable
                    count = wordCount.get(key) + 1;
                    wordCount.put(key, count);
                } else if (!wordCount.containsKey(key) && !isEmpty){ //when the word has not seen and is not empty
                    //add this new word to the hashtable
                    wordCount.put(key, count);
                }
            }
        }
        catch (IOException ioe2) {
            //when the file's lines cannot be read, print error message & end program
            System.err.println("Problem while reading file. " + ioe2);
            return;
        }

        final int NANOS_SEC = 1000000000; // nanosec per sec
        /* Holds the finish time of storing words and frequencies into hashtable */
        long finish = System.nanoTime();
        //print out the time it takes to store unique words and frequencies
        System.out.println(String.format("%.4f", (double) (finish - start) / NANOS_SEC));

        /* Holds the string of the most common word */
        String mostCommonWord = "";
        /* Holds the frequency of the most common word */
        int highestFrequency = 0;
        //parse through each unique words
        for (String ss : wordCount.keySet()){
            //check if current word's frequency is higher than the highest frequency
            if (wordCount.get(ss) > highestFrequency){
                //update the most common word and highest frequency
                mostCommonWord = ss;
                highestFrequency = wordCount.get(ss);
            }
        }
        System.out.println(mostCommonWord + ": " + highestFrequency);
   } //end of main method
}
