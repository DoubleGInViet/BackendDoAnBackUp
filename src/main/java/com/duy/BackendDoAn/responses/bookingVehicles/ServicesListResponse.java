package com.duy.BackendDoAn.responses.bookingVehicles;

import com.duy.BackendDoAn.models.BookingVehicle;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServicesListResponse {
    @JsonProperty("bonusServices")
    private List<ServiceResponse> services;

    private Long totalServices;

    public static ServicesListResponse fromBookingVehicle(BookingVehicle bookingVehicle) {
        ServicesListResponse servicesListResponse = new ServicesListResponse();
        servicesListResponse.services = bookingVehicle.getAccessoryBookings().stream().map(ServiceResponse::fromAccessoryBooking).collect(Collectors.toList());
        servicesListResponse.totalServices = bookingVehicle.getTotalService();
        return servicesListResponse;
    }
}
