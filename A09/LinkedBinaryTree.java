import java.util.ArrayList;
import java.util.Queue;

/**
 * An implemention of a linked binary tree
 * 
 * @author gtowell Written: Feb 2020 
 * Updated: Mar 26, 2020 to fix right-left
 *         inversion throughout code 
 * Updated: Nov 2020 switched some "alt" code
 *         to be the main code and updated documentation
 * 
 * 
 *         Methods with an @override annotation are documented in TreeInterface
 * 
 *         Methods named xxxAltxxx are alternative implementations. The
 *         alternatives have exactly the same effect, they just achieve it
 *         slightly differently.
 * Updated: Nov 2021 -- moved "alt" code to separate class
 * @param <E>
 */
public class LinkedBinaryTree<E extends Comparable<E>> implements TreeInterface<E> {
	/**
	 * A class implementing the tree node Note that this inner class is declared as
	 * protected so it is available and visible to extending classes.
	 */
	protected class Node<F extends Comparable<F>> {
		/** The data in the node */
		F payload;
		/** The right child */
		Node<F> right;
		/** The left child */
		Node<F> left;

		/**
		 * Node constructor. Just takes the data element. Sets the right and left to
		 * null
		 * 
		 * @param e the data element to be held in the node
		 */
		public Node(F e) {
			payload = e;
			right = null;
			left = null;
		}

		/**
		 * A print representation of the node. This just relies on the print rep of the
		 * payload
		 */
		public String toString() {
			return payload.toString();
		}
	}

	/** The number of elements in the tree */
	protected int size;
	/** The root of the tree */
	protected Node<E> root;

	/**
	 * Create an empty LinkedBinaryTree
	 */
	public LinkedBinaryTree() {
		root = null;
		size = 0;
	}

	@Override
	public int size() {
		return size;
	}

	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public E contains(E element) {
		Node<E> tmp = containsUtil(root, element);
		if (tmp != null)
			return tmp.payload;
		return null;
	}

	/**
	 * Recursive helper function for determining if an element is in the tree. This
	 * version follows the algorithm and pseudocode in class. This version is clear
	 * because the base cases are at the top of the function.
	 * 
	 * @param treepart  the root of a subtree
	 * @param toBeFound the value to be looked for
	 * @return if found, the node containing the value, otherwise null.
	 */
	private Node<E> containsUtil(Node<E> treepart, E toBeFound) {
		if (treepart == null)
			return null;
		int cmp = treepart.payload.compareTo(toBeFound);
		if (cmp == 0)
			return treepart;
		if (cmp > 0) { // 3/26
			return containsUtil(treepart.left, toBeFound);
		} else {
			return containsUtil(treepart.right, toBeFound);
		}
	}


	@Override
	public void insert(E element) {
		if (root == null) {
			root = new Node<E>(element);
			size = 1;
		} else
			insertUtil(root, element);
	}

	/**
	 * Recursive helper function for insertion of an element into a tree.
	 * Again, watch out for base cases.
	 * 
	 * @param treepart  the root of the current subtree
	 * @param toBeAdded the element to be added to the tree
	 */
	private void insertUtil(Node<E> treepart, E toBeAdded) {
		int cmp = treepart.payload.compareTo(toBeAdded);
		if (cmp == 0)
			return; // the item is in the tree do NOT add it again.
		if (cmp > 0) { 
			//System.out.println(toBeAdded + " is less than " + treepart.payload + " so left in tree");
			if (treepart.left == null) {
				size++;
				treepart.left = new Node<E>(toBeAdded);
			} else {
				insertUtil(treepart.left, toBeAdded);
			}
		} else {// cmp>0
			//System.out.println(toBeAdded + " is greater than " + treepart.payload + " so right in tree");

			if (treepart.right == null) {
				size++;
				treepart.right = new Node<E>(toBeAdded);
			} else {
				insertUtil(treepart.right, toBeAdded);
			}
		}
	}

	/**
	 * Remove. This version is longer than some other versions, but more easily
	 * understood.
	 * 
	 * @param element the element to be removed
	 * @return the payload of the node being removed, or null if the node is not found (Note that the non-null object returned, while equal to the object passed as toBeRemoved, may not be == to toBeRemoved so the return value can be interesting.)
	 */
	public E remove(E element) {
		if (root == null)
			return null;
		return removeUtil(root, null, element);
	}

	/**
	 * Find the value stored in the leftmost node of the tree
	 * 
	 * @param sRoot the subtree root
	 * @return the data element in the left most node of the subtree
	 */
	protected E minKey(Node<E> sRoot) {
		if (sRoot.left == null)
			return sRoot.payload;
		else
			return minKey(sRoot.left);
	}

	/**
	 * Internal, recursive implementation of remove
	 * 
	 * @param treepart    the root of the current subtree
	 * @param parent      the parent of the root of the current subtree
	 * @param toBeRemoved the element to be removed.
	 * @return the payload of the node being removed, or null if the node is not found (Note that the non-null object returned, while equal to the object passed as toBeRemoved, may not be == to toBeRemoved so the return value can be interesting.)
	 */
	private E removeUtil(Node<E> treepart, Node<E> parent, E toBeRemoved) {
		System.out.println("REM" + treepart + "   " + toBeRemoved);
		if (treepart == null)
			return null;
		int cmp = treepart.payload.compareTo(toBeRemoved);
		System.out.println("REM" + treepart + "   " + toBeRemoved + "  " + cmp);
		if (cmp > 0) {
			System.out.println(toBeRemoved + " is less than " + treepart.payload + " so left in tree");
			return removeUtil(treepart.left, treepart, toBeRemoved);
		} else if (cmp < 0) {
			System.out.println(toBeRemoved + " is greater than " + treepart.payload + " so right in tree");
			return removeUtil(treepart.right, treepart, toBeRemoved);
		} else { // cmp==0
			// this is the thing I want to get rid of!!!!
			if (treepart.left == null && treepart.right == null) {
				// Case 2: no children
				if (parent == null) {
					root = null;
				} else {
					if (parent.right == treepart)
						parent.right = null;
					else
						parent.left = null;
				}
				size--;
				return treepart.payload;
			}
			if (treepart.left == null) { // the right branch is NOT null
				// Case 3: Only a right child
				if (parent == null) {
					root = treepart.right;
				} else {
					if (parent.right == treepart)
						parent.right = treepart.right;
					else
						parent.left = treepart.right;
				}
				size--;
				return treepart.payload;
			}
			if (treepart.right == null) {
				// Case 3: only a left child
				if (parent == null) {
					root = treepart.left;
				} else {
					if (parent.right == treepart)
						parent.right = treepart.left;
					else
						parent.left = treepart.left;
				}
				size--;
				return treepart.payload;
			}
			// case 4: Two children
			E tmp = treepart.payload;
			E pred = minKey(treepart.right);
			removeUtil(treepart.right, treepart, pred);
			treepart.payload = pred;
			return tmp;
		}

	}

	@Override
	public int height() {
		return maxDepthUtil(root) - 1;
	}
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		toStringUtil(sb, 0, root);
		return sb.toString();
	}

	/**
	 * Utility function for toString. This recursively goes through the 
	 * tree whose root is at the given node and collects into the given 
	 * StringBuffer a representation of the tree.   On completion, nothing
	 * is returned, but the StringBuffer has been changed; so the return value
	 * is effectively contained therein.
	 * @param sb the StringBuffer that collects the representation of the tree
	 * @param level the level of the node (to be used in the tree representation)
	 * @param nod the root of the tree (or subtree)
	 */
	public void toStringUtil(StringBuffer sb, int level, Node<E> nod) {
		if (nod==null)
			return;
		toStringUtil(sb, level + 1, nod.left);
		sb.append(String.format("<%d: %s> ", level, nod));
		toStringUtil(sb, level+1, nod.right);
	}
	/**
	 * An internal recursive helper function to calculate the height of the tree
	 * with the given root.
	 * 
	 * @param node the root of the subtree for which the height is desired
	 * @return
	 */
	private int maxDepthUtil(Node<E> node) {
		if (node == null)
			return 0;
		int rd = maxDepthUtil(node.right) + 1;
		int ld = maxDepthUtil(node.left) + 1;
		if (rd > ld)
			return rd;
		else
			return ld;
	}

	/**
	 * The method returns true if the tree given the the method parameter
	 * has the same structure as the (this) tree. For instance, if the given 
	 * tree has only a root node and this tree also has only a root node,
	 * then they are structurally identical regardless of the data item stored
	 * in the root note. One restriction on the comparison, the type of data stored
	 * in the given tree must be identical to the type of data stored in 
	 * this tree. 
	 * @param otherTree the tree whose structure is to be compared to this tree
	 * @return true iff the two trees are structurally identical.
	 */
	public boolean isStructurallyIdentical(LinkedBinaryTree<E> otherTree) {
		Node<E> otherNode = otherTree.root;
		Node<E> thisNode = this.root;
		return isStructurallyIdenticalUtil(otherNode, thisNode);
	}
	/**
	 * The helper method for isStructurallyIdentical. 
	 * @param otherNode a node of the tree whose structure is to be compared to this tree
	 * @param thisNode a node of this tree
	 * @return true iff the two trees are structurally identical.
	 */
	public boolean isStructurallyIdenticalUtil(Node<E> otherNode, Node<E> thisNode) {
		//when when both tree has an empty node, the structures are identical (base case1)
		if (thisNode == null && otherNode == null){
			return true;
		}
		//when one tree has a node but the other does not, the structures are not identical (base case2)
		if ((thisNode != null && otherNode == null) || (thisNode == null && otherNode != null)){
			return false;
		}
		//when both trees has a node, check the left and right children/subtree of this node
		if (thisNode != null && otherNode != null){
			//first, check if their left subtree is structural identical or not
			if (isStructurallyIdenticalUtil(otherNode.left, thisNode.left)){
				//only check right subtree when the left subtrees are structural identical
				return isStructurallyIdenticalUtil(otherNode.right, thisNode.right);
			}
		}
		return false;
	}

    /**
    * Print the values stored in nodes that are at the given level in the tree. 
    * level 0 is the root. level 1 are direct children of the root, etc
    * @param level the level of the nodes 
    */
    public void printValuesAtLevel(int level) {
        Node<E> cRoot = root;
        printValuesAtLevelUtil(level, cRoot);
    }

    private void printValuesAtLevelUtil(int level, Node<E> cRoot) {
        if (cRoot == null){
            return;
        }
        printValuesAtLevelUtil(level-1, cRoot.left);
        if (0 == level){
            System.out.println(cRoot);
        }
        printValuesAtLevelUtil(level-1, cRoot.right);
    }

	ArrayList<E> reversedTreeData = new ArrayList<E>();
	public ArrayList<E> reverseOrder(Node<E> root){
		if (root == null){
			return null;
		}
		reverseOrder(root.right);
		reversedTreeData.add(root.payload);
		reverseOrder(root.left);
		return reversedTreeData;
	}
	
	public static void main(String[] args) {
		LinkedBinaryTree<String> ts = new LinkedBinaryTree<>();
		ts.insert("M");
		ts.insert("D");
		ts.insert("Q");
		ts.insert("F");
		ts.insert("P");
		ts.insert("Z");
		System.out.println(ts);
		System.out.println(ts.reverseOrder(ts.root));

	}
}