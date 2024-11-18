package lk.ijse.gdse72.sisiralearners.model;

import lk.ijse.gdse72.sisiralearners.Util.CrudUtil;
import lk.ijse.gdse72.sisiralearners.db.DBConnection;
import lk.ijse.gdse72.sisiralearners.dto.tm.StudentDto;

import java.sql.*;
import java.util.ArrayList;

public class StudentModel {
    public ArrayList<StudentDto> getAllStudents() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Student");
        ArrayList<StudentDto> studentDtos = new ArrayList<>();

        while (rst.next()) {
            StudentDto studentDto = new StudentDto(
                    rst.getString("student_id"),
                    rst.getString("name"),
                    rst.getString("email"),
                    rst.getString("payment_status"),
                    rst.getString("contact"),
                    rst.getDate("start_day"),
                    rst.getString("registration_id")
            );
            studentDtos.add(studentDto);
        }

        return studentDtos;
    }

    public static boolean deleteStudentById(String studentId) {
        String sql = "DELETE FROM student WHERE student_id = ?";

        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, studentId);

            int affectedRows = pstmt.executeUpdate();

            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception for debugging purposes
            return false; // Return false if there was an exception
        }
    }



    public boolean saveStudent(StudentDto studentDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "INSERT INTO student (student_id, name,email,payment_status, contact, start_day,  registration_id) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)",
                studentDto.getStudent_id(),           // student_id
                studentDto.getName(),
                studentDto.getEmail(),               // email
                studentDto.getPayment_status(),       // payment_status
                studentDto.getContact(),             // contact
                studentDto.getStart_day(),             // address
                studentDto.getRegistration_id()       // enrollment_date
        );
    }


   /* public static boolean updateStudentDetails(String studentId, String courseId, Date enrollmentDate, String status, Date startDate, Date endDate) {
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "UPDATE enrollment SET course_id = ?, enrollment_date = ?, status = ?, start_date = ?, end_date = ? WHERE student_id = ?";

        try (PreparedStatement pst = connection.prepareStatement(query)) {
            // Set the values for the update query
            pst.setString(1, courseId); // Update course_id
            pst.setDate(2, enrollmentDate != null ? new java.sql.Date(enrollmentDate.getTime()) : null); // Update enrollment_date or set NULL
            pst.setString(3, status); // Update status
            pst.setDate(4, startDate != null ? new java.sql.Date(startDate.getTime()) : null); // Update start_date or set NULL
            pst.setDate(5, endDate != null ? new java.sql.Date(endDate.getTime()) : null); // Update end_date or set NULL
            pst.setString(6, studentId); // Use student_id to locate the record (DO NOT update it)

            // Execute the update
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0; // Return true if the update is successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if an error occurs
        }
    }*/

}
