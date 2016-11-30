package dk.stoberiet.Controller;

import dk.stoberiet.BusinessLogic.Login;
import dk.stoberiet.BusinessLogic.LoginImplementation;
import dk.stoberiet.Models.CredentialModel;
//import dk.stoberiet.Models.Reservation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Olivi on 28-11-2016.
 */
public class Controller {
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;

	public Button bookRoom;

	public Button room;

	public Button roomsGoBack;

	public Button bookGoBack;

	public DatePicker chooseDate;

	public SplitMenuButton rooms;

	public MenuButton startTimeT;

	public MenuButton startTimeM;

	public MenuButton endTimeT;

	public MenuButton endTimeM;

	public void handleLogin() throws IOException {
		String username = this.username.getText();
		String password = this.password.getText();

		Login login = new LoginImplementation();
		CredentialModel credentialModel = login.fetchUser(username, password);
		if (credentialModel != null && credentialModel.getRole().equals(0)) {
			Parent root = FXMLLoader.load(getClass().getResource("../View/Menu.fxml"));
			Stage stage = (Stage) this.username.getScene().getWindow();
			stage.setScene(new Scene(root, 600, 400));
			stage.show();
		}

		else if (credentialModel != null && credentialModel.getRole().equals(1)) {
			Parent root = FXMLLoader.load(getClass().getResource("../View/AdminMenu.fxml"));
			Stage stage = (Stage) this.username.getScene().getWindow();
			stage.setScene(new Scene(root, 600, 400));
			stage.show();
		}

		else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Oops");
			alert.setHeaderText("Forkert brugernavn eller adgangskode");
			alert.setContentText("Prøv igen!");

			alert.showAndWait();
		}
	}

	public void handleBookRoom() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../View/BookRoom.fxml"));
		Stage stage = (Stage) this.bookRoom.getScene().getWindow();
		stage.setScene(new Scene(root, 600, 400));
		stage.show();
	}

	public void handleRooms() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../View/Rooms.fxml"));
		Stage stage = (Stage) this.room.getScene().getWindow();
		stage.setScene(new Scene(root, 600, 400));
		stage.show();
	}

	public void roomsGoBack() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../View/Menu.fxml"));
		Stage stage = (Stage) this.roomsGoBack.getScene().getWindow();
		stage.setScene(new Scene(root, 600, 400));
		stage.show();
	}

	public void bookGoBack() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../View/Menu.fxml"));
		Stage stage = (Stage) this.bookGoBack.getScene().getWindow();
		stage.setScene(new Scene(root, 600, 400));
		stage.show();
	}

	public void bookRoom (DatePicker date, String startTime, String endTime, String roomID, String username, int reservationID) {
		String sTime = startTimeT.getText() + ":" + startTimeM.getText();
		String eTime = endTimeT.getText() + ":" + endTimeM.getText();

		date = chooseDate;
		startTime = sTime;
		endTime = eTime;
		roomID = rooms.getText();
		username = this.username.getText();
		reservationID++;


	}
}
