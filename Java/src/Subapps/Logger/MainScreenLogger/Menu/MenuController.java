package Subapps.Logger.MainScreenLogger.Menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    @FXML
    private VBox Menu;

    @FXML
    public void Close(ActionEvent event) {
        SwichToScreen("/MainScreen/Main.fxml", "Main");
    }

    @FXML
    void Addanewlogin(ActionEvent event) {
        SwichToScreen("/Subapps/Logger/Actions/AddLogin/AddLogin.fxml", "Add new Alarm id");
    }

    @FXML
    void ImportLogin(ActionEvent event) {
        SwichToScreen("/Subapps/Logger/Actions/ImportLogin/ImportLogin.fxml", "Import Logins from file");
    }

    @FXML
    void ExportLoginlist(ActionEvent event) {
        SwichToScreen("/Subapps/Logger/Actions/ExportLoginList/ExportLoginList.fxml", "Export Alarm list");
    }

    @FXML
    void About(ActionEvent event) {
        SwichToScreen("/About/About.fxml", "About");
    }

    private void SwichToScreen(String Path, String ScreenName) {
        try {
            Stage stage = (Stage) Menu.getScene().getWindow();
            Parent parent = FXMLLoader.load(getClass().getResource(Path));
            Scene scene = new Scene(parent);
            stage.setTitle(ScreenName);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}