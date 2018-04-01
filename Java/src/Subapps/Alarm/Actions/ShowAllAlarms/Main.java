package Subapps.Alarm.Actions.ShowAllAlarms;

import Subapps.Alarm.Alarm.AlarmRing.AlarmRing;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private AlarmRing ring = new AlarmRing();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Subapps/Alarm/Actions/ShowAllAlarms/ShowAllAlarms.fxml"));
        primaryStage.setTitle("Alarmer");
        primaryStage.setScene(new Scene(root, 260, 220));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
