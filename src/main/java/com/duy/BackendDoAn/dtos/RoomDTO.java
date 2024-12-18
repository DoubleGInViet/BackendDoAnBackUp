package com.duy.BackendDoAn.dtos;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomDTO {
    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private String type;

    @JsonProperty("size")
    private Long roomSize;

    @JsonProperty("max_guests")
    private Long maxGuests;

    @JsonProperty("type_bed_1")
    private String typeBed1;

    @JsonProperty("no_bed_1")
    private Long noBed1;

    @JsonProperty("type_bed_2")
    private String typeBed2;

    @JsonProperty("no_bed_2")
    private Long noBed2;

    @JsonProperty("price")
    private Long price;

    @JsonProperty("available_rooms")
    private Long availableRooms;


    @JsonProperty("hotel_id")
    private Long hotel;
}
