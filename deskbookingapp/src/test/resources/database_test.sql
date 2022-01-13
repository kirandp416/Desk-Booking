DROP DATABASE IF EXISTS deskbookingtest;

CREATE DATABASE deskbookingtest;

USE deskbookingtest;

CREATE TABLE room
(
    room_id   INT AUTO_INCREMENT,
    room_name VARCHAR(255) NOT NULL,
    CONSTRAINT room_pk_index PRIMARY KEY (room_id)
);
CREATE TABLE desk
(
    desk_id      INT AUTO_INCREMENT,
    room_id      INT,
    desk_type_id INT,
    desk_name    VARCHAR(255) NOT NULL,
    notes        VARCHAR(255),
    CONSTRAINT desk_pk_index PRIMARY KEY (desk_id),
    CONSTRAINT desk_room_fk_index FOREIGN KEY (room_id) REFERENCES room (room_id) ON DELETE CASCADE
);