
package serverofcallbridge;

import java.util.ArrayList;

public class ServerHand {
    private ServerDeck allCards;
    private ArrayList<ServerCardClass> right;
    private ArrayList<ServerCardClass> left;
    private ArrayList<ServerCardClass> bottom;
    private ArrayList<ServerCardClass> top;
    
    
    ServerHand(ServerDeck deck){
        allCards = new ServerDeck();
        allCards=deck;        
        
        right = new ArrayList<ServerCardClass>();
        left = new ArrayList<ServerCardClass>();
        bottom= new ArrayList<ServerCardClass>();
        top= new ArrayList<ServerCardClass>();
        
        
    }
    
    public void divideCards(){
        allCards.shuffle();
        int i=0;
        for(ServerCardClass c:allCards.getCards()){
            if(i<13) right.add(c);
            else if(i<26) left.add(c);
            else if(i<39) top.add(c);
            else bottom.add(c);
            i++;
        }
        sort();
    }
    public void sort(){
         int i=0;
        ServerCardClass []a = new ServerCardClass[13];
        for(ServerCardClass c :left){
            a[i]=c;
            i++;
        }
        
        for(int x=0;x<13;x++){
            
            for(int y=x+1;y<13;y++){
                if(a[x].getCardValue()>a[y].getCardValue()){
                    ServerCardClass temp = a[x];
                    a[x] =a[y];
                    a[y]=temp;
                }
            }
        }
       
        left.removeAll(left);
        for(int x=0;x<13;x++){
            left.add(a[x]);
        }
        i=0;
        for(ServerCardClass c :right){
            a[i]=c;
            i++;
        }
        
        for(int x=0;x<13;x++){
            for(int y=x+1;y<13;y++){
                if(a[x].getCardValue()>a[y].getCardValue()){
                    ServerCardClass temp = a[x];
                    a[x] =a[y];
                    a[y]=temp;
                }
            }
        }
        right.removeAll(right);
        for(int x=0;x<13;x++){
            right.add(a[x]);
        }
        i=0;
        for(ServerCardClass c :top){
            a[i]=c;
            i++;
        }
        
        for(int x=0;x<13;x++){
            for(int y=x+1;y<13;y++){
                if(a[x].getCardValue()>a[y].getCardValue()){
                    ServerCardClass temp = a[x];
                    a[x] =a[y];
                    a[y]=temp;
                }
            }
        }
        top.removeAll(top);
        for(int x=0;x<13;x++){
            top.add(a[x]);
        }

        i=0;
        for(ServerCardClass c :bottom){
            a[i]=c;
            i++;
        }
        
        for(int x=0;x<13;x++){
            
            for(int y=x+1;y<13;y++){
                if(a[x].getCardValue()>a[y].getCardValue()){
                    ServerCardClass temp = a[x];
                    a[x] =a[y];
                    a[y]=temp;
                }
            }
            
        }
        bottom.removeAll(bottom);
        
        for(int x=0;x<13;x++){
            bottom.add(a[x]);
        }
    }

    public ArrayList<ServerCardClass> getRight() {
        return right;
    }

    public ArrayList<ServerCardClass> getLeft() {
        return left;
    }

    public ArrayList<ServerCardClass> getBottom() {
        return bottom;
    }

    public ArrayList<ServerCardClass> getTop() {
        return top;
    }
    
}
