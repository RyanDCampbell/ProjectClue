

## Clue Version 0.2

To compile and run the code:

1) Navigate to the folder path that contains the Clue code.

2) Open a new unix terminal 

3) To compile the program, enter the command: make

4) To run the program, enter the command: java Main 

5) The program will run, displaying the output to the console and print an "End of processing" message upon completion.


## To compile and run the test suit:

1) Navigate to the folder path that contains the Clue code.

2) Copy and paste this command to compile .java files: javac Card.java ComputerPlayer.java Guess.java HumanPlayer.java IPlayer.java Model.java Player.java Main.java

3) Copy and paste this command: javac -cp .:junit-platform-console-standalone-1.6.0.jar UnitTests.java

4) Copy and paste this command: java -jar junit-platform-console-standalone-1.6.0.jar --class-path . --scan-class-path
