import java.io.IOException;

/* 
   * Desc:
   *    This file contains three methods and a main method for testing. This program 
   *    checks if the given word can be removed a letter to become a new word until     
   *    it only has two letters. If yes, this program will return true. If not, this
   *    program will return false. 
   *    
   * @author Cecilia Zhang
   * Modified: April 5, 2022
   * Modified: April 6, 2022

*/


public class WordReduction {
    /*Holds the file of all words to check if a word is English or not*/
    ReadCSV dictionary;
    /*Holds all English words for checking if a word is English or not*/
    SepChainHT<String, Integer> wordsHT;

    /*
		* Testing method that creates a list of words and calls wordReduction
        on them. It also prints out the result (true or false). 
        * No param or return. 
	*/
    public static void main(String[] args){
        //check run time argument
        if (args.length != 1){
            System.out.println("Please enter only one word to check. ");
            return;
        }
        WordReduction wr = new WordReduction();
        //Read word file and store all words into a hash table
        wr.readStore();
        //check if this word is English or not & print out result
        for (String s : args){
            Boolean result = wr.wordReduction(s);
            System.out.println(s + " --- " + result);
        }

    }

    /*
		* A method that reads the word file and store each word into a hash table
        * for future use such as checking if a word is English or not.
        * No param or return. 
	*/
    public void readStore(){
        try {
            //read file
            dictionary = new ReadCSV("https://cs.brynmawr.edu/cs151/Data/Hw6/words.txt");
            wordsHT = new SepChainHT<String, Integer>();
            //store all words into a hash table
            while (dictionary.hasNext()) {
                //in dictionary file, there is only one word in each line
                String word = dictionary.getLine()[0];
                wordsHT.put(word.toLowerCase(), 1);
            }
        }
        catch (IOException ioe) { 
            //when the given file is not readable, print error message & end program
            System.err.println("Cannot read. " + ioe.toString());
        }
    }

    /*
		* A recursive method that checks if given word can be reducive and form a new word
        * until this word only has two letters. 
		* @param word, the given word we are going to check
		* @return a boolean, whether or not the given word is reducive
	*/
    public boolean wordReduction(String word){
        /*Holds the new word after original word deleted a letter*/
        String newWord;
        //base case: when word length is 2
        if (word.length() > 2){ 
            for (int i = 0; i < word.length(); i++){
                newWord = removeNchar(i, word); //create a new word by deleting a char
                if (isInEnglish(newWord)){
                    //check if this new word can furthur create another word by deleting characters
                    boolean check = wordReduction(newWord);
                    //return true if the previous check is true
                    if (check){
                        return true;
                    }
                    //if check is false, do nothing & go back to current word to continue deleting & checking
                }
            }
            //tried to deletd each letter, but none of them is a word
            return false;
        }
        //now we only have 2 letter
        return isInEnglish(word); 
    }

    /*
		* This method checks if the given string is actually an English word or not
        * by comparing with words in words.txt (a list of words in dictionary). It 
        * also reads words.txt before we start comparing. 
		* @param aString, the given string that will be checked
		* @return boolean that indicates if the given string is actually an English word or not. 
	*/
    public boolean isInEnglish(String aString){
        //words in dictionary are all in lowercase
        aString = aString.toLowerCase(); 
        if (wordsHT.get(aString) != null){
            return true;
        }
        return false;
    }
    
    /*
		* Remove the nth letter from a string
		* @param n the index of the char to remove
		* @param s the string to remove from
		* @return a string, one letter shorter.  if n is 
		* out of bounds, just return the string without its last letter
	*/
    public String removeNchar(int n, String s) {
        if (s == null) { 
            return s; 
        }
        if (n > 0 && n < s.length() - 1) {
            return s.substring(0, n) + s.substring(n + 1);
        }
        if (n == 0){
            return s.substring(1);
        }
        return s.substring(0, s.length() - 1);
    }
}
