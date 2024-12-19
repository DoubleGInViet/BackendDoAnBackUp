package com.duy.BackendDoAn.responses.allRentals;

import com.duy.BackendDoAn.models.RentalFacility;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RentalFacilitiesResponse {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private List<VehicleRentalResponse> vehicles;

    public static RentalFacilitiesResponse fromRental(RentalFacility rentalFacility) {
        RentalFacilitiesResponse response = new RentalFacilitiesResponse();
        response.id = rentalFacility.getId();
        response.name = rentalFacility.getName();
        response.phone = rentalFacility.getPhone_number();
        response.email = rentalFacility.getEmail();
        response.vehicles = rentalFacility.getVehicleRentalFacilities().stream()
                .map(VehicleRentalResponse::fromVehicleRental).collect(Collectors.toList());
        return response;
    }
}
