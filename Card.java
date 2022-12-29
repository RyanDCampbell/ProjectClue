// CLASS: Card
// Author: Ryan Campbell
// REMARKS: This Class contains the needed
// variables to define the Card Class.
// It contains a constructor with 2 final
// Strings, type and value, and also
// contains a getter method for each.
//-----------------------------------------

public class Card {
    private final String type;
    private final String value;

    public Card(String type, String value) {
       this.type = type;
       this.value = value;
    }

    public String getType(){
        return type;
    }
    public String getValue(){
        return value;
    }
    public String toString() { return "Type: " + type + " Value: " + value;}
}
