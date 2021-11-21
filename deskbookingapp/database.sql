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
    username VARCHAR(255) NOT NULL,
    booking_date DATE
);

CREATE TABLE registerUser
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
		pwd VARCHAR(255) NOT NULL
);


INSERT INTO booking (username, booking_date) VALUES ("user1", '2021-11-12');
INSERT INTO booking (username, booking_date) VALUES ("user1", '2022-03-26');
INSERT INTO booking (username, booking_date) VALUES ("user2", '2023-10-01');
INSERT INTO booking (username, booking_date) VALUES ("user2", '2026-06-20');
INSERT INTO user1 (username,pwd) VALUES ("tester1","123");