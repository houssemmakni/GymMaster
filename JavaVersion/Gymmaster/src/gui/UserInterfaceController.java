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

public class UserInterfaceController {

    @FXML
    private Button ExitBtn;

    @FXML
    private Button ShopBtn;

    @FXML
    private Button ReclamationBtn;

    @FXML
    void HandleExitBtn(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void HandleReclamationBtn(ActionEvent event) {
        try{ Parent ret1Root = FXMLLoader.load(getClass().getResource("ReclamationClient.fxml"));
        Scene Scene4 = new Scene(ret1Root);
       
       
        // Create a new stage  interface
        Stage ret1Stage = new Stage();
        ret1Stage.initStyle(StageStyle.TRANSPARENT);
        ret1Stage.setScene(Scene4);
        ret1Stage.show();
       
        // Close the splash screen stage
        Stage splash4 = (Stage) ReclamationBtn.getScene().getWindow();
        splash4.close();
    } catch (IOException ex) {
           System.out.println("");
    }

    }

    @FXML
    void HandleShopBtn(ActionEvent event) {
        try{ Parent ret1Root = FXMLLoader.load(getClass().getResource("Commande.fxml"));
        Scene Scene4 = new Scene(ret1Root);
       
       
        // Create a new stage  interface
        Stage ret1Stage = new Stage();
        ret1Stage.initStyle(StageStyle.TRANSPARENT);
        ret1Stage.setScene(Scene4);
        ret1Stage.show();
       
        // Close the splash screen stage
        Stage splash4 = (Stage) ShopBtn.getScene().getWindow();
        splash4.close();
    } catch (IOException ex) {
           System.out.println("");
    }

}

}
