// CLASS: Guess
//
// Author: Ryan Campbell
//
// REMARKS: This Class contains the variables
// needed to define a Guess. A Guess consists
// of a boolean for accusation, and 3 Cards,
// a weapon, a suspect and a location.
// This Class contains a constructor and
// getter methods for each variable
//-----------------------------------------

public class Guess
{
    private boolean accusation;
    private Card weapon;
    private Card suspect;
    private Card location;

    public Guess(boolean accusation, Card weapon, Card suspect, Card location){
        this.accusation = accusation;
        this.weapon = weapon;
        this.suspect = suspect;
        this.location = location;
    }

    public boolean getAccusation() {
        return accusation;
    }

    public Card getWeapon(){
        return weapon;
    }

    public Card getSuspect() {
        return suspect;
    }

    public Card getLocation(){
        return location;
    }

    public String print()
    {
        return suspect.getValue() + " in the " + location.getValue() +  " with the " + weapon.getValue();
    }
}
