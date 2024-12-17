package com.duy.BackendDoAn.responses.bookingVehicles;

import com.duy.BackendDoAn.models.BookingVehicle;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {
    private String fullName;
    private String email;
    private String phone;
    private String country;

    public static CustomerResponse fromBooking(BookingVehicle bookingVehicle) {
        return CustomerResponse.builder()
                .fullName(bookingVehicle.getCustomerFullName())
                .email(bookingVehicle.getCustomerEmail())
                .phone(bookingVehicle.getCustomerPhoneNumber())
                .country(bookingVehicle.getCustomerCountry())
                .build();
    }
}
