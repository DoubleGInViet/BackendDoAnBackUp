package com.duy.BackendDoAn.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookedTicketDTO {
    @JsonProperty("ticket_class_id")
    private Long ticketClassId;

    @JsonProperty("quantity")
    private Long quantity;
}
