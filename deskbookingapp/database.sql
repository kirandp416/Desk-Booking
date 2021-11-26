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
    CONSTRAINT desk_room_fk_index FOREIGN KEY (room_id) REFERENCES room (room_id) ON DELETE CASCADE
);

CREATE TABLE booking
(
    booking_id   INT AUTO_INCREMENT,
    username     VARCHAR(255) NOT NULL,
    booking_date DATE,
    room_id INT,
    desk_id INT,
    CONSTRAINT booking_pk_index PRIMARY KEY (booking_id),
    CONSTRAINT booking_room_fk_index FOREIGN KEY (room_id) REFERENCES room (room_id) ON DELETE CASCADE ,
    CONSTRAINT booking_desk_fk_index FOREIGN KEY (desk_id) REFERENCES desk (desk_id) ON DELETE CASCASE
);


INSERT INTO room(room_name) VALUES ("Bristol Main Room");

INSERT INTO desk(room_id, desk_name) VALUES ((SELECT room_id FROM room WHERE room_name = "Bristol Main Room"), "Desk 1"),
                                            ((SELECT room_id FROM room WHERE room_name = "Bristol Main Room"), "Desk 2"),
                                            ((SELECT room_id FROM room WHERE room_name = "Bristol Main Room"), "Desk 3");

INSERT INTO booking (username, booking_date, room_id, desk_id) VALUES ("user1", '2021-11-23', (SELECT room_id FROM room WHERE room_name = "Bristol Main Room"),
                                                                       (SELECT desk_id FROM desk WHERE desk_name = "Desk 1")
                                                                       ),
                                                                      ("user1", '2021-11-23', (SELECT room_id FROM room WHERE room_name = "Bristol Main Room"),
                                                                       (SELECT desk_id FROM desk WHERE desk_name = "Desk 2")
                                                                      ),
                                                                      ("user2", '2021-11-23', (SELECT room_id FROM room WHERE room_name = "Bristol Main Room"),
                                                                       (SELECT desk_id FROM desk WHERE desk_name = "Desk 3")
                                                                      );
