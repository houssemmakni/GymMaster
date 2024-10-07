/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import services.ServiceArticle;
import entities.Article;
import org.controlsfx.control.Notifications;
import utils.DataSource;


/**
 * FXML Controller class
 *
 * @author Houssem
 */
public class AffichearticleController implements Initializable {

    @FXML
    private TableView<Article> Table;
    @FXML
    private TableColumn<Article, String> titre_colonne;
    @FXML
    private TableColumn<Article, String> image_colonne;
    @FXML
    private TableColumn<Article, String> description_colonne;
    @FXML
    private TableColumn<Article, String> prix_colonne;
    @FXML
    private TextField TFTitre;
    @FXML
    private TextField TFImage;
    @FXML
    private TextField TFDesc;
    @FXML
    private TextField TFPrix;
    @FXML
    private Button ReturnBtn;
    @FXML
    private Button btn;
  ObservableList<Article>  list =  FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {


        ObservableList<Article>  list =  FXCollections.observableArrayList();
          try { 
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT titre_article, image, description_article, prix FROM article");
            while(rs.next())
            {
                list.add(new Article(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AffichearticleController.class.getName()).log(Level.SEVERE, null, ex);
        }

       titre_colonne.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().gettitre_article());
        });
    //image_colonne.setCellValueFactory(new PropertyValueFactory<>("image"));
image_colonne.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getimage());
        });
    description_colonne.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getdescription_article());
        });
    prix_colonne.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getprix());
        });
     
  
        // TODO
 Table.setItems(list);
  Table.refresh();
    }    

    @FXML
    private void getSele(MouseEvent event) {
         int index = Table.getSelectionModel().getSelectedIndex();
        if(index<= -1)
        {
            return;
        }
        TFTitre.setText(titre_colonne.getCellData(index).toString());
        TFImage.setText(image_colonne.getCellData(index).toString());
        TFDesc.setText(description_colonne.getCellData(index).toString());
        TFPrix.setText(prix_colonne.getCellData(index).toString());
    }

    @FXML
    private void Ajouterarticle(ActionEvent event) {   
      
       ServiceArticle sp = new ServiceArticle();
       sp.ajouter(new Article(TFTitre.getText(),TFImage.getText(),TFDesc.getText(),TFPrix.getText()));
       Table.refresh();
       try{ 
       Notifications notifications=Notifications.create();
        notifications.text("Ajouté");
        notifications.title("Success Message");
        /*notifications.darkStyle();*/
     /*   notifications.position(Pos.BOTTOM_CENTER);*/
        notifications.show();}
        catch(Exception e){
            e.printStackTrace();
        }

    }

   @FXML
    private void supprimerArticle(ActionEvent event) {
         ServiceArticle sp = new ServiceArticle();
        sp.supprimer(new Article(TFTitre.getText()));
        // JOptionPane.showMessageDialog(null, "Article supprimé !");
        Table.getItems().removeAll(Table.getSelectionModel().getSelectedItem());
          Table.refresh();
             Notifications notifications=Notifications.create();
        notifications.text("supprimé");
        notifications.title("Success Message");
        /*notifications.darkStyle();*/
     /*   notifications.position(Pos.BOTTOM_CENTER);*/
        notifications.show();
    }


    @FXML
    private void modifierArticle(ActionEvent event) {
    
      ServiceArticle sp = new ServiceArticle();
        sp.modifier(new Article(TFTitre.getText(),TFImage.getText(),TFDesc.getText(),TFPrix.getText()));
    Table.refresh();
       Notifications notifications=Notifications.create();
        notifications.text("modifié");
        notifications.title("Success Message");
        /*notifications.darkStyle();*/
     /*   notifications.position(Pos.BOTTOM_CENTER);*/
        notifications.show();
        //JOptionPane.showMessageDialog(null, "Article Modifier !");

    }



    @FXML
private void upload(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    File selectedFile = fileChooser.showOpenDialog(null);

    if (selectedFile != null) {
        // Assuming you have a method to get the selected Article, replace it with your logic
        Article selectedArticle = getSelectedArticle();

        // Set the imageFile attribute in your Article object
        if (selectedArticle != null) {
            selectedArticle.setImageFile(selectedFile);

            // Save the image file to a destination folder
            saveImageFile(selectedFile);

            // Assuming you have a method to convert File to a String path
            String imagePath = convertToFileURL(selectedFile);
            TFImage.setText(imagePath);
        }
    }
}

// Helper method to convert File to a String path
private String convertToFileURL(File file) {
    return file.toURI().toString();
}

// Example method to get the selected Article (replace it with your logic)
private Article getSelectedArticle() {
    int selectedIndex = Table.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {
        return Table.getItems().get(selectedIndex);
    }
    return null;
}

// Helper method to save the image file to a destination folder
private void saveImageFile(File sourceFile) {
    // Specify the destination folder path
    String destinationFolderPath = "uploads";

    // Create the destination folder if it doesn't exist
    File destinationFolder = new File(destinationFolderPath);
    if (!destinationFolder.exists()) {
        destinationFolder.mkdirs();
    }

    // Create the destination file path
    String destinationFilePath = destinationFolderPath + sourceFile.getName();

    // Copy the source file to the destination folder
    File destinationFile = new File(destinationFilePath);
    try {
        Files.copy(sourceFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
        e.printStackTrace();
        // Handle the exception as needed
    }
}

    @FXML
    private void refresh(ActionEvent event) {
          // ImageView image1 = new ImageView (new Image(this.getClass().getResourceAsStream("")));  
           ObservableList<Article>  list =  FXCollections.observableArrayList();
          try { 
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT titre_article, image, description_article, prix FROM article");
            while(rs.next())
            {
                list.add(new Article(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AffichearticleController.class.getName()).log(Level.SEVERE, null, ex);
        }

       titre_colonne.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().gettitre_article());
        });

       image_colonne.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getimage());
        }); 
   description_colonne.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getdescription_article());
        });

    prix_colonne.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getprix());
        });
         
        // TODO
 Table.setItems(list);
  Table.refresh();
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
