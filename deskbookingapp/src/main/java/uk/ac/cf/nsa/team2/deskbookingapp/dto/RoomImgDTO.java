package uk.ac.cf.nsa.team2.deskbookingapp.dto;

/**
 * @description:
 * @author: yiting zheng
 * @time: 2021/12/12
 */

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTOs are often used in conjunction with data access objects to retrieve data from a database.
 */
public class RoomImgDTO {
    @JsonProperty("roomId")
    private int roomId;
    @JsonProperty("roomName")
    private String roomName;
    @JsonProperty("roomImgUrl")
    private String roomImgUrl;

    public RoomImgDTO() {

    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomImgUrl() {
        return roomImgUrl;
    }

    public void setRoomImgUrl(String roomImgUrl) {
        this.roomImgUrl = roomImgUrl;
    }


    public RoomImgDTO(int roomId, String roomName, String roomImgUrl) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.roomImgUrl = roomImgUrl;
    }

    public RoomImgDTO(int roomId) {
        this.roomId = roomId;
    }
}
