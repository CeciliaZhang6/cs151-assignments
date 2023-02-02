/** 
 * Uses an Abstract Class because the abstract class can 
 * provide the definition of pair that is shared across all 
 * priority queue implementations.  Then each extending class must
 * provide its own implementation of the remaining methods, ie, the
 * ones that are defined in PriorityQueueInterface
 * 
 * @author gtowell
 * Created: Feb 2020
 * Modified: Oct 18, 2020
 * Modified: Apr 12, 2020
 */

public abstract class AbstractPriorityQueue <K extends Comparable<K>, V> implements PriorityQInterface<K,V> {
     
    /**
     * Private class to store the key/values pairs of the Priority Queue
     */
    protected class Pair<L extends Comparable<L>, W> implements Comparable<Pair<L,W>> {
        /** Hold the key */
        final L theK;
        /** Hold the  value*/
        final W theV;
        /**
         * Create an Entry instance
         * @param kk the key
         * @param vv the value
         */
        public Pair(L kk, W vv) {
            theK = kk;
            theV = vv;
        }
		@Override
		public int compareTo(AbstractPriorityQueue<K, V>.Pair<L, W> o) {
            return theK.compareTo(o.theK);
        }
        @Override
        public String toString() {
            return "{{"+theK+" " +theV+"}}";
        }

    }


    //Must be abstract as there is no actual storage in the abstract class
    @Override
    public abstract int size();

    @Override 
    public abstract boolean isEmpty();

    @Override
    public abstract boolean offer(K k, V v);

    @Override
    public abstract V poll();

    @Override
    public abstract V peek();


}