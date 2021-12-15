package uk.ac.cf.nsa.team2.deskbookingapp.dto;

import java.util.Date;

/**
 * @description:
 * @author: yiting zheng
 * @time: 2021/12/4
 */

/**
 * DTOs are often used in conjunction with data access objects to retrieve data from a database.
 */

public class BookingCommentDTO {

    private int roomId;
    private int deskId;
    private String userName;
    private String deskName;
    private String roomName;
    private Date bookingDate;

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getDeskId() {
        return deskId;
    }

    public void setDeskId(int deskId) {
        this.deskId = deskId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeskName() {
        return deskName;
    }

    public void setDeskName(String deskName) {
        this.deskName = deskName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public BookingCommentDTO(int roomId, int deskId, String userName, String deskName, String roomName, Date bookingDate) {
        this.roomId = roomId;
        this.deskId = deskId;
        this.userName = userName;
        this.deskName = deskName;
        this.roomName = roomName;
        this.bookingDate = bookingDate;
    }
}
