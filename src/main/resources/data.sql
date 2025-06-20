-- Students
INSERT INTO student (student_id, student_name, email, address, phone_number, semester, password)
VALUES
    ('S001', 'Ali Rahman', 'ali@example.com', '123 Jalan Mawar', '0123456789', '1', 'pass123'),
    ('S002', 'Aisyah Noor', 'aisyah@example.com', '456 Jalan Melati', '0131112233', '2', 'pass456');

-- Teachers
INSERT INTO teacher (teacher_id, teacher_name, email, room_number, phone_number, password)
VALUES('T001', 'Mr. Lim', 'lim@school.edu', 'B-204', '0199990001', 'teach123'),
      ('T002', 'Mr. Tan', 'tan@school.edu', 'A-309', '0188880002', 'teach456');

-- Admins
INSERT INTO admin (admin_id, name, password)
VALUES
    ('A001', 'admin1', 'admin123'),
    ('A002', 'admin2', 'admin456');

-- Sports
INSERT INTO sport (sport_id, sport_name)
VALUES
    ('SP01', 'Badminton'),
    ('SP02', 'Basketball');

-- Sport Sessions
INSERT INTO sport_session (session_id, venue, session_group, session_time, quota, sport_sport_id, teacher_teacher_id)
VALUES('SS001', 'Hall A', 'G1', '8AM - 11AM', 2, 'SP01', 'T001'),
      ('SS002', 'Court A', 'G1', '11AM - 1PM', 20, 'SP02', 'T002');

-- Registrations
INSERT INTO registration (student_id, session_id, semester, registration_date)
VALUES
    ('S001', 'SS001', '1', '2025-05-30'),
    ('S002', 'SS001', '2', '2025-05-30');
