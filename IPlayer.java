import java.util.ArrayList;

public interface IPlayer {
    void setUp(int numPlayers, int index, ArrayList<Card> suspects, ArrayList<Card> locations, ArrayList<Card> weapons);
    void addCard (Card card);
    int getIndex();
    Card canAnswer(Guess guess, IPlayer player);
    Guess getGuess();
    void receiveInfo(IPlayer player, Card card);
}
