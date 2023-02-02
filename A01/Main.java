/** 
   * Desc:
   *    The controller for Assignment 1.
   *    This program creates a PlaceContainer class from the designated URL and reads through the file. 
   *    It also prompts the user for zip code, reads the zip code from user input and search through the given 
   *    file and return the corresponding town name and state abbreviation. If no matching zip code found, 
   *    the program will display “not found”. When user enters "q", the program will end. 
   * @author Cecilia Zhang
   * Modified: Jan 30, 2022
   * Modified: Jan 31, 2022
*/

//not sure if I really need all four of them, but I just imported all anyways. 
import java.util.Scanner;
import java.io.*;
import java.net.URI;
import java.net.URL;


public class Main{
    /* 
    The Controller method of this assignment. See Description above for details. 
    No param or return. 
    */
    public static void main(String[] args) throws IOException {
        //Creates a new Scanner object which scans  the input stream
        Scanner scnr = new Scanner(System.in); 
        //Holds the URL of target file
        String fileURL = "https://cs.brynmawr.edu/cs151/Data/Hw1/uszipcodes.csv"; 
        //Creates a new PlaceContainer object that will hold the information read from given URL
        PlaceContainer pc = new PlaceContainer(); 
        pc.readZipCodes(fileURL); //read file from given URL and store information

        while (true){ //The program will keep asking for user input (zip codes) unless user asks to quit
            System.out.print("Zipcode (q to quit): "); //ask for user input
            String inputZip = scnr.next(); //store user input (the zip code the user wants to look up)
            if (inputZip.equals("q")) { //when the user indicates "quit"
                System.out.println("Bye!"); //print to notify that the programming is ending
                break; //end program
            }
            if (pc.lookupZip(inputZip) != null){ //when the given zip code matches with one zip code from the given file
                //print town name and state abbreviation of the given zip code
                System.out.println(pc.lookupZip(inputZip).getTown() + ", " + pc.lookupZip(inputZip).getState()); 
            } else { //when the given zip code cannot be found
                System.out.println("Zipcode not found :("); //print error messgae
            }
        } //end of while loop
    } //end of main method
} //end of Main class