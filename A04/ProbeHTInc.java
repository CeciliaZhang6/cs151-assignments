import java.io.IOException;
import java.math.BigInteger;
import java.util.Set;
import java.util.TreeSet;

/**
 * An incomplete Hashtable using probing. The get and put functions both use a 
 * private function named find, that is incomplete. It must be completed for 
 * the probe hashtable to work.
 * @param <K> the type of key
 * @param <V> the type of value 
 * @author gtowell 
 * Created:  Sep 27, 2020 
 * Modified Oct 2, 2020
 * Modified: Sep 27, 2021
 * @Modifed by Cecilia Zhang (added a main method)
 * Modified: Feb 28, 2022
 * Modified: Mar 1, 2022
 */
public class ProbeHTInc<K, V> implements Map151Interface<K, V> {

    /**
     * Small inner class to group together key,value pairs
     */
    protected class Pair<L, W> {
        /** The key, cannot be changed */
        final L key;
        /**
         * The value. It can be changed as a second put with the key will change the
         * value
         */
        W value;

        /**
         * Initialize the node
         */
        public Pair(L ll, W ww) {
            key = ll;
            value = ww;
        }

        /** Print the node, and all subsequent nodes in the linked list */
        public String toString() {
            return "<" + key + ", " + value + ">";
        }
    }

    /** A Constant .. One of the cases in which static are acceptable
     * This one specifies the maximum number of tombstones allowed before 
     * rehashing for tombstone accumulation
     */
    /** When the hashtable needs to grow, by what factor should it grow */
    private static final double GROWTH_RATE = 2.0;
    /** How full the table should be before initiating rehash/growth */
    private static final double MAX_OCCUPANCY = 0.60;
    /** The default size of the backing array */
    private static int DEFAULT_CAPACITY = 1009;
   /** The array in which the hashtable stores data */
    private Pair<K, V>[] backingArray;
    /** The number of active items in the hashtable */
    private int itemCount;
    
 
    /** Default initialization */
    public ProbeHTInc() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Initialize a hashtable of the given size
     * 
     * @param size the size of the hashtable to create
     */
    @SuppressWarnings("unchecked")
    public ProbeHTInc(int size) {
        // Cannot make an array in which you mention a parameterized type.
        // So just make the generic array. This is a narrowing cast so it does not
        // even need to be explicitly case.
        backingArray = new Pair[size];
        itemCount = 0;
     }

     private int h(K k) {
         return objectHasher(k).mod(BigInteger.valueOf(backingArray.length)).intValue();
     }
    
    /**
     * The hash function. Just uses the java object hashvalue. 
     * @param key the Key to be hashed
     * @return the hash value
     */
    private BigInteger objectHasher(Object ob) {
        return stringHasher(ob.toString());
    }

    /**
     * Implemets Horner's on strings.
     * Since every object can be translated into a string This can be run
     * on an arbitrary object with no loss of generality.
     * @param ss the string to generate a hash value for
     * @return the hash value
     */
    private BigInteger stringHasher(String ss) {
        BigInteger mul = BigInteger.valueOf(23);
        BigInteger ll = BigInteger.valueOf(0);
        for (int i=0; i<ss.length(); i++) {
            ll = ll.multiply(mul);
            ll = ll.add(BigInteger.valueOf(ss.charAt(i)));
        }
        return ll;
    }

    /**
     * The number of active items in the hashtable
     * @return The number of active items in the hashtable
     */
    public int size() {
        return itemCount;
    }

    /**
     * Find a specific pair that contains a certain key.
     * @param key the key of the pair that will be looked up
     * @return The pair that contains the given key
     */
    private Pair<K, V> find(K key) {
        /* Holds the location of the given key after hashing */
        int hashLoc = h(key);
        /* Holds the probe count */
        int q = 0;
        //keep finding until found
        while (true) {
            //create a new pair to hold the pair in backingArray that has same location we found based on the given probe count
            Pair<K, V> pair = backingArray[(hashLoc + q) % backingArray.length];
            //check if the pair we found is empty or not
            if (pair == null)
                //return null if empty
                return null;
            //check if the pair we found has the same key as the given key
            if (pair.key.equals(key))
                //return this pair if the key matches
                return pair;
            //increment probe count so we can continue probing
            q++;
        }
    }
    /**
     * Add a key-value pair to the hashtable. If the key is already in the
     * hashtable, then the old value is replaced. Otherwise this adds a new
     * key-value pair
     * Be sure to update itemCount as needed.
     * 
     * @param key   the key
     * @param value the value
     */
    public void put(K key, V value) {
        //check if there is enough place in backingArray to put new pairs
        if (itemCount > backingArray.length * MAX_OCCUPANCY) {
            //if it meets maximum occupancy of array, grow
            rehash((int)(backingArray.length*GROWTH_RATE));
        }
        //check if this pair's key already exists in backingArray
        Pair<K, V> foundPair = find(key);

        //when previous pair with the same key does not exist
        if (foundPair == null) {
            /* Holds the location of the given key after hashing */
            int hashLoc = h(key);
            /* Holds the probe count */
            int q = 0;
            //loop to find empty space in backingArray using probing
            while (backingArray[(hashLoc + q) % backingArray.length] != null) {
                //increment the probe count if no empty space during current probing
                q++;
            }
            //put the new pair into the appropriate place in backingArray
            backingArray[(hashLoc + q) % backingArray.length] = new Pair<>(key, value);
            //increment total item after adding a new pair
            itemCount++;
        } else { //when previous pair with the same key exists
            //update the value of the pair found
            foundPair.value = value;
        }
    }

    @SuppressWarnings("unchecked")
    /**
     * Rehash the current table. This should be done rarely as it is expensive
     * @param newSize the size of the table after rehashing
     */
    private void rehash(int newSize) {
        System.out.println("Reshashing to " + newSize);
        Pair<K, V>[] oldArray = backingArray;
        itemCount = 0;
        backingArray = new Pair[newSize];
        for (int i = 0; i < oldArray.length; i++) {
            if (oldArray[i] != null) {
                put(oldArray[i].key, oldArray[i].value);
            }
        }
    }

    /**
     * Get the value associated with the key
     * @param key the key whose value is sought
     * @return the associated value, or null
     */
    public V get(K key) {
        //check if any pair in backingArray contains the given key
        Pair<K, V> pair = find(key);
        //when no pair in backingArray contains the given key, return null
        if (pair==null)
            return null;
        //when there is a pair in backingArray contains the given key, return the value of this key
        return pair.value;
    }

    

    @Override
    /**
     * Does the hashtable contain the key
     * @param key the key
     * @return true iff the key is in the hashtable
     */
    public boolean containsKey(K key) {
        return null != get(key);
    }

    @Override
    /**
     * The complete set of keys active in the hashtable.
     * @return a set containing all of the keys in the hashtable
     */
    public Set<K> keySet() {
        TreeSet<K> set = new TreeSet<>();
        for (Pair<K,V> pr : backingArray) {
            if (pr!=null) {
                set.add(pr.key);
            }
        } 
        return set;
    }

    /**
     * One of the controllers for Assignment 4 that counts the frequencies of unique words in given 
     * file and store pairs of word & frequency into a hashtable using probing. 
     * This program records the time (4 decimal place) of storing unique words and their frequencies,
     * and it prints out the most common word with its exact frequency. 
     * No param or return. 
     */
    public static void main(String[] args) {
        /* Holds the starting time of storing words and frequencies into hashtable */
        long start = System.nanoTime();
        /* Holds the unique words and corresponding frequencies */
        ProbeHTInc<String, Integer> wordCount = new ProbeHTInc<>();

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
    } // end of main method
}
