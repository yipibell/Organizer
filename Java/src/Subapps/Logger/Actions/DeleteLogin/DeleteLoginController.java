package Subapps.Logger.Actions.DeleteLogin;

import Subapps.Logger.Login.Login;
import Subapps.Logger.Login.LoginList;
import Utility.SwichWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class DeleteLoginController {
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

    @FXML
    void Cancel(ActionEvent event) throws IOException {
        swich.SwichNewWindow("/Subapps/Logger/MainScreenLogger/MainScreenLogger.fxml", event);
    }

    @FXML
    void Delete(ActionEvent event) throws Exception {
        loginlist.LoadLoginList();
        loginlist.getLoginList().remove(loginlist.getSavedLoginIndex());
        loginlist.SaveLoginList();
        swich.SwichNewWindow("/Subapps/Logger/MainScreenLogger/MainScreenLogger.fxml", event);
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