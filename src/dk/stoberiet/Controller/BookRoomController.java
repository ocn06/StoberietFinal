package dk.stoberiet.Controller;

import com.sun.xml.internal.ws.org.objectweb.asm.*;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import dk.stoberiet.Main;
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
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;

import static java.awt.SystemColor.infoText;

/**
 * Created by fede0004@stud.kea.dk on 05-12-2016.
 */
public class BookRoomController implements Initializable {

	@FXML
	private TextField username;

	public Button bookGoBack;

    public Button book;

	public DatePicker datePicker;

	public ComboBox chooseRoom;

    private LocalDate date;

    private String roomID;

    private int reservationID;

    private String room;

    public Text apartmentNumber;


	public void bookGoBack() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../View/MainMenu.fxml"));
		Stage stage = (Stage) this.bookGoBack.getScene().getWindow();
		stage.setScene(new Scene(root, 600, 400));
		stage.show();

        //ShowApartmentNumberController.setApartmentNumber();
	}

    public void handleChooseDate() throws  IOException {
        date = datePicker.getValue();
        System.out.println(date);

    }

    public void handleChooseRoom() throws IOException {
        room = String.valueOf(chooseRoom.getValue());
        System.out.println(room);
    }

    public ReservationModel handleBookRoom() throws IOException {

        ReservationModel reservation = new ReservationModel(this.date, this.roomID, LoginController.getApartmentNumber(), reservationID);
        this.date = datePicker.getValue();
        this.roomID = room;

        return reservation;
    }


    public void handleBook () throws IOException {

        try {
            //FXMLLoader loader = new FXMLLoader();
            //loader.setLocation(BookRoomController.class.getResource("../Controller/Terms.fxml"));
            //VBox vb = (VBox) loader.load();
            Parent root = FXMLLoader.load(getClass().getResource("C:\\Users\\Olivi\\Documents\\KEA\\2_semester\\Stoberiet\\src\\dk\\stoberiet\\Controller\\Terms.fxml"));
            Stage stage = (Stage)book.getScene().getWindow();
            //stage.setTitle("Regler for bookning");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chooseRoom.getItems().addAll(
                "Multirum",
                "Fælleslokale 0",
                "Fælleslokale 0 m. køkken",
                "Fælleslokale 0 u. køkken",
                "Fælleslokale 1",
                "Fælleslokale 2",
                "Fælleslokale 3",
                "Fælleslokale 4"
        );

        chooseRoom.setPromptText("Vælg lokale");
        this.roomID = String.valueOf(chooseRoom.getValue());
    }
}
