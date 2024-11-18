package lk.ijse.gdse72.sisiralearners.model;

import lk.ijse.gdse72.sisiralearners.Util.CrudUtil;
import lk.ijse.gdse72.sisiralearners.dto.tm.ScheduleDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScheduleModel {

    public ArrayList<ScheduleDto> getAllSchedules() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM schedule");
        ArrayList<ScheduleDto> scheduleDtos = new ArrayList<>();

        while (rst.next()) {
            ScheduleDto scheduleDto = new ScheduleDto(
                    rst.getString("schedule_id"),
                    rst.getDate("date"), // assuming column name is schedule_id
                    rst.getString("schedule_name"), // assuming column name is schedule_name
                    rst.getString("start_time"),    // assuming column name is start_time
                    rst.getString("end_time"),      // assuming column name is end_time
                    rst.getInt("student_limit")     // assuming column name is student_limit
            );
            scheduleDtos.add(scheduleDto);
        }

        return scheduleDtos;
    }
}
