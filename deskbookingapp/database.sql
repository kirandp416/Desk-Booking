DROP DATABASE IF EXISTS deskbooking;

CREATE DATABASE deskbooking;

USE deskbooking;

CREATE TABLE room
(
    room_id   INT PRIMARY KEY AUTO_INCREMENT,
    room_name VARCHAR(255) NOT NULL
);

CREATE TABLE desk_type
(
    desk_type_id   INT AUTO_INCREMENT,
    desk_type_name VARCHAR(255) NOT NULL,
    CONSTRAINT desk_type_pk_index PRIMARY KEY (desk_type_id)
);

CREATE TABLE desk
(
    desk_id      INT AUTO_INCREMENT,
    room_id      INT,
    desk_type_id INT,
    desk_name    VARCHAR(255) NOT NULL,
    notes        VARCHAR(255),
    CONSTRAINT desk_pk_index PRIMARY KEY (desk_id),
    CONSTRAINT desk_room_fk_index FOREIGN KEY (room_id) REFERENCES room (room_id) ON DELETE CASCADE,
    CONSTRAINT desk_desk_type_id FOREIGN KEY (desk_type_id) REFERENCES desk_type (desk_type_id)
);

CREATE TABLE booking
(
    booking_id   INT AUTO_INCREMENT,
    username     VARCHAR(255) NOT NULL,
    booking_date DATE,
    room_id      INT,
    desk_id      INT,
    CONSTRAINT booking_pk_index PRIMARY KEY (booking_id),
    CONSTRAINT booking_room_fk_index FOREIGN KEY (room_id) REFERENCES room (room_id) ON DELETE CASCADE,
    CONSTRAINT booking_desk_fk_index FOREIGN KEY (desk_id) REFERENCES desk (desk_id) ON DELETE CASCADE
);

INSERT INTO desk_type (desk_type_id, desk_type_name)
VALUES (1, 'Standard'),
       (2, 'Standing');

INSERT INTO room(room_id, room_name)
VALUES (1, 'Room 1'), (2, 'Room 2');

INSERT INTO desk(desk_id, room_id, desk_type_id, desk_name)
VALUES (1, 1, 1, 'Desk 1'),
       (2, 1, 1, 'Desk 2'),
       (3, 1, 1, 'Desk 3'),
       (4, 2, 1, 'Desk 1'),
       (5, 2, 1, 'Desk 2'),
       (6, 2, 1, 'Desk 2');

INSERT INTO booking (username, booking_date, room_id, desk_id)
VALUES ('user1', '2021-11-23', 1, 1),
       ('user1', '2021-11-23', 1, 2),
       ('user1', '2021-11-23', 1, 3),
       ('user2', '2021-11-23', 2, 1),
       ('user2', '2021-11-23', 2, 2),
       ('user2', '2021-11-23', 2, 3);
