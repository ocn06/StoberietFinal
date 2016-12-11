package dk.stoberiet.Data;

public class ReservationDAOImpl implements ReservationDAO {
    private DatabaseHelper databaseHelper;

    public ReservationDAOImpl(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public ReservationDAOImpl() {
        this(new MySqlDatabaseHelper());
    }


}

