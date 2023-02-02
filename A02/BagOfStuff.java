/**
 * Generic Interface defintion for Bag
 * Adapted slightly from Carrano and Henry
 * @author GTowell
 * Created: July 2021
 */

public interface BagOfStuff<S> {
    /**
     * The mount of stuff in the bag
     * @return the amount of stuff in the bag
     */
    public int numberOfItems();

    /**
     * true if there is nothing in the bag
     * @return true if there is nothing in the bag, false otherwise
     */
    public boolean isEmpty();

    /**
     * Put stuff into the bag
     * @param p the stuff to be added to the bag
     * @return true if there is space in the bag for the stuff, false otherwise
     */
    public boolean add(S p);

    /**
     * Remove one piece of stuff from the bag
     * @return the removed stuff, or null if the bag is empty
     */
    public S remove();

    /**
     * Remove a particular stuff from the the bag.  This will remove ALL instance of the stuff
     * @param p the stuff to be removed
     * @return true if there is at least one instance of the stuff in the bag, false otherwise
     */
    public boolean remove(S p);

    /**
     * Remove all stuff from the bag
     */
    public void clear();

    /**
     * The number of instances of the given stuff in the bag.  This comparison
     * requires identical objects
     * @param p the stuff to be counted
     * @return The number of instances of the given stuff in the bag
     */
    public int countOf(S p);

    /**
     * True if the given stuff is in the bag, false otherwise.
     * This comparison requires identical objects.  
     * @param p the stuff to check for
     * @return True if the given stuff is in the bag, false otherwise
     */
    public boolean contains(S p);

    /**
     * Return an object from the bag that is equal to the provided object.
     * Note that the comparison is equal not ==.  Hence, while the returned
     * object is equal to the provided object, it may not be the same 
     * object.  If there is more than one object equal to the provided object,
     * the implementation may return any of the equal objects and could return
     * a different object on consecutive calls.
     * @param p the object to find the equivalent of
     * @return an object equal to the provided object, or null. 
     */
    public S getInstance(S p);

    /**
     * Print the contents of the bag.
     */
    public void display();
}