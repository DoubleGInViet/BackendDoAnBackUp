package com.duy.BackendDoAn.responses.vehicles;

import com.duy.BackendDoAn.models.Car;
import com.duy.BackendDoAn.models.Motor;
import com.duy.BackendDoAn.models.Vehicle;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailResponse {
    private String fuel;
    private Long year;
    private String engine;
    private String brand;
}
