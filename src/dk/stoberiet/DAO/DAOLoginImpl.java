package dk.stoberiet.DAO;

import dk.stoberiet.Models.CredentialModel;

import java.sql.*;

/**
 * Created by Olivi on 18-11-2016.
 */
public class DAOLoginImpl {
	public static CredentialModel fetchUser(String username, String password) {
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

			String s = "SELECT anumber, pass, role FROM users\n" +
					"WHERE anumber = '" + username + "' AND pass = '" + password + "'";

			rs = st.executeQuery(s);
			CredentialModel creds = new CredentialModel();
			if (rs.next()) {
				creds.setUsername(rs.getString("anumber"));
				creds.setPassword(rs.getString("pass"));
				creds.setRole(rs.getInt("role"));

				return creds;
			}

            /*String username = "";
            int m = 101;

            for (int i = 1; i <= 4; i++) {
                for (int j = 0; j < 56; j++) {
                    String s = "INSERT INTO userdata` (`username`) VALUES ();";
                    st.executeUpdate(s);
                }
                m += 100;
            }

            String s = "INSERT INTO `new_schema`.`userdata` (`username`) VALUES (hej);";
            //st.executeUpdate(s);
            /*String s = "SELECT username\n" +
                    "FROM `new_schema`.`userdata`;\n";
            rs = st.executeQuery(s);
            String stuff = "";
            if (rs.next()) {
                stuff = rs.getString(1);
            }
            System.out.println(stuff);
            */

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
			} catch (SQLException ex) {
			}
		}
		return null;
	}
}

