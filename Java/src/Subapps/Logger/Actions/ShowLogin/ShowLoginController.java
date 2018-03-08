package Subapps.Logger.Actions.ShowLogin;

import Subapps.Logger.Login.Login;
import Subapps.Logger.Login.LoginList;
import Utility.SwichWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.io.IOException;

public class ShowLoginController {
    private SwichWindow swich = new SwichWindow();
    private LoginList loginlist = new LoginList();
    private Login login;

    @FXML
    private TextField UsernameOP;

    @FXML
    private PasswordField PasswordOP;

    @FXML
    private TextField URLOP;

    @FXML
    private TextField DescriptionOP;

    public static void copy(String string) {
        Clipboard systemClipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(string);
        systemClipboard.setContent(content);
    }

    @FXML
    void Cancel(ActionEvent event) throws IOException {
        swich.SwichNewWindow("/Subapps/Logger/MainScreenLogger/Main.fxml", event);
    }

    @FXML
    public void initialize() {
        login = loginlist.LoadLogin();
        UsernameOP.setText(login.getUserName());
        PasswordOP.setText(login.getPassWord());
        URLOP.setText(login.getSiteURL());
        DescriptionOP.setText(login.getDescription());
    }

    @FXML
    void CopyUsername(ActionEvent event) {
        copy(UsernameOP.getText());
    }

    @FXML
    void CopyPassword(ActionEvent event) {
        copy(PasswordOP.getText());
    }

    @FXML
    void CopyURL(ActionEvent event) {
        copy(URLOP.getText());
    }

    @FXML
    void CopyDescription(ActionEvent event) {
        copy(DescriptionOP.getText());
    }

}