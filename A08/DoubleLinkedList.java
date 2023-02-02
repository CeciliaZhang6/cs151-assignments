/**
 * A generic Doubly Linked List implementation that requires the
 * thing being held to implement the comparable interface. This is
 * a curious requirement because, except for find,  NONE 
 * of the code here actually uses comparability.  Even find could
 * be written to just use equals instead of compareTo.
 * 
 * @author gtowell 
 * 
 * Created: Jan 2020 
 * Modified: Nov 2020
 * Modified: Nov 2021
 * Modified by Cecilia: April 19, 2022
 */
public class DoubleLinkedList<T extends Comparable<T>> implements LinkedListInterfaceComp<T>  {

    /**
     * The node class. Each element in the linked list is an instance of Node
     */
    protected class DNode<V extends Comparable<V>>{
        public V data;
	    /** The previous item in the linked list */
        public DNode<V> prev;
        public DNode<V> next;
        /**
         * Node constructor. Takes a data item and the preceding and trailing nodes.
         */
        public DNode(V data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    /** The start, first element, of the linked list */
    protected DNode<T> head = null;

    /** The last element in the linked list */
    protected DNode<T> tail = null;

    /** The number of items in the linked list */
    protected int size = 0;

    /**
     * The number of items in the linked list
     * 
     * @return the number of items in the linked list
     */
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Get the first element in the linked list
     * 
     * @return the first element in the linked list or null if list is empty
     */
    @Override
    public T first() {
        if (head == null)
            return null;
        return head.data;
    }

    /**
     * The last element in the linked list
     * 
     * @return the last elment or null if list is empty
     */
    @Override
    public T last() {
        if (head == null)
            return null;
        return tail.data;
    }

    /**
     * Remove the first item from the list
     * 
     * @return the first item, or null if the list is empty
     */
    @Override
    public T removeFirst() {
        if (head == null)
            return null;
        T rtn = head.data;
        head = head.next;
        if (head == null)
            tail = null;
        else
            head.prev = null;
        size--;
        return rtn;
    }

    /**
     * Remove the last item from the list
     * 
     * @return the last item, or null if the list is empty
     */
    @Override
    public T removeLast() {
        if (head == null)
            return null;
        T rtn = tail.data;
        if (head == tail) {
            head = null;
            size = 0;
            tail = null;
            return rtn;
        }
        tail = tail.prev;
        tail.next = null;
        size--;
        return rtn;
    }

    /**
     * Remove the specified rabbit from the list
     * 
     * @param r the item to be removed
     * @return the removed item, or null is the item was not in the list
     */
    @Override
    public T remove(T r) {
        DNode<T> curr = find(r);
        if (curr == null) {
            return null;
        }
        size--;
        if (curr.prev != null)
            curr.prev.next = curr.next;
        if (curr.next != null)
            curr.next.prev = curr.prev;
        if (curr == tail)
            tail = curr.prev;
        return r;
    }

    
    public String toString() {
        StringBuffer s = new StringBuffer();
        for (DNode<T> n = head; n != null; n = n.next) {
            s.append(n.data.toString());
            s.append("\n");
        }
        return s.toString();
    }

    
    /**
     * @param args
     */
    public static void main(String[] args) {
        new DoubleLinkedList<>().testt();
    }

    protected class NC  {
    }

    protected class IC implements Comparable<IC> {
        public int compareTo(IC other) {
            return 0;
        }
    }

    public void testt() {
        DoubleLinkedList<IC> llr = new DoubleLinkedList<>();
        //DoubleLinkedList<NC> llr = new DoubleLinkedList<>();
        System.out.println(llr);
    }

    /**
     * Add an element at the end of the list
     * 
     * @param c the element to be added
     */
    @Override
    public void addLast(T c) {
        DNode<T> newNode = new DNode<>(c);
        if (head == null) {
            head = newNode;
            tail = newNode;
            size = 1;
            return;
        }
        newNode.prev = tail;
        tail.next = newNode;
        tail = newNode;
        size++;
    }

    /**
     * Add a element at the front of the list
     * 
     * @param c the Element to be added
     */
    @Override
    public void addFirst(T c) {
        DNode<T> newNode = new DNode<>(c);
        if (head == null) {
            head = newNode;
            tail = newNode;
            size = 1;
            return;
        }
        newNode.next = head;
        head.prev = newNode;
        head = newNode;
        size++;
    }

    /**
    * Given a linked list whose items are in sorted order,
    * add the new item, r, so that the items in the linked list are 
    * still in sorted order after the addition.  
    * @param: r  the item to be added 
    * @return true iff the addition succeeded (it will always succeed).
    **/
    public boolean addSorted(T r){
        DNode<T> n = head;
        DNode<T> insertNode = new DNode<>(r);
        //when there is no head, set the new node to be the head
        if (n == null){
            head = insertNode;
            return true;
        }
        //when new node is the smallest
        if (r.compareTo(n.data) < 0){ 
            addFirst(r);
            return true;
        }
        while (n.next != null){ //check from the head
            if (r.compareTo(n.data) <= 0 && r.compareTo(n.prev.data) >= 0){ //when new node is in the middle
                DNode<T> newNext = n;
                n.prev.next = insertNode;
                insertNode.next = newNext;
                insertNode.prev = n.prev;
                newNext.prev = insertNode;
                return true;
            }
            else if (r.compareTo(n.data) >= 0 && n.next.data == null){ //when new node is the largest
                addLast(r);
                return true;
            }
            n = n.next;
        }
        //when there is no node after current cnode, add to the last
        n.next = insertNode;
        insertNode.next = null;
        insertNode.prev = n;
        return true;
    }

    private DNode<T> find(T look4) {
        DNode<T> curr = head;
        while (curr != null) {
            if (0 == curr.data.compareTo(look4)) {
                break;
            }
            curr = curr.next;
        }
        return curr;
    }

    /**
    * Find the position of given data's node in the linked list. 
    * @param look4 the data we are looking for (can be just a name for this assignment only)
    * @return the complete data(such as name, frequency, and uses for this assignment) of given data (piece)
    **/
    public T pFind(T look4) {
        DNode<T> r = find(look4);
        if (r!=null)
            return r.data;
        return null;
    }

    /**
    * Find the position of given data's node in the linked list. 
    * @param look4 the data we are looking for
    * @return position of given data's node or -1 if this data does not exist in the list
    **/
    public int findPos(T look4){
        //return -1 if the given data is not in the linked list
        if (find(look4) == null){
            return -1;
        }
        //Holds the current node (it is the head by default)
        DNode<T> curr = head;
        int pos = 0; //default position
        //keep checking if current node's data has the same name as the given data
        while (curr != null){
            if (0 == curr.data.compareTo(look4)) {
                break;
            }
            curr = curr.next;
            pos++;
        }
        return pos;
    }

    public boolean contains(T iD) {
        DNode<T> nod = find(iD);
        return (nod != null);
    }

}