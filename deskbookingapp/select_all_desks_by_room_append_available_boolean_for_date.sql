SELECT desks.desk_id, desks.room_id, desks.desk_name, bookings.booking_date, CASE WHEN booking_date IS NOT NULL THEN FALSE ELSE TRUE END AS available
FROM
(SELECT * FROM desk WHERE room_id = 1 LIMIT 1 OFFSET 1) as desks
LEFT JOIN
(SELECT * FROM booking WHERE booking_date = '2021-11-23') as bookings
ON desks.desk_id = bookings.desk_id AND desks.room_id = bookings.room_id


