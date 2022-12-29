// CLASS: ComputerPlayer
//
// Author: Ryan Campbell
//
// REMARKS: This Class extends the Player
// Class.  This class has several methods
// defined that will override the parent's
// Class to specialize methods for computer
// players.
//-----------------------------------------

import java.util.ArrayList;

public class ComputerPlayer extends Player {

    public ComputerPlayer(int numPlayers, int index, ArrayList<Card> suspects, ArrayList<Card> locations, ArrayList<Card>  weapons) {
        this.setUp(numPlayers, index, suspects, locations, weapons);
    }

    //------------------------------------------------------
    // setCard
    //
    // PURPOSE:    Provides additional processing to the Computer
    // player's setCard method.  When a Computer player is dealt a
    // card, the Computer Player removes that card from the possible
    // solutions to the game.
    //     Takes in a Card as a parameter to add to the Player's hand
    //------------------------------------------------------
    public void addCard(Card card) {

        super.addCard(card);
        //Remove the cards in the hands of the computer player from the possible solution.
        switch (card.getType()) {
            case "weapon" -> weapons.remove(card);
            case "suspect" -> suspects.remove(card);
            case "location" -> locations.remove(card);
        }
    }
}
