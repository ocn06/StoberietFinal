package dk.stoberiet.Controllers;

import dk.stoberiet.BusinessLogic.Authentication;
import dk.stoberiet.BusinessLogic.AuthenticationImpl;
import dk.stoberiet.DAO.RoomDAO;
import dk.stoberiet.DAO.RoomDAOImpl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuAdminController implements Initializable{
    private Authentication authentication;
    private RoomDAO roomDAO;

    @FXML
    public Text usernameText;

    public MainMenuAdminController(Authentication authentication, RoomDAO roomDAO) {
        this.authentication = authentication;
        this.roomDAO = roomDAO;
    }

    public MainMenuAdminController() {
        this(new AuthenticationImpl(), new RoomDAOImpl());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set Username
        String username = this.authentication.getLoggedInUser().getUsername();
        this.usernameText.setText(username);
    }
}
