package gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdminDashboardController {

    @FXML
    private Button ArDashbtn;

    @FXML
    private Button CmdDashbtn;

    @FXML
    private Button RecDashbtn;

    @FXML
    private Button ExitDashbtn;

    @FXML
    void ArDash(ActionEvent event) {
{
        try{ Parent ret1Root = FXMLLoader.load(getClass().getResource("Affichearticle.fxml"));
        Scene Scene4 = new Scene(ret1Root);
       
       
        // Create a new stage  interface
        Stage ret1Stage = new Stage();
        ret1Stage.initStyle(StageStyle.TRANSPARENT);
        ret1Stage.setScene(Scene4);
        ret1Stage.show();
       
        // Close the splash screen stage
        Stage splash4 = (Stage) ArDashbtn.getScene().getWindow();
        splash4.close();
    } catch (IOException ex) {
           System.out.println("");
    }
    }
    }

    @FXML
    void CmdDash(ActionEvent event) {
        {
            try{ Parent ret1Root = FXMLLoader.load(getClass().getResource("AdminCommande.fxml"));
            Scene Scene4 = new Scene(ret1Root);
           
           
            // Create a new stage  interface
            Stage ret1Stage = new Stage();
            ret1Stage.initStyle(StageStyle.TRANSPARENT);
            ret1Stage.setScene(Scene4);
            ret1Stage.show();
           
            // Close the splash screen stage
            Stage splash4 = (Stage) CmdDashbtn.getScene().getWindow();
            splash4.close();
        } catch (IOException ex) {
               System.out.println("");
        }
        }
    }

    @FXML
    void RecDash(ActionEvent event) {
        {
            try{ Parent ret1Root = FXMLLoader.load(getClass().getResource("ReclamationAgent.fxml"));
            Scene Scene4 = new Scene(ret1Root);
           
           
            // Create a new stage  interface
            Stage ret1Stage = new Stage();
            ret1Stage.initStyle(StageStyle.TRANSPARENT);
            ret1Stage.setScene(Scene4);
            ret1Stage.show();
           
            // Close the splash screen stage
            Stage splash4 = (Stage) RecDashbtn.getScene().getWindow();
            splash4.close();
        } catch (IOException ex) {
               System.out.println("");
        }
        }
    }
    
    @FXML
    void handleExitDashbtn(ActionEvent event) {
        System.exit(0);
    }

}
