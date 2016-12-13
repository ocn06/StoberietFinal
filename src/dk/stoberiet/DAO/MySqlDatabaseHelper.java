package dk.stoberiet.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlDatabaseHelper implements DatabaseHelper {

    @Override
    public Connection getConnection() throws SQLException {

        /*try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/

        String connectionUrl = "jdbc:mysql://rds-mysql-projekt.cct8kidkcew5.eu-central-1.rds.amazonaws.com:3306/stoberiet";
        String connectionUsername = "masterkeauser";
        String connectionPassword = "myserver";

        Connection connection = DriverManager.getConnection(connectionUrl, connectionUsername, connectionPassword);

        return connection;
    }

    @Override
    public <T> List<T> queryAll(String preparedSqlQuery, CheckedFunction<ResultSet, T, SQLException> mapFunc) {

        return this.<T>queryAll(preparedSqlQuery, null, mapFunc);
    }

    @Override
    public <T> List<T> queryAll(String preparedSqlQuery, CheckedAction<PreparedStatement, SQLException> prepareStatementFunc, CheckedFunction<ResultSet, T, SQLException> mapFunc) {

        return this.<List<T>>executeQuery(preparedSqlQuery, prepareStatementFunc, (resultSet) -> {

            List<T> result = new ArrayList<T>();
            while(resultSet.next())
            {
                T obj = mapFunc.apply(resultSet);
                result.add(obj);
            }

            return result;
        });
    }

    @Override
    public <T> T querySingle(String preparedSqlQuery, CheckedFunction<ResultSet, T, SQLException> mapFunc) {

        return this.querySingle(preparedSqlQuery, null, mapFunc);
    }

    @Override
    public <T> T querySingle(String preparedSqlQuery, CheckedAction<PreparedStatement, SQLException> prepareStatementFunc, CheckedFunction<ResultSet, T, SQLException> mapFunc) {

        return this.executeQuery(preparedSqlQuery, prepareStatementFunc, (resultSet) -> {

            if (resultSet.next()) {
                return mapFunc.apply(resultSet);
            }

            return null;
        });
    }

    @Override
    public <T> T executeQuery(String preparedSqlQuery, CheckedFunction<ResultSet, T, SQLException> resultSetFunc) {

        return this.executeQuery(preparedSqlQuery, null, resultSetFunc);
    }

    @Override
    public <T> T executeQuery(String preparedSqlQuery, CheckedAction<PreparedStatement, SQLException> prepareStatementFunc, CheckedFunction<ResultSet, T, SQLException> resultSetFunc) {

        return this.execute(preparedSqlQuery, (statement) -> {

            if (prepareStatementFunc != null) {
                prepareStatementFunc.apply(statement);
            }

            // ResultSet implements AutoCloseable
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSetFunc.apply(resultSet);
            }
        });
    }

    @Override
    public int executeUpdate(String preparedSqlQuery) {

        return this.<Integer>executeUpdate(preparedSqlQuery, null);
    }

    @Override
    public int executeUpdate(String preparedSqlQuery, CheckedAction<PreparedStatement, SQLException> prepareStatementFunc) {

        return this.<Integer>execute(preparedSqlQuery, (statement) -> {

            if (prepareStatementFunc != null) {
                prepareStatementFunc.apply(statement);
            }

            return statement.executeUpdate();
        });
    }

    @Override
    public <T> T execute(String preparedSqlQuery, CheckedFunction<PreparedStatement, T, SQLException> func) {
        T result = null;

        // Connection implements AutoClosable which have close() method that will automatically be called when exiting the try statement
        try (Connection connection = this.getConnection()) {

            // PreparedStatement implements AutoCloseable
            // Using PreparedStatement to guard against SQL injection
            try (PreparedStatement statement = connection.prepareStatement(preparedSqlQuery))
            {
                result = func.apply(statement);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
