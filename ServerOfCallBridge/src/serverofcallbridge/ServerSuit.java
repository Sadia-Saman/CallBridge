
package serverofcallbridge;


public enum ServerSuit {
     HEARTS("HEARTS",300),
    CLUBS("CLUBS",200),
    SPADES("SPADES",400),
    DIAMONDS("DIAMONDS",100);
    
    private int SuitValue;
    private String SuitName;
    
    ServerSuit(String n,int v){
        SuitName =n;
        SuitValue = v ;
    }

    public String getSuitName() {
        return SuitName;
    }
    

    public int getSuitValue() {
        return SuitValue;
    }
    
}
