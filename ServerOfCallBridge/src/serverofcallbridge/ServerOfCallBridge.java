
package serverofcallbridge;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import static serverofcallbridge.ServerOfCallBridge.nameOfCard;
import static serverofcallbridge.ServerOfCallBridge.score;
import static serverofcallbridge.ServerOfCallBridge.scoreIdx;
import static serverofcallbridge.ServerOfCallBridge.writer;

public class ServerOfCallBridge {
    static HashSet<DataOutputStream>  writer ;
    static int []score;
    static int scoreIdx;
    static String nameOfCard;
    
    
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(2222);
        writer = new HashSet<>();
        score = new int[4];
        scoreIdx = 0;
        System.out.println("waiting for client...");
        int i=0;
        while(i<4){
            Socket s = server.accept();
            System.out.println("accepted");
            Player player = new Player(s,++i);
            player.start();
        }
        System.out.println("all player joined");
    }
  
}

class Player extends Thread{
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private ServerCardClass cards;
    private int id;
    private ServerDeck sd;
    
    Player(Socket s ,int i) throws IOException{
        socket = s;
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
        writer.add(dos);
        id =i;
        sd = new ServerDeck();
        sd.populate();
        dos.writeUTF(""+id);
        dos.flush();
    }
    
    public void run(){
        while(true){
            try {
                                    
                
                String fromPlayer = dis.readUTF();
                
               // System.out.println("player says "+fromPlayer);
                if(fromPlayer.contains("yes")){
                    
                    ServerHand sh = new ServerHand(sd);
                    sh.divideCards();
                   int i=0;
                    for(DataOutputStream d :writer){
                        if(d.equals(dos)) {
                            dos.writeUTF("yes");
                            dos.flush();
                            for(ServerCardClass c : sh.getBottom()){
                                dos.writeUTF(c.toString());
                                dos.flush();
                            }
                            
                        }
                        else{
                            d.writeUTF("no");
                            d.flush();
                            if(i==0){
                                for(ServerCardClass c : sh.getLeft()){
                                    d.writeUTF(c.toString());
                                    d.flush();
                                }
                            }
                            else if(i==1){
                                for(ServerCardClass c : sh.getRight()){
                                    d.writeUTF(c.toString());
                                    d.flush();
                                }
                            }
                            else if(i==2){
                                for(ServerCardClass c : sh.getTop()){
                                    d.writeUTF(c.toString());
                                    d.flush();
                                 }
                             }
                             i++;
                    }
                } 
             
             } 
            else if(fromPlayer.contains("of")){
               nameOfCard = fromPlayer;
                   
               fromPlayer =fromPlayer+id;
                for(DataOutputStream d :writer){
                    if(d.equals(dos)) continue;
                    else{ 
                        d.writeUTF(fromPlayer);
                        d.flush();
                         
                    }
                }
                //temporary
                for(ServerCardClass card:sd.getCards()){
                    if(nameOfCard.equals(card.toString())){
                       
                        int check =0;
                        for(int k =0;k<scoreIdx;k++){
                            if(score[k]==card.getCardValue())
                                check=1;
                        }
                        if(scoreIdx==0 || check==0) score[scoreIdx++] = card.getCardValue();
                        System.out.println("idx  "+scoreIdx);

                        break;
                    }
                }
                if(scoreIdx>=4){
                    System.out.println("Score 4 hoyeche");
                    int max =0;
                    for(int k =0;k<scoreIdx;k++){
                        System.out.println(score[k]);
                        if(max<score[k]) max = score[k];
                        
                    }
                    for(int k =0;k<scoreIdx;k++){
                       score[k]=0;
                        
                    }
                    nameOfCard ="";
                    scoreIdx=0;
                    for(DataOutputStream d :writer){                    
                        d.writeUTF(max+"score");
                        d.flush();                    
                    }
                    
                    //break;
                }
                
            }
            else if(fromPlayer.contains("done")){
                for(ServerCardClass card:sd.getCards()){
                    if(nameOfCard.equals(card.toString())){
                        for(DataOutputStream d :writer){
                            if(d.equals(dos)) continue;
                            else{                            
                                d.writeUTF(card.getSuit().getSuitName()+"x");
                                d.flush();
                            }
                        }/*
                        int check =0;
                        for(int k =0;k<scoreIdx;k++){
                            if(score[k]==card.getCardValue())
                                check=1;
                        }
                        if(scoreIdx==0 || check==0) score[scoreIdx++] = card.getCardValue();
                        System.out.println("idx  "+scoreIdx);*/

                        break;
                    }
                }/*
                if(scoreIdx>=4){
                    System.out.println("Score 4 hoyeche");
                    int max =0;
                    for(int k =0;k<scoreIdx;k++){
                        System.out.println(score[k]);
                        if(max<score[k]) max = score[k];
                        
                    }
                    for(int k =0;k<scoreIdx;k++){
                       score[k]=0;
                        
                    }
                    nameOfCard ="";
                    scoreIdx=0;
                    for(DataOutputStream d :writer){                    
                        d.writeUTF(max+"score");
                        d.flush();                    
                    }
                    
                    //break;
                }*/
                
            }
                
               
    }       catch (IOException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            }
    
        }
    }
}

    
