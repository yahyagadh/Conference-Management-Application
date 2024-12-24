/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author mghir
 */
public class RejectController implements Initializable {
     @FXML
    private TableView<Article> tab;
     @FXML
    private Button acc;

    @FXML
    private TableColumn<Article, Integer> auteur4;

    @FXML
    private TableColumn<Article, Integer> etat4;

    @FXML
    private TableColumn<Article, Integer> idarticle4;

    @FXML
    private Button rej;

    @FXML
    private TableColumn<Article, Integer> titart;
    
     private int userId;

   


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         userId = AppData.getInstance().getUserId();
        configureTableColumns();
        loadDataForUser(userId);
    }    
     private void configureTableColumns() {
        idarticle4.setCellValueFactory(new PropertyValueFactory<>("idArticle"));
        titart.setCellValueFactory(new PropertyValueFactory<>("titre"));
        auteur4.setCellValueFactory(new PropertyValueFactory<>("nomAuteur"));
        etat4.setCellValueFactory(new PropertyValueFactory<>("etat"));
    }

    private void loadDataForUser(int userId) {
        try {
            try (Connection connection = dbconnection.getConnection()) {
                String query = "SELECT `id_article`, `titre`, `nomauteur`, `mailauteur`, `etat`, `commite_id` FROM article WHERE commite_id = 3";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                  //  preparedStatement.setInt(1, userId);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
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
                        
                        tab.setItems(articlesList);
                    }
                }
            }
        } catch (SQLException e) {
            // Log the error or show an alert to indicate the issue
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to load data: " + e.getMessage());
            alert.showAndWait();
        }
    }
   
     
    

    @FXML
    void acc(ActionEvent event) {
         Article selectedArticle = tab.getSelectionModel().getSelectedItem();
        if (selectedArticle != null) {
            updateArticleState(selectedArticle.getIdArticle(), "Acc");
            // Assuming userId is available from another source
            loadDataForUser(userId);
        } else {
            System.out.println("Veuillez sélectionner un article.");
        }
    }

    @FXML
    void rej(ActionEvent event) {
          Article selectedArticle = tab.getSelectionModel().getSelectedItem();
        if (selectedArticle != null) {
            updateArticleState(selectedArticle.getIdArticle(), "Rej");
            // Assuming userId is available from another source
            loadDataForUser(userId);
        } else {
            System.out.println("Veuillez sélectionner un article.");
        }}
         private void updateArticleState(int articleId, String newState) {
        try (Connection connection = dbconnection.getConnection()) {
            String query = "UPDATE article SET etat = ? WHERE id_article = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, newState);
                preparedStatement.setInt(2, articleId);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Article ajouter");
                } else {
                    System.out.println("Erreurde mise a jour.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Log the error or show an alert to indicate the issue
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("erreur de mise a jour: " + e.getMessage());
            alert.showAndWait();
        }
    }
    }
    

    

