package dk.stoberiet.Data;

import dk.stoberiet.Models.RoleType;
import dk.stoberiet.Models.UserModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    private DatabaseHelper databaseHelper;

    public UserDAOImpl(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public UserDAOImpl() {
        this(new MySqlDatabaseHelper());
    }

    @Override
    public UserModel getUserByUsername(String username) {

        String preparedSqlQuery = "SELECT id, username, password, email, role FROM users WHERE username = ?";

        CheckedAction<PreparedStatement, SQLException> prepareStatement = (statement) -> {
            statement.setString(1, username);
        };

        CheckedFunction<ResultSet, UserModel, SQLException> mapFunc = (resultSet) -> {
            int rsId = resultSet.getInt("id");
            String rsUsername = resultSet.getString("username");
            String rsPassword = resultSet.getString("password");
            String rsEmail = resultSet.getString("email");
            RoleType rsRole = RoleType.getByValue(resultSet.getInt("role"));

            return new UserModel(rsId, rsUsername, rsPassword, rsEmail, rsRole);
        };

        return this.databaseHelper.<UserModel>querySingle(preparedSqlQuery, prepareStatement, mapFunc);
    }
}

