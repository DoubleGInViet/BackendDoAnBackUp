package com.duy.BackendDoAn.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.dynalink.linker.LinkerServices;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingTicketDTO {
    @JsonProperty("user")
    private Long userId;

    @JsonProperty("full_name")
    private String customerFullName;

    @JsonProperty("email")
    private String customerEmail;

    @JsonProperty("phone")
    private String customerPhoneNumber;

    @JsonProperty("country")
    private String customerCountry;

    @JsonProperty("booked_tickets")
    private List<BookedTicketDTO> bookedTickets;
}
