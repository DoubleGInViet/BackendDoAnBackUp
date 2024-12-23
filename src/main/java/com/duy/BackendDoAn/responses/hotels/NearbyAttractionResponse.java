package com.duy.BackendDoAn.responses.hotels;

import com.duy.BackendDoAn.models.NearbyAttractions;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NearbyAttractionResponse {
    private String name;
    private Float distance;
    private String type;

    public static NearbyAttractionResponse fromNearbyAttraction(NearbyAttractions nearbyAttractions){
        NearbyAttractionResponse response = new NearbyAttractionResponse();
        response.name = nearbyAttractions.getAttraction().getName();
        response.distance = nearbyAttractions.getDistance();
        response.type = nearbyAttractions.getAttraction().getType();
        return response;
    }
}
