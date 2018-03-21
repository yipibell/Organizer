package Subapps.Alarm.Actions.ShowAllAlarms;


import Subapps.Alarm.Alarm.Alarm;
import Subapps.Alarm.Alarm.AlarmList;
import Utility.FileEditing;
import Utility.OpenNewWindow;
import Utility.SwichWindow;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ShowAllAlarmsController {
    /*Menu bar*/
    @FXML
    public JFXHamburger MenuButton;

    /*Tab*/
    private ObservableList<Alarm> AlarmObservableList = FXCollections.observableArrayList();
    private AlarmList alarmList = new AlarmList();
    @FXML
    private TableView<Alarm> Tab;
    @FXML
    private TableColumn<Alarm, String> AlarmTimeTab;
    @FXML
    private TableColumn<Alarm, String> DescriptionTab;
    @FXML
    private TableColumn ActivationTab = new TableColumn();

    /*Actions*/
    private FileEditing fe = new FileEditing();
    private String ErrorFilelocation = "java/src/Utility/Error/Error.txt";
    private OpenNewWindow open = new OpenNewWindow();
    private SwichWindow swich = new SwichWindow();

    @FXML
    private JFXDrawer MenuDrawer;
    @FXML
    private AnchorPane MainScreen;

    private void ColumnSet() {
        AlarmTimeTab.setCellValueFactory(new PropertyValueFactory<>("AlarmTimeAsString"));
        DescriptionTab.setCellValueFactory(new PropertyValueFactory<>("Description"));
        ActivationTab.setCellValueFactory(new PropertyValueFactory<>("Check"));
        ActivationTab.setEditable(false);
    }

    private void loadData() {
        AlarmObservableList.clear();
        alarmList.LoadAlarmList();
        for (Alarm log : alarmList.getAlarmList()) {
            AlarmObservableList.add(log);
        }
        Tab.setItems(AlarmObservableList);
    }


    public void a(ActionEvent actionEvent) {
    }

    public void Back(ActionEvent actionEvent) throws IOException {
        swich.SwichNewWindow("/Subapps/Alarm/MainScreenAlarmer/MainScreenAlarmer.fxml", actionEvent);
    }

    public void ShowAlarm(ActionEvent actionEvent) throws IOException {
        if (SelectChecker(Tab.getSelectionModel().getSelectedItem())) {
            alarmList.SaveAlarm(Tab.getSelectionModel().getSelectedItem(), alarmList.getAlarmList().indexOf(Tab.getSelectionModel().getSelectedItem()));
            Stage stage = (Stage) (MainScreen.getScene().getWindow());
            swich.SwichNewWindow("/Subapps/Alarm/Actions/ShowAlarm/ShowAlarm.fxml", stage);
        }
    }

    public void EditAlarm(ActionEvent actionEvent) throws IOException {
        if (SelectChecker(Tab.getSelectionModel().getSelectedItem())) {
            alarmList.SaveAlarm(Tab.getSelectionModel().getSelectedItem(), alarmList.getAlarmList().indexOf(Tab.getSelectionModel().getSelectedItem()));
            Stage stage = (Stage) (MainScreen.getScene().getWindow());
            swich.SwichNewWindow("/Subapps/Alarm/Actions/EditAlarm/EditAlarm.fxml", stage);
        }
    }

    public void DeleteAlarm(ActionEvent actionEvent) throws IOException {
        if (SelectChecker(Tab.getSelectionModel().getSelectedItem())) {
            alarmList.SaveAlarm(Tab.getSelectionModel().getSelectedItem(), alarmList.getAlarmList().indexOf(Tab.getSelectionModel().getSelectedItem()));
            Stage stage = (Stage) (MainScreen.getScene().getWindow());
            swich.SwichNewWindow("/Subapps/Alarm/Actions/DeleteAlarm/DeleteAlarm.fxml", stage);
        }
    }

    /*starter*/

    private boolean SelectChecker(Alarm obj) {
        boolean valid = false;
        Alarm alarm = obj;
        if (obj == null) {
            fe.export(ErrorFilelocation, "4");
            open.LoadNewWindow(("/Utility/Error/Error.fxml"), "Error", null);
            valid = false;
        } else {
            valid = true;
        }
        return valid;
    }

    @FXML
    public void initialize() {
        ColumnSet();
        loadData();
        Menu();
        MenuButtonControll();
    }

    private void MenuButtonControll() {
        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(MenuButton);
        transition.setRate(-1);
        MenuButton.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();
            if (MenuDrawer.isShown()) {
                MenuDrawer.close();
            } else MenuDrawer.open();
        });
    }

    private void Menu() {
        try {
            VBox box = FXMLLoader.load(getClass().getResource("/Subapps/Alarm/Actions/ShowAllAlarms/Menu/Menu.fxml"));
            MenuDrawer.setSidePane(box);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}