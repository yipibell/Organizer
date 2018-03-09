package Subapps.Alarm.MainScreenAlarmer;


import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainController {
    /*Menu bar*/
    @FXML
    public JFXHamburger MenuButton;

    @FXML
    private JFXDrawer MenuDrawer;

    private void MenuButtonControll() {
        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(MenuButton);
        transition.setRate(-1);
        MenuButton.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();
            if (MenuDrawer.isShown()) {
                MenuDrawer.close();
            } else MenuDrawer.open();
        });
    }

    private void Menu() throws IOException {
        VBox box = FXMLLoader.load(getClass().getResource("/Subapps/Alarm/MainScreenAlarmer/Menu/Menu.fxml"));
        MenuDrawer.setSidePane(box);
    }

    /*Clock and timer*/
    @FXML
    private AnchorPane MainScreen;

    private javafx.animation.Timeline Timeline;

    @FXML
    private Text Time;

    @FXML
    private Text Date;

    @FXML
    private Text Day;

    @FXML
    public void SmallClock() {
        Timeline = new Timeline();
        Timeline.setCycleCount(864000);
        Timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> {
            LocalDateTime Time = LocalDateTime.now();
            this.Time.setText("" + Time.format(DateTimeFormatter.ofPattern("HH:mm:ss a")));
            String Date = "";
            Date = "" + Time.getDayOfMonth() + "." + (Time.getMonthValue()) + "(" + Time.getMonth() + ")." +
                    Time.getYear();
            this.Date.setText(Date);
            Day.setText("" + Time.getDayOfWeek());
        }));
        Timeline.play();
    }

    @FXML
    void Clock(MouseEvent event) {
        try {
            Stage stage = (Stage) MainScreen.getScene().getWindow();
            Parent parent = FXMLLoader.load(getClass().getResource("/Subapps/Alarm/Actions/Clock/Clock.fxml"));
            Scene scene = new Scene(parent);
            stage.setTitle("Clock");
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*Alarms windows*/
    @FXML
    private Text PreviousAlarmTime;

    @FXML
    private TextFlow PreviousAlarmDescription;

    @FXML
    private Text Timeleft;

    @FXML
    private Text ClosestAlarmTime;

    @FXML
    private TextFlow ClosestAlarmDescription;

    @FXML
    private Text NextAlarmTime;

    @FXML
    private TextFlow NextAlarmDescription;

    /*Starter Method*/
    @FXML
    public void initialize() throws IOException {
        Menu();
        MenuButtonControll();
        SmallClock();
    }
}