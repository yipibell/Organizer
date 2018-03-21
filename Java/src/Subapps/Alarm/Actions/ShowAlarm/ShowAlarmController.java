package Subapps.Alarm.Actions.ShowAlarm;


import Subapps.Alarm.Alarm.Alarm;
import Subapps.Alarm.Alarm.AlarmList;
import Utility.SwichWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ShowAlarmController {

    @FXML
    private TextField Description;

    @FXML
    private CheckBox Activation;

    @FXML
    private DatePicker Date;

    @FXML
    private ComboBox<String> Hours;

    @FXML
    private ComboBox<String> Minuts;

    /*Actions*/
    private SwichWindow swich = new SwichWindow();
    private AlarmList alarmlist = new AlarmList();
    private Alarm alarm;

    @FXML
    private void Cancel(ActionEvent event) throws IOException {
        swich.SwichNewWindow("/Subapps/Alarm/Actions/ShowAllAlarms/ShowAllAlarms.fxml", event);
    }

    @FXML
    private void Delete(ActionEvent event) throws IOException {
        swich.SwichNewWindow("/Subapps/Alarm/Actions/DeleteAlarm/DeleteAlarm.fxml", event);
    }

    @FXML
    void Edit(ActionEvent event) throws IOException {
        swich.SwichNewWindow("/Subapps/Alarm/Actions/EditAlarm/EditAlarm.fxml", event);
    }

    /*Starter Method*/
    @FXML
    public void initialize() {
        loadalarm();
        Setter();
    }

    private void loadalarm() {
        alarm = alarmlist.LoadAlarm();
        Date.setValue(alarm.getAlarmTime().toLocalDate());
        Hours.setValue("" + alarm.getAlarmTime().getHour());
        Minuts.setValue("" + alarm.getAlarmTime().getMinute());
        Activation.setSelected(alarm.getActivation());
        Description.setText(alarm.getDescription());
    }

    private void Setter() {
        Hours.getItems().add("" + alarm.getAlarmTime().getHour());
        Minuts.getItems().add("" + alarm.getAlarmTime().getMinute());
    }
}