package com.duy.BackendDoAn.responses.bookingVehicles;

import com.duy.BackendDoAn.models.AccessoryBooking;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceResponse {
    private Long id;
    private String name;
    private Long count;
    private Float price;

    public static ServiceResponse fromAccessoryBooking(AccessoryBooking accessoryBooking) {
        return ServiceResponse.builder()
                .id(accessoryBooking.getAccessory().getId())
                .name(accessoryBooking.getAccessory().getName())
                .count(accessoryBooking.getAmount())
                .price(accessoryBooking.getAccessory().getPrice())
                .build();
    }
}
