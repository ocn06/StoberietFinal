package dk.stoberiet.Controller;

import dk.stoberiet.BusinessLogic.Login;
import dk.stoberiet.BusinessLogic.LoginImplementation;
import dk.stoberiet.Models.CredentialModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by fede0004@stud.kea.dk on 05-12-2016.
 */
public class LoginController {
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;


	static private int apartmentNumber;


	public static int getApartmentNumber() {
		return apartmentNumber;
	}

	public void handleLogin() throws IOException {
		String username = this.username.getText();
		String password = this.password.getText();

		Login login = new LoginImplementation();
		CredentialModel credentialModel = login.fetchUser(username, password);
		if (credentialModel != null && credentialModel.getRole().equals(0)) {
			Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
			Stage stage = (Stage) this.username.getScene().getWindow();
			stage.setScene(new Scene(root, 600, 400));
			stage.show();

			this.apartmentNumber = Integer.parseInt(credentialModel.getUsername());
		}

		else if (credentialModel != null && credentialModel.getRole().equals(1)) {
			Parent root = FXMLLoader.load(getClass().getResource("MainMenuAdmin.fxml"));
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

}
