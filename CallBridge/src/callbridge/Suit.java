
package callbridge;

public enum Suit {
    HEARTS(300),
    CLUBS(200),
    SPADES(400),
    DIAMONDS(100);
    
    private int SuitValue;
    
    Suit(int v){
        SuitValue = v ;
    }

    public int getSuitValue() {
        return SuitValue;
    }
    
    
}