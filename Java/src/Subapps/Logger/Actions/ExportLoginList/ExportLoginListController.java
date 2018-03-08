package Subapps.Logger.Actions.ExportLoginList;

import Subapps.Logger.Login.Login;
import Subapps.Logger.Login.LoginList;
import Utility.Encryption;
import Utility.FileEditing;
import Utility.OpenNewWindow;
import Utility.SwichWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExportLoginListController {
    private SwichWindow swich = new SwichWindow();
    private LoginList loginlist = new LoginList();
    private ObservableList<Login> LoginObservableList = FXCollections.observableArrayList();
    private String SaveFilelocation;
    private Encryption encryption;
    private FileEditing fe = new FileEditing();
    private String ErrorFilelocation = "Java/src/Utility/Error/Error.txt";
    private OpenNewWindow open = new OpenNewWindow();

    @FXML
    private AnchorPane EpxortLoginPane;

    @FXML
    private TableView<Login> Tab;
    @FXML
    private TableColumn<Login, String> SiteURLTab;

    @FXML
    private TableColumn<Login, String> DescriptionTab;

    @FXML
    private TableColumn SelectTab = new TableColumn();

    @FXML
    public void initialize() {
        ColumnSet();
        loadData();
    }

    private void ColumnSet() {
        SiteURLTab.setCellValueFactory(new PropertyValueFactory<>("SiteURL"));
        DescriptionTab.setCellValueFactory(new PropertyValueFactory<>("Description"));
        SelectTab.setCellValueFactory(new PropertyValueFactory<>("Check"));
    }

    private void loadData() {
        LoginObservableList.clear();
        loginlist.LoadLoginList();
        for (Login log : loginlist.getLoginList()) {
            LoginObservableList.add(log);
        }
        Tab.setItems(LoginObservableList);
    }

    @FXML
    private void Cancel(ActionEvent event) throws IOException {
        swich.SwichNewWindow("/Subapps/Logger/MainScreenLogger/Main.fxml", event);
    }

    @FXML
    private void Export(ActionEvent event) throws IOException {
        Stage stage = (Stage) (EpxortLoginPane.getScene().getWindow());
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Where do you want to save that login?");
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        String saveLogin = "";
        List<Login> listofselected = new ArrayList<>();
        for (Login login : loginlist.getLoginList()) {
            if (login.getCheck().isSelected()) {
                listofselected.add(login);
            }
        }
        if (!listofselected.isEmpty()) {
            String SecretKey = "Alarm";
            File dir = directoryChooser.showDialog(stage);
            if (dir == null) {
                fe.export(ErrorFilelocation, "1");
                open.LoadNewWindow(("/Utility/Error/Error.fxml"), "Error", null);
            } else {
                SaveFilelocation = dir.getPath() + "/AlarmList.loginfo";
                saveLogin += LoginListToString(listofselected);
                saveLogin.replace(" ", "_");
                saveLogin.replace("\\n", "!@");
                byte[] savelogin = encryption.Encryption(saveLogin, SecretKey);
                fe.SavebitFile(savelogin, SaveFilelocation);
                swich.SwichNewWindow("/Subapps/Logger/MainScreenLogger/Main.fxml", event);
            }
        } else {
            fe.export(ErrorFilelocation, "0");
            open.LoadNewWindow(("/Utility/Error/Error.fxml"), "Error", null);
        }
    }

    private String LoginListToString(List<Login> listoflogins) {
        String listofloginsString = "";
        for (Login login : listoflogins) {
            if (login.getDescription().equals("")) login.setDescription("no_description_enterd");
            listofloginsString += login.getUserName() + "|" + login.getPassWord() + "|" + login.getSiteURL() + "|" + login.getDescription() + "\n";
        }
        ;
        return listofloginsString;
    }
}