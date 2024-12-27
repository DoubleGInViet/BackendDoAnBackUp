package com.duy.BackendDoAn.responses.allRentals;

import com.duy.BackendDoAn.models.RentalFacility;
import lombok.*;

import java.util.ArrayList;
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
    private List<OfficeRentalResponse> offices;

    public static RentalFacilitiesResponse fromRental(RentalFacility rentalFacility) {
        RentalFacilitiesResponse response = new RentalFacilitiesResponse();
        response.id = rentalFacility.getId();
        response.name = rentalFacility.getName();
        response.phone = rentalFacility.getPhone_number();
        response.email = rentalFacility.getEmail();
        response.vehicles = rentalFacility.getVehicleRentalFacilities() != null
                ? rentalFacility.getVehicleRentalFacilities().stream()
                .map(VehicleRentalResponse::fromVehicleRental).collect(Collectors.toList())
                : new ArrayList<>();
        response.offices = rentalFacility.getOffices() != null
                ? rentalFacility.getOffices().stream().map(OfficeRentalResponse::fromOffice).collect(Collectors.toList())
                : new ArrayList<>();
        return response;
    }
}
