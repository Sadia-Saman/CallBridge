
package callbridge;

import javafx.scene.image.Image;


public class Card {
    private final Suit suit;
    private final Rank rank;
    private final Image img;
    private int cardValue;
    
    public Card(Suit s,Rank r,Image CardImage){
        suit = s;
        rank = r;
        img = CardImage;
        cardValue =s.getSuitValue()+r.getRankValue();
    }

    public int getCardValue() {
        return cardValue;
    }

    public Image getImg() {
        return img;
    }

    
    
    public String toString()
    {
        return String.format("%s of %s",rank,suit);
    }
}