NAME:
=====
Cecilia Zhang


Programs Files:
===============
BinaryHeap.java
TernaryHeap.java
AbstractPriorityQueue.java
PriorityQInterface.java



How to Compile:
===============
javac PriorityQInterface.java
javac AbstractPriorityQueue.java
javac BinaryHeap.java
javac TernaryHeap.java


How to Run:
===========
Step 3: java TernaryHeap
Step 4: java BinaryHeap


Reflection:
===========
This assignment is easier than I thought, but I still encountered some difficulties. For instance, I was at first not sure how to change removeTop() in TernaryHeap.java, but then I realized that I can split it into two situations: only one child and more than one child. For situations when there are more than one child, I can compare the first two children and then check if we have a thrid child. If yes, then compare it with best child. This way, I can save up a couple lines of code. Big thanks for inspiration from TA . In addition, when I am testing BinaryHeap.java, I tested the situations including offering when the heap is full(prints false), polling when the heap is empty (prints null), and offering when the Key already has a value(still adds to the array, not update). 

I Worked With:
==============
Myself and Proff. Towell (Thank you!!)


Approximate Hours worked:
=========================
About 5 hours 


Special Instructions to the grader:
===================================
Nope!


Known Bugs or Limitations:
==========================
Nope! It works perfectly (but took a long time to make it work)!


Other comments:
===============
I did not really expect myself to be the first person who found the code error...
