//-----------------------------------------
// REMARKS: Implement "Clue", a game that
// involves deduction to determine the who,
// where and how of a murder. Each player makes
// guesses about the crime, and then other
// players refute that guess, if possible, based
// on information they have on cards that have
// been dealt to them.
//-----------------------------------------

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {

        int numComputers;

        //There are 3 types of cards. Weapons, suspects and locations.
        ArrayList<Card> weapons = new ArrayList<Card>();
        ArrayList<Card> suspects = new ArrayList<Card>();
        ArrayList<Card> locations = new ArrayList<Card>();

        //Each Cards stores a copy of all cards.
        ArrayList<Card> deck = new ArrayList<>();

        //List of all players (Human and Computer Players)
        ArrayList<Player> players = new ArrayList<Player>();

        //Weapon cards
        weapons.add(new Card("weapon", "Butcher's Knife"));
        weapons.add(new Card("weapon", "Chainsaw"));
        weapons.add(new Card("weapon", "Shotgun"));
        weapons.add(new Card("weapon", "Axe"));
        weapons.add(new Card("weapon", "Pistol"));
        weapons.add(new Card("weapon", "Hammer"));
        weapons.add(new Card("weapon", "Pitchfork"));
        weapons.add(new Card("weapon", "Poison"));

        //Suspect cards
        suspects.add(new Card("suspect", "Mr. X"));
        suspects.add(new Card("suspect", "Albert Wesker"));
        suspects.add(new Card("suspect", "Leon Scott Kennedy"));
        suspects.add(new Card("suspect", "Chris Redfield"));
        suspects.add(new Card("suspect", "Ada Wong"));
        suspects.add(new Card("suspect", "Jill Valentine"));
        suspects.add(new Card("suspect", "Ethan Winters"));
        suspects.add(new Card("suspect", "Rebecca Chambers"));

        //Location cards
        locations.add(new Card("location", "Spencer Mansion"));
        locations.add(new Card("location", "Testing Facility"));
        locations.add(new Card("location", "Arklay Mountains"));
        locations.add(new Card("location", "Courtyard"));
        locations.add(new Card("location", "Asylum"));
        locations.add(new Card("location", "Laboratory"));
        locations.add(new Card("location", "Umbrella Facility"));
        locations.add(new Card("location", "Village"));

        deck.addAll(weapons);
        deck.addAll(suspects);
        deck.addAll(locations);
        Collections.shuffle(deck);

        System.out.println("Welcome to \"whodunnit?\"\nHow many computer opponents would you like?");

        Scanner keyboard = new Scanner(System.in);

        //Still need to deal with negative numbers and zero
        while(!keyboard.hasNextInt()){

            System.out.println("Please enter a number greater than 0.\nHow many computer opponents would you like?");
            keyboard = new Scanner(System.in);
        }
        numComputers = keyboard.nextInt();
        keyboard.nextLine();

        if(numComputers < 1)
        {
            System.out.println("The minimum number of computer opponents is 1.");
            numComputers = 1;
        }

        System.out.println("Here are the names of all the suspects:");
        String output = "";
        Card currCard;
        for(int i = 0; i < suspects.size(); i++)
        {
            currCard = suspects.get(i);
            if(i < suspects.size()-1)
            {
                output += currCard.getValue() + ", ";
            }
            else
            {
                output += currCard.getValue() + "\n";
            }
        }
        System.out.println(output);

        System.out.println("Here are all the locations:");
        output = "";
        for(int i = 0; i < locations.size(); i++)
        {
            currCard = locations.get(i);
            if(i < locations.size()-1)
            {
                output += currCard.getValue() + ", ";
            }
            else
            {
                output += currCard.getValue() + "\n";
            }
        }
        System.out.println(output);

        System.out.println("Here are all the weapons:");
        output = "";
        for(int i = 0; i < weapons.size(); i++)
        {
            currCard = weapons.get(i);
            if(i < weapons.size()-1)
            {
                output += currCard.getValue() + ", ";
            }
            else
            {
                output += currCard.getValue() + "\n";
            }
        }
        System.out.println(output);

        Model game = new Model(deck, weapons, suspects, locations, players);

        game.setupPlayers(players, weapons,suspects,locations,numComputers + 1);

        System.out.println("Dealing cards...");

        game.setupCards(deck);

        System.out.println("Playing...");

        game.play();

        System.out.println("Game over");

    }





}
