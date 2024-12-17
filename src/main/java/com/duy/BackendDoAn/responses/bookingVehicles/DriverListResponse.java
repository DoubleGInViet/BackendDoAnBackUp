package com.duy.BackendDoAn.responses.bookingVehicles;

import com.duy.BackendDoAn.models.BookingVehicle;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DriverListResponse {
    private List<DriverResponse> listDrivers;
    private Long totalDriver;

    public static DriverListResponse fromBooking(BookingVehicle bookingVehicle) {
        DriverListResponse responses = new DriverListResponse();
        responses.setListDrivers(bookingVehicle.getAdditionDrivers().stream().map(DriverResponse::fromDriver).collect(Collectors.toList()));
        responses.setTotalDriver((long) responses.listDrivers.size());
        return responses;
    }
}
