package com.duy.BackendDoAn.dtos.bookingVehicleDTOs;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlaceAndDateDTO {
    private String location;
    private String date;
}
