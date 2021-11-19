DROP DATABASE IF EXISTS deskbooking;

CREATE DATABASE deskbooking;

USE deskbooking;

CREATE TABLE room
(
    room_id   INT PRIMARY KEY AUTO_INCREMENT,
    room_name VARCHAR(255) NOT NULL
);

CREATE TABLE booking
(
    booking_id   INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    booking_date DATE
);

INSERT INTO booking (booking_id, username, booking_date) VALUES (null, "user1", '2021-11-19');
INSERT INTO booking (booking_id, username, booking_date) VALUES (null, "user1", '2021-11-19');
INSERT INTO booking (booking_id, username, booking_date) VALUES (null, "user1", '2021-11-19');
INSERT INTO booking (booking_id, username, booking_date) VALUES (null, "user1", '2021-11-19');
INSERT INTO booking (booking_id, username, booking_date) VALUES (null, "user1", '2021-11-19');

