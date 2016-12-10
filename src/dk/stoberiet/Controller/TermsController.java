package dk.stoberiet.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Olivi on 08-12-2016.
 */
public class TermsController {
    public Button accept;

    public void handleAcceptTerms () throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../Controller/MyBookings.fxml))"));
        Stage stage = (Stage) this.accept.getScene().getWindow();
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void handleCancelTerms () throws IOException {

    }
}
