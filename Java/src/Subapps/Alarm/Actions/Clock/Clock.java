package Subapps.Alarm.Actions.Clock;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Clock {
    private Timeline Timeline;

    @FXML
    private AnchorPane MainScreen;

    @FXML
    private Text Time;

    @FXML
    private Text Date;

    @FXML
    private Text Day;

    @FXML
    public void initialize() {
        Timeline = new Timeline();
        Timeline.setCycleCount(864000);
        Timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> {
            runner();
        }));
        Timeline.play();
    }

    private void runner() {
        Time();
        Date();
        Day();
    }

    private void Time() {
        LocalDateTime Time = LocalDateTime.now();
        this.Time.setText("" + Time.format(DateTimeFormatter.ofPattern("HH:mm:ss a")));
    }

    private void Date() {
        LocalDateTime Time = LocalDateTime.now();
        String Date = "";
        Date = "" + Time.getDayOfMonth() + "." + (Time.getMonthValue() + 1) + "(" + Time.getMonth() + ")." +
                Time.getYear();
        this.Date.setText(Date);
    }

    private void Day() {
        LocalDateTime Time = LocalDateTime.now();
        Day.setText("" + Time.getDayOfWeek());
    }


    @FXML
    void Back(javafx.event.ActionEvent event) throws IOException {
        Stage stage = (Stage) MainScreen.getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("/Subapps/Alarm/MainScreenAlarmer/MainScreenAlarmer.fxml"));
        Scene scene = new Scene(parent);
        stage.setTitle("Alarmer");
        stage.setScene(scene);
    }
}