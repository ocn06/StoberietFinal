package dk.stoberiet.DAO;

import dk.stoberiet.Models.ReservationModel;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {

    private DatabaseHelper databaseHelper;

    public ReservationDAOImpl(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public ReservationDAOImpl() {
        this(new MySqlDatabaseHelper());
    }

    @Override
    public List<ReservationModel> getAllReservationsByUserId(int userId) {

        return this.databaseHelper.queryAll(
                "SELECT id, start, end, timestamp, roomId FROM reservations WHERE userId = ?",
                (statement) -> statement.setInt(1, userId),
                (resultSet) -> {
                    return new ReservationModel(
                            new Integer(resultSet.getInt("id")),
                            this.convertToLocalDateTime(resultSet.getTimestamp("start")),
                            this.convertToLocalDateTime(resultSet.getTimestamp("end")),
                            this.convertToLocalDateTime(resultSet.getTimestamp("timestamp")),
                            userId,
                            resultSet.getInt("roomId")
                    );
                }
        );
    }

    // TODO : Move to DatabaseHelper class
    private LocalDateTime convertToLocalDateTime(java.sql.Timestamp timestamp) {
        if (timestamp != null) {
            return timestamp.toLocalDateTime();
        }
        return null;
    }

    @Override
    public ReservationModel createReservation(int userId, int roomId, LocalDateTime start, LocalDateTime end) {
        this.databaseHelper.executeUpdate(
                "INSERT INTO reservations (start, end, userId, roomId) VALUES (?, ?, ?, ?)",
                (statement) -> {
                    statement.setTimestamp(1, Timestamp.valueOf(start));
                    statement.setTimestamp(2, Timestamp.valueOf(end));
                    statement.setInt(3, userId);
                    statement.setInt(4, roomId);
                }
        );
        return null;
    }

    @Override
    public boolean isReservationDateAvailable(LocalDateTime start, LocalDateTime end, int roomId) {

        // TODO ADD ROOM ID CHECK

        Integer count = this.databaseHelper.<Integer>querySingle(
                "SELECT COUNT(1) FROM reservations WHERE(start < ? AND end > ? AND roomId = ?)",
                (statement) -> {
                    statement.setTimestamp(1, Timestamp.valueOf(end));
                    statement.setTimestamp(2, Timestamp.valueOf(start));
                    statement.setInt(3, roomId);
                },
                (resultSet) -> resultSet.getInt(1));

        return count == 0;
    }


}

