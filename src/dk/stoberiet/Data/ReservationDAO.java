package dk.stoberiet.Data;

import dk.stoberiet.Models.ReservationModel;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationDAO {
    List<ReservationModel> getAllReservationsByUserId(int userId);
    ReservationModel createReservation(int userId, int roomId, LocalDateTime start, LocalDateTime end);
    boolean isReservationDateAvailable(LocalDateTime start, LocalDateTime end);
}
