/** 
   * Desc:
   *    This program stores information about each baby name's information (name, frequency, uses)
   *  into a class called Name. Accessors and toString() method are also included. 
   * 
   * @author Cecilia Zhang
   * Modified: April 18, 2022
   * Modified: April 19, 2022
*/
public class Name implements Comparable<Name>{
    //Holds the String of baby's name
    private String name;
    //Holds the integer of name's frequency
    private int frequency;
    //Holds the integer of name's uses (in years)
    private int uses;

    /**
        * Creates a Name with name only (mainly for searching)
        * @param name a string of baby's name
    */
    public Name(String name){
        this.name = name;
    }

    /**
        * Creates a Name with name, frequency, and uses. 
        * Uses will always be 1 by default. It will only increment when this name is seen in another file. 
        * @param name a string of baby's name
        * @param frequency an integer of name's frequency
    */
    public Name(String name, int frequency){
        this.name = name;
        this.frequency = frequency;
        this.uses = 1;
    }

    //Accessors
    public String getName(){
        return name;
    }

    public int getFrequency(){
        return frequency;
    }

    public int getUses(){
        return uses;
    }

    /**
        * Updates the frequency of baby's name if we found the same name in another file. 
        * @param newFreq an integer of name's frequency
    */
    public void updateFrequency(int newFreq){
        frequency += newFreq;
    }

    /**
        * Updates/Increments the uses of baby's name if we found the same name in another file. 
        * no param or return.
    */
    public void updateUses(){
        uses++;
    }

    public String toString(){
        return name + "\n Frequency: " + frequency + " \n Uses: " + uses;
    }
    /**
        * Overrides the compareTo() method so it will only compare the names (will not compare frequency). 
        * @param name2 a string of baby's name
        * @return an integer that indicates if the two names are the same or one greater than another alphabetically
    */
    public int compareTo(Name name2){
        return this.name.compareTo(name2.name);
    }
}