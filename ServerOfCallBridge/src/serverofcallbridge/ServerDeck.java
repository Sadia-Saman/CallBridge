
package serverofcallbridge;

import java.util.ArrayList;
import java.util.Random;

public class ServerDeck {
    private  ArrayList<ServerCardClass> cards;
    
    ServerDeck(){
        cards = new ArrayList<>();
    }
    public void populate(){
        int i=0;
        for(ServerSuit suit : ServerSuit.values()){
            for(ServerRank rank:ServerRank.values()){
                ServerCardClass card = new ServerCardClass(suit ,rank);
                cards.add(card);
                i++;
            }
        }
    }
    
    public void shuffle(){
        Random rand = new Random();
        for(int i=cards.size()-1;i>0;i--){
            int p =rand.nextInt(i);
            ServerCardClass c =cards.get(i);
            cards.set(i, cards.get(p));
            cards.set(p, c);
            
        }
    }
    public ArrayList<ServerCardClass> getCards() {
        return cards;
    }
      
}
