package MainScreen;

import Subapps.Alarm.AlarmRing.AlarmRing;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    AlarmRing alarmRing;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));

        AlarmRing ring = new AlarmRing();
        ring.start();

        primaryStage.setTitle("Main");
        primaryStage.setScene(new Scene(root, 260, 220));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
