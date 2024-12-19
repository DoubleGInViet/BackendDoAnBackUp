package com.duy.BackendDoAn.responses.rentalFacilities;

import com.duy.BackendDoAn.models.City;
import com.duy.BackendDoAn.models.RentalFacility;
import com.duy.BackendDoAn.models.VehicleRentalFacility;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RentalResponse {
    private Long id;

    private String name;

    private Long price;

    private ReviewResponse reviewResponse;



    public static RentalResponse fromRentalFacility(VehicleRentalFacility vehicleRentalFacility) {
        RentalResponse response = new RentalResponse();
        response.id = vehicleRentalFacility.getRentalFacility().getId();
        response.name = vehicleRentalFacility.getRentalFacility().getName();
        response.price = vehicleRentalFacility.getPrice();
        response.reviewResponse = (vehicleRentalFacility.getRentalFacility().getReviewRentalFacilities() != null)
                ? ReviewResponse.fromReviews(vehicleRentalFacility.getRentalFacility())
                : new ReviewResponse();
        return response;
    }
}
