package dk.stoberiet.Controllers;

import dk.stoberiet.BusinessLogic.Authentication;
import dk.stoberiet.BusinessLogic.AuthenticationImpl;
import dk.stoberiet.DAO.ReservationDAO;
import dk.stoberiet.DAO.ReservationDAOImpl;
import dk.stoberiet.DAO.RoomDAO;
import dk.stoberiet.Models.ReservationModel;
import dk.stoberiet.Models.UserModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MyBookingsController implements Initializable {
    private Authentication authentication;
    private RoomDAO roomDAO;
    private ReservationDAO reservationsDAO;

    @FXML
    public Text usernameText;

    @FXML
    public TextArea showBookingsTextArea;

    @FXML
    public Button myBookingsGoBack;


    public MyBookingsController(Authentication authentication, ReservationDAO reservationsDAO) {
        this.authentication = authentication;
        this.reservationsDAO = reservationsDAO;
    }

    public MyBookingsController() {
        this(new AuthenticationImpl(), new ReservationDAOImpl());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set Username
        String username = this.authentication.getLoggedInUser().getUsername();
        this.usernameText.setText(username);

        this.fillBookings();
    }

    @FXML
    public void MyBookingsGoBackEvent () throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../Views/MainMenu.fxml"));
        Stage stage = (Stage) this.myBookingsGoBack.getScene().getWindow();
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void fillBookings() {

        // TODO Move to Business Logic

        UserModel user = this.authentication.getLoggedInUser();

        List<ReservationModel> reservations = this.reservationsDAO.getAllReservationsByUserId(user.getId());

        String bookings = "";
        for (ReservationModel model : reservations) {
           bookings += this.getBookingLine(model) + "\n";
        }

        showBookingsTextArea.setText(bookings);
    }

    private String getBookingLine(ReservationModel reservation) {
        return "ReservationsID: " + reservation.getId() + "\n Starttidspunkt: " + reservation.getStart() + "\n Sluttidspunkt: " + reservation.getEnd() + "\n Rum: " + reservation.getRoomId() + "\n Bruger: " + this.authentication.getLoggedInUser().getUsername()+ "\n\n";
    }
}
