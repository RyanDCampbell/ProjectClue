
// CLASS: abstract class Player
//
// Author: Ryan Campbell
//
// REMARKS: An abstract Player type that
// implements the IPlayer interface
//
//-----------------------------------------

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Player implements IPlayer
{
    private int numPlayers;
    private int index;

    //Lists of all ppl, places and weapons.
    ArrayList<Card> ppl;
    ArrayList<Card> places;
    ArrayList<Card> weapons;

    //Particular cards that the player is holding
    ArrayList<Card> pplHand;
    ArrayList<Card> placesHand;
    ArrayList<Card> weaponsHand;

    //------------------------------------------------------
    // setUp
    //
    // PURPOSE:    Sets up the Player with the global information
    // required to play the game.
    // PARAMETERS:
    //     Takes in the number of players, the player's position at
    // the table, along with an arraylist of each type of card.
    //------------------------------------------------------
    @SuppressWarnings("unchecked")
    public void setUp(int numPlayers, int index, ArrayList<Card> ppl,
                      ArrayList<Card> places, ArrayList<Card> weapons) {

        this.numPlayers = numPlayers;
        this.index = index;

        this.ppl = (ArrayList<Card>) ppl.clone();
        this.places = (ArrayList<Card>) places.clone();
        this.weapons = (ArrayList<Card>) weapons.clone();

        this.pplHand = new ArrayList<Card>();
        this.placesHand = new ArrayList<Card>();
        this.weaponsHand = new ArrayList<Card>();

    }

    //------------------------------------------------------
    // setCard
    //
    // PURPOSE:    When the Player is given a Card, the Player adds
    // the Card to their appropriate hand.
    // PARAMETERS:
    //     Takes in a Card as a parameter to add to the Player's hand
    //------------------------------------------------------
    public void setCard(Card c) {

        if(c.getType().equals("weapon")){
            weaponsHand.add(c);
        }
        else if(c.getType().equals("suspect")){
            pplHand.add(c);
        }
        else if(c.getType().equals("location")) {
            placesHand.add(c);
        }
    }

    //------------------------------------------------------
    // getIndex
    //
    // PURPOSE:    returns the player's position at the table.
    // Returns: returns the player's position at the table.
    //------------------------------------------------------
    public int getIndex(){
        return index;
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

        String location = g.getLocation().getValue();
        String weapon = g.getWeapon().getValue();
        String suspect = g.getSuspect().getValue();

        Card result = null;

        if(pplHand.contains((Card)g.getSuspect()))
        {
            result = g.getSuspect();
            System.out.print("Player "+ this.getIndex() +" answered. ");
        }
        else if(placesHand.contains((Card)g.getLocation()))
        {
            result = g.getLocation();
            System.out.print("Player "+ this.getIndex() +" answered. ");
        }
        else if(weaponsHand.contains((Card)g.getWeapon()))
        {
            result = g.getWeapon();
            System.out.print("Player "+ this.getIndex() +" answered. ");
        }
        return result;
    }

    //------------------------------------------------------
    // getGuess
    //
    // PURPOSE:    Considers whether to make a guess of an accusation
    // depending on which Cards the Player is aware of.  Makes a guess
    // or accusation.
    // Returns: Returns a guess if possible, else returns null.
    //------------------------------------------------------
    public Guess getGuess(){

        boolean accusation = false;
        Card weapon;
        Card suspect;
        Card location;

        Guess guess = null;

        System.out.println("Player " + this.getIndex() + "'s turn:");

        if(ppl.size() == 1 && places.size() == 1 && weapons.size() == 1)
        {
            accusation = true;
        }

        weapon = weapons.get((int) (Math.random()*weapons.size()));
        suspect = ppl.get((int)(Math.random()*ppl.size()));
        location = places.get((int)(Math.random()*places.size()));

        guess = new Guess(accusation, weapon, suspect, location);

        if(guess.getAccusation()){
            System.out.println("Player " + this.getIndex() + ": Accusation:  " + guess.print() + ".\n");
        }
        else
        {
            System.out.println("Player " + this.getIndex() + ": Suggestion:  " + guess.print() + ".\n");
        }
        return guess;
    }

    //------------------------------------------------------
    // receiveInfo
    //
    // PURPOSE:    Player made a guess, and is gets an answer
    // from another player.
    // PARAMETERS:
    //     Takes in Player who is responding along with the Card
    // that they are responding with.
    //------------------------------------------------------
    public void receiveInfo(IPlayer ip, Card c){

        if (c != null){
            if(c.getType().equals("weapon"))
            {
                weapons.remove(c);
            }
            else if(c.getType().equals("suspect"))
            {
                ppl.remove(c);
            }
            else if(c.getType().equals("location"))
            {
                places.remove(c);
            }
            ;
        }
        else
        {
            System.out.println("Player " + getIndex()+ " was unable to answer.");
        }
    }
}
