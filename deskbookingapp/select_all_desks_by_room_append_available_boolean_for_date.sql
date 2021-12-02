-- Values added for:
-- room_id = 1
-- booking_date = '2021-12-02'
-- Limit = 1
-- Offset = 1
SELECT desks.desk_id, desks.room_id, desks.desk_type_id, desk_type.desk_type_name, desks.desk_name, desks.notes,
                CASE WHEN booking_date IS NOT NULL THEN FALSE ELSE TRUE END AS available
                FROM (SELECT * FROM desk WHERE room_id = ? LIMIT ? OFFSET ?) as desks
                LEFT JOIN (SELECT * FROM booking WHERE booking_date = ?) as bookings ON desks.desk_id = bookings.desk_id AND desks.room_id = bookings.room_id
                LEFT JOIN desk_type ON desks.desk_type_id = desk_type.desk_type_id
                ORDER BY desks.desk_id;


