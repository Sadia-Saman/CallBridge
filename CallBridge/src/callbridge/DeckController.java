
package callbridge;

import java.io.*;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class DeckController implements Initializable {
     
    private Socket socket;
    private DataInputStream is;
    private DataOutputStream os;
    private String []cardfromServer;
    private int i;
    private int score;
    private boolean []canClick;
    private Card myCard[];
    boolean canIClick ;
    
    
    @FXML private ImageView club_ace;
    @FXML private ImageView club_king;
    @FXML private ImageView club_queen;
    @FXML private ImageView club_jack;
    @FXML private ImageView club_ten;
    @FXML private ImageView club_nine;
    @FXML private ImageView club_eight;
    @FXML private ImageView club_seven;
    @FXML private ImageView club_six;
    @FXML private ImageView club_five;
    @FXML private ImageView club_four;
    @FXML private ImageView club_three;
    @FXML private ImageView club_two;
    
    @FXML private ImageView diamonds_ace;
    @FXML private ImageView diamonds_King;
    @FXML private ImageView diamonds_queen;
    @FXML private ImageView diamonds_jack;
    @FXML private ImageView diamonds_ten;
    @FXML private ImageView diamonds_nine;
    @FXML private ImageView diamonds_eight;
    @FXML private ImageView diamonds_seven;
    @FXML private ImageView diamonds_six;
    @FXML private ImageView diamonds_five;
    @FXML private ImageView diamonds_four;
    @FXML private ImageView diamonds_three;
    @FXML private ImageView diamonds_two;
    
    @FXML private ImageView hearts_ace;
    @FXML private ImageView hearts_King;
    @FXML private ImageView hearts_queen;
    @FXML private ImageView hearts_jack;
    @FXML private ImageView hearts_ten;
    @FXML private ImageView hearts_nine;
    @FXML private ImageView hearts_eight;
    @FXML private ImageView hearts_seven;
    @FXML private ImageView hearts_six;
    @FXML private ImageView hearts_five;
    @FXML private ImageView hearts_four;
    @FXML private ImageView hearts_three;
    @FXML private ImageView hearts_two;
    
    @FXML private ImageView spade_ace;
    @FXML private ImageView spade_King;
    @FXML private ImageView spade_queen;
    @FXML private ImageView spade_jack;
    @FXML private ImageView spade_ten;
    @FXML private ImageView spade_nine;
    @FXML private ImageView spade_eight;
    @FXML private ImageView spade_seven;
    @FXML private ImageView spade_six;
    @FXML private ImageView spade_five;
    @FXML private ImageView spade_four;
    @FXML private ImageView spade_three;
    @FXML private ImageView spade_two;
    
    @FXML private ImageView n;
    @FXML private ImageView OpponentImage;
    @FXML private ImageView rightImage;
    @FXML private ImageView leftImage;
    @FXML private ImageView topImage;
    @FXML private ImageView myImage;
    @FXML private Button gameStart;
    @FXML private Label lebel;
    @FXML private Label myScore;
    
    @FXML private DeckOfCards deck;
    
    @FXML private Hands hands;
    private String toServer;
    private String fromServer;
    private String PlayerId;
    private String OpponentId;
    private String myCardValue;
    
    private double rightOpponentX;
    private double rightOpponentY;
    private double leftOpponentX;
    private double leftOpponentY;
    private double frontOpponentX;
    private double frontOpponentY;
    
    
    
    @FXML
    public void move() throws IOException{
        if(fromServer.contains("4")){
            os.writeUTF("yes");
            os.flush();
            
        }
               
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fromServer ="";
        canIClick = true;
        OpponentImage = new ImageView();
        OpponentImage.setImage(null);
        rightImage.setImage(null);
        leftImage.setImage(null);
        topImage.setImage(null);
        makeDeck();
        makeHands();
        cardfromServer = new String[13];
        setRightPlayerCard();
        setLeftPlayerCard();
        setTopPlayerCard();
        n.setVisible(false);
        i =0;
        myCard = new Card[13];
        canClick = new boolean[13];
        score=0;
        for(int p=0;p<13;p++) canClick[p]=true;
        myScore.setText("You");
        try {
            socket = new Socket("localhost",2222);
            System.out.println("sent request");
            is = new DataInputStream(socket.getInputStream());
            os = new DataOutputStream(socket.getOutputStream());
            PlayerId =is.readUTF();
            if(PlayerId.contains("1")) lebel.setText("First Player");
            else if(PlayerId.contains("2")) lebel.setText("Second Player");
            else if(PlayerId.contains("3")) lebel.setText("Third Player");
            else if(PlayerId.contains("4")){
                fromServer =PlayerId;
                lebel.setText("Forth Player");
            }
            setOpponentPosition();
            
             playing play = new playing();
             play.start();
            
        } catch (IOException ex) {
            Logger.getLogger(DeckController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
       
    
    

    @FXML
    private void makeDeck(){
        deck = new DeckOfCards();
  
        deck.addFace(hearts_ace);
        deck.addFace(hearts_King);
        deck.addFace(hearts_queen);
        deck.addFace(hearts_jack);
        deck.addFace(hearts_ten);
        deck.addFace(hearts_nine);
        deck.addFace(hearts_eight);
        deck.addFace(hearts_seven);
        deck.addFace(hearts_six);
        deck.addFace(hearts_five);
        deck.addFace(hearts_four);
        deck.addFace(hearts_three);
        deck.addFace(hearts_two);
        
        
        deck.addFace(club_ace);
        deck.addFace(club_king);
        deck.addFace(club_queen);
        deck.addFace(club_jack);
        deck.addFace(club_ten);
        deck.addFace(club_nine);
        deck.addFace(club_eight);
        deck.addFace(club_seven);
        deck.addFace(club_six);
        deck.addFace(club_five);
        deck.addFace(club_four);
        deck.addFace(club_three);
        deck.addFace(club_two);
        
        
        deck.addFace(spade_ace);
        deck.addFace(spade_King);
        deck.addFace(spade_queen);
        deck.addFace(spade_jack);
        deck.addFace(spade_ten);
        deck.addFace(spade_nine);
        deck.addFace(spade_eight);
        deck.addFace(spade_seven);
        deck.addFace(spade_six);
        deck.addFace(spade_five);
        deck.addFace(spade_four);
        deck.addFace(spade_three);
        deck.addFace(spade_two);
        
        deck.addFace(diamonds_ace);
        deck.addFace(diamonds_King);
        deck.addFace(diamonds_queen);
        deck.addFace(diamonds_jack);
        deck.addFace(diamonds_ten);
        deck.addFace(diamonds_nine);
        deck.addFace(diamonds_eight);
        deck.addFace(diamonds_seven);
        deck.addFace(diamonds_six);
        deck.addFace(diamonds_five);
        deck.addFace(diamonds_four);
        deck.addFace(diamonds_three);
        deck.addFace(diamonds_two);
        deck.populate();
    
    }
    
    
    @FXML
    private void makeHands(){
        hands = new Hands();

    }
    
    @FXML
    private void setLeftPlayerCard(){
        diamonds_ace.setImage(n.getImage());
        diamonds_King.setImage(n.getImage());
        diamonds_queen.setImage(n.getImage());
        diamonds_jack.setImage(n.getImage());
        diamonds_ten.setImage(n.getImage());
        diamonds_nine.setImage(n.getImage());
        diamonds_eight.setImage(n.getImage());
        diamonds_seven.setImage(n.getImage());
        diamonds_six.setImage(n.getImage());
        diamonds_five.setImage(n.getImage());
        diamonds_four.setImage(n.getImage());
        diamonds_three.setImage(n.getImage());
        diamonds_two.setImage(n.getImage());
       
    }
       
    
    
    @FXML
    private void setRightPlayerCard(){

        club_ace.setImage(n.getImage());
        club_king.setImage(n.getImage());
        club_queen.setImage(n.getImage());
        club_jack.setImage(n.getImage());
        club_ten.setImage(n.getImage());
        club_nine.setImage(n.getImage());
        club_eight.setImage(n.getImage());
        club_seven.setImage(n.getImage());
        club_six.setImage(n.getImage());
        club_five.setImage(n.getImage());
        club_four.setImage(n.getImage());
        club_three.setImage(n.getImage());
        club_two.setImage(n.getImage());
    
    }
    
    
    
    @FXML
    private void setTopPlayerCard(){
        spade_ace.setImage(n.getImage());
        spade_King.setImage(n.getImage());
        spade_queen.setImage(n.getImage());
        spade_jack.setImage(n.getImage());
        spade_ten.setImage(n.getImage());
        spade_nine.setImage(n.getImage());
        spade_eight.setImage(n.getImage());
        spade_seven.setImage(n.getImage());
        spade_six.setImage(n.getImage());
        spade_five.setImage(n.getImage());
        spade_four.setImage(n.getImage());
        spade_three.setImage(n.getImage());
        spade_two.setImage(n.getImage());
  
    }
    private void setBottomPlayerCard(){
       ArrayList<Card>  bottom =hands.getBottom();
       int i=0;
        for(Card c :bottom){
            myCard[i] =c;
            if(i==0) hearts_ace.setImage(c.getImg());
            else if(i==1) hearts_King.setImage(c.getImg());
            else if(i==2) hearts_queen.setImage(c.getImg());
            else if(i==3) hearts_jack.setImage(c.getImg());
            else if(i==4) hearts_ten.setImage(c.getImg());
            else if(i==5) hearts_nine.setImage(c.getImg());
            else if(i==6) hearts_eight.setImage(c.getImg());
             else if(i==7) hearts_seven.setImage(c.getImg());
            else if(i==8) hearts_six.setImage(c.getImg());
            else if(i==9) hearts_five.setImage(c.getImg());
            else if(i==10) hearts_four.setImage(c.getImg());
            else if(i==11) hearts_three.setImage(c.getImg());
            else  hearts_two.setImage(c.getImg());
            i++;
            
        }
    }
    
   
    @FXML
    private void setRightCardPosition(){
 
        club_ace.setX(533);
        club_ace.setY(-210);
        
        club_king.setX(533);
        club_king.setY(-180);
        
        club_queen.setX(533);
        club_queen.setY(-150);
        
        club_jack.setX(533);
        club_jack.setY(-120);
        
        club_ten.setX(533);
        club_ten.setY(-90);
        
        club_nine.setX(533);
        club_nine.setY(-60);
        
        club_eight.setX(533);
        club_eight.setY(-30);
        
        club_seven.setX(533);
        club_seven.setY(0);
        
        club_six.setX(533);
        club_six.setY(30);
        
        club_five.setX(533);
        club_five.setY(60);
             
        club_four.setX(533);
        club_four.setY(90);
                 
        club_three.setX(533);
        club_three.setY(120);

        club_two.setX(533);
        club_two.setY(150);
    
    }
    
    
    @FXML
    private void setLeftCardPosition(){
        
        diamonds_ace.setX(-633);
        diamonds_ace.setY(-210);
        
        diamonds_King.setX(-633);
        diamonds_King.setY(-180);

        diamonds_queen.setX(-633);
        diamonds_queen.setY(-150);
        
        diamonds_jack.setX(-633);
        diamonds_jack.setY(-120);
        
        diamonds_ten.setX(-633);
        diamonds_ten.setY(-90);
        
        diamonds_nine.setX(-633);
        diamonds_nine.setY(-60);
        
        diamonds_eight.setX(-633);
        diamonds_eight.setY(-30);
        
        diamonds_seven.setX(-633);
        diamonds_seven.setY(0);
        
        diamonds_six.setX(-633);
        diamonds_six.setY(30);
        
        diamonds_five.setX(-633);
        diamonds_five.setY(60);
        
        diamonds_four.setX(-633);
        diamonds_four.setY(90);
        
        diamonds_three.setX(-633);
        diamonds_three.setY(120);
        
        diamonds_two.setX(-633);
        diamonds_two.setY(150);        
        
    }
    @FXML
    private void setTopCardPosition(){
        
        spade_ace.setX(-210);
        spade_ace.setY(-350);
        
        spade_King.setX(-180);
        spade_King.setY(-350);
        
        spade_queen.setX(-150);
        spade_queen.setY(-350);
        
        spade_jack.setX(-120);
        spade_jack.setY(-350);
        
        spade_ten.setX(-90);
        spade_ten.setY(-350);
        
        spade_nine.setX(-60);
        spade_nine.setY(-350);
        
        spade_eight.setX(-30);
        spade_eight.setY(-350);
        
        spade_seven.setX(0);
        spade_seven.setY(-350);
        
        spade_six.setX(30);
        spade_six.setY(-350);
        
        spade_five.setX(60);
        spade_five.setY(-350);
        
        spade_four.setX(90);
        spade_four.setY(-350);
        
        spade_three.setX(120);
        spade_three.setY(-350);
        
        spade_two.setX(150);
        spade_two.setY(-350);
        
    }
    
    @FXML
    private void setBottomCardPosition(){
        
        hearts_ace.setX(-450);
        hearts_ace.setY(233);
        
        hearts_King.setX(-375);
        hearts_King.setY(233);
        
        hearts_queen.setX(-300);
        hearts_queen.setY(233);
        
        hearts_jack.setX(-225);
        hearts_jack.setY(233);
        
        hearts_ten.setX(-150);
        hearts_ten.setY(233);
        
        hearts_nine.setX(-75);
        hearts_nine.setY(233);
        
        hearts_eight.setX(0);
        hearts_eight.setY(233);
         
        hearts_seven.setX(75);
        hearts_seven.setY(233);
        
        hearts_six.setX(150);
        hearts_six.setY(233);
        
        hearts_five.setX(225);
        hearts_five.setY(233);
        
        hearts_four.setX(300);
        hearts_four.setY(233);
        
        hearts_three.setX(375);
        hearts_three.setY(233);
        
        hearts_two.setX(450);
        hearts_two.setY(233);
        
        
        
    }
    
    
    
    public void drag(MouseEvent event) throws IOException{
        double x,y;       
        boolean isClicked = false;
        
        x=event.getSceneX();
        y=event.getSceneY();
        
        if(y>=550 && (x>=230 && x<=1210) && canIClick){
            
            if(x>=230 && x<308 && canClick[0]){
                isClicked = true;
                System.out.println(myCard[0].toString());
                myCardValue = myCard[0].getCardValue()+"";
                os.writeUTF(myCard[0].toString());
                os.flush();
            }
            else if(x>=308 && x<383 && canClick[1]){
                isClicked = true;
                System.out.println(myCard[1].toString());
                myCardValue = myCard[1].getCardValue()+"";
                os.writeUTF(myCard[1].toString());
                os.flush();
            }
            else if(x>=383 && x<458 && canClick[2]){
                isClicked = true;
                System.out.println(myCard[2].toString());
                myCardValue = myCard[2].getCardValue()+"";
                os.writeUTF(myCard[2].toString());
                os.flush();
            }
            else if(x>=458 && x<533 && canClick[3]){
                isClicked = true;
                System.out.println(myCard[3].toString());
                myCardValue = myCard[3].getCardValue()+"";
                os.writeUTF(myCard[3].toString());
                os.flush();
            }
            else if(x>=533 && x<608 && canClick[4]){
                isClicked = true;
                System.out.println(myCard[4].toString());
                myCardValue = myCard[4].getCardValue()+"";
                os.writeUTF(myCard[4].toString());
                os.flush();
            }
            else if(x>=608 && x<683 && canClick[5]){
                isClicked = true;
                System.out.println(myCard[5].toString());
                myCardValue = myCard[5].getCardValue()+"";
                os.writeUTF(myCard[5].toString());
                os.flush();
            }
            else if(x>=683 && x<758 && canClick[6]){
                isClicked = true;
                System.out.println(myCard[6].toString());
                myCardValue = myCard[6].getCardValue()+"";
                os.writeUTF(myCard[6].toString());
                os.flush();
            }
            else if(x>=758 && x<833 && canClick[7]){
                isClicked = true;
                System.out.println(myCard[7].toString());
                myCardValue = myCard[7].getCardValue()+"";
                os.writeUTF(myCard[7].toString());
                os.flush();
            }
            else if(x>=833 && x<908 && canClick[8]){
                isClicked = true;
                System.out.println(myCard[8].toString());
                myCardValue = myCard[8].getCardValue()+"";
                os.writeUTF(myCard[8].toString());
                os.flush();
            }
            else if(x>=908 && x<983 && canClick[9]){
                isClicked = true;
                System.out.println(myCard[9].toString());
                myCardValue = myCard[9].getCardValue()+"";
                os.writeUTF(myCard[9].toString());
                os.flush();
            }
            else if(x>=983 && x<1058 && canClick[10]){
                isClicked = true;
                System.out.println(myCard[10].toString());
                myCardValue = myCard[10].getCardValue()+"";
                os.writeUTF(myCard[10].toString());
                os.flush();
            }
            else if(x>=1058 && x<1133 && canClick[11]){
                isClicked = true;
                System.out.println(myCard[11].toString());
                myCardValue = myCard[11].getCardValue()+"";
                os.writeUTF(myCard[11].toString());
                os.flush();
            }
            else if(x>=1133 && x<1210 && canClick[12]){
                isClicked = true;
                System.out.println(myCard[12].toString());
                myCardValue = myCard[12].getCardValue()+"";
                os.writeUTF(myCard[12].toString());
                os.flush();
            }
            if(isClicked ==true){
                myImage.setImage(((ImageView)event.getSource()).getImage());
                ((ImageView)event.getSource()).setImage(null);
                isClicked = false;
                canIClick = false;
            }
           
        }

    }
    
    public class playing extends Thread{
        @Override
        public void run(){
            while(true){
                try {

                    fromServer=is.readUTF();
                    if(fromServer.contains("score")){
                        canIClick = false;
                        Thread.sleep(2000);
                        if(fromServer.contains(myCardValue)){
                            canIClick = true;
                            myCardValue="";
                            for(int p=0;p<13;p++) canClick[p]=true;
                            score++;
                            String p ="You "+score;
                            Platform.runLater(new Runnable() {
                                public void run() {
                                    int d=0;
                                    while(d<5){
                                        myScore.setText(p);
                                        try {
                                            Thread.sleep(1000);

                                        } catch (InterruptedException ex) {
                                            Logger.getLogger(DeckController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        d++;
                                    }
                                }
                               
                            });
                                                        
                        }
                        eraseCards();
                        os.writeUTF("");
                        os.flush();
                    }
                    else if(fromServer.contains("1") || fromServer.contains("2") || fromServer.contains("3") || fromServer.contains("4")){
                        if(fromServer.contains("1")) OpponentId ="1";
                        else if(fromServer.contains("2")) OpponentId ="2";
                        else if(fromServer.contains("3")) OpponentId ="3";
                        else if(fromServer.contains("4")) OpponentId ="4";
                        for(int p=0;p<13;p++) canClick[p]=false;
                        placePlayerCard();
                        
                        os.writeUTF("done");
                        os.flush();
                       // System.out.println("player card boshaichi!yei!");
                    }
                    else if(fromServer.contains("of")){
                        if(i<13){
                            //System.out.println("card nei??");
                            cardfromServer[i++] = fromServer;
                        }
                        if(i==13){
                           
                            findCard();
                            setBottomPlayerCard();
                            setRightCardPosition();
                            setLeftCardPosition();
                            setTopCardPosition();
                            setBottomCardPosition();
                        }
                        
                    }
                    else if(fromServer.contains("x"))
                    {
                       // System.out.println("suit paichos beta?");
                        checkClick(fromServer);
                    }
                    
                    //System.out.println("i = "+i+" server says "+fromServer);
                    

                } catch (IOException ex) {
                    System.out.println("Player Stopped Streaming.");
                    return;
                } catch (InterruptedException ex) {
                    Logger.getLogger(DeckController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    
    public void findCard(){
        ArrayList<Card> b = new ArrayList<Card>();
        for(int j=0;j<13;j++){
            for(Card c :deck.getCards()){
                if(c.toString().equals(cardfromServer[j])){
                    b.add(c);
                    
                    break;
                }
            }
        }
        hands.setBottom(b);
        
    }
    
    public void placePlayerCard(){
        canIClick = false;
        for(Card c:deck.getCards()){
            if(fromServer.contains(c.toString())) {
                OpponentImage.setImage(c.getImg());
                //System.out.println("opponentImage "+OpponentImage);
                if(PlayerId.contains("1"))
                {
                    if(OpponentId.contains("2")) setRightOpponentCard(OpponentImage);
                    else if(OpponentId.contains("3")) setTopOpponentCard(OpponentImage);
                    else if(OpponentId.contains("4")){
                        setLeftOpponentCard(OpponentImage);
                        canIClick = true;
                    }
                }
                else if(PlayerId.contains("2"))
                {
                    if(OpponentId.contains("3")) setRightOpponentCard(OpponentImage);
                    else if(OpponentId.contains("4")) setTopOpponentCard(OpponentImage);
                    else if(OpponentId.contains("1")) {
                        setLeftOpponentCard(OpponentImage);
                        canIClick = true;
                    }
                }
                else if(PlayerId.contains("3"))
                {
                    if(OpponentId.contains("4")) setRightOpponentCard(OpponentImage);
                    else if(OpponentId.contains("1")) setTopOpponentCard(OpponentImage);
                    else if(OpponentId.contains("2")) {
                        setLeftOpponentCard(OpponentImage);
                        canIClick = true;
                    }
                }
                else if(PlayerId.contains("4"))
                {
                    if(OpponentId.contains("1")) setRightOpponentCard(OpponentImage);
                    else if(OpponentId.contains("2")) setTopOpponentCard(OpponentImage);
                    else if(OpponentId.contains("3")) {
                        setLeftOpponentCard(OpponentImage);
                        canIClick = true;
                    }
                }
            }
        }
    }
    
    public void setOpponentPosition(){
       
        rightOpponentX = 50;
        rightOpponentY = 50;

        frontOpponentX = -25;
        frontOpponentY = -100;

        leftOpponentX = -50;
        leftOpponentX = 50;
    }
    
    public void setRightOpponentCard(ImageView i){
        rightImage.setImage(i.getImage());
       // rightImage.setX(rightOpponentX);
       // rightImage.setY(rightOpponentY);

    }
    public void setLeftOpponentCard(ImageView i){
        leftImage.setImage(i.getImage());
       // leftImage.setX(leftOpponentX);
       // leftImage.setY(leftOpponentY);

    }
    public void setTopOpponentCard(ImageView i){
        topImage.setImage(i.getImage());
       // topImage.setX(frontOpponentX);
        //topImage.setY(frontOpponentY);

    }
    public void checkClick(String suit){
        suit=suit.replace("x","");
        System.out.println("suit "+suit);
        int check=0;
        for(int id =0;id<13;id++){
            if(myCard[id].toString().contains(suit)){
                System.out.println("selected card");
                canClick[id] = true;
                check=1;
            }
        }
        if(check==0){
            System.out.println("trump card"); 
            for(int id =0;id<13;id++){
                if(myCard[id].toString().contains("SPADES")){
                    
                    canClick[id] = true;
                    check=1;
                }
            }
        }
        if(check==0){
            System.out.println("sob card");
            for(int id =0;id<13;id++){
                canClick[id] = true;
            }
        }
    }
    
    public void eraseCards(){
        myImage.setImage(null);
        leftImage.setImage(null);
        rightImage.setImage(null);
        topImage.setImage(null);
    }
}
