DROP DATABASE IF EXISTS deskbooking;

CREATE DATABASE deskbooking;

USE deskbooking;

CREATE TABLE room
(
    room_id   INT PRIMARY KEY AUTO_INCREMENT,
    room_name VARCHAR(255) NOT NULL
);

CREATE TABLE desk
(
    desk_id   INT AUTO_INCREMENT,
    room_id   INT,
    desk_name VARCHAR(255) NOT NULL,
    CONSTRAINT desk_pk_index PRIMARY KEY (desk_id),
    CONSTRAINT desk_room_fk_index FOREIGN KEY (room_id) REFERENCES room (room_id)
);

CREATE TABLE booking
(
    booking_id   INT PRIMARY KEY AUTO_INCREMENT,
    booking_date DATE
);
