package gui;


import java.io.IOException;

import java.sql.Date;





import entities.Reclamation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import javafx.scene.control.TextArea;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.ReclamationCRUD;

public class ReclamationClientController {
    ReclamationCRUD rc = new ReclamationCRUD();



    @FXML
    private TextArea DescriptionField;

    @FXML
    private Button ADDbtn;

    @FXML
    private Button Cancelbtn;
    
    
    @FXML
    private ChoiceBox<String> SubjectChoiceBox;
    private String[] Subject = {"Shop","Payment","Product","Other"};



    @FXML
    private TextArea UserEmailField;








    @FXML
    void handleCancelbtn(ActionEvent event){
        try{ Parent ret1Root = FXMLLoader.load(getClass().getResource("UserInterface.fxml"));
        Scene Scene4 = new Scene(ret1Root);
       
       
        // Create a new stage  interface
        Stage ret1Stage = new Stage();
        ret1Stage.initStyle(StageStyle.TRANSPARENT);
        ret1Stage.setScene(Scene4);
        ret1Stage.show();
       
        // Close the splash screen stage
        Stage splash4 = (Stage) Cancelbtn.getScene().getWindow();
        splash4.close();
    } catch (IOException ex) {
           System.out.println("");
    }
    }


public void initialize() {
        SubjectChoiceBox.getItems().addAll(Subject);
    }

    @FXML
    void envoyer(ActionEvent event) {
         String description = DescriptionField.getText(); // Get the description from the TextArea
         String subject = SubjectChoiceBox.getValue(); // Get the subject from the ChoiceBox
         String userEmail = UserEmailField.getText(); // Get the user email from the TextArea
    Date currentDate = new Date(System.currentTimeMillis());

    
    Reclamation newReclamation = new Reclamation();
    newReclamation.setDescription(description);
    
    newReclamation.setDateReclamation(currentDate);
    newReclamation.setSubject(subject);
    newReclamation.setUserEmail(userEmail);

    boolean isAdded = rc.ajouterReclamation(newReclamation);

    if (isAdded) {
        System.out.println("Reclamation ajoutée !");
        
    } else {
        System.err.println("Failed to add reclamation!");
        
    }

}

    
    
    
    

    @FXML
    void refresh(ActionEvent event) {

    }

    @FXML
    void tableevent(MouseEvent event) {

    }

    

}
