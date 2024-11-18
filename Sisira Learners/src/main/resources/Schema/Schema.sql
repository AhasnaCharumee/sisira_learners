create Database sisiralearners;
use sisiralearners;

CREATE TABLE User (
                      user_id VARCHAR(50) PRIMARY KEY,
                      user_name VARCHAR(100) NOT NULL,
                      email VARCHAR(100) NOT NULL UNIQUE,
                      role VARCHAR(10) NOT NULL,
                      password VARCHAR(255) NOT NULL
);

CREATE TABLE Vehicle (
                         vehicle_id VARCHAR(20) PRIMARY KEY,
                         vehicle_type VARCHAR(50),
                         vehicle_class VARCHAR(20),
                         maintenance_records VARCHAR(255),
                         license_plate VARCHAR(20)
);



CREATE TABLE Course (
                        course_id VARCHAR(50) PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        duration VARCHAR(50),
                        price DECIMAL(10, 2) NOT NULL,
                        status VARCHAR(20) NOT NULL,
                        max_students INT,
                        description VARCHAR(255)
);




CREATE TABLE schedule (
                          schedule_id VARCHAR(255) PRIMARY KEY,
                          date DATE NOT NULL,
                          schedule_name VARCHAR(255) NOT NULL,
                          start_time VARCHAR NOT NULL,
                          end_time VARCHAR NOT NULL,
                          student_limit INT NOT NULL
);






CREATE TABLE Student (
                         student_id VARCHAR(20) PRIMARY KEY,      -- Unique ID for the student
                         name VARCHAR(50) NOT NULL,               -- Student's name
                         email VARCHAR(50),                       -- Student's email
                         payment_status VARCHAR(20),              -- Payment status (Paid, Unpaid, etc.)
                         contact VARCHAR(15),                     -- Student's contact number
                         start_day DATE,                          -- Start day for the course
                         registration_id VARCHAR(20),             -- Foreign key referencing the Registration table
                         FOREIGN KEY (registration_id) REFERENCES Registration(registration_id) -- Link to the Registration table
);


CREATE TABLE Payment (
                         payment_id VARCHAR(20) PRIMARY KEY,        -- Unique ID for the payment
                         payment_date DATE,                          -- The date of the payment
                         payment_method VARCHAR(20),                 -- Payment method (e.g., Cash, Credit, etc.)
                         paid DECIMAL(10, 2) DEFAULT 0.00,           -- Amount paid
                         price DECIMAL(10, 2) NOT NULL,              -- Total price for the service/course
                         balance DECIMAL(10, 2) GENERATED ALWAYS AS (price - paid) STORED, -- Remaining balance
                         payment_status VARCHAR(20),                 -- Payment status (e.g., Paid, Unpaid, Partial)
                         registration_id VARCHAR(20),                -- Foreign key referencing the Registration table
                         FOREIGN KEY (registration_id) REFERENCES Registration(registration_id) -- Link to the Registration table
);


CREATE TABLE ScheduleAssignment (
                                    assignment_id VARCHAR(255) PRIMARY KEY,  -- Changed to VARCHAR
                                    schedule_id VARCHAR(255),    -- The schedule for the driving class
                                    instructor_id VARCHAR(255),  -- The instructor for this schedule
                                    vehicle_id VARCHAR(255),     -- The vehicle assigned to this schedule
                                    student_id VARCHAR(255),     -- The student assigned to this schedule
                                    FOREIGN KEY (schedule_id) REFERENCES Schedule(schedule_id),
                                    FOREIGN KEY (instructor_id) REFERENCES Instructor(instructor_id),
                                    FOREIGN KEY (vehicle_id) REFERENCES Vehicle(vehicle_id),
                                    FOREIGN KEY (student_id) REFERENCES Student(student_id)
);

CREATE TABLE Enrollment (
                            enrollment_id VARCHAR(20) PRIMARY KEY,    -- Unique Enrollment ID
                            student_id VARCHAR(20),                    -- Foreign Key to Student
                            course_id VARCHAR(20),                     -- Foreign Key to Course
                            enrollment_date DATE,                      -- Date of Enrollment
                            status VARCHAR(20),                        -- Status of Enrollment (Active, Completed, Dropped)
                            payment_status VARCHAR(20),                -- Payment Status (Paid, Pending, Unpaid)
                            start_date DATE,                           -- Course start date
                            end_date DATE,                             -- Course end date (Optional)
                            FOREIGN KEY (student_id) REFERENCES Student(student_id), -- Linking to Student
                            FOREIGN KEY (course_id) REFERENCES Course(course_id)  -- Linking to Course
);




CREATE TABLE Registration (
                              registration_id VARCHAR(20) PRIMARY KEY,
                              name VARCHAR(50) NOT NULL,
                              gender VARCHAR(10),
                              address VARCHAR(100),
                              date_of_birth DATE,
                              nic VARCHAR(12) UNIQUE NOT NULL,
                              email VARCHAR(50),
                              course_name VARCHAR(50),
                              paid DECIMAL(10, 2) DEFAULT 0.00,  -- Amount paid by the student
                              price DECIMAL(10, 2) NOT NULL,     -- Total course price
                              balance DECIMAL(10, 2) GENERATED ALWAYS AS (price - paid) STORED, -- Auto-calculates remaining balance
                              registration_date DATE,  -- Removed the default CURRENT_DATE to handle it manually
                              payment_status VARCHAR(20),  -- Removed the CHECK constraint for compatibility
                              contact VARCHAR(15)  -- Added contact column for phone numbers
);




CREATE TABLE instructor (
                            instructor_id VARCHAR(255) PRIMARY KEY,
                            name VARCHAR(255),
                            status VARCHAR(255),
                            address VARCHAR(255),
                            telephone VARCHAR(255),
                            vehicle_class VARCHAR(255),
                            salary VARCHAR(255),
                            email VARCHAR(255)
);
