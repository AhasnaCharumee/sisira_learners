package lk.ijse.gdse72.sisiralearners.model;

import lk.ijse.gdse72.sisiralearners.Util.CrudUtil;
import lk.ijse.gdse72.sisiralearners.db.DBConnection;
import lk.ijse.gdse72.sisiralearners.dto.tm.CourseDto;
import lk.ijse.gdse72.sisiralearners.dto.tm.RegistrationDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseModel {

    public ArrayList<CourseDto> getAllCourses() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Course");
        ArrayList<CourseDto> courseDtos = new ArrayList<>();

        while (rst.next()) {
            CourseDto courseDto = new CourseDto(
                    rst.getString("course_id"),
                    rst.getString("name"),
                    rst.getString("duration"),
                    rst.getDouble("price"),
                    rst.getString("status"),
                    rst.getInt("max_students"),
                    rst.getString("description")
            );
            courseDtos.add(courseDto);
        }

        return courseDtos;
    }


}
