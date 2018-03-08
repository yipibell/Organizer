package Subapps.Alarm.Alarm;


import Utility.CommonCommands;
import Utility.Encryption;
import Utility.FileEditing;

public class AlarmList {
    private String SecretKey = "Alarm";
    private CommonCommands CC = new CommonCommands();
    private FileEditing fe = new FileEditing();
    private Encryption encryption;
    private String SaveFilelocation = "Java/src/Subapps/Logger/Alarm/SavedAlarmList.loginfo";

}
