DROP TABLE IF EXISTS booking;

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

INSERT INTO booking (username, booking_date, room_id, desk_id, book_timestamp)
VALUES ('user1', '2021-12-01', 1, 1, '2021-11-22 12:15:00'),
       ('user1', '2021-12-02', 1, 2, '2021-11-22 12:21:00'),
       ('user1', '2021-12-03', 1, 3, '2021-11-22 12:23:00'),
       ('user2', '2021-12-02', 1, 4, '2021-11-22 16:03:00'),
       ('user2', '2021-12-03', 1, 5, '2021-11-22 16:03:29'),
       ('user2', '2021-12-06', 1, 1, '2021-11-22 16:03:46'),
       ('user3', '2021-12-06', 1, 2, '2021-12-04 19:14:32'),
       ('user4', '2021-12-06', 1, 3, '2021-12-04 21:49:54'),
       ('user5', '2021-12-06', 1, 4, '2021-12-05 08:04:32'),
       ('user6', '2021-12-06', 1, 5, '2021-12-05 09:12:01'),
       ('user7', '2021-12-06', 1, 6, '2021-12-05 09:30:55'),
       ('user8', '2021-12-06', 1, 7, '2021-12-05 11:41:07');
