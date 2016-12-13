package dk.stoberiet.Controllers;

import dk.stoberiet.BusinessLogic.Authentication;
import dk.stoberiet.BusinessLogic.AuthenticationImpl;
import dk.stoberiet.Data.RoomDAO;
import dk.stoberiet.Data.RoomDAOImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RoomsController implements Initializable {
	private Authentication authentication;
	private RoomDAO roomDAO;

	@FXML
	public Button roomsGoBackButton;

	@FXML
	public Text usernameText;

	public RoomsController(Authentication authentication, RoomDAO roomDAO) {
		this.authentication = authentication;
		this.roomDAO = roomDAO;
	}

	public RoomsController() {
		this(new AuthenticationImpl(), new RoomDAOImpl());
	}

	@FXML
	public void roomsGoBackEvent() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../Views/MainMenu.fxml"));
		Stage stage = (Stage) this.roomsGoBackButton.getScene().getWindow();
		stage.setScene(new Scene(root, 600, 400));
		stage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Set Username
		String username = this.authentication.getLoggedInUser().getUsername();
		this.usernameText.setText(username);
	}
}
