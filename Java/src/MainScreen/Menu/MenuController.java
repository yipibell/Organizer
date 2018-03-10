package MainScreen.Menu;

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
        Stage stage = (Stage) Menu.getScene().getWindow();
        stage.close();
    }

    @FXML
    void Logger(ActionEvent event) {
        SwichToScreen("/Subapps/Logger/MainScreenLogger/MainScreenLogger.fxml", "Logger");
    }

    @FXML
    void About(ActionEvent event) {
        SwichToScreen("/About/About.fxml", "About");
    }

    @FXML
    void Alarmer(ActionEvent event) {
        SwichToScreen("/Subapps/Alarm/MainScreenAlarmer/MainScreenAlarmer.fxml", "Add new Alarm id");
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