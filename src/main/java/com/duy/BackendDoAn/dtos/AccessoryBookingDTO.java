package com.duy.BackendDoAn.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccessoryBookingDTO {
    @JsonProperty("amount")
    private Long amount;

    @JsonProperty("price_per")
    private Float pricePer;

    @JsonProperty("accessory_id")
    private Long accessory;
}
