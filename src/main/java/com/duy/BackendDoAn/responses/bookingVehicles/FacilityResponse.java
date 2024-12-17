package com.duy.BackendDoAn.responses.bookingVehicles;

import com.duy.BackendDoAn.models.RentalFacility;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FacilityResponse {
    private Long id;
    private String name;

    public static FacilityResponse fromFacility(RentalFacility rentalFacility){
        return FacilityResponse.builder()
                .id(rentalFacility.getId())
                .name(rentalFacility.getName())
                .build();
    }
}
