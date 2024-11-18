package lk.ijse.gdse72.sisiralearners.dto.tm;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDto {
    private String registration_id;   // Matches registration_id
    private String name;              // Matches name
    private String gender;            // Matches gender
    private String address;           // Matches address
    private Date date_of_birth;       // Matches date_of_birth
    private String nic;               // Matches nic
    private String email;             // Matches email
    private String course_name;       // Matches course_name
    private double paid;              // Matches paid (amount paid)
    private double price;             // Matches price (total price)
    private double balance;           // Matches balance (auto-calculated in DB)
    private Date registration_date;   // Matches registration_date
    private String payment_status;    // Matches payment_status
    private String contact;           // Matches contact
}
