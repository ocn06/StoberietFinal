package dk.stoberiet.Controllers;

import dk.stoberiet.BusinessLogic.Authentication;
import dk.stoberiet.BusinessLogic.AuthenticationImpl;
import dk.stoberiet.Models.UserModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
	Authentication authentication;

	@FXML
	private TextField usernameTextfield;
	@FXML
	private PasswordField passwordTextfield;

//	static private int apartmentNumber;
//
//	public static int getApartmentNumber() {
//		return apartmentNumber;
//	}

	public LoginController(Authentication authentication) {
		this.authentication = authentication;
	}

	public LoginController() {
		this(new AuthenticationImpl());
	}

	public void handleLogin() throws IOException {
		// Get username and password entered by the user
		String username = this.usernameTextfield.getText();
		String password = this.passwordTextfield.getText();

		UserModel userModel = authentication.login(username, password);

		if (userModel != null)
		{
			switch (userModel.getRole())
			{
				case USER:
					this.showUserView();
					break;
				case ADMIN:
					this.showAdminView();
					break;
				default:
					throw new UnsupportedOperationException("RoleType is not supported: " + userModel.getRole().name());
			}

			return;
		}

		// Show error
		this.showValidationError();
	}

	private void showUserView() throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("../Views/MainMenu.fxml"));
		Stage stage = (Stage) this.usernameTextfield.getScene().getWindow();
		stage.setScene(new Scene(root, 600, 400));
		stage.show();

		//ShowApartmentNumberController.setApartmentNumber(credentialModel.getUsername());
		//this.apartmentNumber = Integer.parseInt(credentialModel.getUsername());
	}

	private void showAdminView() throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("../Views/MainMenuAdmin.fxml"));
		Stage stage = (Stage) this.usernameTextfield.getScene().getWindow();
		stage.setScene(new Scene(root, 600, 400));
		stage.show();
	}

	private void showValidationError()
	{
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Oops");
		alert.setHeaderText("Forkert brugernavn eller adgangskode");
		alert.setContentText("Prøv igen!");
		alert.showAndWait();
	}
}
