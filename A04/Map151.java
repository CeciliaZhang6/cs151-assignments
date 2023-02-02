import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

/**
 * Implements the interface. A concrete implementaion of a map with all of the
 * ugly search
 * 
 * @author gtowell Created: Sep 21, 2020 Modified: Jul 2021
 * @Modifed by Cecilia Zhang (added a main method)
 * Modified: Feb 28, 2022
 * Modified: Mar 1, 2022
 */
public class Map151<K, V> implements Map151Interface<K, V> {

    /** The underlying data structure to actually do the storage */
    private ArrayList<Pair<K, V>> underlying = new ArrayList<>();

    /**
     * Holds key-value pairs together The only time it is OK (or even good) to have
     * public instance vars is in private classes. (Since they are not really public
     * anyway)
     */
    private class Pair<L, W> {
        // the key. Once set it canot be changed
        public final L key;
        // the value
        public W value;

        // Create a key value pair.
        Pair(L ky, W val) {
            key = ky;
            value = val;
        }
    }

    /**
     * Set a key value pair. Will overwrite value if key already present.
     * 
     * @param key the key
     * @param val the new value
     */
    public void put(K key, V val) {
        Pair<K, V> pair = iContainsKey(key);
        if (pair == null) {
            Pair<K, V> np = new Pair<>(key, val);
            underlying.add(np);
        } else {
            pair.value = val;
        }
    }

    /**
     * Get the value associated with a key
     * 
     * @param key the key
     * @return the associated value
     */
    public V get(K key) {
        Pair<K, V> pair = iContainsKey(key);
        if (pair != null)
            return pair.value;
        return null;
    }

    /**
     * does the Map contain the key?
     * 
     * @param key the key in question
     * @return true iff the key is in the map
     */
    public boolean containsKey(K key) {
        return null != iContainsKey(key);
    }

    private Pair<K, V> iContainsKey(K ky) {
        for (Pair<K, V> pair : underlying) {
            if (pair.key.equals(ky)) {
                return pair;
            }
        }
        return null;
    }

    /**
     * The number of items in the map
     * 
     * @return The number of items in the map
     */
    public int size() {
        return underlying.size();
    }

    /**
     * All of the keys in the map This method allows users of the map to see all of
     * the keys. That this returns a Set is to make this consistent with the Java
     * Map interface.
     * 
     * @return All of the keys in the map
     */
    public Set<K> keySet() {
        TreeSet<K> set = new TreeSet<>();
        for (Pair<K, V> pair : underlying) {
            set.add(pair.key);
        }
        return set;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (Pair<K, V> pair : underlying) {
            sb.append("[" + pair.key + ":" + pair.value + "]\n");
        }
        return sb.toString();
    }

    /**
     * One of the controllers for Assignment 4 that counts the frequencies of unique words in given 
     * file and store pairs of word & frequency into a map. 
     * This program records the time (4 decimal place) of storing unique words and their frequencies,
     * and it prints out the most common word with its exact frequency. 
     * No param or return. 
     */
    public static void main(String[] args) {
        /* Holds the starting time of storing words and frequencies into map */
        long start = System.nanoTime();
        /* Holds the unique words and corresponding frequencies */
        Map151<String, Integer> wordCount = new Map151<>();

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
                    //update the frequency of this word in map
                    count = wordCount.get(key) + 1;
                    wordCount.put(key, count);
                } else if (!wordCount.containsKey(key) && !isEmpty){ //when the word has not seen and is not empty
                    //add this new word to the map
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
        /* Holds the finish time of storing words and frequencies into map */
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
