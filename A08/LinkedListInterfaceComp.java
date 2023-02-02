public interface LinkedListInterfaceComp<E extends Comparable<E>> {
    int size();
    boolean isEmpty();
    E first();
    E last();
    void addLast(E c);
    void addFirst(E c);
    E removeFirst();
    E removeLast();

    E remove(E r);
    
    /**
     * Return true iff the linked list contains
     * an object that is equal to the target, where equality
     * is given by compareTo returning 0.
     * @param iD the object to be searched for
     * @return true iff the list contains an equal object
     */
    boolean contains(E iD);
}