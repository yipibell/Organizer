package Subapps.Alarm.Actions.ShowAllAlarms.Menu;

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
    void About(ActionEvent event) {
        SwichToScreen("/About/About.fxml", "About");
    }

    @FXML
    public void AddAlarm(ActionEvent event) {
        SwichToScreen("/Subapps/Alarm/Actions/AddAlarm/AddAlarm.fxml", "AddAlarm");
    }

    @FXML
    public void ShowAllAlarms(ActionEvent event) {
        SwichToScreen("/Subapps/Alarm/Actions/ShowAllAlarms/ShowAllAlarms.fxml", "AddAlarm");
    }

    public void a(ActionEvent actionEvent) {
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