package com.duy.BackendDoAn.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.checkerframework.common.aliasing.qual.MaybeAliased;

@Entity
@Table(name = "booked_ticket")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookedTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "price_with_quantity")
    private Long priceWithQuantity;

    @ManyToOne
    @JoinColumn(name = "daily_ticket_availability_id")
    @JsonBackReference
    private DailyTicketAvailability availability;

    @ManyToOne
    @JoinColumn(name = "booking_ticket_id")
    @JsonBackReference
    private BookingTicket bookingTicket;
}
