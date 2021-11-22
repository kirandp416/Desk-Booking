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

INSERT INTO booking (username, booking_date) VALUES ("user1", '2021-11-12'),
                                                     ("user1", '2022-03-26'),
                                                     ("user2", '2023-10-01'),
                                                     ("user2", '2026-06-20');

INSERT INTO room(room_name) VALUES ("Bristol Main Room"),
                                    ("Cardiff Main Room");

INSERT INTO desk(room_id, desk_name) VALUES ((SELECT room_id FROM room WHERE room_name = "Bristol Main Room"), "Main Desk"),
                                             ((SELECT room_id FROM room WHERE room_name = "Cardiff Main Room"), "Standing Desk");

