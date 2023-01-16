
package serverofcallbridge;

import javafx.scene.image.Image;

public class ServerCardClass {
    private final ServerSuit suit;
    private final ServerRank rank;
    private int cardValue;
    
     ServerCardClass(ServerSuit s,ServerRank r){
        suit = s;
        rank = r;
        cardValue =s.getSuitValue()+r.getRankValue();
    }

    public int getCardValue() {
        return cardValue;
    }
     public ServerSuit getSuit() {
        return suit;
    }

   
    public String toString()
    {
        return String.format("%s of %s",rank,suit);
    }

   
}
