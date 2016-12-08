package dk.stoberiet.DAO;

import java.sql.*;
import java.time.LocalDate;

/**
 * Created by Olivi on 06-12-2016.
 */
public class DAOReservation {


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

            //st.executeUpdate("INSERT into reservation (datee, roomID, anumber, reservationID) values('" + date + "', '" + roomID + "','" + username + "' , '" + reservationID + "')");


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

