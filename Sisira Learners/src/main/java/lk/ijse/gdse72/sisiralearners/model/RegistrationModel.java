package lk.ijse.gdse72.sisiralearners.model;

import javafx.scene.control.Alert;
import lk.ijse.gdse72.sisiralearners.Util.CrudUtil;
import lk.ijse.gdse72.sisiralearners.db.DBConnection;
import lk.ijse.gdse72.sisiralearners.dto.tm.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class RegistrationModel {

    // Fetch all registrations from the database
    public ArrayList<RegistrationDto> getAllRegistrations() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Registration");
        ArrayList<RegistrationDto> registrationDtos = new ArrayList<>();

        while (rst.next()) {
            RegistrationDto registrationDto = new RegistrationDto(
                    rst.getString("registration_id"),
                    rst.getString("name"),
                    rst.getString("gender"),
                    rst.getString("address"),
                    rst.getDate("date_of_birth"),
                    rst.getString("nic"),
                    rst.getString("email"),
                    rst.getString("course_name"),
                    rst.getDouble("paid"),
                    rst.getDouble("price"),
                    0.0, // Skip balance (handled in DB)
                    rst.getDate("registration_date"),
                    rst.getString("payment_status"),
                    rst.getString("contact")
            );
            registrationDtos.add(registrationDto);
        }

        return registrationDtos;
    }

    // Save registration details along with payment, student, and enrollment
    public boolean saveRegistration(RegistrationDto registrationDTO) throws SQLException {
        PaymentDto paymentDTO = new PaymentDto(
                "P" + registrationDTO.getRegistration_id(),  // Create a unique payment ID based on registration ID
                registrationDTO.getRegistration_date(),      // Payment date
                "Cash",                                      // Payment method
                registrationDTO.getPaid(),                   // Amount paid
                registrationDTO.getPrice(),                  // Total price
                0.0,                                         // Balance (defaulted to 0.0, assuming it's generated in DB)
                registrationDTO.getPayment_status(),         // Payment status
                registrationDTO.getRegistration_id()         // Registration ID
        );


        StudentDto studentDTO = new StudentDto(
                "S" + registrationDTO.getRegistration_id(),
                registrationDTO.getName(),
                registrationDTO.getEmail(),
                registrationDTO.getPayment_status(),
                registrationDTO.getContact(),
                registrationDTO.getRegistration_date(),
                registrationDTO.getRegistration_id()
        );

        LocalDate registrationDate = registrationDTO.getRegistration_date().toLocalDate();
        LocalDate endDate = registrationDate.plusMonths(6);

// Create the EnrollmentDto with the correct end date
        EnrollmentDto enrollmentDTO = new EnrollmentDto(
                "E" + registrationDTO.getRegistration_id(),
                studentDTO.getStudent_id(),
                "C001", // Hardcoded course ID (can be parameterized)
                registrationDTO.getRegistration_date(),
                "Active",
                registrationDTO.getPayment_status(),
                registrationDTO.getRegistration_date(),
                Date.valueOf(endDate) // Convert LocalDate to java.sql.Date
        );

        return saveRegistrationWithDetails(registrationDTO, paymentDTO, studentDTO, enrollmentDTO);
    }

    // Save all details with transaction management
    private boolean saveRegistrationWithDetails(RegistrationDto registrationDTO, PaymentDto paymentDTO,
                                                StudentDto studentDTO, EnrollmentDto enrollmentDTO) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);

            boolean isRegistrationSaved = CrudUtil.execute(
                    "INSERT INTO Registration (registration_id, name, gender, address, date_of_birth, nic, email, course_name, paid, price, registration_date, payment_status, contact) " +
                            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)",
                    registrationDTO.getRegistration_id(),
                    registrationDTO.getName(),
                    registrationDTO.getGender(),
                    registrationDTO.getAddress(),
                    registrationDTO.getDate_of_birth(),
                    registrationDTO.getNic(),
                    registrationDTO.getEmail(),
                    registrationDTO.getCourse_name(),
                    registrationDTO.getPaid(),
                    registrationDTO.getPrice(),
                    registrationDTO.getRegistration_date(),
                    registrationDTO.getPayment_status(),
                    registrationDTO.getContact()
            );

            if (!isRegistrationSaved) throw new SQLException("Failed to save Registration!");

            boolean isPaymentSaved = CrudUtil.execute(
                    "INSERT INTO Payment (payment_id, payment_date, payment_method, paid, price, payment_status, registration_id) " +
                            "VALUES (?,?,?,?,?,?,?)",
                    paymentDTO.getPayment_id(),
                    paymentDTO.getPayment_date(),
                    paymentDTO.getPayment_method(),
                    paymentDTO.getPaid(),
                    paymentDTO.getPrice(),
                    paymentDTO.getPayment_status(),
                    paymentDTO.getRegistration_id()
            );

            if (!isPaymentSaved) throw new SQLException("Failed to save Payment!");

            boolean isStudentSaved = CrudUtil.execute(
                    "INSERT INTO Student (student_id, name, email, payment_status, contact, start_day, registration_id) " +
                            "VALUES (?,?,?,?,?,?,?)",
                    studentDTO.getStudent_id(),
                    studentDTO.getName(),
                    studentDTO.getEmail(),
                    studentDTO.getPayment_status(),
                    studentDTO.getContact(),
                    studentDTO.getStart_day(),
                    studentDTO.getRegistration_id()
            );

            if (!isStudentSaved) throw new SQLException("Failed to save Student!");

            boolean isEnrollmentSaved = CrudUtil.execute(
                    "INSERT INTO Enrollment (enrollment_id, student_id, course_id, enrollment_date, status, payment_status, start_date, end_date) " +
                            "VALUES (?,?,?,?,?,?,?,?)",
                    enrollmentDTO.getEnrollment_id(),
                    enrollmentDTO.getStudent_id(),
                    enrollmentDTO.getCourse_id(),
                    enrollmentDTO.getEnrollment_date(),
                    enrollmentDTO.getStatus(),
                    enrollmentDTO.getPayment_status(),
                    enrollmentDTO.getStart_date(),
                    enrollmentDTO.getEnd_date()
            );

            if (!isEnrollmentSaved) throw new SQLException("Failed to save Enrollment!");

            connection.commit();
            return true;

        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
            return false;

        } finally {
            connection.setAutoCommit(true);
        }
        // Database insert logic


    }
    public static boolean deleteRegistrationById(String registration_id) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            // Step 1: Find the student_id related to the registration_id from the student table
            String getStudentIdQuery = "SELECT student_id FROM student WHERE registration_id = ?";
            String student_id = null;
            try (PreparedStatement getStudentIdStmt = connection.prepareStatement(getStudentIdQuery)) {
                getStudentIdStmt.setString(1, registration_id);
                try (ResultSet rs = getStudentIdStmt.executeQuery()) {
                    if (rs.next()) {
                        student_id = rs.getString("student_id");
                    }
                }
            }

            if (student_id == null) {
                throw new SQLException("Student with the given registration_id not found.");
            }

            // Step 2: Delete from enrollment table using student_id
            String deleteEnrolmentQuery = "DELETE FROM enrollment WHERE student_id = ?";
            try (PreparedStatement enrolmentStmt = connection.prepareStatement(deleteEnrolmentQuery)) {
                enrolmentStmt.setString(1, student_id);
                enrolmentStmt.executeUpdate();
            }

            // Step 3: Delete from payment table using registration_id
            String deletePaymentQuery = "DELETE FROM payment WHERE registration_id = ?";
            try (PreparedStatement paymentStmt = connection.prepareStatement(deletePaymentQuery)) {
                paymentStmt.setString(1, registration_id);
                paymentStmt.executeUpdate();
            }

            // Step 4: Delete from student table using registration_id
            String deleteStudentQuery = "DELETE FROM student WHERE registration_id = ?";
            try (PreparedStatement studentStmt = connection.prepareStatement(deleteStudentQuery)) {
                studentStmt.setString(1, registration_id);
                studentStmt.executeUpdate();
            }

            // Step 5: Delete from registration table using registration_id
            String deleteRegistrationQuery = "DELETE FROM registration WHERE registration_id = ?";
            try (PreparedStatement registrationStmt = connection.prepareStatement(deleteRegistrationQuery)) {
                registrationStmt.setString(1, registration_id);
                registrationStmt.executeUpdate();
            }

            connection.commit();
            return true;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }



    public static boolean updateRegistration(RegistrationDto registrationDto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "UPDATE registration SET name = ?, gender = ?, address = ?, course_name = ?, email = ?, " +
                "payment_status = ?, date_of_birth = ?, contact = ?, nic = ? WHERE registration_id = ?";

        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, registrationDto.getName());
            pst.setString(2, registrationDto.getGender());
            pst.setString(3, registrationDto.getAddress());
            pst.setString(4, registrationDto.getCourse_name());
            pst.setString(5, registrationDto.getEmail());
            pst.setString(6, registrationDto.getPayment_status());
            pst.setDate(7, registrationDto.getDate_of_birth());
            pst.setString(8, registrationDto.getContact());
            pst.setString(9, registrationDto.getNic());
            pst.setString(10, registrationDto.getRegistration_id());

            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;  // Return true if update is successful
        }
    }
}
