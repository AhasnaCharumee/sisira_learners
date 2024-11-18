package lk.ijse.gdse72.sisiralearners.dto.tm;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentDto {
    private String enrollment_id;    // Unique Enrollment ID
    private String student_id;       // Foreign Key referencing Student
    private String course_id;        // Foreign Key referencing Course
    private Date enrollment_date;    // Date of Enrollment
    private String status;           // Enrollment Status (Active, Completed, Dropped)
    private String payment_status;   // Payment Status (Paid, Pending, Unpaid)
    private Date start_date;         // Course Start Date
    private Date end_date;           // Course End Date (Optional)
}
