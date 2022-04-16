// CLASS: UnitTest
//
// Author: Ryan Campbell
//
// REMARKS: A class that handles all UnitTesting
// for the "Clue" based card game.
//-----------------------------------------

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

public class UnitTests
{
    int numComputers;
    //There are 3 types of cards, weapon, suspect and location.
    ArrayList<Card> weapons = new ArrayList<Card>();
    ArrayList<Card> suspects = new ArrayList<Card>();
    ArrayList<Card> locations = new ArrayList<Card>();

    //Cards stores a copy of all cards.
    ArrayList<Card> cards = new ArrayList<Card>();

    //List of all players (Human and Computer Players)
    ArrayList<Player> players = new ArrayList<Player>();

    @BeforeEach
    public void setup(){

        //Weapon cards (5-8 cards)
        weapons.add(new Card("weapon", "Butcher's Knife"));
        weapons.add(new Card("weapon", "Chainsaw"));
        weapons.add(new Card("weapon", "Shotgun"));
        weapons.add(new Card("weapon", "Axe"));
        weapons.add(new Card("weapon", "Pistol"));
        weapons.add(new Card("weapon", "Hammer"));
        weapons.add(new Card("weapon", "Pitchfork"));
        weapons.add(new Card("weapon", "Poison"));

        //Suspect cards (5-8 cards)
        suspects.add(new Card("suspect", "Mr. X"));
        suspects.add(new Card("suspect", "Albert Wesker"));
        suspects.add(new Card("suspect", "Leon Scott Kennedy"));
        suspects.add(new Card("suspect", "Chris Redfield"));
        suspects.add(new Card("suspect", "Ada Wong"));
        suspects.add(new Card("suspect", "Jill Valentine"));
        suspects.add(new Card("suspect", "Ethan Winters"));
        suspects.add(new Card("suspect", "Rebecca Chambers"));

        //Location cards (5-8 cards)
        locations.add(new Card("location", "Spencer Mansion"));
        locations.add(new Card("location", "Testing Facility"));
        locations.add(new Card("location", "Arklay Mountains"));
        locations.add(new Card("location", "Courtyard"));
        locations.add(new Card("location", "Asylum"));
        locations.add(new Card("location", "Laboratory"));
        locations.add(new Card("location", "Umbrella Facility"));
        locations.add(new Card("location", "Village"));

        cards.addAll(weapons);
        cards.addAll(suspects);
        cards.addAll(locations);
        Collections.shuffle(cards);
    }


    @Test
    public void computerNoCards(){

        System.out.println("Test 1: If a computer player has no cards, canAnswer should return null.");

        numComputers = 1;

        ComputerPlayer computer1 = new ComputerPlayer(numComputers,1, suspects, locations, weapons);

        Guess guess = new Guess(false,weapons.get(0),suspects.get(0),locations.get(0));

        Card result = computer1.canAnswer(guess, null);

        assert (result == null);

    }

    @Test
    public void computerOneCardCanAnswer(){

        System.out.println("Test 2: If a computer player has exactly one card from a guess, canAnswer should return that card.");

        numComputers = 1;

        ComputerPlayer computer1 = new ComputerPlayer(numComputers,1, suspects, locations, weapons);

        Guess guess = new Guess(false,weapons.get(0),suspects.get(0),locations.get(0));

        computer1.setCard(weapons.get(0));

        Card result = computer1.canAnswer(guess, null);

        assert (result == weapons.get(0));

    }

    @Test
    public void computerMoreThanOneCanAnswer(){

        System.out.println("Test 3: If a computer player has more than one card from a guess, canAnswer should return one of the cards.");

        numComputers = 1;

        ComputerPlayer computer1 = new ComputerPlayer(numComputers,1, suspects, locations, weapons);

        Guess guess = new Guess(false,weapons.get(0),suspects.get(0),locations.get(0));

        computer1.setCard(weapons.get(0));
        computer1.setCard(suspects.get(0));
        computer1.setCard(locations.get(0));

        Card result = computer1.canAnswer(guess, null);

        assert(result == weapons.get(0) || result == suspects.get(0) || result == locations.get(0));

    }

    @Test
    public void computerAllButNCards(){

        System.out.println("Test 4: If a computer player has all but n cards (for some number n > 2 that you should choose) from the set of cards, a call to getGuess should return a guess consisting of cards that are not in the Player's hand.");

        numComputers = 1;

        ComputerPlayer computer1 = new ComputerPlayer(numComputers,1, suspects, locations, weapons);

        Guess guess;

        for(int i = 0; i < weapons.size(); i++)
        {
            computer1.setCard(weapons.remove(i));
            computer1.setCard(suspects.remove(i));
            computer1.setCard(locations.remove(i));
        }


        guess = computer1.getGuess();

        //Card result = computer1.canAnswer(guess, null);

        assert(!computer1.weaponsHand.contains(guess.getWeapon()));
        assert(!computer1.pplHand.contains(guess.getSuspect()));
        assert(!computer1.placesHand.contains(guess.getLocation()));

    }

    @Test
    public void computerAllBut3(){

        System.out.println("Test 5: If a computer player has all but 3 cards from the set of cards, a call to getGuess should return an accusation consisting of cards that are not in the Player's hand.");

        numComputers = 1;

        ComputerPlayer computer1 = new ComputerPlayer(numComputers,1, suspects, locations, weapons);

        Guess guess;

        for(int i = 0; i < weapons.size()-1; i++)
        {
            computer1.setCard(weapons.remove(i));
            computer1.setCard(suspects.remove(i));
            computer1.setCard(locations.remove(i));
        }

        guess = computer1.getGuess();

        assert(!guess.getAccusation());
    }

}
