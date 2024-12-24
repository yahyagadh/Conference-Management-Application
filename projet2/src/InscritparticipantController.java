/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author mghir
 */
public class InscritparticipantController implements Initializable {
             private Connection conn;
    private ResultSet rs;
    private PreparedStatement pst;
    @FXML
    private TableView<ConferenceData> tableview7;
 private   ObservableList<ConferenceData> data = FXCollections.observableArrayList();

    @FXML
    private TextField nom;
    @FXML
    private TextField mail;
    @FXML
    private TextField pwrd;
    @FXML
    private RadioButton chequeRadioButton;
    @FXML
    private RadioButton especeRadioButton;
    @FXML
    private RadioButton bonDeCommandeRadioButton;
    @FXML
    private RadioButton virementRadioButton;
    @FXML
    private Button innscrir;

    @FXML
    private TableColumn<ConferenceData, String> inst7;
    @FXML
    private TableColumn<ConferenceData, String> titre7;
    @FXML
    private TableColumn<ConferenceData, String> lieu7;
    @FXML
    private TableColumn<ConferenceData, String> topics7;
    @FXML
    private TableColumn<ConferenceData, String> part;
    @FXML
    private TableColumn<ConferenceData, String> mt7;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            conn = dbconnection.getConnection();
            rs = conn.createStatement().executeQuery("SELECT `instiorg`, `president`, `titre`, `date`, `lieu`, `topics`, `membrecomitesc`, `membrerecomiteorg`, `datelimsoum`, `datelimins`, `montant` FROM conference");

            while (rs.next()) {
                data.add(new ConferenceData(
                        rs.getString("instiorg"),
                        rs.getString("president"),
                        rs.getString("titre"),
                        rs.getDate("date"),
                        rs.getString("lieu"),
                        rs.getString("topics"),
                        rs.getString("membrecomitesc"),
                        rs.getString("membrerecomiteorg"),
                        rs.getDate("datelimsoum"),
                        rs.getDate("datelimins"),
                        rs.getString("montant")
                ));
            }

            // Configure your columns
            inst7.setCellValueFactory(new PropertyValueFactory<>("inst"));
            titre7.setCellValueFactory(new PropertyValueFactory<>("titre"));
            lieu7.setCellValueFactory(new PropertyValueFactory<>("lieu"));
            topics7.setCellValueFactory(new PropertyValueFactory<>("topics"));
            part.setCellValueFactory(new PropertyValueFactory<>("datelimsoum"));
            mt7.setCellValueFactory(new PropertyValueFactory<>("montant"));

            tableview7.setItems(data);
          
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }    
       @FXML
    void handleConferenceSelection(ActionEvent event) {
     

    }

    @FXML
    private void inscription(ActionEvent event) {
    

         // Récupérer la date de participation de la conférence sélectionnée dans le TableView
    ConferenceData selectedConference = tableview7.getSelectionModel().getSelectedItem();
    Date dateLimiteInscription = selectedConference.getDatelimins();

    // Comparer la date limite d'inscription avec la date actuelle
    Date currentDate = new Date();
    if (dateLimiteInscription != null && currentDate.before(dateLimiteInscription)) {
        // Continuer le processus d'inscription
        String nomValue = nom.getText();
        String mailValue = mail.getText();
        String pwrdValue = pwrd.getText();
        String modePaiement = getSelectedPaymentMethod();

        Connection conn = dbconnection.getConnection();
        if (conn != null) {
            try {
                String sql = "INSERT INTO participants (nom, mail, password, mode_paiement) VALUES (?, ?, ?, ?)";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, nomValue);
                statement.setString(2, mailValue);
                statement.setString(3, pwrdValue);
                statement.setString(4, modePaiement);

                statement.executeUpdate();
                  afficherAlerte(AlertType.INFORMATION, "Succès", "utilisateur inscrit avec succes.");            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    } else {
        // Afficher une alerte si la date limite d'inscription est dépassée
        afficherAlerte(AlertType.ERROR, "Erreur", "La date d'inscription est dépassée.");
    }
           }
    

    private void afficherAlerte(AlertType type, String titre, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    

   private String getSelectedPaymentMethod() {
        if (chequeRadioButton.isSelected()) {
            return "Par chèque";
        } else if (virementRadioButton.isSelected()) {
            return "Virement bancaire";
        } else if (bonDeCommandeRadioButton.isSelected()) {
            return "Bon de commande d’une institution";
        } else if (especeRadioButton.isSelected()) {
            return "En espèce sur place";
        } else {
            return "Non spécifié";
        }
    }
}









