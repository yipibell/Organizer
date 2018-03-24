package Subapps.Alarm.AlarmRing;

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
/*    private Timeline Timeline;

https://github.com/yipibell/FXTutorials.git

    public void run() {
        alarmList.LoadAlarmList();
            Timeline = new Timeline();
            Timeline.setCycleCount(31471200);
            Timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> {
                int i=1;
                System.out.println(i);
                LocalDateTime timenow=LocalDateTime.now();
                if (alarmList.getNextAlarm().getAlarmTime().isBefore(timenow)){
                    open.LoadNewWindow(("Subapps/Alarm/AlarmRing/AlarmRing.fxml"), "Alarm Ringing", null);
                    System.out.println("Ring ring ring");
                }
                i++;
            }));
        Timeline.play();
    }*/

    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    public void start() {
        executorService.scheduleAtFixedRate(this::tick, 0, 1, TimeUnit.SECONDS);
    }

    public void stop() {
        executorService.shutdownNow();
    }

    private void tick() {
        Platform.runLater(() -> {
            alarmList.getAlarmRingList().stream()
                    .filter(alarm -> LocalDateTime.now().isAfter(alarm.getAlarmTime()) && alarm.getActivation())
                    .forEach(Alarm::report);
            alarmList.getAlarmRingList().removeIf(alarm -> !alarm.getActivation());
        });
    }
}

