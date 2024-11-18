package lk.ijse.gdse72.sisiralearners.model;

import lk.ijse.gdse72.sisiralearners.Util.CrudUtil;
import lk.ijse.gdse72.sisiralearners.db.DBConnection;
import lk.ijse.gdse72.sisiralearners.dto.tm.PaymentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentModel {
    public ArrayList<PaymentDto> getAllPayments() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Payment");
        ArrayList<PaymentDto> paymentDtos = new ArrayList<>();

        while (rst.next()) {
            PaymentDto paymentDto = new PaymentDto(
                    rst.getString("payment_id"),
                    rst.getDate("payment_date"),
                    rst.getString("payment_method"),
                    rst.getDouble("paid"),
                    rst.getDouble("price"),
                    rst.getDouble("balance"),
                    rst.getString("payment_status"),
                    rst.getString("registration_id")
            );
            paymentDtos.add(paymentDto);
        }

        return paymentDtos;
    }
    public boolean savePayment(PaymentDto paymentDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "INSERT INTO Payment (payment_id, payment_date, payment_method, paid,price,balance, payment_status,registration_id) " +
                        "VALUES (?, ?, ?, ?, ?, ?)",
                paymentDto.getPayment_id(),        // payment_id
                paymentDto.getPayment_date(),      // payment_date
                paymentDto.getPayment_method(),    // payment_method
                paymentDto.getPaid(),
                paymentDto.getPrice(),
                paymentDto.getBalance(),            // amount
                paymentDto.getPayment_status(),
                paymentDto.getRegistration_id()   // registration_id (this links to the Registration table)
// payment_status
        );
    }

    public static boolean deleteStudentById(String paymentId) {
        String sql = "DELETE FROM Payment WHERE payment_id = ?";

        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, paymentId);

            int affectedRows = pstmt.executeUpdate();

            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception for debugging purposes
            return false; // Return false if there was an exception
        }
    }
    public boolean updatePayment(PaymentDto paymentDto) {
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "UPDATE payment SET payment_method = ?, paid = ?, price = ?, payment_status = ? WHERE payment_id = ?";

        try (PreparedStatement pst = connection.prepareStatement(query)) {
            System.out.println("Executing query: " + query);  // Debugging line

            // Set the parameters for the update query
            pst.setString(1, paymentDto.getPayment_method());  // Payment method
            pst.setDouble(2, paymentDto.getPaid());  // Paid amount
            pst.setDouble(3, paymentDto.getPrice());  // Price
            pst.setString(4, paymentDto.getPayment_status());  // Payment status
            pst.setString(5, paymentDto.getPayment_id());  // Payment ID (to identify the record)

            // Execute the update query
            int rowsAffected = pst.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);  // Debugging line

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
