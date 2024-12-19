package com.duy.BackendDoAn.responses;

import com.duy.BackendDoAn.models.Attraction;
import com.duy.BackendDoAn.models.Office;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttractionResponse {
    private Long id;

    @JsonProperty("name")
    private String name;

    private Float longitude;
    private Float latitude;

    private String type;
    private String image;

    @JsonProperty("city_name")
    private String city;


    public static AttractionResponse fromAttraction(Attraction attraction) {
        AttractionResponse response = AttractionResponse.builder()
                .id(attraction.getOffices().get(0).getId())
                .name(attraction.getName())
                .latitude(attraction.getLatitude())
                .longitude(attraction.getLongitude())
                .type(attraction.getType())
                .image(attraction.getImage())
                .city(attraction.getCity().getCity_name())
                .build();
        return response;
    }
}
