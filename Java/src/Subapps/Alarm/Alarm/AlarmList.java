package Subapps.Alarm.Alarm;


import Utility.CommonCommands;
import Utility.Encryption;
import Utility.FileEditing;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AlarmList {
    private String SecretKey = "Alarm";
    private CommonCommands CC = new CommonCommands();
    private FileEditing fe = new FileEditing();
    private Encryption encryption;
    private String SaveFilelocation = "Java/src/Subapps/Alarm/Alarm/SavedAlarmList.Alarm";

    /*Alarm list*/
    private List<Alarm> AlarmList = new ArrayList();

    public AlarmList() {
    }

    public void AddAlarm(Alarm alarm) {
        this.AlarmList.add(alarm);
    }

    public void SaveAlarmList() {
        String saveAlarm = "";
        for (Alarm alarm : AlarmList) {
            saveAlarm += AlarmToString(alarm);
        }
        saveAlarm.replace(" ", "_");
        byte[] savealarm = Encryption.Encryption(saveAlarm, SecretKey);
        fe.SavebitFile(savealarm, SaveFilelocation);
    }

    private String AlarmToString(Alarm alarm) {
        if (alarm.getDescription().equals("")) alarm.setDescription("no_description_enterd");
        String AsString =
                alarm.getActivation() + "|" +
                        alarm.getAlarmTime().format(DateTimeFormatter.ofPattern("YYYY:MM:dd:HH:mm")) + "|" +
                        alarm.getDescription() + "\n";
        return AsString;
    }

    public void LoadAlarmList() {
        byte[] Decrypt = fe.LoadbitFile(SaveFilelocation);
        Decrypt = Encryption.Decryption(Decrypt, SecretKey);
        String sDecrypt = new String(Decrypt);
        String[] Decrypted = sDecrypt.replace("!@", "\\n").split("\\n");
        for (String decrypted : Decrypted) {
            if (decrypted.equals("")) break;
            String[] arg = decrypted.split("\\|");
            if (arg[2].contains("_")) arg[2].replace("_", " ");
            AddAlarm(new Alarm(StringToBoolean(arg[0]), StringToLDT(arg[1]), arg[2]));
        }
    }

    private Boolean StringToBoolean(String bool) {
        Boolean Result;
        if (bool.equals("true")) {
            Result = true;
        } else if (bool.equals("false")) {
            Result = false;
        } else {
            System.out.println("looks like ther was a problem in loading that file");
            Result = false;
        }
        return Result;
    }

    private LocalDateTime StringToLDT(String LDT) {
        String[] arg = LDT.split("\\:");
        LocalDateTime Result = LocalDateTime.of(CC.SetNum(arg[0]), CC.SetNum(arg[1]), CC.SetNum(arg[2]), CC.SetNum(arg[3]), CC.SetNum(arg[4]));
        return Result;
    }

    public List<Alarm> getAlarmList() {
        return AlarmList;
    }

    public void ChangeActivation(Alarm alarm) {
        if (alarm.getActivation()) {
            alarm.setActivation(false);
        } else {
            alarm.setActivation(true);
        }
    }
}