package Utility.Error;


import Utility.CommonCommands;
import Utility.FileEditing;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ErrorController {
    @FXML
    public Text ErrorText;
    private FileEditing fe = new FileEditing();
    private String ErrorFilelocation = "Java/src/Utility/Error/Error.txt";
    private CommonCommands CC = new CommonCommands();
    @FXML
    private AnchorPane rootPane;

    @FXML
    private void Cancel(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize() {
        ErrorController(CC.SetNum(fe.Import(ErrorFilelocation)));
    }

    public void ErrorController(int ErrorNumber) {
        String error = "";
        switch (ErrorNumber) {
            case 0:
                error = "Error Number #00, You Must check at lest one login to export";
                break;
            case 1:
                error = "Error Number #01, You Must Select a location";
                break;
            case 2:
                error = "Error Number #02, You must enterd a Username";
                break;
            case 3:
                error = "Error Number #03, You must enterd a Password";
                break;
            case 4:
                error = "Error Number #04, You must select a Alarm to use that option" + "/n if there are no lines you can choose add a login";
                break;
            default:
                error = "Error not specified";
                break;
        }
        ErrorText.setText(error);
    }
}
