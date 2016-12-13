package dk.stoberiet.DAO;

import dk.stoberiet.Models.RoomModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {
    private DatabaseHelper databaseHelper;

    public RoomDAOImpl(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public RoomDAOImpl()  {
        this(new MySqlDatabaseHelper());
    }

    @Override
    public List<RoomModel> getAllRooms() {

        CheckedFunction<ResultSet, RoomModel, SQLException> mapFunc = (resultSet) ->
        {
            int rsId = resultSet.getInt("id");
            String rsName = resultSet.getString("name");
            String rsDescription = resultSet.getString("description");

            return new RoomModel(rsId, rsName, rsDescription);
        };

        return this.databaseHelper.<RoomModel>queryAll(
                "SELECT id, name, description FROM rooms",
                mapFunc);
    }
}

