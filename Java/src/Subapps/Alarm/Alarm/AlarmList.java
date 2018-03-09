package Subapps.Alarm.Alarm;


import Utility.CommonCommands;
import Utility.Encryption;
import Utility.FileEditing;

import java.util.ArrayList;
import java.util.List;

public class AlarmList {
    private String SecretKey = "Alarm";
    private CommonCommands CC = new CommonCommands();
    private FileEditing fe = new FileEditing();
    private Encryption encryption;
    private String SaveFilelocation = "Java/src/Subapps/Logger/Alarm/SavedAlarmList.loginfo";

    /*Alarm list*/
    private List<Alarm> AlarmList = new ArrayList();

    public void AddAlarm(Alarm alarm) {
        this.AlarmList.add(alarm);
    }

    public void SaveAlarmList() {
        String saveAlarm = "";
        for (Alarm alarm : AlarmList) {
            saveAlarm += AlarmToString(alarm);
        }
        saveAlarm.replace(" ", "_");
        byte[] savealarm = encryption.Encryption(saveAlarm, SecretKey);
        fe.SavebitFile(savealarm, SaveFilelocation);
    }

    private String AlarmToString(Alarm alarm) {
        if (alarm.getDescription().equals("")) alarm.setDescription("no_description_enterd");
        String AsString =
                alarm.getAlarmTime() + "|" +
                        alarm.getActivation() + "|" +
                        alarm.getDescription() + "\n";
        return AsString;
    }
}