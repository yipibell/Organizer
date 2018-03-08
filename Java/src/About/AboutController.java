package About;

import Utility.SwichWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class AboutController {
    private SwichWindow swich = new SwichWindow();

    @FXML
    void Cancel(ActionEvent event) throws IOException {
        swich.SwichNewWindow("/MainScreen/Main.fxml", event);
    }

}