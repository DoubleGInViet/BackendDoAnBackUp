package com.duy.BackendDoAn.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TicketClassDTO {
    private String name;
    private Long price;
    @JsonProperty("max_amount")
    private Long maxAmount;

    private String description;
    private Long tour;
}
