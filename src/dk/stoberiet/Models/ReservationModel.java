package dk.stoberiet.Models;

import java.time.LocalDate;

/**
 * Created by fede0004@stud.kea.dk on 05-12-2016.
 */
public class ReservationModel {
    private LocalDate date;
    private String roomID;
    public String username;
    private int reservationID;

    public ReservationModel(LocalDate date, String roomID, int apartmentNumber, int reservationID){

    }

    public ReservationModel(LocalDate date, String roomID, String username, int reservationID) {
        this.date = date;
        this.roomID = roomID;
        this.username = username;
        this.reservationID = reservationID;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "date=" + date +
                ", roomID=" + roomID +
                ", username='" + username + '\'' +
                ", reservationID=" + reservationID +
                '}';
    }
}
