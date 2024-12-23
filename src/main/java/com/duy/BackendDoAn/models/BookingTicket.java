package com.duy.BackendDoAn.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "booking_ticket")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "booking_date")
    private LocalDate booking_date;

    @Column(name = "tour_date")
    private LocalDate tour_date;

    @Column(name = "number_adult_ticket")
    private Long number_adult_ticket;

    @Column(name = "number_children_ticket")
    private Long number_children_ticket;

    @Column(name = "total_price")
    private Long total_price;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "tour_schedule_id")
    private TourSchedule tourSchedule;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "bookingTicket", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<BookedTicket> bookedTickets;
}
