package gui;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import entities.Reclamation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.ReclamationCRUD;

public class ReclamationAgentController {

    @FXML
    private TableView<Reclamation> tab;
    List<Reclamation> listReclamations = new ArrayList<>();
    ObservableList<Reclamation> observableListReclamations;

    @FXML
    private TableColumn<?, ?> IdreclamationCul;

    @FXML
    private TableColumn<?, ?> DateReclamationCul;

    @FXML
    private TableColumn<?, ?> DateReponseCul;

    @FXML
    private TableColumn<?, ?> ReponseCul;

    @FXML
    private TableColumn<?, ?> UserEmailCul;

    @FXML
    private TableColumn<?, ?> DescriptionCul;

    @FXML
    private TableColumn<?, ?> SubjectCul;


      
    @FXML
    private Button SupprimerReclamationbtn;


    @FXML
    private Button Reponsebtn;

    
    @FXML
    private TextField ResponseField;

    @FXML
    private Button Cloturebtn;


    @FXML
    private TableColumn<?, ?> StatusCul;

    @FXML
    private Button ReturnBtn;

    @FXML
    private Button RefBtn;



    @FXML
    void handleReturnBtn(ActionEvent event) {
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


    @FXML
    void handleResponsebtn(ActionEvent event){
        Reclamation selectedReclamation = tab.getSelectionModel().getSelectedItem();

        // Check if an item is selected
        if (selectedReclamation != null) {
            ReclamationCRUD reclamationCRUD = new ReclamationCRUD();
            
            String reponse = ResponseField.getText(); // Get the description from the TextArea
            Date currentDate = new Date(System.currentTimeMillis());
            reclamationCRUD.repondreReclamation(selectedReclamation.getIdReclamation(),reponse,currentDate);


            // After deleting the reclamation, you might want to refresh the table, if necessary
            refreshTable();
        }
    }

    @FXML
    void handleRefBtn(ActionEvent event) {
        refreshTable();
    }



    @FXML
    void tableevent(MouseEvent event) {

    }
    public void initialize() {
        ReclamationCRUD rc = new ReclamationCRUD();
        List<Reclamation> listReclamations = rc.AfficherReclamations();
        ObservableList<Reclamation> observableListReclamations = FXCollections.observableArrayList(listReclamations);
        IdreclamationCul.setCellValueFactory(new PropertyValueFactory<>("idReclamation"));
        DateReclamationCul.setCellValueFactory(new PropertyValueFactory<>("dateReclamation"));
        DateReponseCul.setCellValueFactory(new PropertyValueFactory<>("dateReponse"));
        ReponseCul.setCellValueFactory(new PropertyValueFactory<>("reponse"));
        UserEmailCul.setCellValueFactory(new PropertyValueFactory<>("UserEmail"));
        DescriptionCul.setCellValueFactory(new PropertyValueFactory<>("description"));
        StatusCul.setCellValueFactory(new PropertyValueFactory<>("Status"));
        SubjectCul.setCellValueFactory(new PropertyValueFactory<>("Subject"));

        // Load data into the table
       
        // Display all reclamations
        tab.setItems(observableListReclamations);
    }
     @FXML
    void handleSupprimerReclamation(ActionEvent event) {
        // Assuming you have access to the selected Reclamation object
        Reclamation selectedReclamation = tab.getSelectionModel().getSelectedItem();

        // Check if an item is selected
        if (selectedReclamation != null) {
            ReclamationCRUD reclamationCRUD = new ReclamationCRUD();
            boolean isDeleted = reclamationCRUD.SupprimerrReclamation(selectedReclamation.getIdReclamation());

            if (isDeleted) {
                System.out.println("Reclamation supprimée !");
                // Optionally, you might want to show a success message or perform any other action
            } else {
                System.err.println("Failed to delete reclamation!");
                // Optionally, you might want to show an error message or perform any other action
            }

            // After deleting the reclamation, you might want to refresh the table, if necessary
            refreshTable();
        }
    }



    private void refreshTable() {
        // Clear the table
        tab.getItems().clear();

        // Reload the data from the database
        ReclamationCRUD reclamationCRUD = new ReclamationCRUD();
        List<Reclamation> listReclamations = reclamationCRUD.AfficherReclamations();
        ObservableList<Reclamation> observableListReclamations = FXCollections.observableArrayList(listReclamations);

        // Display all reclamations
        tab.setItems(observableListReclamations);
    }   

    @FXML
    void handleCloturebtn(ActionEvent event) {
         Reclamation selectedReclamation = tab.getSelectionModel().getSelectedItem();

        // Check if an item is selected
        if (selectedReclamation != null) {
            ReclamationCRUD reclamationCRUD = new ReclamationCRUD();
            reclamationCRUD.SetCloturee(selectedReclamation.getIdReclamation());


            // After deleting the reclamation, you might want to refresh the table, if necessary
            refreshTable();
        }

    }




      










}
