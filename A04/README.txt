NAME:
=====
Cecilia Zhang


Programs Files:
===============
Map151.java
Map151Interface.java
ProbeHTInc.java
SepChainHT.java
ReadCSV.java


How to Compile:
===============
javac Map151Interface.java
javac Map151.java
javac ProbeHTInc.java
javac SepChainHT.java
javac ReadCSV.java


How to Run:
===========
I chose to read GibbonOne.txt file written by Edward Gibbon:

java ProbeHTInc https://cs.brynmawr.edu/cs151/Data/Hw4/GibbonOne.txt

java Map151 https://cs.brynmawr.edu/cs151/Data/Hw4/GibbonOne.txt

java SepChainHT https://cs.brynmawr.edu/cs151/Data/Hw4/GibbonOne.txt
        

Reflection:
===========
Run time for hashtable using probing in sec:
Trial 1 - 3.2987
Trial 2 - 3.1330
Trial 3 - 3.1631
Average run time (truncated down to 4 decimal places): 3.1982
Standard Deviation (truncated down to 4 decimal places): 0.0882

Run time for hashtable using separate chaining in sec:
Trial 1 - 3.2190
Trial 2 - 3.2971
Trial 3 - 3.1891
Average run time (truncated down to 4 decimal places): 3.2350
Standard Deviation (truncated down to 4 decimal places): 0.0557

Run time for map in sec:
Trial 1 - 148.5944
Trial 2 - 147.3160
Trial 3 - 144.7454
Average run time (truncated down to 4 decimal places): 146.8852 
Standard Deviation (truncated down to 4 decimal places): 1.9603

Conclusions:
* The run time for hash with probing and separate chaining are very close, while map takes significantly longer time to store unique words and frequencies. Also, map’s run time has a larger standard deviation than both probing and separate chaining. 
* I agree that map implements an “ugly search”... Hash tables are much more efficient than maps. 
* The most common word in GibbonOne.txt is “the”, and it appeared 156219 times. This result surprised me because I thought it would be “it” or “was”. I assume the reason why “the” is the most frequently used word in GibbonOne.txt is because this novel was written during the pre-Victorian period, when people in that time used a slightly different syntax in writing. 
* Programming reflection: overall a smooth programming experience that took less time than I thought (except I was stuck on timing until the correction email was sent). 


I Worked With:
==============
Myself, lecture slides, TA’s, and Proff. Towell (Thank you all!!)


Approximate Hours worked:
=========================
About 6 hours 


Special Instructions to the grader:
===================================
Nope!


Known Bugs or Limitations:
==========================
Nope! It works perfectly!


Other comments:
===============
I actually tried to read both authors’ work and found out the most common words for both pieces of work are the same - “the”. It is an interesting outcome, and it will be a good topic to discuss in my linguistics class.
