/** 
   * Desc:
   *    This program is inherited from LocatedPlace.java, so it can not only store information about 
   *    each zipcode's zipcode, city name, state abbreviation, location (latitude & longitude), 
   *    but also population. Accessors and toString() methods are also included. 
   * @author Cecilia Zhang
   * Modified: Feb 7, 2022
   * Modified: Feb 8, 2022
*/
public class PopulatedPlace extends LocatedPlace{
    //Holds the String of population in that place
    private String population;

    /**
      * Creates a place with zip, city name, state abbreviation, and location (latitude & longitude)
      * @param zip a 5 digit zip code
      * @param city the city name
      * @param state the state abbreviation
      * @param latitude the latitude of the location
      * @param longitude the longitude of the location
      * @param population the population of the city
    */
    public PopulatedPlace(String zip, String city, String state, String latitude, 
                            String longitude, String population) {
        super(zip, city, state, latitude, longitude);
        this.population = population;
    }

    //Accessors
    public String getPopulation(){
        return population;
    }

    @Override
    /**
     * Overrides the toString() method to print out all information
    */
    public String toString(){
        return getCity() + ", " + getState() + " " + getZip() + "  Pop: " + population + 
                "  Lat: " + getLatitude() + "  Lon: " + getLongitude() ;
    }
}