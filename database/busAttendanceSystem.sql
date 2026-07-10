
create database Bus_Attendance_System;
use Bus_Attendance_System;
-- drop database Bus_Attendance_System;

CREATE TABLE STUDENT (
    StudentID INT NOT NULL,
    S_Fname VARCHAR(20) NOT NULL,
    S_Lname VARCHAR(20) NOT NULL,
    S_Phone VARCHAR(15)   NOT NULL UNIQUE,
    PRIMARY KEY (StudentID)
);

CREATE TABLE STUDENT_DAYS (
    StudentID INT NOT NULL,
    DayOfWeek VARCHAR(20) NOT NULL,
    PRIMARY KEY (StudentID, DayOfWeek),
    FOREIGN KEY (StudentID) REFERENCES STUDENT(StudentID)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE DRIVER (
    DriverID INT NOT NULL ,
    D_Fname VARCHAR(20) NOT NULL,
    D_Lname VARCHAR(20) NOT NULL,
    PRIMARY KEY (DriverID)
);

CREATE TABLE DRIVER_PHONE (
    DriverID INT NOT NULL,
    Phone VARCHAR(15) NOT NULL,
    PRIMARY KEY (DriverID, Phone),
    FOREIGN KEY (DriverID) REFERENCES DRIVER(DriverID)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);


CREATE TABLE BUS (
    BusID INT NOT NULL AUTO_INCREMENT,
    Capacity INT NOT NULL CHECK (Capacity > 0),
    AvailabilityTime VARCHAR(20)  NOT NULL,
    DriverID INT NOT NULL,
    PRIMARY KEY (BusID),
    FOREIGN KEY (DriverID) REFERENCES DRIVER(DriverID)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);

CREATE TABLE SCHEDULE (
    ScheduleID INT NOT NULL AUTO_INCREMENT,
    DayOfWeek VARCHAR(20) NOT NULL,
    Time VARCHAR(20) NOT NULL, 
    BusID INT NOT NULL,
    PRIMARY KEY (ScheduleID),
	FOREIGN KEY (BusID) REFERENCES BUS(BusID)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);

CREATE TABLE ATTENDS (
    StudentID INT NOT NULL,
    ScheduleID INT NOT NULL,
    Status VARCHAR(20) NOT NULL DEFAULT 'Present'
    CHECK (Status IN ('Present', 'Absent')),
    PRIMARY KEY (StudentID,ScheduleID),
    FOREIGN KEY (StudentID) REFERENCES STUDENT(StudentID)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
	FOREIGN KEY (ScheduleID) REFERENCES SCHEDULE(ScheduleID)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE ALERT (
    AlertID INT NOT NULL AUTO_INCREMENT,
    Message TEXT NOT NULL,
	AlertType  VARCHAR(20) NOT NULL,
    BusID INT NOT NULL,
    PRIMARY KEY (AlertID),
    FOREIGN KEY (BusID) REFERENCES BUS(BusID)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);

CREATE TABLE SIGNS_UP_FOR (
    StudentID INT,
    ScheduleID INT,
    PRIMARY KEY (StudentID, ScheduleID),
    FOREIGN KEY (StudentID) REFERENCES STUDENT(StudentID),
    FOREIGN KEY (ScheduleID) REFERENCES SCHEDULE(ScheduleID));
    
DROP TABLE SENT_TO;

CREATE TABLE SENT_TO (
    StudentID INT,
    AlertID INT,
    PRIMARY KEY (StudentID, AlertID),
    FOREIGN KEY (StudentID) REFERENCES STUDENT(StudentID),
    FOREIGN KEY (AlertID) REFERENCES ALERT(AlertID)
);


INSERT INTO DRIVER (DriverID, D_Fname, D_Lname) VALUES
(1,  'Ahmed',  'Al-Harbi'),
(2,  'Khalid', 'Al-Dosari'),
(3,  'Omar',   'Al-Rashidi'),
(4,  'Saad',   'Al-Mutairi'),
(5,  'Faisal', 'Al-Ghamdi'),
(6,  'Nasser', 'Al-Zahrani'),
(7,  'Tariq',  'Al-Otaibi'),
(8,  'Saleh',  'Al-Shehri'),
(9,  'Hamad',  'Al-Anazi'),
(10, 'Yousef', 'Al-Qahtani');

INSERT INTO DRIVER_PHONE (DriverID, Phone) VALUES
(1,  '0501112233'),
(2,  '0502223344'),
(3,  '0503334455'),
(4,  '0504445566'),
(5,  '0505556677'),
(6,  '0506667788'),
(7,  '0507778899'),
(8,  '0508889900'),
(9,  '0509990011'),
(10, '0501234321');
 

INSERT INTO BUS (Capacity, AvailabilityTime, DriverID) VALUES
(30, 'Morning',1),
(40, 'Evening',2),
(35, 'Morning',3),
(45, 'Afternoon',4),
(30, 'Morning',5),
(50, 'Evening',6),
(40, 'Morning',7),
(35, 'Afternoon',8),
(30, 'Evening',9),
(45, 'Morning',10);
 

INSERT INTO SCHEDULE (DayOfWeek, Time, BusID) VALUES
('Sunday',    '07:30 AM',  1),
('Monday',    '08:00 AM',  2),
('Tuesday',   '07:30 AM',  3),
('Wednesday', '08:30 AM',  4),
('Thursday',  '07:00 AM',  5),
('Sunday',    '02:00 PM',  6),
('Monday',    '01:30 PM',  7),
('Tuesday',   '02:30 PM',  8),
('Wednesday', '01:00 PM',  9),
('Thursday',  '02:00 PM', 10);
 


INSERT INTO STUDENT (StudentID, S_Fname, S_Lname, S_Phone) VALUES
(001,'Lama','Alnajmah','0551234567'),
(002,'Ghala','Alali','0552345678'),
(003,'Wadha','Alnaimi','0553456789'),
(004,'Farah','Alghamdi','0554567890'),
(005,'Sara','Alsalem','0555678901'),
(006,'Reem','Alhussain','0556789012'),
(007,'Hessa','Aldossari','0557890123'),
(008,'Nora','Almutairi','0558901234'),
(009,'Dana','Alrashidi','0559012345'),
(010,'Mona','Alotaibi','0550123456');

 INSERT INTO STUDENT_DAYS (StudentID, DayOfWeek) VALUES
(001,'Sunday'),
(002,'Monday'),
(003,'Tuesday'),
(004,'Wednesday'),
(005,'Thursday'),
(006,'Sunday'),
(007,'Tuesday'),
(008,'Thursday'),
(009,'Monday'),
(010,'Wednesday');

INSERT INTO ATTENDS (StudentID, ScheduleID, Status) VALUES
(001, 1,  'Present'),
(002, 2,  'Present'),
(003, 3,  'Absent'),
(004, 4,  'Present'),
(005, 5,  'Present'),
(006, 6,  'Absent'),
(007, 7,  'Present'),
(008, 8,  'Present'),
(009, 9,  'Absent'),
(010, 10, 'Present');
 

INSERT INTO ALERT (Message, AlertType, BusID) VALUES
('Bus 1 is delayed due to heavy traffic on King Fahd Road','Delay',1),
('Bus 2 route changed due to road construction in Jubail','RouteChange',2),
('Bus 3 will depart 10 minutes earlier than scheduled','Delay',3),
('Bus 4 trip cancelled due to engine maintenance','Cancellation',4),
('Bus 5 is now back on its regular schedule','RouteChange',5),
('Bus 6 delayed due to heavy fog','Delay',6),
('Bus 7 pick-up point changed to main university gate','RouteChange',7),
('Bus 8 will arrive 20 minutes late due to traffic','Delay',8),
('Bus 9 service restored after emergency breakdown','Breakdown',9),
('Bus 10 departure time updated to 02:15 PM today','Delay',10);

-- DELETE Query
DELETE FROM STUDENT WHERE StudentID='001';

-- Update Query
UPDATE ATTENDS
SET Status ='Absent'
WHERE StudentID ='002';

-- select Query
SELECT*FROM DRIVER;
SELECT*FROM BUS;
SELECT*FROM SCHEDULE;
SELECT*FROM ATTENDS;

SELECT*FROM STUDENT;

SELECT*FROM STUDENT_DAYS;
SELECT*FROM ALERT;
SELECT * FROM DRIVER;

-- join Query
SELECT STUDENT.S_Fname, SCHEDULE.DayOfWeek, SCHEDULE.Time
FROM STUDENT
JOIN signs_up_for
ON STUDENT.StudentID = signs_up_for.StudentID
JOIN SCHEDULE
ON signs_up_for.ScheduleID = SCHEDULE.ScheduleID;


-- like Query
SELECT * FROM STUDENT
WHERE S_Fname LIKE '%a';

-- order by Query
SELECT * FROM DRIVER
ORDER BY D_Fname ASC, D_Lname ASC;

-- between Query
SELECT *
FROM BUS
WHERE Capacity BETWEEN 20 AND 40;

-- in Query
SELECT *
FROM SCHEDULE
WHERE DayOfWeek IN ('Sunday', 'Monday');
-- is null Query
SELECT *
FROM ALERT
WHERE Message IS NULL;

-- Complex SQL Queries 
-- UNION:  Students who attend on Sunday OR Monday schedules

SELECT StudentID FROM ATTENDS 
WHERE ScheduleID IN (SELECT ScheduleID FROM SCHEDULE WHERE DayOfWeek = 'Sunday')
UNION
SELECT StudentID FROM ATTENDS 
WHERE ScheduleID IN (SELECT ScheduleID FROM SCHEDULE WHERE DayOfWeek = 'Monday');

-- INTERSECT: Students who are Present AND registered on Sunday
SELECT StudentID FROM ATTENDS WHERE Status = 'Present' 
INTERSECT SELECT 
StudentID FROM STUDENT_DAYS WHERE DayOfWeek = 'Sunday';

-- EXCEPT: Students registered on Sunday but marked Absent
SELECT StudentID FROM STUDENT_DAYS WHERE DayOfWeek = 'Sunday'
EXCEPT
SELECT StudentID FROM ATTENDS WHERE Status = 'Present';

-- Count of Present/Absent students per schedule
SELECT ScheduleID, Status, COUNT(StudentID) AS TotalStudents 
FROM ATTENDS 
GROUP BY ScheduleID, Status 
HAVING COUNT(StudentID) >= 1;

-- Average bus capacity per availability time 
SELECT AvailabilityTime,
 AVG(Capacity) AS AvgCapacity,
 MAX(Capacity) AS MaxCapacity,
 MIN(Capacity) AS MinCapacity 
FROM BUS 
GROUP BY AvailabilityTime
HAVING AVG(Capacity) > 0;

-- Natural Join between BUS and DRIVER 
SELECT BusID, Capacity, AvailabilityTime, D_Fname, D_Lname
FROM BUS NATURAL JOIN DRIVER;

-- LEFT OUTER JOIN: All drivers with their buses
SELECT D.DriverID, D.D_Fname, D.D_Lname, B.BusID, B.Capacity
FROM DRIVER D
LEFT OUTER JOIN BUS B ON D.DriverID = B.DriverID;

-- RIGHT OUTER JOIN: All buses with their schedules
SELECT B.BusID, B.Capacity, S.ScheduleID, S.DayOfWeek
FROM SCHEDULE S
RIGHT OUTER JOIN BUS B ON S.BusID = B.BusID;

-- Students who have at least one attendance record 
SELECT ST.StudentID, ST.S_Fname, ST.S_Lname 
FROM STUDENT ST 
WHERE EXISTS ( SELECT 1 FROM ATTENDS A
 WHERE A.StudentID = ST.StudentID );

-- Students who ride buses with capacity greater than average capacity
SELECT ST.StudentID, ST.S_Fname, ST.S_Lname
FROM STUDENT ST
WHERE ST.StudentID IN (
    SELECT A.StudentID 
    FROM ATTENDS A
    JOIN SCHEDULE S ON A.ScheduleID = S.ScheduleID
    WHERE S.BusID IN (
        SELECT BusID FROM BUS
        WHERE Capacity > (SELECT AVG(Capacity) FROM BUS)
    )
);

-- FUNCTION
DELIMITER $$
CREATE FUNCTION GetAbsenceCount(p_StudentID INT)
RETURNS INT
DETERMINISTIC
BEGIN
    DECLARE absCount INT;
    SELECT COUNT(*) INTO absCount
    FROM ATTENDS
    WHERE StudentID = p_StudentID AND Status = 'Absent';
    RETURN absCount;
END$$
DELIMITER ;

-- Usage: Student 3 has 1 absence → returns 1
SELECT S_Fname, S_Lname, GetAbsenceCount(3) AS AbsenceCount
FROM STUDENT
WHERE StudentID = 3;

-- STORED PROCEDURE:
DELIMITER $$
CREATE PROCEDURE GetScheduleByDay(IN p_Day VARCHAR(20))
 BEGIN
 SELECT
 S.ScheduleID,
 S.DayOfWeek, 
S.Time, 
B.BusID,
 B.Capacity,
 B.AvailabilityTime, 
D.D_Fname, 
D.D_Lname F 
FROM SCHEDULE S 
JOIN BUS B ON S.BusID = B.BusID 
JOIN DRIVER D ON B.DriverID = D.DriverID 
WHERE S.DayOfWeek = p_Day; 
END$$
DELIMITER ;
-- Usage: returns 2 rows for Sunday 
CALL GetScheduleByDay('Sunday');

-- TRIGGER:
DELIMITER $$
CREATE TRIGGER Log_Absent_Student
AFTER INSERT ON ATTENDS
FOR EACH ROW
BEGIN
    IF NEW.Status = 'Absent' THEN
        INSERT IGNORE INTO SENT_TO (StudentID, AlertID)
        SELECT NEW.StudentID, AL.AlertID
        FROM ALERT AL
        JOIN SCHEDULE SC ON SC.BusID = AL.BusID
        WHERE SC.ScheduleID = NEW.ScheduleID;
    END IF;
END$$
DELIMITER ;
 
INSERT INTO ATTENDS (StudentID, ScheduleID, Status)
VALUES (2, 1, 'Absent');

SELECT ST.S_Fname,ST.S_Lname,AL.Message,AL.AlertType
FROM SENT_TO S
JOIN STUDENT ST ON S.StudentID = ST.StudentID
JOIN ALERT AL ON S.AlertID = AL.AlertID;

CREATE VIEW ScheduleDetails AS
SELECT 
    S.ScheduleID,
    S.DayOfWeek,
    S.Time,
    B.BusID,
    B.Capacity,
    B.AvailabilityTime,
    D.D_Fname AS DriverFirstName,
    D.D_Lname AS DriverLastName
FROM SCHEDULE S
JOIN BUS B ON S.BusID = B.BusID
JOIN DRIVER D ON B.DriverID = D.DriverID;
 
SELECT * FROM ScheduleDetails;


