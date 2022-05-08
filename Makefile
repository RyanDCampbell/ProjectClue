
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $*.java

CLASSES = \
	Card.java \
	ComputerPlayer.java \
	Guess.java \
	HumanPlayer.java \
	IPlayer.java \
	Model.java \
	Player.java \
	Main.java

clue: classes
classes: $(CLASSES:.java=.class)
clean:
	$(RM) *.class