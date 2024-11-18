package lk.ijse.gdse72.sisiralearners.model;

import lk.ijse.gdse72.sisiralearners.Util.CrudUtil;
import lk.ijse.gdse72.sisiralearners.dto.tm.InstructorDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InstructorModel {

    public ArrayList<InstructorDto> getAllInstructors() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM instructor");
        ArrayList<InstructorDto> instructorDtos = new ArrayList<>();

        while (rst.next()) {
            InstructorDto instructorDto = new InstructorDto(
                    rst.getString("instructor_id"),
                    rst.getString("name"),
                    rst.getString("status"),
                    rst.getString("address"),
                    rst.getString("telephone"),
                    rst.getString("vehicle_class"),
                    rst.getString("salary"),
                    rst.getString("email")
            );
            instructorDtos.add(instructorDto);
        }

        return instructorDtos;
    }
}
