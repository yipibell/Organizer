package Subapps.Alarm.Alarm;

import javafx.scene.control.CheckBox;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class Alarm implements Comparable<Alarm> {

    /*Constructor*/
    private Boolean Activation;
    private LocalDateTime AlarmTime;
    private String Description;
    private String AlarmTimeAsString;
    private AlarmList alarmList = new AlarmList();

    private CheckBox Check;


    public static Comparator<Alarm> AlarmDateComparator = new Comparator<Alarm>() {
        public int compare(Alarm a, Alarm b) {
            LocalDateTime a1 = a.getAlarmTime();
            LocalDateTime b1 = b.getAlarmTime();
            return a1.compareTo(b1);
        }

    };

    public Alarm(Boolean activation, LocalDateTime alarmTime, String description) {
        super();
        this.Activation = activation;
        this.AlarmTime = alarmTime;
        this.Description = description;
        this.AlarmTimeAsString = AlarmTime.format(DateTimeFormatter.ofPattern("HH:mm dd/MM/YYYY"));

        this.Check = new CheckBox();
        if (Activation) {
            this.Check.setSelected(true);
        }
    }

    @Override
    public int compareTo(Alarm o) {
        if (this.AlarmTime.isBefore(o.getAlarmTime())) {
            return 1;
        } else if (this.AlarmTime.equals(o.getAlarmTime())) {
            return 0;
        } else {
            return -1;
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

    /*Actions*/
    public void report() {
        alarmList.LoadAlarmList();
        alarmList.getAlarmList().remove(new Alarm(Activation, AlarmTime, Description));
        alarmList.getAlarmList().add(new Alarm(!Activation, AlarmTime, Description));
        alarmList.SaveAlarmList();
        System.out.println("Alarm went off");
    }
}