package dk.stoberiet.Controllers;

import dk.stoberiet.BusinessLogic.Authentication;
import dk.stoberiet.BusinessLogic.AuthenticationImpl;
import dk.stoberiet.DAO.ReservationDAO;
import dk.stoberiet.DAO.ReservationDAOImpl;
import dk.stoberiet.DAO.RoomDAO;
import dk.stoberiet.DAO.RoomDAOImpl;
import dk.stoberiet.Models.RoomModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

public class BookRoomController implements Initializable {
    private Authentication authentication;
    private RoomDAO roomDAO;
    private ReservationDAO reservationDAO;

    @FXML
    private DatePicker selectDateDatePicker;
    @FXML
    private ComboBox<RoomModel> selectRoomComboBox;
    @FXML
    private CheckBox acceptTermsCheckBox;
    @FXML
    private Button bookButton;
    @FXML
	private Text usernameText;
    @FXML
	private Button bookGoBackButton;

    public BookRoomController(Authentication authentication, RoomDAO roomDAO, ReservationDAO reservationDAO) {
        this.authentication = authentication;
        this.roomDAO = roomDAO;
        this.reservationDAO = reservationDAO;
    }

    public BookRoomController() {
        this(new AuthenticationImpl(), new RoomDAOImpl(), new ReservationDAOImpl());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set Username
        String username = this.authentication.getLoggedInUser().getUsername();
        this.usernameText.setText(username);

        // Get all rooms
        List<RoomModel> rooms = this.roomDAO.getAllRooms();
        // Fill ComboBox with options
        this.selectRoomComboBox.getItems().addAll(rooms);
    }

    @FXML
    public void acceptTermsEvent() throws IOException {
        boolean isTermsAccepted = this.acceptTermsCheckBox.isSelected();
        this.bookButton.setDisable(!isTermsAccepted);
    }

    public boolean bookRoom() throws IOException {

	    // TODO : GUARD AGAINST VALUES NOT BEING SELECTED ELSE THIS WILL THROW AN EXCEPTION

        LocalDateTime start = this.selectDateDatePicker.getValue().atStartOfDay();
        LocalDateTime end = this.selectDateDatePicker.getValue().atStartOfDay().plusHours(23).plusMinutes(59).plusSeconds(59);
	    int userId = this.authentication.getLoggedInUser().getId();
	    int roomId = this.selectRoomComboBox.getValue().getId();

        if (start.isBefore(LocalDateTime.now())) {
            WindowUtility.showWindow(
                    Alert.AlertType.ERROR,
                    "Oops",
                    "Datoen er passeret",
                    "Prøv med en ny dato");
            return false;
        }

        if (!this.reservationDAO.isReservationDateAvailable(start, end, roomId)) {
            WindowUtility.showWindow(
                    Alert.AlertType.ERROR,
                    "Oops",
                    "Lokalet er optaget på givne dato",
                    "Prøv med en ny dato eller lokale");
            return false;
        }

        this.reservationDAO.createReservation(userId, roomId, start, end);
        return true;
    }

    @FXML
    public void bookEvent () throws IOException {
        boolean isRoomBooked = this.bookRoom();
        if (isRoomBooked) {
            this.showMyBookings();
        }
    }

    private void showMyBookings() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../Views/MyBookings.fxml"));
        Stage stage = (Stage) this.bookButton.getScene().getWindow();
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    @FXML
    public void bookGoBackEvent () throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../Views/MainMenu.fxml"));
        Stage stage = (Stage) this.bookGoBackButton.getScene().getWindow();
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
}
