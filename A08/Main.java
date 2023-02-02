import java.io.IOException;
import java.util.Scanner;

/**
 * The main controller of assignment 8. It reads specified file(s) given by run time 
 * argument, stores names and frequencies from file(s) into an instance of Name class,
 * and put this Name class into a double linked list if this name is not existed in 
 * the list. If this name is already exists, this program will update the freuqncy 
 * and uses of this name. Finally, the program will ask user for the name to search up. 
 * If name found in the list, it will print out the name, frequency, uses, percentage, 
 * and position. If not, it will print out "not found". When user enters "q", this program
 * will stop asking. 
 * 
 * @author Cecilia Zhang 
 *
 * Modified: April 18, 2022
 * Modified: April 19, 2022
 */
public class Main {
    /**
     * Main method of Main class. See above for more info. 
     * No param or return. 
     */
    public static void main(String[] args){
        //when no run time argument given, the default year will be 1990
        String[] myArgs = {"1990"};
	    if (args.length == 0){
            args = myArgs;
        }

        //Holds the file(s) that will be read
        ReadCSV nameFile;
        //Holds the info for all baby names (as a Name class) based on the given file(s)
        DoubleLinkedList<Name> nameList = new DoubleLinkedList<Name>();
        //Holds the info for a baby name (name & frequency)
        Name babyName;
        //Holds the total frequency of all names
        int totalFreq = 0;

        try {
            //keep looping to read all file(s) specified in run time argument
            for (int i = 0; i < args.length; i++){
                //read file
                nameFile = new ReadCSV("https://cs.brynmawr.edu/cs151/Data/Hw8/NN" + args[i] + ".csv");
                //read each line
                while (nameFile.hasNext()){
                    //store name and frequency
                    String[] info = nameFile.getLine();
                    String name = info[0];
                    int frequency = Integer.parseInt(info[1]);
                    //store name and frequency into a Name class, set name to lowercase so searching is case-insensitive 
                    babyName = new Name(name.toLowerCase(), frequency);
                    //increment total frequency
                    totalFreq += frequency;
                    //if this name doesn't exist in list, add it
                    if (!nameList.contains(babyName)){ 
                        nameList.addSorted(babyName);
                    }
                    else{ 
                        //if this name already exists, update this name's stats
                        Name existingName = nameList.pFind(babyName);
                        existingName.updateUses();
                        existingName.updateFrequency(frequency);
                    }
                }
            }
            
        }
        //catch when the file is not readable
        catch (IOException ioe) { 
            System.err.println("Ending. Cannot read file. " + ioe.toString());
            return;
        }
        //catch when the file does not contain name or frequency
        catch (NumberFormatException nfe){
            System.err.println("Ending. Cannot read names or frequency numbers. " + nfe.toString());
            return;
        }

        //Create a scanner class to take user input
        Scanner scnr = new Scanner(System.in); 
        while (true){ //Keep asking for user input
            System.out.print("Name (q to quit): "); 
            String inputName = scnr.next().toLowerCase(); //store user input (name) in lower case
            if (inputName.equals("q")) { //when the user indicates "quit"
                System.out.println("Bye!"); 
                break;
            }
            //create a instance of Name class with only a name to check if the list contains this Name
            Name checkName = new Name(inputName); 
            if (nameList.contains(checkName)){ //when name list has this name 
                //Holds the instance of Name class of the name we are searching for
                Name targetName = nameList.pFind(checkName); 
                //Holds the percentage of this name's frequency
                double percentage = (double) targetName.getFrequency() / totalFreq * 100;
                //print out result
                System.out.printf(targetName + "\n Percentage: %.6f \n", percentage);
                System.out.println(" Position: " + nameList.findPos(targetName));
            } else { //when given name cannot be found in name list
                System.out.println(inputName + " not found! Make sure you are spelling correctly :) ");
            }
        }
    } //end of main method
}
