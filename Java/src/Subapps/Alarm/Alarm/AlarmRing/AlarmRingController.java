package Subapps.Alarm.Alarm.AlarmRing;

import Subapps.Alarm.Alarm.Alarm;
import Subapps.Alarm.Alarm.AlarmList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.util.Date;

public class AlarmRingController {


    @FXML
    private Label AlarmTime;

    @FXML
    private TextArea Discription;

    /*Actions*/
    @FXML
    void Puse(ActionEvent event) {

    }

    @FXML
    void Stop(ActionEvent event) {

    }

    /*Starter Method*/
    private AlarmList alarmlist = new AlarmList();
    private Alarm alarm;

    @FXML
    public void initialize() {
        loadalarm();
    }

    private void loadalarm() {
        alarm = alarmlist.LoadAlarm();
        AlarmTime.setText(alarm.getAlarmTimeAsString());
        Discription.setText(alarm.getDescription());
    }

}