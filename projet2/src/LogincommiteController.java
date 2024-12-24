/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import com.sun.jdi.connect.spi.Connection;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mghir
 */
public class LogincommiteController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private PasswordField pwrd;
    @FXML
    private Button btn;
     @FXML
    private Label errorLabel;
     private java.sql.Connection connection;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connection = dbconnection.getConnection();
    }    
 

    private boolean verifierConnexion(String nom, String role) {
      try {
            // Prepare a SELECT query to check credentials
            String query = "SELECT * FROM commite WHERE nom = ? AND role = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, role);

            // Execute the query and check if any result is returned
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next(); // Returns true if credentials are valid

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            return false;
        }
    }
     private int obtenirIdUtilisateur(String nom) {
        try {
        String query = "SELECT commite_id FROM commite WHERE nom = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, nom);
        ResultSet resultSet = preparedStatement.executeQuery();
if (resultSet.next()) {
    int commite_id = resultSet.getInt("commite_id");
    System.out.println(commite_id); // This line will print the commite_id to the console
    return commite_id;
} else {
    // Handle the case when no result is returned
    System.out.println("pas de commite id .");
    return 0; // Or handle it as needed
}
    } catch (SQLException e) {
        System.out.println("id non recuperer : " + e.getMessage());
    }
    return 0; 
        
    }

    private void afficherAlerte(Alert.AlertType type, String titre, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(titre);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void login(ActionEvent event) {
      String pseudo = nom.getText();
    String role = pwrd.getText();

    if (verifierConnexion(pseudo, role)) {
        int userId = obtenirIdUtilisateur(pseudo);
        if (userId != 0) {
            afficherAlerte(Alert.AlertType.INFORMATION, "Connexion réussie", "welcome, " + pseudo + "!");

            // Appeler la méthode pour passer à une autre page ou effectuer d'autres actions
            switchToAdminSpace(userId);
        } else {
            afficherAlerte(Alert.AlertType.ERROR, "Erreur", "id non recuperer.");
        }
    } else {
        afficherAlerte(Alert.AlertType.ERROR, "Erreur de connexion", "identifiants  incorrect.");
    }
        
        
    }
    private void switchToAdminSpace(int userId) {
    try {
        // Charger le fichier FXML pour l'espace administrateur
        FXMLLoader loader = new FXMLLoader(getClass().getResource("reject.fxml"));
        Parent adminSpaceRoot = loader.load();

        // Créer une nouvelle scène avec l'espace administrateur
        Scene adminSpaceScene = new Scene(adminSpaceRoot);

        // Obtenir la fenêtre principale à partir de l'événement actuel
        Stage stage = (Stage) btn.getScene().getWindow();

        // Définir la nouvelle scène sur la fenêtre principale
        stage.setScene(adminSpaceScene);
        stage.show();

    } catch (IOException e) {
        System.out.println("Erreur lors du chargement de page commite : " + e.getMessage());
    }
}

}

   
    
    

