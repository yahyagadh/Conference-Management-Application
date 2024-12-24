/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.awt.event.MouseEvent;
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
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.TouchEvent;

/**
 * FXML Controller class
 *
 * @author mghir
 */
public class AjouterconferenciersController implements Initializable {
    private Connection conn;
    private ResultSet rs;
    private PreparedStatement pst;



   @FXML
    private TableView<conferencier> tableview;
    @FXML
    private Button ajouter;

    @FXML
    private TextField cpays;

    @FXML
    private TableColumn<conferencier, String> inst;

    @FXML
    private TextField instt;

    @FXML
    private Button modifer;

    @FXML
    private TextField name;

    @FXML
    private TableColumn<conferencier, String> nom;

    @FXML
    private TableColumn<conferencier, String> pays;

    @FXML
    private TextField pname;

    @FXML
    private TableColumn<conferencier, String> prenom;

    @FXML
    private TableColumn<conferencier, String> titre;
     private ObservableList<conferencier> conferencier = FXCollections.observableArrayList();

    @FXML
    private TextField pres;

    @FXML
    private Button suprimer;

    @FXML
    void ajouter(ActionEvent event) {
            String nomConferencier = name.getText();
    String prenomConferencier = pname.getText();
    String paysOrigine = cpays.getText();
    String institution = instt.getText();
    String titrePresentation = pres.getText();

    try {
        conn = dbconnection.getConnection();
        String query = "INSERT INTO conferencier (nom, prenom, pays, inst, titre) VALUES (?, ?, ?, ?, ?)";
        pst = conn.prepareStatement(query);
        pst.setString(1, nomConferencier);
        pst.setString(2, prenomConferencier);
        pst.setString(3, paysOrigine);
        pst.setString(4, institution);
        pst.setString(5, titrePresentation);

        int rowsInserted = pst.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Conférencier ajouté avec succès !");
            // Vous pouvez aussi rafraîchir la TableView avec les nouveaux données ici
        }
    } catch (SQLException ex) {
        Logger.getLogger(AjouterconferenciersController.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        // Close resources
        try {
            if (pst != null) pst.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            Logger.getLogger(AjouterconferenciersController.class.getName()).log(Level.SEVERE, null, e);
        }
    }


    }

    @FXML
    void modifeir(ActionEvent event) {

    }

    @FXML
    void suprimer(ActionEvent event) {

    }
    Integer index;

     @Override
    public void initialize(URL url, ResourceBundle rb) {
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
                tableview.getItems().add(conferencier);
            }

            // Set up column cell value factories
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            pays.setCellValueFactory(new PropertyValueFactory<>("paysOrigine"));
            inst.setCellValueFactory(new PropertyValueFactory<>("institution"));
            titre.setCellValueFactory(new PropertyValueFactory<>("titrePresentation"));
        } catch (SQLException ex) {
            Logger.getLogger(AjouterconferenciersController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                Logger.getLogger(AjouterconferenciersController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    @FXML
    private void getitem(javafx.scene.input.MouseEvent event) {
         int index = tableview.getSelectionModel().getSelectedIndex();
    if (index <= -1) {
        return;
    }

    conferencier selectedItem = tableview.getSelectionModel().getSelectedItem();
    if (selectedItem != null) {
        // Mise à jour des champs de texte avec les données de l'élément sélectionné
        name.setText(selectedItem.getNom());
        pname.setText(selectedItem.getPrenom());
        cpays.setText(selectedItem.getTitrePresentation());
        instt.setText(selectedItem.getPaysOrigine());
        pres.setText(selectedItem.getInstitution());
    }
    }

void refreshTableView(TouchEvent event) {
    tableview.getItems().clear(); // Effacer les données actuelles de la TableView
    // Recharger les données depuis la base de données et les ajouter à la TableView
    initialize(null, null);

    }
}


   