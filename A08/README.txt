NAME:
=====
Cecilia Zhang


Programs Files:
===============
ReadCSV.java
Name.java
DoubleLinkedList.java
LinkedListInterfaceComp.java
Main.java


How to Compile:
===============
javac Main.java


How to Run:
===========
For the script, enter:
java Main 1995 1996 1997 1998 1999 2000 2001 2002 2003 2004 2005

If you want to read other files, feel free to specify the year of the file in command line & use spaces to separate each year, such as:
java Main 1995 1999 2005

You can also run without specifying the year(s), the program will set the year to 1990 by default: 
java Main

Reflection:
===========
Relatively challenging assignment, but I found it to be somewhat similar to previous assignmnets such as the one with zipcodes and stocks. They all need to create a new class to store information and utilized scanner class for user interface. The process of reading multiple files and saving each information is also similar. The main difference was the data structure we used to store informaiton (becuase it is a data structures class). Also, I added three new methods to DoubleLinkedList.java: 1) addSorted(); when I am adding new Name, it will automatically sort based on Name's alphabetical order. 2) pFind(); a public method to find the actual Name object with name, frequency, and uses based on the given name. 3) findPos(); a public method to find the position of the given name in the list. 


I Worked With:
==============
Myself, TA's, and Proff. Towell (Thank you!!)


Approximate Hours worked:
=========================
About 6.5 hours 


Special Instructions to the grader:
===================================
Nope! 


Known Bugs or Limitations:
==========================
Nope! It works perfectly (but took a long time to make it work as usual)!


Other comments:
===============
There is a minor error in assignment instruction... for the sample output, when you enter "SYDeny", the output message should not be "sideny not found" (with the letter i). 
