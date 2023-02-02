/** 
   * Desc:
   *    This program contains readZips method to read and store every line of a zip code file 
   *    into a List of Place objects. It also has a checkInputPlace method to check if the given  
   *    Place can be found in the Place List. If not found, it will return null. 
   * @author Cecilia Zhang
   * Modified: Feb 14, 2022
   * Modified: Feb 15, 2022
   * Modified: Feb 17, 2022
*/

public class SortedArrayList<Y> extends List151Impl<Y>{
    /**
        * Creates a sorted array list with an initial size of 100.
        * No param or return. 
    */
    public SortedArrayList(){
        super(100); //defult size for arra
    }

    @SuppressWarnings("unchecked")
    /**
        * Multiplies the size of array list by a factor of two. 
        * No param or return.
    */
    private void grow(){
        Y[] array = (Y[]) new Object[arra.length * 2];
        for (int i = 0; i < arra.length; i++){
            array[i] = arra[i];
        }   
        arra = array;
        //System.out.println(arra.length); //check if it is storing
    }
    
    @Override
    /**
     * Overrides the add(t) method so the array list grows when there is not enough space to store.
     * It throws an exception with a message indicating this method should not be called. 
     * @param t the value that will be added to the array list.
     * @return a boolean that indicates the value is added successfully.
    */
	public boolean add(Y t) {
        //if there is nothing inside of arra, just add the value to the array list
        if (count == 0){
            arra[0] = t;
            count++;
            return true;
        }
        
        //make sure there is enough space for arra to store new value
		while (count >= arra.length){
            grow();
        }

        //Set the index of new value to defult (last place in the array list)
        int index = count; 

        //check what the new index of new value should be
        for (int i = 0; i < count; i++){
            if (t.toString().compareTo(arra[i].toString()) < 0){ //if t is smaller
                index = i;
                break;
            }
        } 

        //push back existing values that are greater than the new value to make space for it
        for (int j = count; j > index; j--){
            arra[j] = arra[j - 1];
        }

        //put the new value to its ordered index
        arra[index] = t;
		count++;
		return true;
	} //end of add(t) method

    @Override
    /**
     * Overrides the add(index, t) method so it will not insert new value that breaks the order.
     * It throws an exception with a message indicating this method should not be called. 
     * @param t the value that will be added
     * @return a boolean that indicates the value is added (will not happen in this case)
    */
	public int indexOf(Y t) {
        //if the new value is greater than the middle value, check the values after the middle value
        if (t.toString().compareTo(arra[count / 2].toString()) > 0){ 
            for (int i = count / 2; i < arra.length; i++) {
                if (arra[i].equals(t))
                    return i;
            }
        } 
        //if the new value is smaller than the middle value, check the values before the middle value
        if (t.toString().compareTo(arra[count / 2].toString()) < 0){ // if t is smaller
            for (int i = count / 2; i >= 0; i--) {
                if (arra[i].equals(t))
                    return i;
            }
        } 
        //if the new value is equal to the middle value, return the index of middle value
        if (t.toString().compareTo(arra[count / 2].toString()) == 0){
            return count / 2;
        }
		return -1; //return index not found
	} //end of indexOf method

    @Override
    /**
     * Overrides the add(index, t) method so will not insert new value that breaks the order.
     * It throws an exception with a message indicating this method should not be called. 
     * @param index the specific index the user wants to insert the new value (not allowed in this case)
     * @param t the value that will be added
     * @return boolean that indicates the value is added (will not happen in this case)
    */
	public boolean add(int index, Y t) throws IndexOutOfBoundsException {
		throw new IndexOutOfBoundsException("Do not use this method. Do not break the order!");
	} //end of add(index,t) method

} //end of SortedArrayList class
