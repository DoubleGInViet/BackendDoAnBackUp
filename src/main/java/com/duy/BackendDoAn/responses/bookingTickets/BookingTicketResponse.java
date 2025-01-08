package com.duy.BackendDoAn.responses.bookingTickets;

import com.duy.BackendDoAn.models.BookingTicket;
import com.duy.BackendDoAn.responses.bookingRooms.BookingRoomResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingTicketResponse {
    private String id;

    @JsonProperty("booking_date")
    private String bookingDate;

    private String status;

    @JsonProperty("total_price")
    private Long totalPrice;

    @JsonProperty("happen_date")
    private String happenDate;

    @JsonProperty("tour")
    private TourResponse tourResponse;

    @JsonProperty("customer")
    private CustomerResponse customerResponse;

    @JsonProperty("buyed_ticket")
    private List<BookedTicketResponse> bookedTicketResponses;

    public static BookingTicketResponse fromBooking(BookingTicket bookingTicket){
        return BookingTicketResponse.builder()
                .id(bookingTicket.getId())
                .bookingDate(bookingTicket.getBooking_date().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                .status(bookingTicket.getStatus())
                .totalPrice(bookingTicket.getTotal_price())
                .happenDate(bookingTicket.getBookedTickets().get(0).getAvailability()
                        .getHappenDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .tourResponse(
                        TourResponse.fromTour(bookingTicket.getBookedTickets().get(0)
                                .getAvailability().getTourSchedule())
                )
                .bookedTicketResponses(
                        bookingTicket.getBookedTickets().stream()
                                .map(BookedTicketResponse::fromBookedTicket)
                                .collect(Collectors.toList())
                )
                .customerResponse(
                        CustomerResponse.fromCustomer(bookingTicket)
                )
                .build();

    }
}
