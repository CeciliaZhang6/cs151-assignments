/** 
   * Desc:
   *    This program stores information about each zipcode that only has zip code, city name, and state
   *    abbreviation into a class named Place. Accessors and toString() method are also included. 
   * @author Cecilia Zhang
   * Modified: Feb 7, 2022
   * Modified: Feb 8, 2022
   * Modified: Feb 14, 2022
*/

public class Place implements Comparable <Place>{
    //Holds the String of zip code
    private String zipCode; 
    //Holds the String of city name
    private String cityName; 
    //Holds the String of state abbreviation
    private String stateAbbr; 

    /**
          * Creates a Place with zip, city name, and state abbreviation
          * @param zip a 5 digit zip code
          * @param city the city name
          * @param state the state abbreviation
    */
    public Place (String zip, String city, String state) {
          zipCode = zip;
          cityName = city;
          stateAbbr = state;  
    }
    
    //Accessors
    public String getZip(){
          return zipCode;
    }
    
    public String getCity(){
          return cityName;
    }
    
    public String getState(){
          return stateAbbr;
    }

    @Override
    /**
     * Overrides the toString() method to print out all information
     */
    public String toString() {
          return zipCode + " " + cityName + ", " + stateAbbr;
    }

    @Override
    /**
     * Overrides the equals() method so it can take an input of Place and only compare the zip code
    */
    public boolean equals(Object inputZipcode){
          Place inputZip = (Place) inputZipcode; 
          if (this.getZip().equals(inputZip.getZip())){
                return true;
          }
          return false;
    }

    @Override
    /**
     * Overrides the compareTo() method so it can compare the zip codes of the newly added place
    */
    public int compareTo(Place p){
          return this.getZip().compareTo(p.getZip());
    }

} //end of Place class