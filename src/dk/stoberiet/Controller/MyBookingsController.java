package dk.stoberiet.Controller;

import javafx.scene.control.TextArea;

import java.sql.*;

/**
 * Created by Olivi on 08-12-2016.
 */
public class MyBookingsController {
    public TextArea showBookings;


    public void setShowBookings(TextArea showBookings) {

        //this.showBookings = s;
    }
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

            String s = "SELECT reservation_date, roomID, anumber, reservationID FROM reservation\n" +
                    "WHERE anumber = '" + 248;

             st.executeQuery(s);

            //this.showBookings = s;


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
