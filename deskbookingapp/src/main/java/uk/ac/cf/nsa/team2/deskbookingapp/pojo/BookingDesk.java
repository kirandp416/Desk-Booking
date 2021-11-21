package uk.ac.cf.nsa.team2.deskbookingapp.pojo;

//import lombok.Data;

import java.util.Date;
import java.util.Objects;


public class BookingDesk {
    private Integer bookingId;
    private String username;
    private Date bookingDate;
    private Integer roomId;
    private Integer deskId;

    public BookingDesk(Integer bookingId, String username, Date bookingDate, Integer roomId, Integer deskId) {
        this.bookingId = bookingId;
        this.username = username;
        this.bookingDate = bookingDate;
        this.roomId = roomId;
        this.deskId = deskId;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getDeskId() {
        return deskId;
    }

    public void setDeskId(Integer deskId) {
        this.deskId = deskId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingDesk that = (BookingDesk) o;
        return Objects.equals(bookingId, that.bookingId) &&
                Objects.equals(username, that.username) &&
                Objects.equals(bookingDate, that.bookingDate) &&
                Objects.equals(roomId, that.roomId) &&
                Objects.equals(deskId, that.deskId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId, username, bookingDate, roomId, deskId);
    }

    @Override
    public String toString() {
        return "bookingDesk{" +
                "bookingId=" + bookingId +
                ", username='" + username + '\'' +
                ", bookingDate=" + bookingDate +
                ", roomId=" + roomId +
                ", deskId=" + deskId +
                '}';
    }
}
