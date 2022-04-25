-- USE db;
DROP TABLE IF EXISTS room;

CREATE TABLE room
(
    room_id   INT AUTO_INCREMENT,
    room_name VARCHAR(255) NOT NULL,
    CONSTRAINT room_pk_index PRIMARY KEY (room_id)
);

INSERT INTO room(room_id, room_name) VALUES (1, 'Room 1');
