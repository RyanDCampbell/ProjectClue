// CLASS: Player
// Author: Ryan Campbell
// REMARKS: An abstract Player type that
// implements the IPlayer interface

//-----------------------------------------

import java.util.ArrayList;

public abstract class Player implements IPlayer {
    private int index;

    //Lists of all ppl, places and weapons.
    ArrayList<Card> suspects;
    ArrayList<Card> locations;
    ArrayList<Card> weapons;

    //Particular cards that the player is holding
    ArrayList<Card> suspectsHand;
    ArrayList<Card> locationsHand;
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
    public void setUp(int numPlayers, int index, ArrayList<Card> suspects,
                      ArrayList<Card> locations, ArrayList<Card> weapons) {

        this.index = index;
        this.suspects = suspects;
        this.locations = locations;
        this.weapons = weapons;
        this.suspectsHand = new ArrayList<>();
        this.locationsHand = new ArrayList<>();
        this.weaponsHand = new ArrayList<>();
    }

    //------------------------------------------------------
    // addCard
    //
    // PURPOSE:    When the Player is given a Card, the Player adds
    // the Card to their appropriate hand.
    // PARAMETERS:
    //     Takes in a Card as a parameter to add to the Player's hand
    //------------------------------------------------------
    public void addCard(Card card) {

        switch (card.getType()) {
            case "weapon" -> weaponsHand.add(card);
            case "suspect" -> suspectsHand.add(card);
            case "location" -> locationsHand.add(card);
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
    public Card canAnswer(Guess guess, IPlayer player){

        Card result = null;

        if (suspectsHand.contains(guess.getSuspect())) {
            result = guess.getSuspect();
            System.out.print("Player "+ this.getIndex() + " answered. ");
        }
        else if (locationsHand.contains(guess.getLocation())) {
            result = guess.getLocation();
            System.out.print("Player "+ this.getIndex() + " answered. ");
        }
        else if (weaponsHand.contains(guess.getWeapon())) {
            result = guess.getWeapon();
            System.out.print("Player "+ this.getIndex() + " answered. ");
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
        Guess guess;

        System.out.println("Player " + this.getIndex() + "'s turn:");

        if (suspects.size() == 1 && locations.size() == 1 && weapons.size() == 1) {
            accusation = true;
        }

        weapon = weapons.get((int) (Math.random()*weapons.size()));
        suspect = suspects.get((int)(Math.random()*suspects.size()));
        location = locations.get((int)(Math.random()*locations.size()));
        guess = new Guess(accusation, weapon, suspect, location);

        if (guess.getAccusation()) {
            System.out.println("Player " + this.getIndex() + ": Accusation:  " + guess + ".\n");
        }
        else {
            System.out.println("Player " + this.getIndex() + ": Suggestion:  " + guess + ".\n");
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
    public void receiveInfo(IPlayer player, Card card){
        if (card != null) {
            switch (card.getType()) {
                case "weapon" -> weapons.remove(card);
                case "suspect" -> suspects.remove(card);
                case "location" -> locations.remove(card);
            }
        }
        else {
            System.out.println("Player " + getIndex() + " was unable to answer.");
        }
    }
}
