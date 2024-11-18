package lk.ijse.gdse72.sisiralearners.model;

import lk.ijse.gdse72.sisiralearners.Util.CrudUtil;
import lk.ijse.gdse72.sisiralearners.dto.tm.ScheduleAssignmentDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScheduleAssignmentModel {

    public ArrayList<ScheduleAssignmentDto> getAllAssignments() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM ScheduleAssignment");
        ArrayList<ScheduleAssignmentDto> assignmentDtos = new ArrayList<>();

        while (rst.next()) {
            ScheduleAssignmentDto assignmentDto = new ScheduleAssignmentDto(
                    rst.getString("assignment_id"),
                    rst.getString("schedule_id"),
                    rst.getString("instructor_id"),
                    rst.getString("vehicle_id"),
                    rst.getString("student_id")
            );
            assignmentDtos.add(assignmentDto);
        }

        return assignmentDtos;
    }
}
