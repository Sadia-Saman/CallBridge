
package callbridge;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public  class DeckOfCards {
    private  ArrayList<Card> cards;
    private ImageView []face;
    private static int imageIdx;
    
     DeckOfCards(){
        cards = new ArrayList<Card>();
        face = new ImageView[52];
        imageIdx=0;
    }
    
    public void addFace(ImageView i){
        face[imageIdx] =i;
        imageIdx++;
    }
    public void populate(){
        int i=0;
        for(Suit suit : Suit.values()){
            for(Rank rank:Rank.values()){
                Card card = new Card(suit ,rank,face[i].getImage());
                cards.add(card);
                i++;
            }
        }
    }
    
    public void shuffle(){
        Random rand = new Random();
        for(int i=cards.size()-1;i>0;i--){
            int p =rand.nextInt(i);
            Card c =cards.get(i);
            cards.set(i, cards.get(p));
            cards.set(p, c);
            
        }
    }
    
    
    /*public void printDeck(){
        int i=0;
        for(Card card:cards){
            System.out.print(card);
            i++;
        }
        System.out.println(i);
    }*/

    public ArrayList<Card> getCards() {
        return cards;
    }
          
}
