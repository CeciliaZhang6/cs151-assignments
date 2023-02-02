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

import java.io.IOException;

public class PlaceArrayList151{
    //declear an instance variable of SortedArrayList to hold Places
    private SortedArrayList<Place> placeArrayList;

    /**
     * Reads a zip code file, parsing and storing every line of information to corresponding classes 
     * based on the extra infomation(other than zip, city, state) it contains:
     *  Group 1: know location population
     *  Group 2: know location but don't know population
     *  Group 3: don't know location or population
     * Stores all 3 types of classes into a list of Places
     * @param name a string containing URL of the zip code file
     * No return.
     */
    public void readZips(String name) throws IOException{
        //Holds all info of each zip codes from given file
        ReadCSV file = new ReadCSV(name); 
        //Holds the information for each line as an array
        String[] lineInfo; 
        //initialize placeList with 100 entries
        placeArrayList = new SortedArrayList<>();

        while (file.hasNext()){
            lineInfo = file.getLine();
            //group 1: know both location & population
            if (lineInfo[5].length() > 0 && lineInfo[10].length() > 0){
                PopulatedPlace pp = new PopulatedPlace(lineInfo[0].replace("\"", ""), lineInfo[2].replace("\"", ""), 
                                            lineInfo[3].replace("\"", ""), lineInfo[5], lineInfo[6], lineInfo[10]);
                placeArrayList.add(pp);
            }
            //group 2: know location & no population
            if (lineInfo[10].length() == 0 && lineInfo[5].length() > 0){
                LocatedPlace lp = new LocatedPlace(lineInfo[0].replace("\"", ""), lineInfo[2].replace("\"", ""), 
                                            lineInfo[3].replace("\"", ""), lineInfo[5], lineInfo[6]);
                placeArrayList.add(lp);
            }
            //group 3: no location or population
            if (lineInfo[5].length() == 0 && lineInfo[10].length() == 0){
                Place p = new Place(lineInfo[0].replace("\"", ""), lineInfo[2].replace("\"", ""), 
                                    lineInfo[3].replace("\"", ""));
                placeArrayList.add(p);
            }
        } //end of while loop
    } //end of readZips

    /**
     * Checks if the given zip code matches to one of the zip codes in Place List.
     * @param userinput a Place object that contains only the user's zip code 
     * @return Place the Place from Place List that has the same zip code
     */
    public Place checkInputPlace (Place userinput){
        return placeArrayList.getInstance(userinput);
    } //end of checkInputPlace

} //end of PlaceList151 class