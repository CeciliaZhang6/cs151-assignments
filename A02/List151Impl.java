/**
 * @author geoffreytowell 
 * Created: Feb 2021
 * Modified: Jul 2021
 * 
 * A complete implementation of the ArraListInterface
 *         discussed in class
 */

public class List151Impl<Y> implements List151<Y> {
	/** The actual number of items stored */
	protected int count;
	/** The array in which all the data is actually stored */
	protected Y[] arra;

	/**
	 * Basic Constructor. Just initializes the underlying array
	 */
	public List151Impl() {
        //initialize to hold at least 50000 entries
		this(100);
	}

	/**
	 * Constructor to set up the underlying array to a specific startng size
	 * 
	 * @param initialCapacity the starting size.
	 */
	@SuppressWarnings("unchecked")
	public List151Impl(int initialCapacity) {
		arra = (Y[]) new Object[initialCapacity];
		count = 0;
	}

	@Override
	public boolean add(Y t) {
		if (count >= arra.length)
			return false;
		arra[count] = t;
		count++;
		return true;
	}


	@Override
	public boolean add(int index, Y t) throws IndexOutOfBoundsException {
		if (index > count) {
			throw new IndexOutOfBoundsException("Can only add where there are  already items");
		}
		if (index < 0) {
			throw new IndexOutOfBoundsException("Canot store to negative location");
		}
		count++;
		if (count >= arra.length)
			throw new IndexOutOfBoundsException("No space left");
		for (int i = (count - 1); i >= index; i--) {
			arra[i] = arra[i - 1];
		}
		arra[index] = t;
		return true;
	}

	@Override
	public Y get(int index) throws IndexOutOfBoundsException {
		if (index > count) {
			throw new IndexOutOfBoundsException("Can only get where there are  already items");
		}
		if (index < 0) {
			throw new IndexOutOfBoundsException("Cannot get from a negative location");
		}
		return arra[index];
	}

	@Override
	public void remove(int index) throws IndexOutOfBoundsException {
		if (index > count) {
			throw new IndexOutOfBoundsException("Can only remove where there are  already items");
		}
		if (index < 0) {
			throw new IndexOutOfBoundsException("Cannot remove from negative location");
		}
		for (int i = index; i < (count - 1); i++) {
			arra[i] = arra[i + 1];
		}
		count--;
	}

	@Override
	public boolean set(int index, Y t) throws IndexOutOfBoundsException {
		if (index > count) {
			throw new IndexOutOfBoundsException("Can only change where there are  already items");
		}
		if (index < 0) {
			throw new IndexOutOfBoundsException("Canot change a negative location");
		}
		arra[index] = t;
		return true;
	}

	@Override
	public int size() {
		return count;
	}

	@Override
	public int indexOf(Y t) {
		for (int i = 0; i < count; i++) {
			if (arra[i].equals(t))
				return i;
		}
		return -1;
	}

	@Override
	public void clear() {
		for (int i = 0; i < arra.length; i++) {
			arra[i] = null;
		}
		count = 0;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[MAX:");
		sb.append(arra.length);
		sb.append(" Count:");
		sb.append(count);
		sb.append(" ");
		for (int i = 0; i < count; i++) {
			sb.append(String.format("<%d,%s>", i, arra[i]));
			if (i < (count - 1))
				sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}

	
	@Override
	public boolean contains(Y item) {
		return indexOf(item) >= 0;
	}

	@Override
	public boolean empty() {
		return count == 0;
	}

	@Override
	public int numberOfItems() {
		return count;
	}

	@Override
	public boolean isEmpty() {
		return count == 0;
	}

	@Override
	public Y remove() {
		if (count==0) 
			return null;
		// remove the last item, it is easiest.
		count--;
		Y tmp = arra[count];
		arra[count] = null;
		return tmp;
	}

	@Override
	public boolean remove(Y p) {
		boolean once = false;
		while (true) {
			int aa = indexOf(p);
			if (aa < 0)
				return once;
			remove(aa);
			once = true;
		}
	}

	@Override
	public int countOf(Y p) {
		int findCount = 0;
		for (int i = 0; i < count; i++) {
			if (arra[i].equals(p))
				findCount++;
		}
		return findCount;
	}

	@Override
	public void display() {
		System.out.println(this.toString());
	}
	@Override
    public Y getInstance(Y toget) {
        for (int i = 0; i < arra.length; i++) {
            if (arra[i]!=null && arra[i].equals(toget)) {
                return arra[i];
            }
        }
        return null;
    }


	/**
	 * A Driver with a set of illustrative tests showing te use fo the the class and
	 * that the functions of the class actually work
	 * 
	 * @param args ignored
	 */
	public static void main(String[] args) {
		System.out.println("\nTest A: adding consecutive integers to List151 with capacity of 10\nResult should be 0; 0,1; 0,1,2; etc");
		for (int i = 0; i < 4; i++) {
			List151Impl<Integer> test = new List151Impl<>(10);
			for (int j = 0; j <= i; j++) {
				test.add(j);
			}
			System.out.println("\n"+i+":");
			test.display();
		}
		
		System.out.println("\nTest B: Fill a list to capacity, then overfill");
		List151Impl<Integer> test = new List151Impl<>(10);
		for (int i = 10; i < 20; i++) {
			test.add(i);
		}
		System.out.println("Should be numbers 10..19 in positions 0..9");
		test.display();
		System.out.println("\nOverfill!!");
		for (int i = 100; i < 105; i++) {
			if (test.add(i)) {
				System.out.println("Should have returned false!!");
			}
		}
		System.out.println("Should Still be numbers 10..19 in positions 0..9");
		test.display();
		

	}

}