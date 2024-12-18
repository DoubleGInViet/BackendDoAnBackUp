package com.duy.BackendDoAn.responses.bookingRooms;
import com.duy.BackendDoAn.models.BookedRoom;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeperatedRoomResponse {
    private String name;
    private String type;
    private Long count;
    private Long price;

    public static SeperatedRoomResponse fromBookedRoom(BookedRoom bookedRoom) {
        SeperatedRoomResponse response = new SeperatedRoomResponse();
        response.name = bookedRoom.getRoom().getName();
        response.type = bookedRoom.getRoom().getType();
        response.count = bookedRoom.getAmount();
        response.price = bookedRoom.getPrice_per();
        return response;
    }
}
