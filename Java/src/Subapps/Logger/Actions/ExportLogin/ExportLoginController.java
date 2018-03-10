package Subapps.Logger.Actions.ExportLogin;

import Subapps.Logger.Login.Login;
import Subapps.Logger.Login.LoginList;
import Utility.Encryption;
import Utility.FileEditing;
import Utility.OpenNewWindow;
import Utility.SwichWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class ExportLoginController {
    private String ErrorFilelocation = "src/Utility/Error/Error.txt";
    private OpenNewWindow open = new OpenNewWindow();
    private SwichWindow swich = new SwichWindow();
    private FileEditing fe = new FileEditing();
    private Encryption encryption;
    private LoginList loginlist = new LoginList();
    private String SaveFilelocation;
    private Login login;

    @FXML
    private AnchorPane EpxortLoginPane;

    @FXML
    private TextField UsernameOP;

    @FXML
    private PasswordField PasswordOP;

    @FXML
    private TextField URLOP;

    @FXML
    private TextField DescriptionOP;

    @FXML
    void Cancel(ActionEvent event) throws IOException {
        swich.SwichNewWindow("/Subapps/Logger/MainScreenLogger/MainScreenLogger.fxml", event);
    }

    @FXML
    void Export(ActionEvent event) throws Exception {
        String SecretKey = "Alarm";
        Stage stage = (Stage) (EpxortLoginPane.getScene().getWindow());
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Where do you want to save that login?");
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File dir = directoryChooser.showDialog(stage);
        if (dir == null) {
            fe.export(ErrorFilelocation, "1");
            open.LoadNewWindow(("/Utility/Error/Error.fxml"), "Error", null);
        } else {
            SaveFilelocation = dir.getPath() + "/" + login.getDescription() + ".loginfo";
            String saveLogin = "";
            saveLogin += loginlist.LoginToString(login);
            saveLogin.replace(" ", "_");
            saveLogin.replace("\\n", "!@");
            byte[] savelogin = Encryption.Encryption(saveLogin, SecretKey);
            fe.SavebitFile(savelogin, SaveFilelocation);
            swich.SwichNewWindow("/Subapps/Logger/MainScreenLogger/MainScreenLogger.fxml", event);
        }
    }

    @FXML
    public void initialize() {
        login = loginlist.LoadLogin();
        UsernameOP.setText(login.getUserName());
        PasswordOP.setText(login.getPassWord());
        URLOP.setText(login.getSiteURL());
        DescriptionOP.setText(login.getDescription());
    }

}