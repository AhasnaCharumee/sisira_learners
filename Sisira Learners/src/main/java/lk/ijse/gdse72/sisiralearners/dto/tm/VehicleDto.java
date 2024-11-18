package lk.ijse.gdse72.sisiralearners.dto.tm;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDto {
    private String vehicle_id;
    private String vehicle_type;
    private String vehicle_class;
    private String maintenance_records;
    private String license_plate;
}
