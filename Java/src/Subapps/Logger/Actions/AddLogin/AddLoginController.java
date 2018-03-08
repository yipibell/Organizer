package Subapps.Logger.Actions.AddLogin;

import Subapps.Logger.Login.Login;
import Subapps.Logger.Login.LoginList;
import Utility.FileEditing;
import Utility.OpenNewWindow;
import Utility.SwichWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddLoginController {
    private SwichWindow swich = new SwichWindow();
    private LoginList loginlist = new LoginList();
    private FileEditing fe = new FileEditing();
    private String ErrorFilelocation = "java/src/Utility/Error/Error.txt";
    private OpenNewWindow open = new OpenNewWindow();

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
        swich.SwichNewWindow("/Subapps/Logger/MainScreenLogger/Main.fxml", event);
    }

    @FXML
    private void Save(ActionEvent event) throws IOException {
        loginlist.LoadLoginList();
        if (!UsernameIP.getText().equals("")) {
            if (!PasswordIP.getText().equals("")) {
                loginlist.AddLogin(new Login(UsernameIP.getText(), PasswordIP.getText(), URLIP.getText(), DescriptionIP.getText()));
                loginlist.SaveLoginList();
                swich.SwichNewWindow("/Subapps/Logger/MainScreenLogger/Main.fxml", event);
            } else {
                fe.export(ErrorFilelocation, "3");
                open.LoadNewWindow(("/Utility/Error/Error.fxml"), "Error", null);
            }
        } else {
            fe.export(ErrorFilelocation, "2");
            open.LoadNewWindow(("/Utility/Error/Error.fxml"), "Error", null);
        }
    }
}