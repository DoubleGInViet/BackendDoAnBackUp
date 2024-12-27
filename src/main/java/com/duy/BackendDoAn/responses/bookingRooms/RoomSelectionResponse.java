package com.duy.BackendDoAn.responses.bookingRooms;

import com.duy.BackendDoAn.models.BookedRoom;
import com.duy.BackendDoAn.models.BookingRoom;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomSelectionResponse {
    @JsonProperty("selected_rooms")
    private List<SeperatedRoomResponse> selectedRooms = new ArrayList<>();

    private Long totalRooms;

    public static RoomSelectionResponse fromBooking(BookingRoom bookingRoom) {
        RoomSelectionResponse response = new RoomSelectionResponse();
        List<BookedRoom> rooms = bookingRoom.getBookedRooms();
        response.selectedRooms =
                rooms != null
                        ? rooms.stream().map(SeperatedRoomResponse::fromBookedRoom).collect(Collectors.toList())
                        : new ArrayList<>();
        response.totalRooms = bookingRoom.getTotal_rooms();
        return response;
    }
}
