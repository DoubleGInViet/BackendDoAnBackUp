package com.duy.BackendDoAn.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VehicleDTO {
    @JsonProperty("name")
    private String name;

    private String brand;
    private Long year;

    @JsonProperty("type")
    private String type;

    @JsonProperty("price")
    private Long price;

    private Long stake;

    @JsonProperty("transmission_type")
    private String transmissionType;

    @JsonProperty("baggage_capacity")
    private Long baggageCapacity;

    private Long seats;

    @JsonProperty("capacity")
    private String capacity;
    private String fuel;
    private String engine;

    @JsonProperty("facility_id")
    private Long facility;

    @JsonProperty("available_vehicle")
    private Long availableVehicle;
}
