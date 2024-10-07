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
import java.util.Comparator;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import entities.Article;
import org.controlsfx.control.Notifications;
import utils.DataSource;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;


/**
 * FXML Controller class
 *
 * @author Houssem
 */
public class CommandeController implements Initializable {
    @FXML
    private Button searchButton;

    @FXML
    private TextField searchField;

    @FXML
    private Button sortById;

    @FXML
    private Label idc;
    @FXML
    private Label idp;   
    @FXML
    private Label prixt;   
    @FXML
    private Button imprimer;
    @FXML
    private Label qtt;
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
    private Button Commander;
    @FXML
    private TextField email;
    @FXML
    private TextField nomprenom;
    @FXML
    private TextField addressec;
    @FXML
    private Button ReturnBtn;
    @FXML
    private TextField ntel;
    @FXML
    private TextField nombre;
    @FXML
    private TextField TFid;
    @FXML
    private TextField titre;
    @FXML
    private TableColumn<Article, String> id;
  ObservableList<Article>  list =  FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {


        ObservableList<Article>  list =  FXCollections.observableArrayList();
          try { 
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT id, titre_article, image, description_article, prix FROM article");
            while(rs.next())
            {
                list.add(new Article(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AffichearticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        id.setCellValueFactory(data ->
                {
            int idValue = data.getValue().getidArticle();
            return new ReadOnlyStringWrapper(Integer.toString(idValue));
        });
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
        TFid.setText(id.getCellData(index));
        titre.setText(titre_colonne.getCellData(index));
    }

    @FXML
    private void refresh(ActionEvent event) {
          // ImageView image1 = new ImageView (new Image(this.getClass().getResourceAsStream("")));  
           ObservableList<Article>  list =  FXCollections.observableArrayList();
          try { 
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT id, titre_article, image, description_article, prix FROM article");
            while(rs.next())
            {
                list.add(new Article(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AffichearticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        id.setCellValueFactory(data ->
        {
            int idValue = data.getValue().getidArticle();
            return new ReadOnlyStringWrapper(Integer.toString(idValue));
        });
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
    private void searchArticle(ActionEvent event) {
        String searchText = searchField.getText().toLowerCase();
        ObservableList<Article> filteredList = FXCollections.observableArrayList();

        for (Article article : list) {
            if (article.gettitre_article().toLowerCase().contains(searchText)) {
                filteredList.add(article);
            }
        }

        Table.setItems(filteredList);
    }
  @FXML
    private void sortById(ActionEvent event) {
        ObservableList<Article> sortedList = list.sorted(Comparator.comparing(Article::getidArticle));
        Table.setItems(sortedList);
    }
    @FXML
private void Commander(ActionEvent event) throws SQLException {
    // Get the values from your TextFields
    String emailValue =email.getText();
    String nombreValue = nombre.getText();
    String nomprenomValue = nomprenom.getText();
    String addressecValue = addressec.getText();
    String ntelValue = ntel.getText();


    int idArticleValue = Integer.parseInt(TFid.getText()); // Assuming TFid is for the article ID
    String titreValue = (titre.getText()); // Assuming TFid is for the article ID
    
    // Assuming you have a database connection named "connection"
    try {
         Connection cnx = DataSource.getInstance().getCnx();
        // Create a SQL query to insert data into the "Commande" table
        String insertQuery = "INSERT INTO Commande (nombre, addresse_commande, nom_prenom_commande, tel_commande,idarticle,email,nomarticle) VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = cnx.prepareStatement(insertQuery);
        
        preparedStatement.setString(1, nombreValue);
        preparedStatement.setString(2, addressecValue);
        preparedStatement.setString(3, nomprenomValue);
        preparedStatement.setString(4, ntelValue);
        preparedStatement.setInt(5, idArticleValue);
        preparedStatement.setString(6, emailValue);
        preparedStatement.setString(7, titreValue);
        
        

        // Execute the query to insert the data
        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            // Data inserted successfully
            System.out.println("Commande added to the database.");
        String selectQuery = "SELECT * FROM Commande WHERE idarticle = ? AND nombre = ?";

            PreparedStatement selectStatement = cnx.prepareStatement(selectQuery);
            selectStatement.setInt(1, idArticleValue);
            selectStatement.setString(2, nombreValue);

            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                int commandeId = resultSet.getInt("id");
                idc.setText("      " + commandeId);
                idp.setText("      " + idArticleValue);
                qtt.setText("      " + nombreValue);
             
        String selectPrixQuery = "SELECT prix FROM article WHERE id = ?";
        PreparedStatement selectPrixStatement = cnx.prepareStatement(selectPrixQuery);
        selectPrixStatement.setInt(1, idArticleValue);
        ResultSet prixResultSet = selectPrixStatement.executeQuery();

        if (prixResultSet.next()) {
            String prix = prixResultSet.getString("prix");
            prixt.setText("     " + Integer.parseInt(prix)*Integer.parseInt(nombreValue));
        }
        selectPrixStatement.close();
    }
    selectStatement.close();
          
                
            

            
         
        } else {   
           
            
        
            // Data insertion failed
            System.out.println("Failed to add Commande to the database.");
        }

        // Close the PreparedStatement
        preparedStatement.close();
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle any SQL exception as needed
    }
 
  } 

   @FXML
private void imprimer(ActionEvent event) throws IOException {
    String fileName = "commande.pdf"; // PDF file name
    // Create a new document
    PDDocument document = new PDDocument();
    PDPage page = new PDPage(PDRectangle.A4);
    document.addPage(page);
    // Create a content stream for the page
    PDPageContentStream contentStream = new PDPageContentStream(document, page);
    // Add data to the PDF
    contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.COURIER), 12);
    contentStream.beginText();
    contentStream.newLineAtOffset(50, 700);
    contentStream.showText("Commande ID: " + idc.getText());
    contentStream.newLineAtOffset(0, -20);
    contentStream.showText("ID Produit Commandé: " + idp.getText());
    contentStream.newLineAtOffset(0, -20);
    contentStream.showText("Quantité: " + qtt.getText());
    contentStream.newLineAtOffset(0, -20);
    contentStream.showText("Prix à Payer: " + prixt.getText());
    contentStream.endText();
    // Close the content stream
    contentStream.close();
    // Save the document to a file
    document.save(fileName);
    // Close the document
    document.close();
    // Inform the user that the PDF has been generated
    Notifications.create()
            .title("PDF Created")
            .text("Commande PDF has been generated successfully.")
            .showInformation();

}
@FXML
    void HandleReturnBtn(ActionEvent event) {
try{ Parent ret1Root = FXMLLoader.load(getClass().getResource("UserInterface.fxml"));
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
    /*try {
        generatePdf();
    } catch (FileNotFoundException | DocumentException e) {
        e.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("PDF Export Error");
        alert.setHeaderText("Error exporting to PDF");
        alert.setContentText("An error occurred while exporting the BMC table to PDF. Please try again.");
        alert.showAndWait();
    }
}
    private void generatePdf() throws FileNotFoundException, DocumentException {

         FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
    File selectedFile = fileChooser.showSaveDialog(null);
    if (selectedFile != null) {
        // create a new PDF document
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        PdfWriter.getInstance(document, new FileOutputStream(selectedFile));

        document.open();

        PdfPTable table = new PdfPTable(4);

        PdfPCell pclesCell = new PdfPCell(new Phrase("Adresse Livraison"));
        PdfPCell actclesCell = new PdfPCell(new Phrase("Date Commande"));
        PdfPCell rsclesCell = new PdfPCell(new Phrase("Statut "));
        PdfPCell propvalCell = new PdfPCell(new Phrase("montant total"));
     

        table.addCell(pclesCell);
         table.addCell(actclesCell);
        table.addCell(rsclesCell);
        table.addCell(propvalCell);
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("PDF Export");
        alert.setHeaderText("Export successful");
        alert.setContentText("The BMC has been exported to PDF successfully.");
        alert.showAndWait();

        for (Article bmc : this.Table.getItems()) {
            table.addCell(bmc.getdescription_article());
            table.addCell(bmc.getprix());
            table.addCell(bmc.getimage());
            table.addCell(String.valueOf(bmc.gettitre_article()));
        }

        document.add(table);
        document.close();

        
    }
}
}*/