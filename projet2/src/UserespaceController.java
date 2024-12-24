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
public class UserespaceController implements Initializable {
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
    private TableColumn<ConferenceData,String> soum;

    @FXML
    private TableColumn<ConferenceData,String> titre;

    @FXML
    private TableColumn<ConferenceData,String> topics;


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
            instt.setCellValueFactory(new PropertyValueFactory<>("inst"));
            titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
            lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
            topics.setCellValueFactory(new PropertyValueFactory<>("topics"));
            soum.setCellValueFactory(new PropertyValueFactory<>("datelimsoum"));
            ins.setCellValueFactory(new PropertyValueFactory<>("datelimins"));
            mt.setCellValueFactory(new PropertyValueFactory<>("montant"));

            tableview.setItems(data);
          
        } catch (SQLException ex) {
            Logger.getLogger(UserespaceController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                Logger.getLogger(UserespaceController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        
    }    
    
}
