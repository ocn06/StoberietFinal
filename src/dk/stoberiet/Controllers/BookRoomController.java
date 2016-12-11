package dk.stoberiet.Controllers;

import dk.stoberiet.BusinessLogic.Authentication;
import dk.stoberiet.BusinessLogic.AuthenticationImpl;
import dk.stoberiet.Data.RoomDAO;
import dk.stoberiet.Data.RoomDAOImpl;
import dk.stoberiet.Models.ReservationModel;
import dk.stoberiet.Models.RoomModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
	private Button bookGoBack;

    public BookRoomController(Authentication authentication, RoomDAO roomDAO) {
        this.authentication = authentication;
        this.roomDAO = roomDAO;
    }

    public BookRoomController() {
        this(new AuthenticationImpl(), new RoomDAOImpl());
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

    public ReservationModel bookRoom() throws IOException {
	    System.out.println("bookRoomEvent");

	    // TODO : GUARD AGAINST VALUES NOT BEING SELECTED ELSE THIS WILL THROW AN EXCEPTION

        LocalDateTime start = this.selectDateDatePicker.getValue().atStartOfDay();
        LocalDateTime end = this.selectDateDatePicker.getValue().atStartOfDay().plusHours(24);
	    int userId = this.authentication.getLoggedInUser().getId();
	    int roomId = this.selectRoomComboBox.getValue().getId();

        ReservationModel reservation = new ReservationModel(start, end, userId, roomId);

        return reservation;
    }

    @FXML
    public void bookEvent () throws IOException {

        Parent root;
        try {

            root = FXMLLoader.load(getClass().getResource("../Views/Terms.fxml"));
//            root = FXMLLoader.load(getClass().getClassLoader().getResource("Terms.fxml")); //, resources);

            Stage stage = new Stage();
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root, 450, 450));
            stage.show();
            // Hide this current window (if this is what you want)
            //((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

//
//        try {
//            //FXMLLoader loader = new FXMLLoader();
//            //loader.setLocation(BookRoomController.class.getResource("../Controllers/Terms.fxml"));
//            //VBox vb = (VBox) loader.load();
//            Parent root = FXMLLoader.load(getClass().getResource("C:\\Users\\Olivi\\Documents\\KEA\\2_semester\\Stoberiet\\src\\dk\\stoberiet\\Controllers\\Terms.fxml"));
//            Stage stage = (Stage)bookButton.getScene().getWindow();
//            //stage.setTitle("Regler for bookning");
//            stage.initModality(Modality.WINDOW_MODAL);
//            stage.setScene(new Scene(root));
//            stage.showAndWait();
//        }
//
//        catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void bookGoBack() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/MainMenu.fxml"));
        Stage stage = (Stage) this.bookGoBack.getScene().getWindow();
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
}
