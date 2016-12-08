package dk.stoberiet.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by fede0004@stud.kea.dk on 05-12-2016.
 */
public class RoomsController {
	public Button roomsGoBack;

	public void roomsGoBack() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
		Stage stage = (Stage) this.roomsGoBack.getScene().getWindow();
		stage.setScene(new Scene(root, 600, 400));
		stage.show();
	}
}
