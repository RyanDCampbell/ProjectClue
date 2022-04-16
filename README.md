

## ProjectClue Version 0.1

To compile and run the code:

1) navigate to the folder path that contains all the files I have provided (In my case, the command is "cd clue").

2) Copy and paste this command to compile .java files: javac Card.java ComputerPlayer.java Guess.java HumanPlayer.java IPlayer.java Model.java Player.java Main.java

3) Enter the command: java Main

5)The program will run, displaying the output to the console and print an "End of processing" message upon completion.



## To compile and run the test suit:

1) navigate to the folder path that contains all the files I have provided (In my case, the command is "cd clue").

2) Copy and paste this command to compile .java files: javac Card.java ComputerPlayer.java Guess.java HumanPlayer.java IPlayer.java Model.java Player.java Main.java

3) Copy and paste this command: javac -cp .:junit-platform-console-standalone-1.6.0.jar UnitTests.java

4) Copy and paste this command: java -jar junit-platform-console-standalone-1.6.0.jar --class-path . --scan-class-path
