package lk.ijse.gdse72.sisiralearners.dto.tm;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {
    private String course_id;
    private String name;
    private String duration;
    private double price;
    private String status;
    private Integer max_students;
    private String description;
}
