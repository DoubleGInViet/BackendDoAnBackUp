package com.duy.BackendDoAn.responses.allRentals;

import com.duy.BackendDoAn.models.Office;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OfficeRentalResponse {
    private Long id;

    @JsonProperty("office_name")
    private String name;

    @JsonProperty("city_name")
    private String cityName;

    public static OfficeRentalResponse fromOffice(Office office) {
        return OfficeRentalResponse.builder()
                .id(office.getAttraction().getId())
                .name(office.getAttraction().getName())
                .cityName(office.getAttraction().getCity().getCity_name())
                .build();
    }
}
