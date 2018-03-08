package Subapps.Alarm.MainScreenAlarmer.Menu;

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
    public void Close(ActionEvent event) throws IOException {
        Stage stage = (Stage) Menu.getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("/MainScreen/Main.fxml"));
        Scene scene = new Scene(parent);
        stage.setTitle("About");
        stage.setScene(scene);
    }

    @FXML
    void About(ActionEvent event) throws IOException {
        Stage stage = (Stage) Menu.getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("/About/About.fxml"));
        Scene scene = new Scene(parent);
        stage.setTitle("About");
        stage.setScene(scene);
    }

    public void a(ActionEvent actionEvent) {
    }
}