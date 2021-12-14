-- Query for:
-- room_id = 1
-- date = '2021-12-01'
-- limit = 10
-- offset = 0

-- SELECT desk_with_type.desk_id, desk_with_type.room_id, desk_with_type.desk_name, desk_with_type.desk_type_name, desk_with_type.notes,
-- CASE WHEN booking_id IS NULL THEN 1 ELSE 0 END AS available,
-- booking.username as booked_by, booking.booking_id
-- FROM (SELECT desk.desk_id, desk.room_id, desk.desk_name, desk.notes, desk_type.desk_type_name FROM desk INNER JOIN desk_type ON desk.desk_type_id = desk_type.desk_type_id WHERE desk.room_id = 1 LIMIT 10 OFFSET 0) AS desk_with_type
-- LEFT JOIN (SELECT * FROM booking WHERE booking_date = '2021-12-01') as booking
-- ON desk_with_type.desk_id = booking.desk_id

-- Generic query

-- SELECT desk_with_type.desk_id, desk_with_type.room_id, desk_with_type.desk_name, desk_with_type.desk_type_name, desk_with_type.notes,
-- CASE WHEN booking_id IS NULL THEN 1 ELSE 0 END AS available,
-- booking.username as booked_by, booking.booking_id
-- FROM (SELECT desk.desk_id, desk.room_id, desk.desk_name, desk.notes, desk_type.desk_type_name FROM desk INNER JOIN desk_type ON desk.desk_type_id = desk_type.desk_type_id WHERE desk.room_id = ? LIMIT ? OFFSET ?) AS desk_with_type
-- LEFT JOIN (SELECT * FROM booking WHERE booking_date = ?) as booking
-- ON desk_with_type.desk_id = booking.desk_id

-- Generic query as one line

SELECT desk_with_type.desk_id, desk_with_type.room_id, desk_with_type.desk_name, desk_with_type.desk_type_name, desk_with_type.notes, CASE WHEN booking_id IS NULL THEN 1 ELSE 0 END AS available, booking.username as booked_by, booking.booking_id FROM (SELECT desk.desk_id, desk.room_id, desk.desk_name, desk.notes, desk_type.desk_type_name FROM desk INNER JOIN desk_type ON desk.desk_type_id = desk_type.desk_type_id WHERE desk.room_id = ? LIMIT ? OFFSET ?) AS desk_with_type LEFT JOIN (SELECT * FROM booking WHERE booking_date = ?) as booking ON desk_with_type.desk_id = booking.desk_id
