package com.duy.BackendDoAn.responses.bookingVehicles;

import com.duy.BackendDoAn.models.BookingVehicle;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlaceAndTimeResponse {
    private String name;
    private String date;

    public static PlaceAndTimeResponse fromBookingStart(BookingVehicle bookingVehicle) {
        return PlaceAndTimeResponse.builder()
                .name(bookingVehicle.getStart_address())
                .date(formatDate(bookingVehicle.getStart_date()))
                .build();
    }

    public static PlaceAndTimeResponse fromBookingReturn(BookingVehicle bookingVehicle) {
        return PlaceAndTimeResponse.builder()
                .name(bookingVehicle.getReturn_address())
                .date(formatDate(bookingVehicle.getReturn_date()))
                .build();
    }

    private static String formatDate(LocalDate pickUpDate) {
        return (pickUpDate != null) ? pickUpDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null;
    }
}
