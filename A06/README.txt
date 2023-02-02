NAME:
=====
Cecilia Zhang


Programs Files:
===============
WordReduction.java
ReadCSV.java


How to Compile:
===============
javac WordReduction.java
javac ReadCSV.java


How to Run:
===========
java WordReduction


Script: 
===========
~/Desktop/CS151/A6> javac WordReduction.java
~/Desktop/CS151/A6> java WordReduction cored
cored --- true
~/Desktop/CS151/A6> java WordReduction head
head --- true
~/Desktop/CS151/A6> java WordReduction flawed
flawed --- false


Reflection:
===========
This assignment is relatively smaller than previous ones, but it is still somewhat tricky. I was stuck with making my program do backtracking when a word's derived forms are not English words. I tried to use a variable to store previous word, but that will not work because it only stores one word, not all previous words. So I decided to ask Proff Towell and that was a wise decision (didn't get a chance to go to TA sessions because I was really feeling bad last, but I feel way better now). 
Revision note: now this program takes run time argument as the given work to check (only 1 word allowed each time). Also, now all words is stored in a hash table so it only takes O(1) time to look up words, 

I Worked With:
==============
Myself and Proff. Towell (Thank you!!)


Approximate Hours worked:
=========================
About 3 hours 


Special Instructions to the grader:
===================================
Nope!


Known Bugs or Limitations:
==========================
Nope! It works perfectly (but took a long time to make it work)!


Other comments:
===============
Thank you for taking time to take a look at my revision!
