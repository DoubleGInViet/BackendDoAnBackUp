package com.duy.BackendDoAn.dtos.bookingVehicleDTOs;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerInfoDTO {
    private String fullName;
    private String email;
    private String phone;
    private String country;
}
