DROP
DATABASE IF EXISTS deskbooking;

CREATE
DATABASE deskbooking;

USE
deskbooking;

CREATE TABLE room
(
    room_id      INT PRIMARY KEY AUTO_INCREMENT,
    room_name    VARCHAR(255) NOT NULL,
    room_img_url VARCHAR(255)
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

CREATE TABLE `comment`
(
    `id`           bigint                                                        NOT NULL,
    `room_id`      int                                                           NOT NULL,
    `desk_id`      int NULL DEFAULT NULL,
    `comment`      varchar(255),
    `user_name`    varchar(255),
    `img`          varchar(255),
    `down_url`     varchar(255),
    `created_time` datetime NULL DEFAULT NULL,
    `updated_time` datetime NULL DEFAULT NULL,
    `deleted`      varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT 'N',
    INDEX          `username`(`user_name`) USING BTREE
);

CREATE TABLE booking
(
    booking_id     INT AUTO_INCREMENT,
    username       VARCHAR(255) NOT NULL,
    booking_date   DATE,
    room_id        INT,
    desk_id        INT,
    book_timestamp DATETIME     NOT NULL,
    CONSTRAINT booking_pk_index PRIMARY KEY (booking_id),
    CONSTRAINT booking_room_fk_index FOREIGN KEY (room_id) REFERENCES room (room_id) ON DELETE CASCADE,
    CONSTRAINT booking_desk_fk_index FOREIGN KEY (desk_id) REFERENCES desk (desk_id) ON DELETE CASCADE,
    CONSTRAINT booking_user_fk_index FOREIGN KEY (username) REFERENCES employee (username) ON DELETE CASCADE
);



INSERT INTO desk_type (desk_type_id, desk_type_name)
VALUES (1, 'Standard'),
       (2, 'Standing');

INSERT INTO room(room_id, room_name)
VALUES (1, 'Room 1');

INSERT INTO desk(desk_id, room_id, desk_type_id, desk_name)
VALUES (1, 1, 1, 'Desk 1'),
       (2, 1, 1, 'Desk 2'),
       (3, 1, 2, 'Desk 3'),
       (4, 1, 1, 'Desk 4'),
       (5, 1, 1, 'Desk 5'),
       (6, 1, 2, 'Desk 6'),
       (7, 1, 1, 'Desk 7'),
       (8, 1, 1, 'Desk 8'),
       (9, 1, 2, 'Desk 9'),
       (10, 1, 1, 'Desk 10'),
       (11, 1, 1, 'Desk 11');

INSERT INTO employee (username) VALUES ('user1'), ('user2');

INSERT INTO booking (username, booking_date, room_id, desk_id, book_timestamp)
VALUES ('user1', '2021-12-09', 1, 1, '2021-11-22 12:15:00'),
       ('user1', '2021-12-09', 1, 2, '2021-11-22 12:21:00'),
       ('user1', '2021-12-09', 1, 3, '2021-11-22 12:23:00'),
       ('user2', '2021-12-09', 1, 4, '2021-11-22 16:03:00'),
       ('user2', '2021-12-09', 1, 5, '2021-11-22 16:03:29');



