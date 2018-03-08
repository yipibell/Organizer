package Subapps.Logger.MainScreenLogger;


import Subapps.Logger.Login.Login;
import Subapps.Logger.Login.LoginList;
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

public class MainController {
    /*Menu bar*/
    @FXML
    public JFXHamburger MenuButton;
    private SwichWindow swich = new SwichWindow();
    @FXML
    private AnchorPane MainScreen;
    @FXML
    private JFXDrawer MenuDrawer;
    /*Login info Table*/
    @FXML
    private TableView<Login> Tab;
    @FXML
    private TableColumn<Login, String> SiteURLTab;
    @FXML
    private TableColumn<Login, String> DescriptionTab;
    private LoginList loginlist = new LoginList();
    private ObservableList<Login> LoginObservableList = FXCollections.observableArrayList();

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

    private void Menu() throws IOException {
        VBox box = FXMLLoader.load(getClass().getResource("/Subapps/Logger/Menu/Menu.fxml"));
        MenuDrawer.setSidePane(box);
    }

    private void ColumnSet() {
        SiteURLTab.setCellValueFactory(new PropertyValueFactory<>("SiteURL"));
        DescriptionTab.setCellValueFactory(new PropertyValueFactory<>("Description"));
    }

    private void loadData() {
        LoginObservableList.clear();
        loginlist.LoadLoginList();
        for (Login log : loginlist.getLoginList()) {
            LoginObservableList.add(log);
        }
        Tab.setItems(LoginObservableList);
    }

    /*Table Menu*/
    @FXML
    private void RCMenuDelete(ActionEvent actionEvent) throws IOException {
        loginlist.SaveLogin(Tab.getSelectionModel().getSelectedItem(), loginlist.getLoginList().indexOf(Tab.getSelectionModel().getSelectedItem()));
        Stage stage = (Stage) (MainScreen.getScene().getWindow());
        swich.SwichNewWindow("/Subapps/Logger/Actions/DeleteLogin/DeleteLogin.fxml", stage);
    }

    @FXML
    public void RCMenuEdit(ActionEvent actionEvent) throws IOException {
        loginlist.SaveLogin(Tab.getSelectionModel().getSelectedItem(), loginlist.getLoginList().indexOf(Tab.getSelectionModel().getSelectedItem()));
        Stage stage = (Stage) (MainScreen.getScene().getWindow());
        swich.SwichNewWindow("/Subapps/Logger/Actions/EditLogin/EditLogin.fxml", stage);
    }

    @FXML
    public void RCMenuGetinfo(ActionEvent actionEvent) throws IOException {
        loginlist.SaveLogin(Tab.getSelectionModel().getSelectedItem(), loginlist.getLoginList().indexOf(Tab.getSelectionModel().getSelectedItem()));
        Stage stage = (Stage) (MainScreen.getScene().getWindow());
        swich.SwichNewWindow("/Subapps/Logger/Actions/ShowLogin/ShowLogin.fxml", stage);
    }

    @FXML
    public void RCMenuExport(ActionEvent actionEvent) throws IOException {
        loginlist.SaveLogin(Tab.getSelectionModel().getSelectedItem(), loginlist.getLoginList().indexOf(Tab.getSelectionModel().getSelectedItem()));
        Stage stage = (Stage) (MainScreen.getScene().getWindow());
        swich.SwichNewWindow("/Subapps/Logger/Actions/ExportLogin/ExportLogin.fxml", stage);
    }

    @FXML
    public void RCMenuCopy(ActionEvent actionEvent) {
        loginlist.getLoginList().add(Tab.getSelectionModel().getSelectedItem());
        LoginObservableList.add(Tab.getSelectionModel().getSelectedItem());
        loginlist.SaveLoginList();
    }
    /*Starter Method*/

    @FXML
    public void initialize() throws IOException {
        Menu();
        MenuButtonControll();
        ColumnSet();
        loadData();
    }
}