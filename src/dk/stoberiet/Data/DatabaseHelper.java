package dk.stoberiet.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DatabaseHelper {
    Connection getConnection() throws SQLException;

    <T> List<T> queryAll(String preparedSqlQuery, CheckedFunction<ResultSet, T, SQLException> mapFunc);
    <T> List<T> queryAll(String preparedSqlQuery, CheckedAction<PreparedStatement, SQLException> prepareStatementFunc, CheckedFunction<ResultSet, T, SQLException> mapFunc);

    <T> T querySingle(String preparedSqlQuery, CheckedFunction<ResultSet, T, SQLException> mapFunc);
    <T> T querySingle(String preparedSqlQuery, CheckedAction<PreparedStatement, SQLException> prepareStatementFunc, CheckedFunction<ResultSet, T, SQLException> mapFunc);

    <T> T executeQuery(String preparedSqlQuery, CheckedFunction<ResultSet, T, SQLException> resultSetFunc);
    <T> T executeQuery(String preparedSqlQuery, CheckedAction<PreparedStatement, SQLException> prepareStatementFunc, CheckedFunction<ResultSet, T, SQLException> resultSetFunc);

    int executeUpdate(String preparedSqlQuery);
    int executeUpdate(String preparedSqlQuery, CheckedAction<PreparedStatement, SQLException> prepareStatementFunc);

    <T> T execute(String preparedSqlQuery, CheckedFunction<PreparedStatement, T, SQLException> func);
}
