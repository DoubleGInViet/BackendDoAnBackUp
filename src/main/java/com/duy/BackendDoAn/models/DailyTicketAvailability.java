package com.duy.BackendDoAn.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "daily_ticket_availability")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DailyTicketAvailability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "happen_date")
    private LocalDate happenDate;

    @ManyToOne
    @JoinColumn(name = "tour_schedule_id")
    @JsonBackReference
    private TourSchedule tourSchedule;

    @ManyToOne
    @JoinColumn(name = "ticket_class_id")
    @JsonBackReference
    private TicketClass ticketClass;

    @Column(name = "available_ticket")
    private Long availableTicket;

    @OneToMany(mappedBy = "availability", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<BookedTicket> bookedTickets;
}
