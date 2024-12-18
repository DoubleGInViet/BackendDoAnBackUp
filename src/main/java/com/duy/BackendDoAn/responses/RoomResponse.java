package com.duy.BackendDoAn.responses;

import com.duy.BackendDoAn.models.AmenityForRoom;
import com.duy.BackendDoAn.models.*;
import com.duy.BackendDoAn.services.AmenityService;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomResponse {
    @JsonProperty("room_id")
    private Long id;

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

    @JsonProperty("amenity_for_room")
    private List<String> amenityForRoom = new ArrayList<>();

    public static RoomResponse fromRoom(Room room){
        RoomResponse response = new RoomResponse();
        response.id = room.getId();
        response.name = room.getName();
        response.price = room.getPrice();
        response.availableRooms = room.getAvailable_room();
        response.type = room.getType();
        response.typeBed1 = room.getTypeBed1();
        response.noBed1 = room.getNoBed1();
        response.typeBed2 = room.getTypeBed2();
        response.noBed2 = room.getNoBed2();
        response.maxGuests = room.getMax_guests();
        response.roomSize = room.getRoom_size();
        response.amenityForRoom = (room.getAmenityForRooms() != null)
                ? room.getAmenityForRooms().stream()
                .map(amenityForRoom -> amenityForRoom.getAmenity().getName())
                .collect(Collectors.toList())
                : new ArrayList<>();
        return response;
    }
}
