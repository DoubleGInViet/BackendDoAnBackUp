package com.duy.BackendDoAn.responses.bookingRooms;
import com.duy.BackendDoAn.models.BookedRoom;
import com.duy.BackendDoAn.models.BookingRoom;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingRoomResponse {
    private String id;
    @JsonProperty("no_adult")
    private Long adults;
    @JsonProperty("no_children")
    private Long children;

    @JsonProperty("user_id")
    private Long user;

    @JsonProperty("checkin_date")
    private String checkInDate;

    @JsonProperty("checkout_date")
    private String checkOutDate;

    @JsonProperty("customerInfo")
    private CustomerResponse customerResponse;

    @JsonProperty("room_selection")
    private RoomSelectionResponse roomSelectionResponse;

    private Long totalPrice;

    private HotelBookingResponse hotel;
    private String status;

    @JsonProperty("payed_money")
    private Long payedMoney;


    public static BookingRoomResponse fromBooking(BookingRoom bookingRoom) {
        BookingRoomResponse response = new BookingRoomResponse();
        response.id = bookingRoom.getId();
        response.user = bookingRoom.getUser().getId();
        response.adults = bookingRoom.getAdults();
        response.children = bookingRoom.getChildren();
        response.hotel = HotelBookingResponse.fromBooking(bookingRoom);
        response.checkInDate = bookingRoom.getCheck_in_date().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        response.checkOutDate = bookingRoom.getCheck_out_date().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        response.roomSelectionResponse = (bookingRoom.getBookedRooms() != null)
                ? RoomSelectionResponse.fromBooking(bookingRoom)
                : new RoomSelectionResponse();
        response.customerResponse = (bookingRoom.getUser() != null)
                ? CustomerResponse.fromBookingCustomer(bookingRoom)
                : new CustomerResponse();
        response.totalPrice = bookingRoom.getTotal_price();
        response.status = bookingRoom.getStatus();
        response.payedMoney = response.getStatus().equals("1")
                ? 0
                : response.getTotalPrice();
        return response;
    }
}
