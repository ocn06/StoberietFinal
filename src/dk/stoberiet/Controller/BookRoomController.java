package dk.stoberiet.Controller;

import dk.stoberiet.Models.ReservationModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by fede0004@stud.kea.dk on 05-12-2016.
 */
public class BookRoomController {

	@FXML
	private TextField username;

	@FXML
	private Button bookGoBack;

	@FXML
	private DatePicker datePicker;

	@FXML
	private ComboBox chooseRoom;

	@FXML
    private LocalDate date;

    private String roomID;

    private int reservationID;

    private String room;

	@FXML
	private void bookGoBack() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
		Stage stage = (Stage) this.bookGoBack.getScene().getWindow();
		stage.setScene(new Scene(root, 600, 400));
		stage.show();
	}
	@FXML
    private void handleChooseDate() throws  IOException {
        this.date = datePicker.getValue();
		System.out.println("Selected date: " + date);


    }

	@FXML
    private void handleChooseRoom() throws IOException {
        room = String.valueOf(chooseRoom.getValue());
        System.out.println(room);
    }

	@FXML
    private void handleBookRoom() throws IOException {
		ReservationModel reservation = new ReservationModel(this.date, this.roomID, LoginController.getApartmentNumber(), reservationID);

        this.handleChooseDate();
        this.roomID = room;

		//storeReservation(reservation));
    }

    @FXML
    private void initialize() {
		datePicker.setValue(LocalDate.now());




        /*ObservableList<String> options = FXCollections.observableArrayList();
        options.add("Multirum");
        options.add("Fælleslokale 0");
        options.add("Fælleslokale 0 m. køkken");
        options.add("Fælleslokale 0 u. køkken");
        options.add("Fælleslokale 1");
        options.add("Fælleslokale 2");
        options.add("Fælleslokale 3");
		final boolean add = options.add("Fælleslokale 4");

		chooseRoom.setItems(options);*/
    }
}
