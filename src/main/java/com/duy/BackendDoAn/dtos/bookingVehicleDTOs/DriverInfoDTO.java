package com.duy.BackendDoAn.dtos.bookingVehicleDTOs;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DriverInfoDTO {
    private int title;
    private String fullName;
    private String phone;
}
