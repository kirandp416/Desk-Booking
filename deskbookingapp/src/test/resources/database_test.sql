DROP DATABASE IF EXISTS deskbookingtest;

CREATE DATABASE deskbookingtest;

USE deskbookingtest;

CREATE TABLE room
(
    room_id   INT AUTO_INCREMENT,
    room_name VARCHAR(255) NOT NULL,
    CONSTRAINT room_pk_index PRIMARY KEY (room_id)
);
