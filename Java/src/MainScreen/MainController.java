package MainScreen;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainController {
    /*Menu bar*/
    @FXML
    public JFXHamburger MenuButton;

    @FXML
    private JFXDrawer MenuDrawer;

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
        VBox box = FXMLLoader.load(getClass().getResource("/MainScreen/Menu/Menu.fxml"));
        MenuDrawer.setSidePane(box);
    }

    /*Starter Method*/
    @FXML
    public void initialize() throws IOException {
        Menu();
        MenuButtonControll();
    }
}