package com.duy.BackendDoAn.responses.bookingRooms;

import com.duy.BackendDoAn.models.BookingRoom;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingRoomPaymentLinkResponse {
    private BookingRoomResponse bookingRoom;
    private String payment;
}
