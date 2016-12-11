package dk.stoberiet.Models;

import java.time.LocalDateTime;

public class ReservationModel {
    private Integer id;
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime timestamp;
    private int userId;
    private int roomId;

    public ReservationModel(Integer id, LocalDateTime start, LocalDateTime end, LocalDateTime timestamp, int userId, int roomId){
        this.id = id;
        this.start = start;
        this.end = end;
        this.timestamp = timestamp;
        this.userId = userId;
        this.roomId = roomId;
    }

    public ReservationModel(LocalDateTime start, LocalDateTime end, int userId, int roomId){
        this(null, start, end, null, userId, roomId);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
}
