-- SQL statement for testing

-- -- Dummy values in test statement:
-- -- room_id = 1
-- -- booking_date = '2021-12-02'
-- -- Limit = 10
-- -- Offset = 0
-- -- user = 'user1'
-- -- desk_id = 1

SELECT desks.desk_id as current_desk_id, desks.room_id, desks.desk_type_id, desk_type.desk_type_name, desks.desk_name, desks.notes,
       CASE WHEN booking_date IS NOT NULL THEN FALSE ELSE TRUE END AS available,
       CASE WHEN (SELECT COUNT(*) FROM booking where username = 'user1' and booking_date = '2021-12-02') > 0 THEN 1 ELSE 0 END AS does_user_have_booking_on_that_day,
       CASE WHEN (SELECT COUNT(*) FROM booking where username = 'user1'and booking_date = '2021-12-02' and desk_id = current_desk_id) > 0 THEN 1 ELSE 0 END AS does_user_have_that_desk_booked_on_that_day
FROM (SELECT * FROM desk WHERE room_id = 1 LIMIT 10 OFFSET 0) as desks
         LEFT JOIN (SELECT * FROM booking WHERE booking_date = '2021-12-02') as bookings ON desks.desk_id = bookings.desk_id AND desks.room_id = bookings.room_id
         LEFT JOIN desk_type ON desks.desk_type_id = desk_type.desk_type_id
ORDER BY desks.desk_id;

-- SQL Statement to be used (has ?)

SELECT desks.desk_id as current_desk_id, desks.room_id, desks.desk_type_id, desk_type.desk_type_name, desks.desk_name, desks.notes,
       CASE WHEN booking_date IS NOT NULL THEN FALSE ELSE TRUE END AS available,
       CASE WHEN (SELECT COUNT(*) FROM booking where username = ? and booking_date = ?) > 0 THEN 1 ELSE 0 END AS does_user_have_booking_on_that_day,
       CASE WHEN (SELECT COUNT(*) FROM booking where username = ? and booking_date = ? and desk_id = current_desk_id) > 0 THEN 1 ELSE 0 END AS does_user_have_that_desk_booked_on_that_day
FROM (SELECT * FROM desk WHERE room_id = ? LIMIT ? OFFSET ?) as desks
         LEFT JOIN (SELECT * FROM booking WHERE booking_date = ?) as bookings ON desks.desk_id = bookings.desk_id AND desks.room_id = bookings.room_id
         LEFT JOIN desk_type ON desks.desk_type_id = desk_type.desk_type_id
ORDER BY desks.desk_id;

-- Written as one line

SELECT desks.desk_id as current_desk_id, desks.room_id, desks.desk_type_id, desk_type.desk_type_name, desks.desk_name, desks.notes, CASE WHEN booking_date IS NOT NULL THEN FALSE ELSE TRUE END AS available, CASE WHEN (SELECT COUNT(*) FROM booking where username = ? and booking_date = ?) > 0 THEN 1 ELSE 0 END AS does_user_have_booking_on_that_day, CASE WHEN (SELECT COUNT(*) FROM booking where username = ? and booking_date = ? and desk_id = current_desk_id) > 0 THEN 1 ELSE 0 END AS does_user_have_that_desk_booked_on_that_day FROM (SELECT * FROM desk WHERE room_id = ? LIMIT ? OFFSET ?) as desks LEFT JOIN (SELECT * FROM booking WHERE booking_date = ?) as bookings ON desks.desk_id = bookings.desk_id AND desks.room_id = bookings.room_id LEFT JOIN desk_type ON desks.desk_type_id = desk_type.desk_type_id ORDER BY desks.desk_id;




