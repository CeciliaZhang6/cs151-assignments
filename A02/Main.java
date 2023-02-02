/** 
   * Desc:
   *    The controller for Assignment 2.
   *    This program creates a PlaceList151 class instance from the designated URL and reads through the file. 
   *    It also prompts the user for zip code, reads the zip code from user input, and search through the given 
   *    file in order to return the corresponding details about this zip code. If no matching zip code found, 
   *    the program will display “not found”. When user enters "q", the program will end. 
   * @author Cecilia Zhang
   * Modified: Feb 7, 2022
   * Modified: Feb 8, 2022
*/

import java.util.Scanner;
import java.io.*;

public class Main{
    /* 
    The Controller method of this assignment. See Description above for details. 
    No param or return. 
    */
    public static void main(String[] args) throws IOException {
        //Creates a new Scanner object which scans  the input stream
        Scanner scnr = new Scanner(System.in); 
        //Holds the URL of target file
        String fileURL = "https://cs.brynmawr.edu/cs151/Data/Hw2/shuffzip.csv"; 
        //ReadCSV fileName = new ReadCSV("Desktop/CS151/A2/shuffzip.csv"); //local file
        PlaceList151 pl = new PlaceList151(); 
        pl.readZips(fileURL); //read file from given URL and store information

        while (true){ //The program will keep asking for user input (zip codes) unless user asks to quit
            System.out.print("Zipcode (q to quit): "); 
            String inputZip = scnr.next(); //store user input (the zip code the user wants to look up)
            if (inputZip.equals("q")) { //when the user indicates "quit"
                System.out.println("Bye!"); 
                break;
            }
            //creates a Place with the given zip code ONLY for search
            Place userInput = new Place(inputZip, null, null);
            if (pl.checkInputPlace(userInput) != null){
                System.out.println(pl.checkInputPlace(userInput).toString());
            } else {
                System.out.println("Zip code not found! :/");
                break;
            }
        } //end of while loop
    } //end of main method
} //end of Main class