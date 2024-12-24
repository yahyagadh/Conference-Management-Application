import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;

public class ConsulterarticleController implements Initializable {
    @FXML
    private TableView<Article> tablearicle;

    @FXML
    private TableColumn<Article, Integer> idArticleColumn;

    @FXML
    private TableColumn<Article, String> titreColumn;

    @FXML
    private TableColumn<Article, String> nomAuteurColumn11;

    @FXML
    private TableColumn<Article, String> mailAuteurColumn;

    @FXML
    private TableColumn<Article, String> etatColumn;
        @FXML
    private TableView<Comite> tablecommiteee;
        
    @FXML
    private TableColumn<Comite, String> idcommitee;
            @FXML
    private TableColumn<Comite, String> nomcommite;
            
    @FXML
    private TableColumn<Comite, String> mailcommite;
     @FXML
    private Button affecter;

   

   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configureTableColumns();
        loadArticlesFromDatabase();
         configureCommiteTableColumns();
        loadCommiteFromDatabase();
        
         // Associer la méthode handleArticleSelection() à l'événement de sélection d'une ligne dans tablearicle
    tablearicle.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        if (newSelection != null) {
            handleArticleSelection();
        }
    });
    }
    private void configureTableColumns() {
        idArticleColumn.setCellValueFactory(new PropertyValueFactory<>("idArticle"));
        titreColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
        nomAuteurColumn11.setCellValueFactory(new PropertyValueFactory<>("nomAuteur"));
        mailAuteurColumn.setCellValueFactory(new PropertyValueFactory<>("mailAuteur"));
        etatColumn.setCellValueFactory(new PropertyValueFactory<>("etat"));
    }
     private void loadArticlesFromDatabase() {
        try {
            Connection connection = dbconnection.getConnection();
            Statement statement = (Statement) connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id_article, titre, nomauteur, mailauteur, etat FROM article");

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

            tablearicle.setItems(articlesList);

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }}
             private void configureCommiteTableColumns() {
        idcommitee.setCellValueFactory(new PropertyValueFactory<>("idComite"));
        nomcommite.setCellValueFactory(new PropertyValueFactory<>("nom"));
        mailcommite.setCellValueFactory(new PropertyValueFactory<>("emailAuteur"));
    }

    private void loadCommiteFromDatabase() {
        try (Connection connection = dbconnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT commite_id, nom, emailauteur FROM commite WHERE role = 'scientific'")) {

            ObservableList<Comite> commiteList = FXCollections.observableArrayList();
            while (resultSet.next()) {
                String idComite = resultSet.getString("commite_id");
                String nom = resultSet.getString("nom");
                String emailAuteur = resultSet.getString("emailauteur");

                Comite comite = new Comite(idComite, nom, emailAuteur);
                commiteList.add(comite);
            }

            tablecommiteee.setItems(commiteList);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }

    }
  @FXML
void handleArticleSelection() {
    Article selectedArticle = tablearicle.getSelectionModel().getSelectedItem();
    if (selectedArticle != null) {
        // Sélectionne l'article correspondant dans la table commite
        for (Comite comite : tablecommiteee.getItems()) {
            try {
                int comiteId = Integer.parseInt(comite.getIdComite());
                if (comiteId == selectedArticle.getIdArticle()) {
                    tablecommiteee.getSelectionModel().select(comite);
                    break;
                }
            } catch (NumberFormatException e) {
                // Handle the conversion error appropriately
                System.err.println("Error parsing comite ID: " + e.getMessage());
            }
        }
    }
}

    @FXML
    void affecter() {
       Article selectedArticle = tablearicle.getSelectionModel().getSelectedItem();
    Comite selectedComite = tablecommiteee.getSelectionModel().getSelectedItem();

    if (selectedArticle != null && selectedComite != null) {
        int articleId = selectedArticle.getIdArticle();
        String comiteId = selectedComite.getIdComite();

        // Insérer l'article sélectionné dans la table commite avec son ID
        try (Connection connection = dbconnection.getConnection();
             Statement statement = connection.createStatement()) {

            String updateQuery = "UPDATE article SET etat = 'UE' ";
            int affectedRows = statement.executeUpdate(updateQuery);
            if (affectedRows > 0) {
                // Afficher une alerte de succès
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setHeaderText(null);
                alert.setContentText("L'article a été affecté au comité avec succès.");
                alert.showAndWait();
            } else {
                System.out.println("Erreur lors de l'affectation de l'article au comité.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception de manière appropriée
        }
    } else {
        System.out.println("Veuillez sélectionner un article et un comité.");
    }
    
}}


   

