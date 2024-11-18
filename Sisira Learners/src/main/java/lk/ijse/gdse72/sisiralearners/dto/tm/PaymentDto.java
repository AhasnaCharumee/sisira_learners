package lk.ijse.gdse72.sisiralearners.dto.tm;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private String payment_id;          // Payment ID (Primary Key)
    private Date payment_date;          // Date of the payment
    private String payment_method;      // Payment method (Cash, Credit, etc.)
    private double paid;                // Amount paid
    private double price;               // Total price
    private double balance;             // Remaining balance (Generated in DB, no setter needed)
    private String payment_status;      // Payment status (Paid, Unpaid, Partial)
    private String registration_id;     // Registration ID (Foreign Key)
}
