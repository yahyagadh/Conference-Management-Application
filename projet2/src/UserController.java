/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import com.mysql.cj.xdevapi.Statement;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mghir
 */
public class UserController implements Initializable {
         private Connection conn;
    private ResultSet rs;
    private PreparedStatement pst;
    private TableView<ConferenceData> tableview;
 private  ObservableList<ConferenceData> data = FXCollections.observableArrayList();

    @FXML
    private TableColumn<ConferenceData,String> date;

    @FXML
    private TableColumn<ConferenceData,String> ins;

    @FXML
    private TableColumn<ConferenceData,String> instt;

    @FXML
    private TableColumn<ConferenceData,String> lieu;

    @FXML
    private TableColumn<ConferenceData,String> mt;
      @FXML
    private TableColumn<ConferenceData,String> topics;

    @FXML
    private TableColumn<ConferenceData,String> soum;

    @FXML
    private TableView<ConferenceData> tableview2;

    @FXML
    private TableColumn<ConferenceData,String> titre;
    //deuxeme tableaux
    
    @FXML
    private TableColumn<conferencier, String> nom;

    @FXML
    private TableColumn<conferencier, String> pays;

    @FXML
    private TableColumn<conferencier, String> prenom;

    @FXML
    private TableColumn<conferencier, String> pres;
    @FXML
    private TableColumn<conferencier, String> instt1;
     private ObservableList<conferencier> conferencier = FXCollections.observableArrayList();
     @FXML
    private TableView<conferencier> tableview3;
    @FXML
    private Button stat;
  


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
            instt.setCellValueFactory(new PropertyValueFactory<>("inst"));
            titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
            lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
            topics.setCellValueFactory(new PropertyValueFactory<>("topics"));
            soum.setCellValueFactory(new PropertyValueFactory<>("datelimsoum"));
            ins.setCellValueFactory(new PropertyValueFactory<>("datelimins"));
            mt.setCellValueFactory(new PropertyValueFactory<>("montant"));

            tableview2.setItems(data);
          
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        
    
      try {
            conn = dbconnection.getConnection();
            rs = conn.createStatement().executeQuery("SELECT `nom`, `prenom`, `pays`, `inst`, `titre` FROM `conferencier`");

            while (rs.next()) {
                conferencier conferencier = new conferencier(
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("pays"),
                        rs.getString("inst"),
                        rs.getString("titre")
                );
                tableview3.getItems().add(conferencier);
            }

            // Set up column cell value factories
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            pays.setCellValueFactory(new PropertyValueFactory<>("paysOrigine"));
            instt1.setCellValueFactory(new PropertyValueFactory<>("institution"));
            pres.setCellValueFactory(new PropertyValueFactory<>("titrePresentation"));
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    
}

   void imprimer(ActionEvent event) {

    }

    @FXML
    void soumettre(ActionEvent event) {
           try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Soumissionarticles.fxml"));
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
    private void stat(ActionEvent event) {
            try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("statetinfos.fxml"));
            Parent root = fxmlLoader.load();
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("espace utilisateur");
            stage.show();
        } catch (IOException ex) {
            System.out.println("Error loading consultertous.fxml: " + ex.getMessage());
        }
    }

   
    private void showAlert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

        
    }



