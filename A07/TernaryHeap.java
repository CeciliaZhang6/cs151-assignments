/**
 * Priority Queue implementation using ternary heaps. 
 * @param <K>  the key
 * @param <V>  the value 
 * 
 * @author gtowell
 * Created April 6, 2020
 * Modified: April 12, 2021
 * Modified: April 4, 2022
 * Modified by Cecilia: April 12, 2022
 */
@SuppressWarnings("unchecked")
public class TernaryHeap<K extends Comparable<K>, V> extends BinaryHeap<K, V>
{
	/* The default size of the heap. */
	private static final int CAPACITY = 1032;
	/* The array that holds the heap. */
	private Pair<K,V>[] backArray;
	/* The number of actual items in the heap. */
    private int size;

	/**
     * Initialize the queue with default size. 
	 * Create a minimum heap with the default size. 
     */
	public TernaryHeap() {
        this(CAPACITY);
	}
	/**
	 * Initialize a heap. 
	 * @param capacity the max number of items in the heap.
	 */
	public TernaryHeap(int capacity) {
        backArray = new Pair[capacity];
	}
	
    /**
     * Finds the total number of items in the heap
     * @return the number of items in the heap
     */
    @Override
    public int size()
    {
		return size;
    }

    /**
     * Return true if the heap is empty
     * @return true if the heap is empty
     */
    @Override
    public boolean isEmpty()
    {
		return size==0;
    }

    /**
     * Inserts the specified element into this heap if it is possible to do so
     * immediately without violating capacity restrictions, returning true upon
     * success and false on failure
     * 
     * @param key the key of the element to be added
     * @param value the value of the element 
     * @return true if success, false if failure
     */
    @Override
    public boolean offer(K key, V value)
	{
		if (size >= backArray.length)
			return false;
		int loc = size++;
		backArray[loc] = new Pair<K, V>(key, value);
		int upp = (loc - 1) / 3;
		while (loc != 0) {
			if (0 > backArray[loc].compareTo(backArray[upp])) {
				Pair<K, V> tmp = backArray[upp];
				backArray[upp] = backArray[loc];
				backArray[loc] = tmp;
				loc = upp;
				upp = (loc - 1) / 3;
			} else {
				break;
			}
		}
		return true;
	}
	/**
     * Removes the head of the heap. The last element will be placed at top instead. 
	 * Then, we will compare the values of all elements to rearrange them into order. 
     */
	private void removeTop()
    {
		//update the actual number of items in the heap
		size--;
		//move the last element to the top of the heap
		backArray[0] = backArray[size];
		//delete the last element of the heap
		backArray[size]=null;
		int parentLoc=0; 
		while (true) 
		{
			/* The location of the smallest child. */
	    	int bestChildLoc; 
			/* The location of child 1 of the parent. */
	    	int childLoc1 = parentLoc*3+1; 
			/* The location of child 2 of the parent. */
	    	int childLoc2 = parentLoc*3+2; 
            /* The location of child 3 of the parent. */
	    	int childLoc3 = parentLoc*3+3;
			//check if this parent has no child
	    	if (childLoc1>=size)
			{
				break; 
			}
			//check if this parent only has one child
	    	if (childLoc2>=size)
	    	{
				//the only child will automatically be the best child 
				bestChildLoc=childLoc1; 
	    	}
	    	else//when the parent has 2 or 3 children
	    	{	
				//compare first two children to find the best child
				int cmp = backArray[childLoc1].compareTo(backArray[childLoc2]);
				if (cmp<=0){ //when child 1 is smaller than child 2
					bestChildLoc=childLoc1; //child 1 is the best child
				}
				else{ //when child 2 is smaller than child 2
					bestChildLoc=childLoc2; //child 2 is the best child
				}
				//when parent has 3 children, compare 3rd child to the previous best child
				if (childLoc3 < size){
					cmp = backArray[bestChildLoc].compareTo(backArray[childLoc3]);
					if (cmp >= 0){ //when child 3 is smaller than previous best child
						bestChildLoc = childLoc3; //reset best child to child 3
					}
				}
			}
			
			//when best child is smaller than parent
	    	if (0 > backArray[bestChildLoc].compareTo(backArray[parentLoc]))
	    	{
				//temporary stores the pair of best child
				Pair<K,V> tmp = backArray[bestChildLoc];
				//move parent to the best child's location
				backArray[bestChildLoc] = backArray[parentLoc];
				//put the best child in the location of parent
				backArray[parentLoc] = tmp;
				//make the best child the new parent
				parentLoc=bestChildLoc;
	    	}
	    	else { //when best child is greater than parent
				break; //nothing will be moved
	    	}
		}
    }

    /**
     * Retrieves and removes the head of this heap.
     * 
     * @return the removed element, or null if the queue is empty
     */
	@Override
	public V poll() {
		if (isEmpty())
			return null;
		Pair<K,V> tmp = backArray[0];
		removeTop();
		return tmp.theV;
	}

    /**
     * Retrieves, but does not remove, the head of this heap. 
     * 
     * @return the element at the head of the queue or null if the queue is empty
     */
	@Override
	public V peek() {
		if (isEmpty())
			return null;
		return backArray[0].theV;
	}
    /**
     * Testing method for Ternary Heaps. 
     */
	public static void main(String[] args) {
		TernaryHeap<Integer, String> pq = new TernaryHeap<>(CAPACITY);
		pq.offer(1, "Jane");
		pq.offer(10, "WET");
		pq.offer(5, "WAS");
		System.out.println(pq.poll());
		System.out.println(pq.poll());
		System.out.println(pq.poll());
		System.out.println();

		pq = new TernaryHeap<>(CAPACITY);
		pq.offer(2, "Jane");
		pq.offer(1, "WET");
		pq.offer(5, "WAS");
		System.out.println(pq.poll());
		System.out.println(pq.poll());
		System.out.println(pq.poll());
	}

}