package dk.stoberiet.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by fede0004@stud.kea.dk on 05-12-2016.
 */
public class BookRoomController {

	@FXML
	private TextField username;

	public Button bookGoBack;

	public DatePicker chooseDate;

	public SplitMenuButton rooms;

	public MenuButton startTimeT;

	public MenuButton startTimeM;

	public MenuButton endTimeT;

	public MenuButton endTimeM;


	public void bookGoBack() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../View/MainMenu.fxml"));
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
