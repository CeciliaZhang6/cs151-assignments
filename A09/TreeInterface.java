/**
 * A public interface for Trees. In particular, binary trees (although there is
 * nothing in the interface that requires binary trees. )
 * 
 * @author gtowell Created: March 2020 Modified: Nov 2020
 */
public interface TreeInterface<E> {
    /**
     * @return the number of items in the tree
     */
    int size();

    /**
     * 
     * @return the maximum number of links between the root of the tree and the most
     *         distant leaf
     */
    int height();

    /**
     * @return return true if the tree has no nodes
     */
    boolean isEmpty();

    /**
     * @return if the given element exists in the tree, the elementy from the tree
     *         is returned. Null otherwise.
     */
    E contains(E element);

    /**
     * Add an element to the tree (if the element does not already exist in the
     * tree) If the element already is in the tree, do nothing.
     * 
     * @param element the element to be added
     */
    void insert(E element);

    /**
     * Remove an element from the tree, if it is in the tree
     * 
     * @param element the element to be removed
     * @return element if it was in the tree, null otherwise
     */
    E remove(E element);


}