// CLASS: HumanPlayer
// Author: Ryan Campbell
// REMARKS: This Class extends the Player
// Class.  This class has several methods
// defined that will override the parent's
// Class to specialize methods for human
// players.
//-----------------------------------------

import java.util.ArrayList;
import java.util.Scanner;

public class HumanPlayer extends Player  {

    public HumanPlayer(int numPlayers, int index, ArrayList<Card> suspects, ArrayList<Card> locations, ArrayList<Card>  weapons) {
        this.setUp(numPlayers, index, suspects, locations, weapons);
    }

    //------------------------------------------------------
    // addCard
    //
    // PURPOSE:    Adds additional processing to the setCard method,
    // allowing Human players to see what cards they have been dealt.
    // PARAMETERS:
    //     Takes in a Card as a parameter.
    //------------------------------------------------------
    public void addCard(Card card) {
        super.addCard(card);
        System.out.println("You received card: " +  card.getValue());
    }

    //------------------------------------------------------
    // getGuess
    //
    // PURPOSE:    Asks the Human Player what cards to suggest and
    // whether it is an accusation or a guess.
    // Returns: Returns a guess if possible, else returns null.
    //------------------------------------------------------
    public Guess getGuess() {

        Scanner keyboard = new Scanner(System.in);
        Card suspect;
        Card location;
        Card weapon;
        Guess guess;

        boolean accusation = false;
        int guessIndex;

        System.out.println("Start of turn: \"It is your turn.\"");

        //Ask for person: "Which person do you want to suggest?"
        System.out.println("Which suspect would you like to suggest?");
        outputDeck(suspects);

        //Still need to deal with negative numbers and zero
        while (!keyboard.hasNextInt() ) {
            System.out.println("Please enter a valid number.\nWhich suspect would you like to suggest?");
            keyboard = new Scanner(System.in);
        }
        guessIndex = keyboard.nextInt() % suspects.size();
        suspect = suspects.get(guessIndex);
        System.out.println(suspect.getValue());

        //Ask for place: "Which location do you want to suggest?"
        System.out.println("\nWhich location would you like to suggest?");
        outputDeck(locations);

        keyboard = new Scanner(System.in);
        while (!keyboard.hasNextInt()) {
            System.out.println("Please enter a valid number.\nWhich location would you like to suggest?");
            keyboard = new Scanner(System.in);
        }
        guessIndex = keyboard.nextInt()%locations.size();
        location = locations.get(guessIndex);
        System.out.println(location.getValue());


        //Ask for weapon: Which weapon do you want to suggest?"
        System.out.println("\nWhich weapon would you like to suggest?");
        outputDeck(weapons);

        keyboard = new Scanner(System.in);
        //Still need to deal with negative numbers and zero
        while (!keyboard.hasNextInt()) {
            System.out.println("Please enter a valid number.\nWhich weapon would you like to suggest?");
            keyboard = new Scanner(System.in);
        }
        guessIndex = keyboard.nextInt()%weapons.size();
        weapon = weapons.get(guessIndex);
        System.out.println(weapon.getValue());

        //Ask for suggestion/accusation: "Is this an accusation (Y/[N])?"
        System.out.println("Is this an accusation (Y/N)?");
        keyboard = new Scanner(System.in);
        char answer = keyboard.next().charAt(0);
        answer = Character.toLowerCase(answer);

        //Still need to deal with negative numbers and zero
        while (answer != 'n' && answer != 'y') {
            System.out.println("Please enter either 'Y' or 'N'.\nIs this an accusation (Y/N)?");
            keyboard = new Scanner(System.in);
            answer = keyboard.next().charAt(0);
            answer = Character.toLowerCase(answer);
        }

        if (answer == 'y') {
            accusation = true;
        }

        guess = new Guess(accusation, weapon, suspect, location);
        return guess;
    }

    //------------------------------------------------------
    // canAnswer
    //
    // PURPOSE:    Determines if the Player is able to answer the
    // guess. That is, the Player's hand contains one of the Cards
    // contained in the guess.
    // PARAMETERS:
    //     Takes in the guessing player along with the guess as
    // parameters.
    // Returns: Returns the Card that the Player can answer to.
    //------------------------------------------------------
    public Card canAnswer(Guess guess, IPlayer ip) {

        //Answer the guess by showing a Card if possible.
        Card answer = null;

        if (suspectsHand.contains(guess.getSuspect()) || locationsHand.contains(guess.getLocation()) || weaponsHand.contains(guess.getWeapon())) {
            System.out.println("Player " + ip.getIndex() + " asked you about Suggestion: " + guess.print() + ". Which card do you show?");
            ArrayList<Card> responses = new ArrayList<>();

            if (suspectsHand.contains(guess.getSuspect())) {
                responses.add(guess.getSuspect());
            }
            if (weaponsHand.contains(guess.getWeapon())) {
                responses.add(guess.getWeapon());
            }
            if (locationsHand.contains(guess.getLocation())) {
                responses.add(guess.getLocation());
            }

            Card currCard;
            int guessIndex;
            for (int i = 0; i < responses.size(); i++) {
                currCard = responses.get(i);
                System.out.println(i + ". " + currCard.getValue());
            }

            Scanner keyboard = new Scanner(System.in);

            //Still need to deal with negative numbers and zero
            while (!keyboard.hasNextInt()) {
                System.out.println("Please enter a valid number.\nWhich card do you want to suggest?");
                keyboard = new Scanner(System.in);
            }
            guessIndex = keyboard.nextInt() % responses.size();
            answer = responses.get(guessIndex);
            System.out.println(answer.getValue());
        }
        else {
            System.out.println("Player " + ip.getIndex() + " asked you about Suggestion: " + guess.print() + ", but you couldn't answer.");
        }
        return answer;
    }

    private static void outputDeck(ArrayList<Card> deck) {
        Card currCard;
        for (int i = 0; i < deck.size(); i ++) {
            currCard = deck.get(i);
            System.out.println(i + ". " + currCard.getValue());
        }
    }
}
