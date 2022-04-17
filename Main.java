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
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        int numComputers = 0;
        int userInput = 0;

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



        System.out.println("Welcome to \"Clue\"!");
        while(numComputers <= 0) {

            System.out.println("How many computer players would you like?");
            Scanner keyboard = new Scanner(System.in);

            if(keyboard.hasNextInt()){
                userInput = keyboard.nextInt();
                //Support 2 - 10 players inclusive.
                if(userInput > 0 && userInput < 10){
                    numComputers = userInput;
                }
            }
            else {
                System.out.println("Please select a number between 1 and 9 inclusive.");
            }
        }



        System.out.println("Here are the names of all the suspects:");
        outputDeck(suspects);

        System.out.println("Here are the names of all the locations:");
        outputDeck(locations);

        System.out.println("Here are the names of all the weapons:");
        outputDeck(weapons);



        Model game = new Model(deck, weapons, suspects, locations, players);
        game.setupPlayers(players, weapons, suspects, locations, numComputers + 1);

        System.out.println("Dealing cards...");
        game.setupCards(deck);

        System.out.println("\nBeginning Game.");
        game.play();

        System.out.println("Game over.");
    }

    private static void outputDeck(ArrayList<Card> deck) {
        Card currCard;
        StringBuilder output = new StringBuilder();
        for(int i = 0; i < deck.size(); i++) {
            currCard = deck.get(i);
            if(i < deck.size() - 1) {
                output.append(currCard.getValue()).append(", ");
            }
            else {
                output.append(currCard.getValue()).append("\n");
            }
        }
        System.out.println(output);
    }
}
