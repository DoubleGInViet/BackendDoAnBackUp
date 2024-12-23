package com.duy.BackendDoAn.responses.bookingTickets;

import com.duy.BackendDoAn.models.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {
    private String name;

    public static CustomerResponse fromCustomer(User user) {
        return CustomerResponse.builder()
                .name(user.getName())
                .build();
    }
}
