package com.duy.BackendDoAn.responses.bookingVehicles;

import com.duy.BackendDoAn.models.BookingVehicle;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.duy.BackendDoAn.responses.bookingVehicles.PlaceAndTimeResponse.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingVehicleResponse {
    private String id;
    private Long user;
    private VehicleResponse vehicle;
    private FacilityResponse facility;

    @JsonProperty("pickup")
    private PlaceAndTimeResponse pickUpResponse;

    @JsonProperty("return")
    private PlaceAndTimeResponse returnResponse;

    @JsonProperty("total_price")
    private Float totalPrice;

    @JsonProperty("services")
    private ServicesListResponse servicesResponse;

    @JsonProperty("customerInfo")
    private CustomerResponse customerResponse;

    @JsonProperty("driverInfo")
    private DriverListResponse driverListResponse;

    public static BookingVehicleResponse fromBooking(BookingVehicle bookingVehicle) {
        if (bookingVehicle != null) {
            return BookingVehicleResponse.builder()
                    .id(bookingVehicle.getId())
                    .user(bookingVehicle.getUser().getId())
                    .customerResponse(CustomerResponse.fromBooking(bookingVehicle))
                    .vehicle(
                            (bookingVehicle.getVehicleRentalFacility().getVehicle() != null)
                                    ? VehicleResponse.fromVehicle(bookingVehicle.getVehicleRentalFacility().getVehicle())
                                    : new VehicleResponse()
                    )
                    .facility(
                            (bookingVehicle.getVehicleRentalFacility().getRentalFacility() != null)
                                    ? FacilityResponse.fromFacility(bookingVehicle.getVehicleRentalFacility().getRentalFacility())
                                    : new FacilityResponse()
                    )
                    .pickUpResponse(PlaceAndTimeResponse.fromBookingStart(bookingVehicle))
                    .returnResponse(PlaceAndTimeResponse.fromBookingReturn(bookingVehicle))
                    .totalPrice(bookingVehicle.getTotal_price())
                    .servicesResponse(
                            (bookingVehicle.getAccessoryBookings() != null)
                                    ? ServicesListResponse.fromBookingVehicle(bookingVehicle)
                                    : new ServicesListResponse()
                    )
                    .customerResponse(CustomerResponse.fromBooking(bookingVehicle))
                    .driverListResponse(
                            (bookingVehicle.getAdditionDrivers() != null)
                                    ? DriverListResponse.fromBooking(bookingVehicle)
                                    : new DriverListResponse()
                    )
                    .build();
        }
        return null;
    }
}
