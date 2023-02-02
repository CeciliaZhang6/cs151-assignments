/** 
   * Desc:
   *    This program contains readZipCodes method to read and store every line of a zip 
   *    code file into an array of Place objects. It also has a lookupZip method to search for 
   *    a Place using the given zip code. If not found, it will return null. 
   * @author Cecilia Zhang
   * Modified: Jan 30, 2022
   * Modified: Jan 31, 2022
*/
import java.io.*;

public class PlaceContainer{
    //declear an array of Place objects to store information for all zip codes
    private Place[] allZips; 

    /**
     * Reads a zip code file, parsing and storing every line
     * @param a string containing either the name the zip code file or a URL for the zip code file
     */
    public void readZipCodes(String name) throws IOException{
        //Holds all info of each zip codes from given file
        ReadCSV tempStore = new ReadCSV(name); 
        //Holds the information for each line as an array
        String[] lineInfo; 
        //Represents the line number / index for Place array to store Place objects
        int index = 0; 
        //Holds the total # of zip codes by reading the first element of the first line
        int total = Integer.parseInt(tempStore.getLine()[0]); 
        //initialize allZips
        allZips = new Place[total];

        while (tempStore.hasNext()){ //ensure there's still something to read in the file
            lineInfo = tempStore.getLine(); //store the information of next line as an array
            //Holds zip code of the latest line that was read
            String zip = ""; 
            //Holds town name of the latest line that was read
            String town = "";
            //Holds state abbreviation of the latest line that was read
            String state = "";

            for (int i = 0; i < lineInfo.length; i++){ //parse through the array of info
                if (i == 0){ //first element (index # of 0) represents zip code
                    zip = lineInfo[i]; //store the zip code
                }
                if (i == 1){ //econd element (index # of 1)represents town name
                    town = lineInfo[i]; //store the town name
                }
                if (i == 2){ //third element (index # of 2) represents state abbreviation
                    state = lineInfo[i]; //store state abbreviation
                }
            }
            //create a new Place & store each variables into Place array w/ current index
            allZips[index] = new Place(zip, town, state); 
            index++; //increment index to record the next position of Place array
        }
    } //end of readZipCodes method

    /**
     * Find a Place record given a zip code
     * @param zipCode the zip code to find.
     * @return the place, or null if the zip code is unknown
     */ 
    public Place lookupZip(String zipCode){
        for (int i = 0; i < allZips.length; i++){ //parse through each Place in the Place array
            //check the zip code of current Place is the same as the given zip code
            if (zipCode.equals(allZips[i].getZip())){
                return allZips[i]; //return all information about the given zip code
            }
        }
        return null; //the given zip code not found
    } //end of lookupZip method

} //end of PlaceContainer class