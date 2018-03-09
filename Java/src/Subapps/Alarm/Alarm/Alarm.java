package Subapps.Alarm.Alarm;

import java.time.LocalDateTime;

public class Alarm {
    /*Constructor*/
    private Boolean Activation;
    private LocalDateTime AlarmTime;
    private String Description;

    public Alarm(Boolean activation, LocalDateTime alarmTime, String description) {
        Activation = activation;
        AlarmTime = alarmTime;
        Description = description;
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

    public void setAlarmTime(int hour, int minutes, int day, int month, int year) {
        AlarmTime = LocalDateTime.of(year, month, day, hour, minutes);
    }
}