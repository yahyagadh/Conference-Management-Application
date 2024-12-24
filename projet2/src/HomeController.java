import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeController implements Initializable {

        @FXML
    private Button button4;

    // Action method for Button 1
    @FXML
    void handleButton1(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loginadmin.fxml"));
            Parent root = fxmlLoader.load();
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("login");
            stage.show();
        } catch (IOException ex) {
            System.out.println("Error loading createconf.fxml: " + ex.getMessage());
        }
    }

    // Action method for Button 2
    @FXML
    void handleButton2(ActionEvent event) {
         try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("logincommite.fxml"));
            Parent root = fxmlLoader.load();
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("login");
            stage.show();
        } catch (IOException ex) {
            System.out.println("Error loading createconf.fxml: " + ex.getMessage());
        }
    }

    // Action method for Button 3
    @FXML
    void handleButton3(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("user.fxml"));
            Parent root = fxmlLoader.load();
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("espace utilisateur");
            stage.show();
        } catch (IOException ex) {
            System.out.println("Error loading consultertous.fxml: " + ex.getMessage());
        }
    }
       @FXML
    void handleButton4(ActionEvent event) {
          try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("inscritparticipant.fxml"));
            Parent root = fxmlLoader.load();
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("login");
            stage.show();
        } catch (IOException ex) {
            System.out.println("Error loading createconf.fxml: " + ex.getMessage());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    void setPrimaryStage(Stage primaryStage) {
      //
    }
}
