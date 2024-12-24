/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author mghir
 */
public class SoumissionarticlesController implements Initializable {
         private Connection conn;
    private ResultSet rs;
    private PreparedStatement pst;
    @FXML
    private TableView<ConferenceData> tableview5;
 private   ObservableList<ConferenceData> data = FXCollections.observableArrayList();
    

   @FXML
    private TextField auteur;

    @FXML
    private TextField inst;

    @FXML
    private TableColumn<ConferenceData,String> instt;

    @FXML
    private Hyperlink lien;

    @FXML
    private TableColumn<ConferenceData,String> lieu;

    @FXML
    private TextField mail;

    @FXML
    private TableColumn<ConferenceData,String> mt;

    @FXML
    private TableColumn<ConferenceData,String> soum;

    @FXML
    private Button soumission;


    @FXML
    private TextField titre;

    @FXML
    private TableColumn<ConferenceData,String> titre1;

    @FXML
    private TextField titrearticle;

    @FXML
    private TableColumn<ConferenceData,String> topics;




    /**
     * Initializes the controller class.
     * @param url
     * @param rb
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
            instt.setCellValueFactory(new PropertyValueFactory<>("inst"));
            titre1.setCellValueFactory(new PropertyValueFactory<>("titre"));
            lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
            topics.setCellValueFactory(new PropertyValueFactory<>("topics"));
            soum.setCellValueFactory(new PropertyValueFactory<>("datelimsoum"));
            mt.setCellValueFactory(new PropertyValueFactory<>("montant"));

            tableview5.setItems(data);
          
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } 
       
    }    

    @FXML
    private void soumissionarticle(ActionEvent event) {
        String articleAuteur = auteur.getText();
    String articleMail = mail.getText();
    String articleInst = inst.getText();
  

        if (validerFormulaire( articleAuteur, articleMail, articleInst)) {
            ajouterArticle( articleAuteur, articleMail, articleInst);
            afficherAlerte(AlertType.INFORMATION, "Succès", "Article ajouté !");
        } else {
            afficherAlerte(AlertType.ERROR, "Erreur", "les champs sont requis.");
        }
    }

    private boolean validerFormulaire(String titre, String auteur, String mail) {
        return !titre.isEmpty() && !auteur.isEmpty() && !mail.isEmpty() ;
    }

    private void ajouterArticle(String titre, String auteur, String mail) {
        try {
            String sql = "INSERT INTO article (titre, nomauteur, mailauteur) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, titre);
            statement.setString(2, auteur);
            statement.setString(3, mail);
           

            statement.executeUpdate();
            System.out.println("Article ajouté avec succès !");
        } catch (SQLException ex) {
            Logger.getLogger(SoumissionarticlesController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Erreur lors de l'ajout de l'article : " + ex.getMessage());
        }
    }

    private void afficherAlerte(AlertType type, String titre, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }




    @FXML
    private void getitem2(ActionEvent event) {
                 int index = tableview5.getSelectionModel().getSelectedIndex();
    if (index <= -1) {
        return;
    }

    ConferenceData selectedItem = tableview5.getSelectionModel().getSelectedItem();
    if (selectedItem != null) {
        // Update text fields with the data from the selected item
   
        titre.setText(selectedItem.getTitre());
       
   
       
        mt.setText(selectedItem.getMontant());
    }

    }
    

    }