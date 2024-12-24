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
public class GererconfrencierController implements Initializable {
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
    private Button refrech;

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
          conferencier selectedConferencier = tableview.getSelectionModel().getSelectedItem();
    if (selectedConferencier == null) {
        // Aucun conférencier sélectionné, affichez un message d'erreur ou une alerte
        return;
    }

    String nomConferencier = name.getText();
    String prenomConferencier = pname.getText();
    String paysOrigine = cpays.getText();
    String institution = instt.getText();
    String titrePresentation = pres.getText();

    try {
        conn = dbconnection.getConnection();
        String query = "UPDATE conferencier SET nom = ?, prenom = ?, pays = ?, inst = ?, titre = ? WHERE nom = ?";
        pst = conn.prepareStatement(query);
        pst.setString(1, nomConferencier);
        pst.setString(2, prenomConferencier);
        pst.setString(3, paysOrigine);
        pst.setString(4, institution);
        pst.setString(5, titrePresentation);
        pst.setString(6, selectedConferencier.getNom()); 

        int rowsUpdated = pst.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Conférencier modifié avec succès !");
            refreshTableView(); // Rafraîchir la TableView après la modification
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
    void suprimer(ActionEvent event) {
         // Récupérer l'élément sélectionné dans la TableView
    conferencier conferencierSelectionne = tableview.getSelectionModel().getSelectedItem();
    if (conferencierSelectionne != null) {
        try {
            conn = dbconnection.getConnection();
            String sql = "DELETE FROM conferencier WHERE nom = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, conferencierSelectionne.getNom());
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                // Suppression réussie dans la base de données, maintenant retirer de la TableView
                tableview.getItems().remove(conferencierSelectionne);
                // Vous pouvez également afficher un message de confirmation
                System.out.println("Le conférencier a été supprimé avec succès.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AjouterconferenciersController.class.getName()).log(Level.SEVERE, null, ex);
            // Gérer l'exception en affichant un message d'erreur ou en effectuant d'autres actions nécessaires
        } finally {
            // Fermer la connexion
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                Logger.getLogger(AjouterconferenciersController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    } else {
        // Aucun élément sélectionné, afficher un message d'avertissement
        System.out.println("Veuillez sélectionner un conférencier à supprimer.");
    }

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
        cpays.setText(selectedItem.getPaysOrigine());
        instt.setText(selectedItem.getInstitution());
        pres.setText(selectedItem.getTitrePresentation());
    }
    }



    @FXML
    private void refreshTableView() {
         tableview.getItems().clear(); // Effacer les données actuelles de la TableView
    // Recharger les données depuis la base de données et les ajouter à la TableView
    initialize(null, null);
    }
}
