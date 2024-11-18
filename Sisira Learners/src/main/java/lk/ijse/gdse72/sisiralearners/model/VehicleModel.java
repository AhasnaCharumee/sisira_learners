package lk.ijse.gdse72.sisiralearners.model;

import lk.ijse.gdse72.sisiralearners.Util.CrudUtil;
import lk.ijse.gdse72.sisiralearners.dto.tm.VehicleDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleModel {
    public ArrayList<VehicleDto> getAllVehicles() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Vehicle");  // Modify table name as needed
        ArrayList<VehicleDto> vehicleDtos = new ArrayList<>();

        while (rst.next()) {
            VehicleDto vehicleDto = new VehicleDto(
                    rst.getString("vehicle_id"),        // Adjust column names as per the database
                    rst.getString("vehicle_type"),
                    rst.getString("vehicle_class"),
                    rst.getString("maintenance_records"),
                    rst.getString("license_plate")
            );
            vehicleDtos.add(vehicleDto);
        }

        return vehicleDtos;
    }
}
