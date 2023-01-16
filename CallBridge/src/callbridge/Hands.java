
package callbridge;

import java.util.ArrayList;

public class Hands  {

    private ArrayList<Card> bottom;
      
    Hands(){
        bottom= new ArrayList<Card>();  
    }

    public void setBottom(ArrayList<Card> bottom) {
        this.bottom = bottom;
    }
    
    
    public ArrayList<Card> getBottom() {
        int i=0;
        Card []a = new Card[13];
        for(Card c :bottom){
            a[i]=c;
            i++;
        }
        
        for(int x=0;x<13;x++){
            
            for(int y=x+1;y<13;y++){
                if(a[x].getCardValue()>a[y].getCardValue()){
                    Card temp = a[x];
                    a[x] =a[y];
                    a[y]=temp;
                }
            }
            
        }
        bottom.removeAll(bottom);
        
        for(int x=0;x<13;x++){
            bottom.add(a[x]);
        }
        return bottom;
    }

}
