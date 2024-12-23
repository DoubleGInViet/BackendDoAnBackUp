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
    private Long id;

    @JsonProperty("booking_date")
    private String bookingDate;

    private String status;

    @JsonProperty("total_price")
    private Long totalPrice;

    @JsonProperty("tour")
    private TourResponse tourResponse;

    @JsonProperty("customer")
    private CustomerResponse customerResponse;

    @JsonProperty("buyed_ticket")
    private List<BookedTicketResponse> bookedTicketResponses;

    public static BookingTicketResponse fromBooking(BookingTicket bookingTicket){
        return BookingTicketResponse.builder()
                .id(bookingTicket.getId())
                .bookingDate(bookingTicket.getBooking_date().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .status(bookingTicket.getStatus())
                .totalPrice(bookingTicket.getTotal_price())
                .tourResponse(
                        TourResponse.fromTour(bookingTicket.getTourSchedule().getTour())
                )
                .bookedTicketResponses(
                        bookingTicket.getBookedTickets().stream()
                                .map(BookedTicketResponse::fromBookedTicket)
                                .collect(Collectors.toList())
                )
                .build();

    }
}
