package lk.ijse.gdse72.sisiralearners.dto.tm;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleAssignmentDto {
    private String assignment_id;   // Unique ID for the assignment
    private String schedule_id;     // The schedule for the driving class
    private String instructor_id;   // The instructor for this schedule
    private String vehicle_id;      // The vehicle assigned to this schedule
    private String student_id;      // The student assigned to this schedule
}
