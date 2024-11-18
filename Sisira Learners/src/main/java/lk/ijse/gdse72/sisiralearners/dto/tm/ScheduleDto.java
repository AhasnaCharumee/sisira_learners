package lk.ijse.gdse72.sisiralearners.dto.tm;

import lombok.*;
import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDto {
    private String schedule_id;
    private Date date;
    private String   schedule_name;
    private String start_time;
    private String end_time;
    private int   student_limit;
}
