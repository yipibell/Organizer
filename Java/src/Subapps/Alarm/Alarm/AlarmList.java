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
    private String SelectedSaveFilelocation = "Java/src/Subapps/Alarm/Alarm/OperetedAlarm.Alarm";
    private int SelectedIndex;

    /*Alarm list*/
    private List<Alarm> AlarmList = new ArrayList();

    private Alarm NextAlarm;

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
        if (alarm.getDescription().equals("")) alarm.setDescription("no_description");
        String AsString =
                alarm.getActivation() + "|" +
                        alarm.getAlarmTime().format(DateTimeFormatter.ofPattern("YYYY:MM:dd:HH:mm")) + "|" +
                        alarm.getDescription() + "\n";
        return AsString;
    }

    public void LoadAlarmList() {
        if (!AlarmList.isEmpty())AlarmList.clear();
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
        AlarmList.sort(Alarm.AlarmDateComparator);
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

    /*selected alarm*/

    public void SaveAlarm(Alarm alarm, int index) {
        String saveAlarm = "";
        saveAlarm += index + "|";
        saveAlarm += AlarmToString(alarm);
        saveAlarm.replace(" ", "_");
        byte[] savelogin = Encryption.Encryption(saveAlarm, SecretKey);
        fe.SavebitFile(savelogin, SelectedSaveFilelocation);
    }

    public Alarm LoadAlarm() {
        Alarm alarm = (new Alarm(false, LocalDateTime.now(), "error"));
        byte[] Decrypt = fe.LoadbitFile(SelectedSaveFilelocation);
        Decrypt = Encryption.Decryption(Decrypt, SecretKey);
        String sDecrypt = new String(Decrypt);
        String[] Decrypted = sDecrypt.replace("!@", "\\n").split("\\n");
        for (String decrypted : Decrypted) {
            if (decrypted.equals("")) break;
            String[] arg = decrypted.split("\\|");
            if (arg[3].contains("_")) arg[3].replace("_", " ");
            alarm = (new Alarm(StringToBoolean(arg[1]), StringToLDT(arg[2]), arg[3]));
            SelectedIndex = CC.SetNum(arg[0]);
        }
        return alarm;
    }

    public int getSelectedIndex() {
        return SelectedIndex;
    }
}