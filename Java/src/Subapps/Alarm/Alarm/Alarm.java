package Subapps.Alarm.Alarm;

import javafx.scene.control.CheckBox;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Alarm {
    /*Constructor*/
    private Boolean Activation;
    private LocalDateTime AlarmTime;
    private String Description;
    private String AlarmTimeAsString;

    private CheckBox Check;


    public Alarm(Boolean activation, LocalDateTime alarmTime, String description) {
        this.Activation = activation;
        this.AlarmTime = alarmTime;
        this.Description = description;
        this.AlarmTimeAsString = AlarmTime.format(DateTimeFormatter.ofPattern("HH:mm dd/MM/YYYY"));

        this.Check = new CheckBox();
        if (Activation) {
            this.Check.setSelected(true);
        }
    }


    /*Getters & Setters*/
    public Boolean getActivation() {
        return Activation;
    }

    public void setActivation(Boolean activation) {
        Activation = activation;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public LocalDateTime getAlarmTime() {
        return AlarmTime;
    }

    public void setAlarmTime(LocalDateTime alarmTime) {
        AlarmTime = alarmTime;
    }

    public String getAlarmTimeAsString() {
        return AlarmTimeAsString;
    }

    public void setAlarmTime(int hour, int minutes, int day, int month, int year) {
        AlarmTime = LocalDateTime.of(year, month, day, hour, minutes);
    }

    public CheckBox getCheck() {
        return Check;
    }

    private void ActivationSwich() {
        Activation = !Activation;
    }
}