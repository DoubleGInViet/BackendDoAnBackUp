package com.duy.BackendDoAn.responses.allRentals;

import com.duy.BackendDoAn.models.Car;
import com.duy.BackendDoAn.models.Motor;
import com.duy.BackendDoAn.models.VehicleRentalFacility;
import com.duy.BackendDoAn.responses.vehicles.DetailCarResponse;
import com.duy.BackendDoAn.responses.vehicles.DetailMotorResponse;
import com.duy.BackendDoAn.responses.vehicles.DetailResponse;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleRentalResponse {
    private Long id;
    private String name;
    private String brand;
    private String type;
    private DetailResponse details;

    public static VehicleRentalResponse fromVehicleRental(VehicleRentalFacility vehicleRentalFacility) {
        VehicleRentalResponse response = new VehicleRentalResponse();
        response.id = vehicleRentalFacility.getVehicle().getId();
        response.name = vehicleRentalFacility.getVehicle().getName();
        response.brand = vehicleRentalFacility.getVehicle().getBrand();
        response.type = vehicleRentalFacility.getVehicle().getVehicle_type();
        if ("car".equalsIgnoreCase(vehicleRentalFacility.getVehicle().getVehicle_type())) {
            response.details = DetailCarResponse.fromCar((Car) vehicleRentalFacility.getVehicle());
        } else if ("motor".equalsIgnoreCase(vehicleRentalFacility.getVehicle().getVehicle_type())) {
            response.details = DetailMotorResponse.fromMotor((Motor) vehicleRentalFacility.getVehicle());
        } else {
            response.details = null; // Hoặc xử lý ngoại lệ nếu cần
        }
        return response;
    }
}
