package com.duy.BackendDoAn.responses.bookingTickets;

import com.duy.BackendDoAn.models.BookingTicket;
import com.duy.BackendDoAn.models.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {
    private String name;
    private String email;
    private String phone;
    private String country;

    public static CustomerResponse fromCustomer(BookingTicket bookingTicket) {
        return CustomerResponse.builder()
                .name(bookingTicket.getCustomerFullName())
                .email(bookingTicket.getCustomerEmail())
                .phone(bookingTicket.getCustomerPhoneNumber())
                .country(bookingTicket.getCustomerCountry())
                .build();
    }
}
