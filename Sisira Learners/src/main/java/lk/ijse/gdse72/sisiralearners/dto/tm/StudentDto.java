package lk.ijse.gdse72.sisiralearners.dto.tm;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private String student_id;         // Unique ID for the student
    private String name;              // Student's name
    private String email;             // Student's email
    private String payment_status;     // Payment status (Paid, Unpaid, etc.)
    private String contact;           // Student's contact number
    private Date start_day;            // Start day for the course
    private String registration_id;    // Foreign key referencing the Registration table
}
