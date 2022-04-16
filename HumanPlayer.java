// CLASS: HumanPlayer
//
// Author: Ryan Campbell
//
// REMARKS: This Class extends the Player
// Class.  This class has several methods
// defined that will override the parent's
// Class to specialize methods for human
// players.
//-----------------------------------------

import java.util.ArrayList;
import java.util.Scanner;

public class HumanPlayer extends Player
{

    public HumanPlayer(int numPlayers, int index, ArrayList<Card> ppl, ArrayList<Card> places, ArrayList<Card>  weapons){
        this.setUp(numPlayers, index, ppl, places, weapons);
    }



    //------------------------------------------------------
    // setCard
    //
    // PURPOSE:    Adds additional processing to the setCard method,
    // allowing Human players to see what cards they have been dealt.
    // PARAMETERS:
    //     Takes in a Card as a parameter.
    //------------------------------------------------------
    public void setCard(Card c){
        super.setCard(c);
        System.out.println("You received card: " +  c.getValue());
    }


    //------------------------------------------------------
    // getGuess
    //
    // PURPOSE:    Asks the Human Player what cards to suggest and
    // whether it is an accusation or a guess.
    // Returns: Returns a guess if possible, else returns null.
    //------------------------------------------------------
    public Guess getGuess() {

        boolean accusation = false;
        Card suspect;
        Card location;
        Card weapon;
        Card currCard;

        Guess guess = null;
        int guessIndex;

        System.out.println("Start of turn: \"It is your turn.\"");

        //Ask for person: "Which person do you want to suggest?"
        System.out.println("Which person do you want to suggest?");
        for(int i = 0; i < ppl.size(); i++)
        {
            currCard = ppl.get(i);
            System.out.println(i + ". " + currCard.getValue());
        }

        Scanner keyboard = new Scanner(System.in);

        //Still need to deal with negative numbers and zero
        while(!keyboard.hasNextInt()){

            System.out.println("Please enter a valid number.\nWhich person do you want to suggest?");
            keyboard = new Scanner(System.in);
        }
        guessIndex = keyboard.nextInt()%ppl.size();
        suspect = ppl.get(guessIndex);
        System.out.println(suspect.getValue());


        //Ask for place: "Which location do you want to suggest?"
        System.out.println("\nWhich location do you want to suggest?");
        for(int i = 0; i < places.size(); i++)
        {
            currCard = places.get(i);
            System.out.println(i + ". " + currCard.getValue());
        }

        keyboard = new Scanner(System.in);
        while(!keyboard.hasNextInt()){

            System.out.println("Please enter a valid number.\nWhich location do you want to suggest?");
            keyboard = new Scanner(System.in);
        }
        guessIndex = keyboard.nextInt()%places.size();
        location = places.get(guessIndex);
        System.out.println(location.getValue());


        //Ask for weapon: Which weapon do you want to suggest?"
        System.out.println("\nWhich weapon do you want to suggest?");
        for(int i = 0; i < weapons.size(); i++)
        {
            currCard = weapons.get(i);
            System.out.println(i + ". " + currCard.getValue());
        }

        keyboard = new Scanner(System.in);
        //Still need to deal with negative numbers and zero
        while(!keyboard.hasNextInt()){

            System.out.println("Please enter a valid number.\nWhich weapon do you want to suggest?");
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
        while(answer != 'n' && answer != 'y'){

            System.out.println("Please enter either 'Y' or 'N'.\nWhich person do you want to suggest?");
            keyboard = new Scanner(System.in);
            answer = keyboard.next().charAt(0);
            answer = Character.toLowerCase(answer);
        }

        if(answer == 'y'){
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
    public Card canAnswer(Guess g, IPlayer ip){

        //Answer the guess by showing a Card if possible.
        Card answer = null;

        if(pplHand.contains((Card)g.getSuspect()) || placesHand.contains((Card)g.getLocation()) || weaponsHand.contains((Card)g.getWeapon()))
        {
            System.out.println("Player " + ip.getIndex() + " asked you about Suggestion: " + g.print() + ". Which do you show?");

            ArrayList<Card> responses = new ArrayList<Card>();

            String location = g.getLocation().getValue();
            String weapon = g.getWeapon().getValue();
            String suspect = g.getSuspect().getValue();

            if(pplHand.contains((Card)g.getSuspect())){
                responses.add(g.getSuspect());
            }
            if(weaponsHand.contains((Card)g.getWeapon())){
                responses.add(g.getWeapon());
            }
            if(placesHand.contains((Card)g.getLocation())){
                responses.add(g.getLocation());
            }

            Card currCard;
            int guessIndex = 0;
            for(int i = 0; i < responses.size(); i++)
            {
                currCard = responses.get(i);
                System.out.println(i + ". " + currCard.getValue());
            }

            Scanner keyboard = new Scanner(System.in);

            //Still need to deal with negative numbers and zero
            while(!keyboard.hasNextInt()){

                System.out.println("Please enter a valid number.\nWhich card do you want to suggest?");
                keyboard = new Scanner(System.in);
            }
            guessIndex = keyboard.nextInt()%responses.size();
            answer = responses.get(guessIndex);
            System.out.println(answer.getValue());
        }
        else
        {
            System.out.println("Player " + ip.getIndex() + " asked you about Suggestion: " + g.print() + ", but you couldn't answer.");
        }
        return answer;
    }
}
