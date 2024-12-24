/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AdmiinespaceController implements Initializable {

    @FXML
    private TextField instt;
    @FXML
    private TextField lieuu;
         @FXML
    private TextField mt;

    @FXML
    private TextField org;

    @FXML
    private TextField pres;
    @FXML
    private TextField sc;

    @FXML
    private TextField titt;
    @FXML
    private TextField topp;
       


     @FXML
    private TableView<ConferenceData> TableView;

    @FXML
    private Button ajoutconf;

    @FXML
    private TableColumn<ConferenceData, String> dateColumn;

    @FXML
    private TableColumn<ConferenceData, String> dateliminsColumn;

    @FXML
    private TableColumn<ConferenceData, String> datelimsoumColumn;

    @FXML
    private TableColumn<ConferenceData, String> instColumn;

    @FXML
    private TableColumn<ConferenceData, String> lieuColumn;

    @FXML
    private TableColumn<ConferenceData, String> membrecomorgColumn;

    @FXML
    private TableColumn<ConferenceData, String> membrecomscColumn;

    @FXML
    private TableColumn<ConferenceData, String> montantColumn;

    @FXML
    private TableColumn<ConferenceData, String> presidentColumn;

    @FXML
    private TableColumn<ConferenceData, String> titreColumn;

    @FXML
    private TableColumn<ConferenceData, String> topicsColumn;
        private  ObservableList<ConferenceData> data = FXCollections.observableArrayList();
        private Connection conn;
    private ResultSet rs;
    private PreparedStatement pst;
    @FXML
    private Button mofifer;
    @FXML
    private DatePicker dateee;
    @FXML
    private DatePicker soum;
    @FXML
    private DatePicker inss;
    @FXML
    private Button actualiser;
    @FXML
    private Button consulterarticle;
    
     @FXML
    private Button printButton;
    
    
    

    @FXML
    void ajoutconf(ActionEvent event) {
    try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gererconfrencier.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("ajouter confirencier");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    @FXML
    void cree(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("creeconf.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Créer une conférence");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
@FXML
void modifier(ActionEvent event) {
    // Récupérer l'élément sélectionné dans la TableView
    ConferenceData selectedItem = TableView.getSelectionModel().getSelectedItem();
    if (selectedItem != null) {
        try {
            conn = dbconnection.getConnection();
            String sql = "UPDATE conference SET instiorg=?, president=?, titre=?, lieu=?, topics=?, membrecomitesc=?, membrerecomiteorg=?, montant=? WHERE titre=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, instt.getText()); // Assuming instt is a TextField containing the institution value
            pst.setString(2, pres.getText()); // Assuming pres is a TextField containing the president value
            pst.setString(3, titt.getText()); // Assuming titt is a TextField containing the titre value
            pst.setString(4, lieuu.getText()); // Assuming lieuu is a TextField containing the lieu value
            pst.setString(5, topp.getText()); // Assuming topp is a TextField containing the topics value
            pst.setString(6, sc.getText()); // Assuming sc is a TextField containing the membrecomitesc value
            pst.setString(7, org.getText()); // Assuming org is a TextField containing the membrerecomiteorg value
            pst.setString(8, mt.getText()); // Assuming mt is a TextField containing the montant value
            pst.setString(9, selectedItem.getTitre()); // Assuming selectedItem.getTitre() returns the titre value of the selected conference

            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                // Mise à jour réussie, vous pouvez afficher un message de confirmation
                System.out.println("Conférence mise à jour avec succès.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdmiinespaceController.class.getName()).log(Level.SEVERE, null, ex);
            // Gérer l'exception en affichant un message d'erreur ou en effectuant d'autres actions nécessaires
        } finally {
            // Fermer la connexion
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                Logger.getLogger(AdmiinespaceController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    } else {
        // Aucun élément sélectionné, afficher un message d'avertissement
        System.out.println("Veuillez sélectionner une conférence à modifier.");
    }
}

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
            instColumn.setCellValueFactory(new PropertyValueFactory<>("inst"));
            presidentColumn.setCellValueFactory(new PropertyValueFactory<>("president"));
            titreColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
            lieuColumn.setCellValueFactory(new PropertyValueFactory<>("lieu"));
            topicsColumn.setCellValueFactory(new PropertyValueFactory<>("topics"));
            membrecomscColumn.setCellValueFactory(new PropertyValueFactory<>("memrecomsc"));
            membrecomorgColumn.setCellValueFactory(new PropertyValueFactory<>("membrecomorg"));
            datelimsoumColumn.setCellValueFactory(new PropertyValueFactory<>("datelimsoum"));
            dateliminsColumn.setCellValueFactory(new PropertyValueFactory<>("datelimins"));
            montantColumn.setCellValueFactory(new PropertyValueFactory<>("montant"));

            TableView.setItems(data);
          
        } catch (SQLException ex) {
            Logger.getLogger(AdmiinespaceController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                Logger.getLogger(AdmiinespaceController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    @FXML
    private void getitem(javafx.scene.input.MouseEvent event) {
         int index = TableView.getSelectionModel().getSelectedIndex();
    if (index <= -1) {
        return;
    }

    ConferenceData selectedItem = TableView.getSelectionModel().getSelectedItem();
    if (selectedItem != null) {
        // Update text fields with the data from the selected item
        instt.setText(selectedItem.getInst());
        pres.setText(selectedItem.getPresident());
        titt.setText(selectedItem.getTitre());
       
        lieuu.setText(selectedItem.getLieu());
        topp.setText(selectedItem.getTopics());
        sc.setText(selectedItem.getMemrecomsc());
        org.setText(selectedItem.getMembrecomorg());
       
        mt.setText(selectedItem.getMontant());
    }
    }
    @FXML
        void actualiser(ActionEvent event) {

    
        TableView.getItems().clear(); // Effacer les données actuelles de la TableView
    // Recharger les données depuis la base de données et les ajouter à la TableView
    initialize(null, null);
    }

    @FXML
    private void consulterarticle(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("consulterarticle.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("consulter article");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    @FXML
    private void handlePrintButtonAction(ActionEvent event) {
         try {
        conn = dbconnection.getConnection();
        rs = conn.createStatement().executeQuery("SELECT nom, mail FROM participants");

        StringBuilder content = new StringBuilder();
        while (rs.next()) {
            String nom = rs.getString("nom");
            String email = rs.getString("mail");
            content.append(nom).append(", ").append(email).append("\n");
        }

        // Write content to a text file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("participants.txt"))) {
            writer.write(content.toString());
        }

        showAlert("Succès", "La liste des participants a été imprimée avec succès.", Alert.AlertType.INFORMATION);
    } catch (SQLException | IOException e) {
        e.printStackTrace();
        showAlert("Erreur", "Erreur lors de l'impression de la liste des participants.", Alert.AlertType.ERROR);
    } finally {
        // Close resources in the finally block
        try {
            if (rs != null) rs.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }
     private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

 } 
    

