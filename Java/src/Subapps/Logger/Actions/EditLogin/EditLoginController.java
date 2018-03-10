package Subapps.Logger.Actions.EditLogin;

import Subapps.Logger.Login.Login;
import Subapps.Logger.Login.LoginList;
import Utility.SwichWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class EditLoginController {
    private SwichWindow swich = new SwichWindow();
    private LoginList loginlist = new LoginList();
    private Login login;

    @FXML
    private TextField UsernameIP;
    @FXML
    private PasswordField PasswordIP;
    @FXML
    private TextField URLIP;
    @FXML
    private TextField DescriptionIP;

    @FXML
    private void Cancel(ActionEvent event) throws IOException {
        swich.SwichNewWindow("/Subapps/Logger/MainScreenLogger/MainScreenLogger.fxml", event);
    }

    @FXML
    private void SaveChanges(ActionEvent event) throws IOException {
        loginlist.LoadLoginList();
        loginlist.getLoginList().remove(loginlist.getSavedLoginIndex());
        loginlist.getLoginList().add(loginlist.getSavedLoginIndex(), new Login(UsernameIP.getText(), PasswordIP.getText(), URLIP.getText(), DescriptionIP.getText()));
        loginlist.SaveLoginList();
        swich.SwichNewWindow("/Subapps/Logger/MainScreenLogger/MainScreenLogger.fxml", event);
    }

    @FXML
    public void initialize() {
        login = loginlist.LoadLogin();
        UsernameIP.setText(login.getUserName());
        PasswordIP.setText(login.getPassWord());
        URLIP.setText(login.getSiteURL());
        DescriptionIP.setText(login.getDescription());
    }
}