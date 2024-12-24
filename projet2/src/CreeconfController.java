/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class CreeconfController implements Initializable {
    @FXML
    private TextField institutionField;

    @FXML
    private TextField presidentField;

    @FXML
    private TextField titleField;

    @FXML
    private TextField locationField;

    @FXML
    private TextField topicsField;

    @FXML
    private TextField scientificCommitteeField;

    @FXML
    private TextField organizingCommitteeField;

    @FXML
    private DatePicker submissionDeadlinePicker;

    @FXML
    private DatePicker registrationDeadlinePicker;

    @FXML
    private TextField registrationFeesField;

    private Connection connection;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connection = dbconnection.getConnection();
    }

    @FXML
    private void ajouter(ActionEvent event) {
        try {
            if (institutionField == null || presidentField == null || titleField == null ||
                locationField == null || topicsField == null || scientificCommitteeField == null ||
                organizingCommitteeField == null || submissionDeadlinePicker == null ||
                registrationDeadlinePicker == null || registrationFeesField == null) {
                System.err.println("One or more fields are not properly initialized.");
                return; // Exit the method if any field is null
            }

            // Retrieve data from the fields
            String institution = institutionField.getText();
            String president = presidentField.getText();
            String title = titleField.getText();
            String currentDate = java.time.LocalDate.now().toString();
            String location = locationField.getText();
            String topics = topicsField.getText();
            String scientificCommittee = scientificCommitteeField.getText();
            String organizingCommittee = organizingCommitteeField.getText();
            String submissionDeadline = submissionDeadlinePicker.getValue().toString();
            String registrationDeadline = registrationDeadlinePicker.getValue().toString();
            String registrationFees = registrationFeesField.getText();

            // Prepare the SQL statement and execute it
           String sql = "INSERT INTO conference (instiorg, president, titre, date, lieu, topics, membrecomitesc, membrerecomiteorg, datelimsoum, datelimins, montant) " +
             "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, institution);
            statement.setString(2, president);
            statement.setString(3, title);
            statement.setString(4, currentDate);
            statement.setString(5, location);
            statement.setString(6, topics);
            statement.setString(7, scientificCommittee);
            statement.setString(8, organizingCommittee);
            statement.setString(9, submissionDeadline);
            statement.setString(10, registrationDeadline);
            statement.setString(11, registrationFees);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Conference tzedet successfully!");
                // You can add further actions here, such as clearing the form or showing a success message.
            } else {
                System.out.println("Failed to add conference.");
                // You can handle failure cases here, such as showing an error message.
            }
        } catch (Exception e) {
            System.err.println("Error adding conference: " + e.getMessage());
            // Handle exceptions here, such as showing an error message to the user.
        }
    }
}

