package Utility;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SwichWindow {
    public void SwichNewWindow(String Link, ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource(Link));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void SwichNewWindow(String Link, Stage stage) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource(Link));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }
}
