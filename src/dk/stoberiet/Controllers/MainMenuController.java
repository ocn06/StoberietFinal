package dk.stoberiet.Controllers;

import dk.stoberiet.BusinessLogic.Authentication;
import dk.stoberiet.BusinessLogic.AuthenticationImpl;
import dk.stoberiet.DAO.RoomDAO;
import dk.stoberiet.DAO.RoomDAOImpl;
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

public class MainMenuController implements Initializable {
	private Authentication authentication;
	private RoomDAO roomDAO;

	@FXML
	public Button bookRoomButton;

	@FXML
	public Button roomButton;

	@FXML
	public Button myBookingsButton;

	@FXML
	public  Text usernameText;

	public MainMenuController(Authentication authentication, RoomDAO roomDAO) {
		this.authentication = authentication;
		this.roomDAO = roomDAO;
	}

	public MainMenuController() {
		this(new AuthenticationImpl(), new RoomDAOImpl());
	}

	public void BookRoomEvent() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../Views/BookRoom.fxml"));
		Stage stage = (Stage) this.bookRoomButton.getScene().getWindow();
		stage.setScene(new Scene(root, 600, 400));
		stage.show();
	}

	public void RoomsEvent() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../Views/Rooms.fxml"));
		Stage stage = (Stage) this.roomButton.getScene().getWindow();
		stage.setScene(new Scene(root, 600, 400));
		stage.show();
	}

	public void MyBookingsEvent() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../Views/MyBookings.fxml"));
		Stage stage = (Stage) this.roomButton.getScene().getWindow();
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
