/** 
   * Desc:
   *    This program is inherited from Place.java, so it can not only store information about 
   *    each zipcode's zipcode, city name, and state abbreviation, but also location 
   *    (latitude & longitude). Accessors and toString() methods are also included. 
   * @author Cecilia Zhang
   * Modified: Feb 7, 2022
   * Modified: Feb 8, 2022
*/
public class LocatedPlace extends Place{
    //Holds the String of latitude
    private String latitude;
    //Holds the String of longitude
    private String longitude;

    /**
      * Creates a place with zip, city name, state abbreviation, and location (latitude & longitude)
      * @param zip a 5 digit zip code
      * @param city the city name
      * @param state the state abbreviation
      * @param latitude the latitude of the location
      * @param longitude the longitude of the location
    */
    public LocatedPlace(String zip, String city, String state, String latitude, String longitude) {
        super(zip, city, state);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    //Accessors
    public String getLatitude(){
        return latitude;
    }

    public String getLongitude(){
        return longitude;
    }

    @Override
    /**
     * Overrides the toString() method to print out all information
    */
    public String toString(){
        return super.toString() + "  Lat: " + latitude + "  Lon: " + longitude;
    }
}