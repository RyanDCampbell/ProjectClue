// CLASS: Model
//
// Author: Ryan Campbell
//
// REMARKS: This Class is the main logic of
// the program. This Class selects the answer
// which consists of a weapon, location and
// perpetrator.  This Class contains methods
// to deal the remaining cards, seat the players,
// and a play method to drive the game.
//-----------------------------------------

import java.util.ArrayList;
import java.util.Collections;

public class Model
{

    //List of current Players (Human and Computer)
    private ArrayList<Player> players;
    private ArrayList<Player> spectators;
    //List of all Cards
    private ArrayList<Card> cards;

    private Card weaponAnswer;
    private Card locationAnswer;
    private Card perpetratorAnswer;

    public Model(ArrayList<Card> cards, ArrayList<Card> weapons, ArrayList<Card> suspects, ArrayList<Card> locations, ArrayList<Player> players){

        this.players = (ArrayList<Player>) players;
        this.cards = (ArrayList<Card>) cards.clone();
        this.spectators = new ArrayList<Player>();

        //Shuffle all ArrayLists to ensure a random game each time.
        Collections.shuffle(weapons);
        Collections.shuffle(suspects);
        Collections.shuffle(locations);
        Collections.shuffle(players);

        //Assign each card type answer by the first item in the appropriate list.
        //Then remove from the list
        weaponAnswer = weapons.get(0);
        cards.remove(weaponAnswer);
        locationAnswer = locations.get(0);
        cards.remove(locationAnswer);
        perpetratorAnswer = suspects.get(0);
        cards.remove(perpetratorAnswer);

        // Shuffle again for good measure
        Collections.shuffle(weapons);
        Collections.shuffle(suspects);
        Collections.shuffle(locations);
    }

    public void setupCards(ArrayList<Card> cards){

        Collections.shuffle(cards);

        Player currPlayer;
        HumanPlayer hPlayer;
        ComputerPlayer cPlayer;
        Card currCard;

        for(int i = 0; i < cards.size(); i++){

            currPlayer = players.get(i % (players.size()-1));

            currCard = cards.get(i);

            if(currPlayer instanceof ComputerPlayer)
            {
                cPlayer = (ComputerPlayer) currPlayer;
                cPlayer.setCard(currCard);
            }
            else
            {
                hPlayer = (HumanPlayer) currPlayer;
                hPlayer.setCard(currCard);
            }
        }
    }

    public void setupPlayers(ArrayList<Player> players, ArrayList<Card> weapons, ArrayList<Card> suspects, ArrayList<Card> locations, int numPlayers){

        players.add(new HumanPlayer(numPlayers, 0, suspects, locations, weapons));

        for(int i = 1; i < numPlayers; i++)
        {
            players.add((new ComputerPlayer(numPlayers, i, suspects, locations, weapons)));
        }
    }

    public void play()
    {

        boolean gameOver = false;
        int playerIndex = 0;

        Player currPlayer;
        Player winner = null;
        Guess guess;

        Card weaponGuess;
        Card locationGuess;
        Card suspectGuess;

        while(!gameOver){

            currPlayer = players.get(playerIndex);
            guess = currPlayer.getGuess();
            weaponGuess = guess.getWeapon();
            locationGuess = guess.getLocation();
            suspectGuess = guess.getSuspect();

            if(guess.getAccusation())
            {
                if(weaponGuess.getValue().equals(weaponAnswer.getValue()) &&
                        locationGuess.getValue().equals(locationAnswer.getValue()) &&
                        suspectGuess.getValue().equals(perpetratorAnswer.getValue())) {

                    winner = currPlayer;
                    System.out.println("Correct accusation");
                    gameOver = true;
                }
                else {
                    System.out.println("Incorrect accusation, Player " + currPlayer.getIndex() + " removed from the game.");
                    spectators.add(players.get(playerIndex));
                    players.remove(playerIndex);
                }
                if(players.size() == 1){
                    winner = players.get(0);
                    gameOver = true;
                }
            }
            else {

                //Ask players if they can respond to the guess starting with the next player index.
                int guessIndex = playerIndex+1;
                guessIndex = guessIndex%(players.size()-1);

                Player guesser = players.get(guessIndex);
                HumanPlayer hPlayer;
                Card answer = null;
                boolean answered = false;

                for(int i = guessIndex; (i < players.size()) && !answered; i++) {

                    answer = guesser.canAnswer(guess, currPlayer);

                    if(answer != null) {
                        currPlayer.receiveInfo(guesser,answer);
                        answered = true;
                    }
                    guessIndex++;
                    guessIndex = guessIndex%(players.size()-1);
                    guesser = players.get(guessIndex);
                }

                if(!spectators.isEmpty()) {
                    for(int i = 0; (i < spectators.size()) && !answered; i++) {
                        guesser = spectators.get(i);
                        if(guesser.getIndex() == 0) {
                            hPlayer = (HumanPlayer)spectators.get(i);
                            answer = hPlayer.canAnswer(guess, currPlayer);
                        }
                        else {
                            answer = guesser.canAnswer(guess, currPlayer);
                        }

                        if(answer != null) {
                            currPlayer.receiveInfo(guesser,answer);
                            answered = true;
                        }
                    }
                }
                if(currPlayer.getIndex()==0)
                {
                        System.out.println(answer.getValue());

                }
                else
                {
                    System.out.println();
                }
            }
            playerIndex++;
            playerIndex = playerIndex%(players.size());
        }


        System.out.println("Player " + winner.getIndex() + " wins.");


    }
}
