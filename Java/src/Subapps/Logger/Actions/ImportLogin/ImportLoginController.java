package Subapps.Logger.Actions.ImportLogin;

import Subapps.Logger.Login.LoginList;
import Utility.FileEditing;
import Utility.OpenNewWindow;
import Utility.SwichWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class ImportLoginController {
    private SwichWindow swich = new SwichWindow();
    private LoginList loginlist = new LoginList();
    private FileEditing fe = new FileEditing();
    private String ErrorFilelocation = "Java/src/Utility/Error/Error.txt";
    private OpenNewWindow open = new OpenNewWindow();

    @FXML
    private ListView listview;

    @FXML
    private void Cancel(ActionEvent event) throws IOException {
        swich.SwichNewWindow("/Subapps/Logger/MainScreenLogger/MainScreenLogger.fxml", event);
    }

    @FXML
    private void Save(ActionEvent event) throws IOException {
        loginlist.SaveLoginList();
        swich.SwichNewWindow("/Subapps/Logger/MainScreenLogger/MainScreenLogger.fxml", event);
    }

    @FXML
    private void Chosefile(ActionEvent event) throws Exception {
        FileChooser fc = new FileChooser();
        File Chosen = fc.showOpenDialog(null);
        if (Chosen == null) {
            fe.export(ErrorFilelocation, "1");
            open.LoadNewWindow(("/Utility/Error/Error.fxml"), "Error", null);
        } else {
            if (Chosen != null) {
                loginlist.LoadLoginList();
                loginlist.ImportLoginList(Chosen.getPath());
                listview.getItems().add(Chosen.getName());
            } else {
                System.out.println("Thet is a wrong format file");
            }
        }
    }
}