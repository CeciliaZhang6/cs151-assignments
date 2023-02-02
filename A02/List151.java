/**
 * @author geoffreytowell 
 * Created: Jul 2021
 * Define all of the methods in List 
 */

public interface List151<W> extends BagOfStuff<W> {
    /**
     * Add an item to the array list at a particular location. Inserts the specified
     * element at the specified position in this list. Shifts the element currently
     * at that position (if any) and any subsequent elements to the right (adds one
     * to their indices).
     * 
     * @param index Location at which the items is to be added
     * @param t  the item to be added.
     * @return true. Always.
     * @throws if the index is out of range (index < 0 || index > size())
     */
    boolean add(int index, W t) throws IndexOutOfBoundsException;

    /**
     * Removes the element at the specified position in this list. Shifts any
     * subsequent elements to the left (subtracts one from their indices).
     * 
     * @param index the index of the element to be removed
     */
    void remove(int index) throws IndexOutOfBoundsException;

    /**
     * Get an item from the array list
     * 
     * @param index the location of the item to be retrieved.
     * @return the specified item (or null if the array list does not have that many
     *         items)
     * @throws if the index is out of range (index < 0 || index > size())
     */
    W get(int index) throws IndexOutOfBoundsException;

    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     * 
     * @param index place to put teh element
     * @param t     the element to be put into the array
     * @return
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 ||
     *                                   index > size())
     */
    boolean set(int index, W t) throws IndexOutOfBoundsException;

    /**
     * Returns the number of elements in this list.
     * 
     * @return the number of elements in this list.
     */
    int size();

    /**
     * Returns the index of the first occurrence of the specified element in this
     * list, or -1 if this list does not contain the element. More formally, returns
     * the lowest index i such that (o==null ? get(i)==null : o.equals(get(i))), or
     * -1 if there is no such index.
     * 
     * @param t the item to be found
     * @return the index of the first occurrence of the specified element in this
     *         list, or -1 if this list does not contain the element
     */
    int indexOf(W t);

    /**
     * Removes all of the elements from this list. The list will be empty after this
     * call returns.
     */
    void clear();

    /**
     * Returns true if the the list is empty, false if it has one or more members
     * @return true if the the list is empty, false if it has one or more members
     */
    boolean empty();
}