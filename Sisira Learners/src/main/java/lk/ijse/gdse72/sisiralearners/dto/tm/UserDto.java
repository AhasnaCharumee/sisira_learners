

package lk.ijse.gdse72.sisiralearners.dto.tm;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto { // Changed to match UserDTO convention in UserModel
    private String userId; // Adjusted field names for consistent camelCase
    private String userName;
    private String role;
    private String password;
    private String email;
}

