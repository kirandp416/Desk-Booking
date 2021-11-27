SELECT booking_id, booking_date, room_name, desk_name, desk_type_name, notes
FROM booking bookings
LEFT OUTER JOIN room rooms
ON bookings.room_id = rooms.room_id
LEFT OUTER JOIN (SELECT desk_id, room_id, desk.desk_type_id, desk_name, notes, desk_type_name FROM desk INNER JOIN desk_type ON desk.desk_type_id = desk_type.desk_type_id) desks_with_type
ON bookings.desk_id = desks_with_type.desk_id
WHERE username = 'user2'
 