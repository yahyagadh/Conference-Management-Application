/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author mghir
 */
public class StatetinfosController implements Initializable {

    @FXML
    private TableColumn<Article, Integer> articleinfo;
    @FXML
    private TableColumn<Article, Integer> auteurinfo;

    @FXML
    private TableView<Article> tableview;
    @FXML
    private TextField totalArticlesField;
    @FXML
    private TextField acceptedArticlesField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     articleinfo.setCellValueFactory(new PropertyValueFactory<>("titre"));
        auteurinfo.setCellValueFactory(new PropertyValueFactory<>("nomAuteur"));
        
        loadArticlesStatistics();

        // Load data from database
        loadDataFromDatabase();    } 
       private void loadDataFromDatabase() {
   try {
            Connection connection = dbconnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id_article, titre, nomauteur, mailauteur, etat FROM article WHERE etat = 'ACC'");
            
            ObservableList<Article> articlesList = FXCollections.observableArrayList();
            while (resultSet.next()) {
                int idArticle = resultSet.getInt("id_article");
                String titre = resultSet.getString("titre");
                String nomAuteur = resultSet.getString("nomauteur");
                String mailAuteur = resultSet.getString("mailauteur");
                String etat = resultSet.getString("etat");

                Article article = new Article(idArticle, titre, nomAuteur, mailAuteur, etat);
                articlesList.add(article);
            }

            tableview.setItems(articlesList);

            resultSet.close();
            statement.close();
            connection.close();

          
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
    }
 private void loadArticlesStatistics() {
        try {
            Connection connection = dbconnection.getConnection();
            Statement statement = connection.createStatement();

            String sql = "SELECT count(*) AS nb_acc FROM article ";
            ResultSet resultSet = statement.executeQuery(sql);

            int nb_acc = 0;
            if (resultSet.next()) {
                nb_acc = resultSet.getInt("nb_acc");
            }

            sql = "SELECT count(*) AS nb_art FROM article WHERE etat = 'ACC'";
            resultSet = statement.executeQuery(sql);
            int nb_art = 0;
            if (resultSet.next()) {
                nb_art = resultSet.getInt("nb_art");
            }

            totalArticlesField.setText(String.valueOf(nb_art));
            if (nb_art != 0) {
              
              double percentage = 0.0;
if (nb_art != 0) {
    percentage = (double) nb_art / nb_acc * 100.0;
    }

acceptedArticlesField.setText(String.format("%.2f%%", percentage));
            } else {
                acceptedArticlesField.setText("0");
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            showAlert("Error", "Error loading  statistics: " + e.getMessage(), AlertType.ERROR);
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
       
    


