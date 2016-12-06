package dk.stoberiet.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

/**
 * Created by fede0004@stud.kea.dk on 05-12-2016.
 */
public class BookRoomController {

	@FXML
	private TextField username;

	public Button bookGoBack;

	public DatePicker chooseDate;

	public SplitMenuButton rooms;



	public void bookGoBack() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../View/MainMenu.fxml"));
		Stage stage = (Stage) this.bookGoBack.getScene().getWindow();
		stage.setScene(new Scene(root, 600, 400));
		stage.show();
	}

    public LocalDate handleChooseDate() throws  IOException {
        LocalDate date = chooseDate.getValue();
        return date;
    }

    public void handleBookRoomFunction(LocalDate date, String roomID, String username, int reservationID) throws IOException {
        date = handleChooseDate();
        roomID = rooms.getText();
        username = this.username.getText();
        reservationID++;

        {
            Connection con = null;
            Statement st = null;
            ResultSet rs = null;
            String url = "jdbc:mysql://rds-mysql-projekt.cct8kidkcew5.eu-central-1.rds.amazonaws.com:3306/stoberiet";
            String user = "masterkeauser";
            String passwordConnection = "myserver";

            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();

                con = DriverManager.getConnection(url, user, passwordConnection);
                st = con.createStatement();

                st.executeUpdate("INSERT into reservation (datee, roomID, anumber, reservationID) values('" + date + "', '" + roomID + "','" + username + "' , '" + reservationID + "')");


            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (st != null) {
                        st.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                }
                catch (SQLException ex) {
                }
            }
        }
    }
}
