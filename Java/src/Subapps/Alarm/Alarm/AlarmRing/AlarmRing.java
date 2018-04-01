package Subapps.Alarm.Alarm.AlarmRing;

import Subapps.Alarm.Alarm.Alarm;
import Subapps.Alarm.Alarm.AlarmList;
import Utility.OpenNewWindow;
import javafx.application.Platform;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AlarmRing {
    private OpenNewWindow open = new OpenNewWindow();
    private AlarmList alarmList = new AlarmList();

    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    public void start() {
        executorService.scheduleAtFixedRate(this::tick, 0, 1, TimeUnit.SECONDS);
    }

    public void stop() {
        executorService.shutdownNow();
    }

    private void tick() {
        alarmList.LoadAlarmList();
        Platform.runLater(() -> {
          for (Alarm alarm:alarmList.getAlarmList()){
              if (alarm.getAlarmTime().isBefore(LocalDateTime.now())&&alarm.getActivation()){
                  alarmList.SaveAlarm(alarm,alarmList.getAlarmList().indexOf(alarm));
                  open.LoadNewWindow("/Subapps/Alarm/Alarm/AlarmRing/AlarmRing.fxml", "Alarm!!!", null);
                  alarm.setActivation(false);
                  alarmList.SaveAlarmList();
              }
          }
        });
    }
}

