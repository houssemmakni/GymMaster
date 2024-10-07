/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import entities.Commande;
import org.controlsfx.control.Notifications;

import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author Houssem
 */
public class AdminCommandeController implements Initializable {
    @FXML
    private TableView<Commande> table;

    @FXML
    private Button ReturnBtn;

    @FXML
    private TableColumn<Commande, Integer> idcommande;
    @FXML
    private TableColumn<Commande, Integer> idarticle;
    @FXML
    private TableColumn<Commande, String> nbr;
    @FXML
    private TableColumn<Commande, String> addressec;
    @FXML
    private TableColumn<Commande, String> client;
    @FXML
     private TableColumn<Commande, String> ntel;
    @FXML
    private Text statCommande;
    @FXML
    private Text Statarticle;
    ObservableList<Commande>  list =  FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override

 public void initialize(URL url, ResourceBundle rb) {


        ObservableList<Commande>  list =  FXCollections.observableArrayList();
          try { 
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT id, idarticle, nombre, addresse_commande, nom_prenom_commande, tel_commande FROM commande");
            while(rs.next())
            {
                list.add(new Commande(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }

       idcommande.setCellValueFactory(data ->
        {
        return new ReadOnlyObjectWrapper<>(data.getValue().getId());
        });
    //image_colonne.setCellValueFactory(new PropertyValueFactory<>("image"));
        idarticle.setCellValueFactory(data ->
        {
         return new ReadOnlyObjectWrapper<>(data.getValue().getIdArticle());
        });
         nbr.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getNombre());
        });
          addressec.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getAddresse_commande());
        });
        client.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getNom_prenom_commande());
        });
        ntel.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getTel_commande());
        });
    
     
  
        // TODO
     table.setItems(list);
     table.refresh();
    } 

    @FXML
   private void stat(ActionEvent event) {
    try {
        Connection cnx = DataSource.getInstance().getCnx();

        // Query to count the number of commandes
        ResultSet rsCommandeCount = cnx.createStatement().executeQuery("SELECT COUNT(*) FROM commande");
        if (rsCommandeCount.next()) {
            int commandeCount = rsCommandeCount.getInt(1);
            statCommande.setText(" " + commandeCount);
        }

        // Query to count the number of articles (you should adjust this query as per your database structure)
        ResultSet rsArticleCount = cnx.createStatement().executeQuery("SELECT COUNT(*) FROM article");
        if (rsArticleCount.next()) {
            int articleCount = rsArticleCount.getInt(1);
            Statarticle.setText(" " + articleCount);
        }
    } catch (SQLException ex) {
        Logger.getLogger(AdminCommandeController.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    @FXML
    private void supprimer(ActionEvent event) {
       
    
    // Get the selected item from the TableView
    Commande selectedCommande = table.getSelectionModel().getSelectedItem();

    if (selectedCommande != null) {
        try {
            // Get the connection
            Connection cnx = DataSource.getInstance().getCnx();

            // Prepare the SQL statement for deletion
            String deleteQuery = "DELETE FROM commande WHERE id = ?";
            try (PreparedStatement preparedStatement = cnx.prepareStatement(deleteQuery)) {
                // Set the parameter for the prepared statement
                preparedStatement.setInt(1, selectedCommande.getId());

                // Execute the deletion
                preparedStatement.executeUpdate();

                // Remove the item from the TableView
                table.getItems().remove(selectedCommande);

                // Show a notification or message for successful deletion
                Notifications.create()
                        .title("Success")
                        .text("Commande deleted successfully.")
                        .showInformation();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminCommandeController.class.getName()).log(Level.SEVERE, null, ex);
            // Show an error notification or message in case of an exception
            Notifications.create()
                    .title("Error")
                    .text("Error deleting commande.")
                    .showError();
        }
    } else {
        // Show a warning if no item is selected
        Notifications.create()
                .title("Warning")
                .text("Please select a commande to delete.")
                .showWarning();
    }
}




@FXML
public void handleReturnBtn(ActionEvent event) {
try{ Parent ret1Root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
        Scene Scene4 = new Scene(ret1Root);
       
       
        // Create a new stage  interface
        Stage ret1Stage = new Stage();
        ret1Stage.initStyle(StageStyle.TRANSPARENT);
        ret1Stage.setScene(Scene4);
        ret1Stage.show();
       
        // Close the splash screen stage
        Stage splash4 = (Stage) ReturnBtn.getScene().getWindow();
        splash4.close();
    } catch (IOException ex) {
           System.out.println("");
    }
    }
  
        
    }

