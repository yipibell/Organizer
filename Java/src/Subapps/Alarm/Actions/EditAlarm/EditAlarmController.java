package Subapps.Alarm.Actions.EditAlarm;


import Subapps.Alarm.Alarm.Alarm;
import Subapps.Alarm.Alarm.AlarmList;
import Utility.CommonCommands;
import Utility.FileEditing;
import Utility.OpenNewWindow;
import Utility.SwichWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EditAlarmController {

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
    private CommonCommands CC = new CommonCommands();
    private FileEditing fe = new FileEditing();
    private String ErrorFilelocation = "java/src/Utility/Error/Error.txt";
    private OpenNewWindow open = new OpenNewWindow();
    private AlarmList alarmlist = new AlarmList();
    private Alarm alarm;

    @FXML
    private void Cancel(ActionEvent event) throws IOException {
        swich.SwichNewWindow("/Subapps/Alarm/Actions/ShowAllAlarms/ShowAllAlarms.fxml", event);
    }

    @FXML
    private void Save(ActionEvent event) throws IOException {
        alarmlist.LoadAlarmList();
        if (!Hours.getItems().contains(Hours.getValue()) | !Minuts.getItems().contains(Minuts.getValue()) | Date.getValue() == null) {
            fe.export(ErrorFilelocation, "5");
            open.LoadNewWindow(("/Utility/Error/Error.fxml"), "Error", null);
        } else {
            LocalDateTime AlarmTime = LocalDateTime.of(Date.getValue(), LocalTime.of(CC.SetNum(Hours.getValue()), CC.SetNum(Minuts.getValue())));
            if (AlarmTime.isBefore(LocalDateTime.now())) {
                fe.export(ErrorFilelocation, "6");
                open.LoadNewWindow(("/Utility/Error/Error.fxml"), "Error", null);
            } else {
                alarmlist.getAlarmList().remove(alarmlist.getSelectedIndex());
                alarmlist.AddAlarm(new Alarm(Activation.isSelected(), AlarmTime, Description.getText()));
                alarmlist.SaveAlarmList();
                swich.SwichNewWindow("/Subapps/Alarm/Actions/ShowAllAlarms/ShowAllAlarms.fxml", event);
            }
        }
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
        Hours.getItems().add("00");
        Hours.getItems().add("01");
        Hours.getItems().add("02");
        Hours.getItems().add("03");
        Hours.getItems().add("04");
        Hours.getItems().add("05");
        Hours.getItems().add("06");
        Hours.getItems().add("07");
        Hours.getItems().add("08");
        Hours.getItems().add("09");
        Hours.getItems().add("10");
        Hours.getItems().add("11");
        Hours.getItems().add("12");
        Hours.getItems().add("13");
        Hours.getItems().add("14");
        Hours.getItems().add("15");
        Hours.getItems().add("16");
        Hours.getItems().add("17");
        Hours.getItems().add("18");
        Hours.getItems().add("19");
        Hours.getItems().add("20");
        Hours.getItems().add("21");
        Hours.getItems().add("22");
        Hours.getItems().add("23");
        Minuts.getItems().add("00");
        Minuts.getItems().add("01");
        Minuts.getItems().add("02");
        Minuts.getItems().add("03");
        Minuts.getItems().add("04");
        Minuts.getItems().add("05");
        Minuts.getItems().add("06");
        Minuts.getItems().add("07");
        Minuts.getItems().add("08");
        Minuts.getItems().add("09");
        Minuts.getItems().add("10");
        Minuts.getItems().add("11");
        Minuts.getItems().add("12");
        Minuts.getItems().add("13");
        Minuts.getItems().add("14");
        Minuts.getItems().add("15");
        Minuts.getItems().add("16");
        Minuts.getItems().add("17");
        Minuts.getItems().add("18");
        Minuts.getItems().add("19");
        Minuts.getItems().add("20");
        Minuts.getItems().add("21");
        Minuts.getItems().add("22");
        Minuts.getItems().add("23");
        Minuts.getItems().add("24");
        Minuts.getItems().add("25");
        Minuts.getItems().add("26");
        Minuts.getItems().add("27");
        Minuts.getItems().add("28");
        Minuts.getItems().add("29");
        Minuts.getItems().add("30");
        Minuts.getItems().add("31");
        Minuts.getItems().add("32");
        Minuts.getItems().add("33");
        Minuts.getItems().add("34");
        Minuts.getItems().add("35");
        Minuts.getItems().add("36");
        Minuts.getItems().add("37");
        Minuts.getItems().add("38");
        Minuts.getItems().add("39");
        Minuts.getItems().add("40");
        Minuts.getItems().add("41");
        Minuts.getItems().add("42");
        Minuts.getItems().add("43");
        Minuts.getItems().add("44");
        Minuts.getItems().add("45");
        Minuts.getItems().add("46");
        Minuts.getItems().add("47");
        Minuts.getItems().add("48");
        Minuts.getItems().add("49");
        Minuts.getItems().add("50");
        Minuts.getItems().add("51");
        Minuts.getItems().add("52");
        Minuts.getItems().add("53");
        Minuts.getItems().add("54");
        Minuts.getItems().add("55");
        Minuts.getItems().add("56");
        Minuts.getItems().add("57");
        Minuts.getItems().add("58");
        Minuts.getItems().add("59");
        Hours.getItems().set(alarm.getAlarmTime().getHour(), "" + alarm.getAlarmTime().getHour());
        Minuts.getItems().set(alarm.getAlarmTime().getMinute(), "" + alarm.getAlarmTime().getMinute());
    }
}