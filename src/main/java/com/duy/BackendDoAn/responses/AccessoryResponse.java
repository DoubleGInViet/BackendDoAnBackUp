package com.duy.BackendDoAn.responses;

import com.duy.BackendDoAn.models.Accessory;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccessoryResponse {
    @JsonProperty("name")
    private String name;

    @JsonProperty("price")
    private Long price;

    @JsonProperty("type")
    private String type;

    @JsonProperty("max_value")
    private Long maxValue;

    public static AccessoryResponse fromService(Accessory accessory) {
        AccessoryResponse serviceResponse = AccessoryResponse.builder()
                .name(accessory.getName())
                .price(accessory.getPrice())
                .type(accessory.getType())
                .maxValue(accessory.getMaxValue())
                .build();
        return serviceResponse;
    }
}
