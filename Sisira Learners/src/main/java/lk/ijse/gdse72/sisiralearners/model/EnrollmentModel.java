package lk.ijse.gdse72.sisiralearners.model;

import lk.ijse.gdse72.sisiralearners.Util.CrudUtil;
import lk.ijse.gdse72.sisiralearners.db.DBConnection;
import lk.ijse.gdse72.sisiralearners.dto.tm.EnrollmentDto;

import java.sql.*;
import java.util.ArrayList;

public class EnrollmentModel {

    public ArrayList<EnrollmentDto> getAllEnrollments() throws SQLException, ClassNotFoundException {

            ResultSet rst = CrudUtil.execute("SELECT * FROM enrollment");
            ArrayList<EnrollmentDto> enrollmentDtos = new ArrayList<>();

            while (rst.next()) {
                EnrollmentDto enrollmentDto = new EnrollmentDto(
                        rst.getString("enrollment_id"),    // enrollment_id
                        rst.getString("student_id"),       // student_id
                        rst.getString("course_id"),        // course_id
                        rst.getDate("enrollment_date"),    // enrollment_date (now as Date)
                        rst.getString("status"),
                        rst.getString("payment_status"),
                        rst.getDate("start_date"),
                        rst.getDate("end_date")    // enrollment_date (now as Date)
// enrollment_date (now as Date)

                        // schedule_id
// status
                );
                enrollmentDtos.add(enrollmentDto);  // Add to the list
            }

            return enrollmentDtos;  // Return the list of EnrollmentDto objects
        }


        // Method to save a new enrollment
    public boolean saveEnrollment(EnrollmentDto enrollmentDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "INSERT INTO enrollment (enrollment_id, student_id, schedule_id, course_id, enrollment_date, status) " +
                        "VALUES (?, ?, ?, ?, ?, ?)",
                enrollmentDto.getEnrollment_id(),           // enrollment_id
                enrollmentDto.getStudent_id(),              // student_i// schedule_id
                enrollmentDto.getCourse_id(),               // course_id
                enrollmentDto.getEnrollment_date(),         // enrollment_date
                enrollmentDto.getStatus() ,
                enrollmentDto.getPayment_status(),           // status
                enrollmentDto.getStart_date() ,           // status
                enrollmentDto.getEnd_date()            // status
// status

        );
    }
    public boolean updateEnrollment(EnrollmentDto enrollmentDto) {
        String query = "UPDATE enrollment SET course_id = ?, status = ?, start_date = ?, end_date = ? WHERE enrollment_id = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, enrollmentDto.getCourse_id());
            preparedStatement.setString(2, enrollmentDto.getStatus());
            preparedStatement.setDate(3, enrollmentDto.getStart_date());
            preparedStatement.setDate(4, enrollmentDto.getEnd_date());
            preparedStatement.setString(5, enrollmentDto.getEnrollment_id());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            // Log the error with a more descriptive message
            System.err.println("Error executing update on Enrollment table: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteEnrollmentById(String enrollment_id) {
        String sql = "DELETE FROM Enrollment WHERE enrollment_id = ?";

        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, enrollment_id);

            int affectedRows = pstmt.executeUpdate();

            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception for debugging purposes
            return false; // Return false if there was an exception
        }
    }
}
