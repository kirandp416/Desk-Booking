package uk.ac.cf.nsa.team2.deskbookingapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.time.LocalDateTime;

/**
 * @description:
 * @author: yiting zheng
 * @time: 2021/12/1
 */
public class CommentDTO {
    // solve the problem of the JS cannot access long ig due to IEEE 754
    @JsonSerialize(using = ToStringSerializer.class)
    private long id;
    private int roomId;
    private String comment;
    private String userName;
    private String img;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedTime;
    private String deleted;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public CommentDTO() {

    }

    public CommentDTO(long id, int roomId, String comment, String userName, String img, LocalDateTime createdTime, LocalDateTime updatedTime, String deleted) {
        this.id = id;
        this.roomId = roomId;
        this.comment = comment;
        this.userName = userName;
        this.img = img;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "id=" + id +
                ", roomId=" + roomId +
                ", comment='" + comment + '\'' +
                ", userName='" + userName + '\'' +
                ", img='" + img + '\'' +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                ", deleted='" + deleted + '\'' +
                '}';
    }

}
