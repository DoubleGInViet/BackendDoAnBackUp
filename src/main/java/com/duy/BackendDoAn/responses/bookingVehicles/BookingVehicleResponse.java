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
    private Long user;
    private VehicleResponse vehicle;
    private FacilityResponse facility;

    @JsonProperty("pickup")
    private PlaceAndTimeResponse pickUpResponse;

    @JsonProperty("return")
    private PlaceAndTimeResponse returnResponse;

    @JsonProperty("total_price")
    private Long totalPrice;

    @JsonProperty("services")
    private ServicesListResponse servicesResponse;

    @JsonProperty("customerInfo")
    private CustomerResponse customerResponse;

    @JsonProperty("driverInfo")
    private DriverListResponse driverListResponse;

    private String status;

    @JsonProperty("payed_money")
    private Long payedMoney;

    public static BookingVehicleResponse fromBooking(BookingVehicle bookingVehicle) {
        if (bookingVehicle != null) {
            BookingVehicleResponse response = BookingVehicleResponse.builder()
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
                    .status(bookingVehicle.getStatus())
                    .build();

            response.payedMoney = response.getStatus().equals("1")
                    ? 0
                    : response.getTotalPrice();
            return response;
        }
        return null;
    }
}
