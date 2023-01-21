How to run



Linux:
Run bash script file
Run the following ./RunScript.sh
RunScript.sh can be found at the followingpath location: 
FYProject/out/artifacts/MyProject_jar/

Windows:
Run the MyProject.jar file
File can be found in FYProject/out/artifacts/MyProject_jar/
Run it like: java -jar MyProject.jar [path to .jar file]

Requires: Java 13 or a later version and Linux OS




How to use


After running, a window will appear that contains instructions on how to use the program.

I will rewrite the same instructions here:
Click Show Algorithm List and a new window will appear that contains a list of the algorithms.
In order to run the algorithm the user must select ( left click on an algorithm name from the list ) and then press one of the buttons bellow the list according to the location where
the user inserted the input.
The input can be inserted either in the blank filed where the numbers 5 2 3 1 are, or in fileinput.txt which can be opened by pressing "Modify input.txt file or benchmarkLibrary.txt" 
button.
After, the user can press the "Open" button, select input.txt file and press Open. Afterthe user can insert the input, the file might already have some numbers inside (see section
on file input format before attempting to modify the file).
Next, the user has to hit the "Save" button so any changes made to the file are saved.
The user can also modify the file "benchmarkLibrary" and add more inputs, please see the input format before attempting to do so.
Then the user has to press the corresponding running button in the 'algorithm list' window.

input.txt file input Format
In order to insert input the user must know and understand the input format. The input is an adjacency lists of the graph and has the following format:
VERTEX NUMBER 0 ADJACENT LIST VERTEX NUMBER 0 ADJACENT LIST 0
So the vertex number followed by a 0 followed by the vertex neighbours, and a 0 at the end.

benchmarkLibrary.txt input Format
The input format is the same, besides the fact it containts a line above the adjacency lists which gives a description about the graph. This line must begin with # character.

Viewing the graph and result

After clicking the 'Run selected algorithm..' button two windows will appear which containts the graph before the algorithm run, respectively the graph after the algorithm run.
On the first window and also on the second window the user has to drag with the mouse the middle lane that splits the window in two panes in order to see the graph.
The vertices that are included in the vertex cover set have 'CV' characters near the corresponding vertex number.
The user can select with mouse the vertices and move them inside the frame. This might prove usefull in cases which the graph vertices might appear too crowded.[finalReportSENDED.pdf](https://github.com/dragosh45/FYProject/files/10473006/finalReportSENDED.pdf)
