import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginadminController implements Initializable {

    @FXML
    private Button btn;

    @FXML
    private TextField nom;

    @FXML
    private PasswordField pwrd;

    private Label errorLabel;

    private Connection connection;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize your database connection
        connection = dbconnection.getConnection();
    }

    @FXML
    private void login(ActionEvent event) {
        String username = nom.getText().trim();
        String password = pwrd.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("entrer le pseudo et le mot de passe.");
        } else {
            // Validate the credentials
            if (validateCredentials(username, password)) {
                // Switch to the admin space
                switchToAdminSpace();
            } else {
                errorLabel.setText("mot de passe invalid.");
            }
        }
    }

    private boolean validateCredentials(String username, String password) {
        try {
        // Prepare a SELECT query to check credentials
        String query = "SELECT * FROM admin WHERE nom = ? AND pwrd = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        // Execute the query and check if any result is returned
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return true; // Returns true if credentials are valid
        } else {
            // Show an alert for invalid credentials
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid Credentials");
            alert.setHeaderText(null);
            alert.setContentText("Idetifiant incorrecte.");
            alert.showAndWait();
            return false;
        }

    } catch (SQLException e) {
        System.out.println("SQL Error: " + e.getMessage());
        return false;
    }
        
    }

private void switchToAdminSpace() {
    try {
        // Load the FXML file for the admin space
        FXMLLoader loader = new FXMLLoader(getClass().getResource("admiinespace.fxml"));
        Parent adminSpaceRoot = loader.load();

        // Get the controller of the admin space
        AdmiinespaceController adminSpaceController = loader.getController();

        // Close the connection before switching scenes
        connection.close();

        // Create a new scene with the admin space root
        Scene adminSpaceScene = new Scene(adminSpaceRoot);

        // Get the stage from the current event
        Stage stage = (Stage) btn.getScene().getWindow();

        // Set the new scene on the stage
        stage.setScene(adminSpaceScene);
        stage.show();

    } catch (IOException | SQLException e) {
        System.out.println("Error loading admin espace: " + e.getMessage());
    }
}



    public static class AdminSpaceController {

        public AdminSpaceController() {
        }
    }
}
