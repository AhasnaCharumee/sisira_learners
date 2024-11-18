package lk.ijse.gdse72.sisiralearners.model;

import lk.ijse.gdse72.sisiralearners.Util.CrudUtil;
import lk.ijse.gdse72.sisiralearners.dto.tm.UserDto;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {
    public ArrayList<UserDto> getAllUsers() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM User");
        ArrayList<UserDto> userDtos = new ArrayList<>();

        while (rst.next()) {
            UserDto userDto = new UserDto(
                    rst.getString("user_id"),        // user_id from User table
                    rst.getString("user_name"),      // user_name from User table
                    rst.getString("email"),          // email from User table
                    rst.getString("role"),           // role from User table
                    rst.getString("password")        // password from User table
            );
            userDtos.add(userDto);
        }

        return userDtos;
    }

    public boolean addUser(UserDto userDto) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO User (user_id, user_name, email, role, password) VALUES (?, ?, ?, ?, ?)";
        return CrudUtil.execute(query, userDto.getUserId(), userDto.getUserName(), userDto.getEmail(), userDto.getRole(), userDto.getPassword());
    }

    public UserDto getUserById(String userId) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM User WHERE user_id = ?";
        ResultSet rst = CrudUtil.execute(query, userId);

        if (rst.next()) {
            return new UserDto(
                    rst.getString("user_id"),
                    rst.getString("user_name"),
                    rst.getString("email"),
                    rst.getString("role"),
                    rst.getString("password")
            );
        }
        return null;
    }

}
