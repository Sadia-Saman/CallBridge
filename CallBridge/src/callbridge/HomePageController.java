
package callbridge;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class HomePageController implements Initializable {
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
       Parent anotherRoot = FXMLLoader.load(getClass().getResource("DeckOfCards.fxml"));
       Scene anotherScene = new Scene(anotherRoot);
       Stage anotherStage =(Stage)((Node)event.getSource()).getScene().getWindow();
       anotherStage.setScene(anotherScene);
       anotherStage.show();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
