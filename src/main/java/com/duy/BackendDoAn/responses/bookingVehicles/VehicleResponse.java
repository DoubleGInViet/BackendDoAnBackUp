package com.duy.BackendDoAn.responses.bookingVehicles;

import com.duy.BackendDoAn.models.Vehicle;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleResponse {
    private Long id;
    private String name;
    private String brand;

    public static VehicleResponse fromVehicle(Vehicle vehicle) {
        return VehicleResponse.builder()
                .id(vehicle.getId())
                .name(vehicle.getName())
                .brand(vehicle.getBrand())
                .build();
    }
}
