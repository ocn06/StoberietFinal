package dk.stoberiet.Controller;

import dk.stoberiet.Models.CredentialModel;
import dk.stoberiet.Models.ReservationModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;

/**
 * Created by fede0004@stud.kea.dk on 05-12-2016.
 */
public class BookRoomController {

	@FXML
	private TextField username;

	public Button bookGoBack;

	public DatePicker chooseDate;

	public ComboBox chooseRoom;

    private LocalDate date;

    private String roomID;

    private int reservationID;

    private String room;


	public void bookGoBack() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../View/MainMenu.fxml"));
		Stage stage = (Stage) this.bookGoBack.getScene().getWindow();
		stage.setScene(new Scene(root, 600, 400));
		stage.show();
	}

    public void handleChooseDate() throws  IOException {
        //this.date = chooseDate.getValue();
        this.date = new LocalDate(chooseDate.getValue().toEpochDay());

    }

    public void handleChooseRoom() throws IOException {
        room = String.valueOf(chooseRoom.getValue());
        System.out.println(room);
    }

    public ReservationModel handleBookRoom() throws IOException {

        ReservationModel reservation = new ReservationModel(this.date, this.roomID, LoginController.getApartmentNumber(), reservationID);
        this.handleChooseDate();
        this.roomID = room;


        return reservation;
    }

    @FXML
    public void initialize() {
        ObservableList<String> options = FXCollections.observableArrayList();
        options.add("Multirum");
        options.add("Fælleslokale 0");
        options.add("Fælleslokale 0 m. køkken");
        options.add("Fælleslokale 0 u. køkken");
        options.add("Fælleslokale 1");
        options.add("Fælleslokale 2");
        options.add("Fælleslokale 3");
        options.add("Fælleslokale 4");

        chooseRoom.setItems(options);

    }
}
