package com.duy.BackendDoAn.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "ticket_class")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Long price;

    @Column(name = "max_amount")
    private Long maxAmount;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @OneToMany(mappedBy = "ticketClass", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<DailyTicketAvailability> dailyTicketAvailabilities;

    @OneToMany(mappedBy = "ticketClass", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<BookedTicket> bookedTickets;
}
